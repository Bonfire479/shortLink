package com.example.shortlink.admin.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shortlink.admin.common.biz.user.UserContext;
import com.example.shortlink.admin.common.convention.exception.ClientException;
import com.example.shortlink.admin.common.convention.exception.ServiceException;
import com.example.shortlink.admin.common.convention.result.Result;
import com.example.shortlink.admin.dao.entity.BaseDO;
import com.example.shortlink.admin.dao.entity.GroupDO;
import com.example.shortlink.admin.dao.mapper.GroupMapper;
import com.example.shortlink.admin.dto.req.GroupDeleteReqDTO;
import com.example.shortlink.admin.dto.req.GroupSaveReqDTO;
import com.example.shortlink.admin.dto.req.GroupSortReqDTO;
import com.example.shortlink.admin.dto.req.GroupUpdateReqDTO;
import com.example.shortlink.admin.dto.resp.ListGroupRespDTO;
import com.example.shortlink.admin.remote.ShortLinkRemoteService;
import com.example.shortlink.admin.service.GroupService;
import com.example.shortlink.admin.util.BeanUtil;
import com.example.shortlink.admin.util.GroupIDGenerator;
import com.example.shotlink.project.dto.resp.ShortLinkGroupCountRespDTO;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    private static final ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @Override
    public Boolean addGroup(String username, GroupSaveReqDTO param) {
        String gid;
        while (true) {
            gid = GroupIDGenerator.generate();
            LambdaQueryWrapper<GroupDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(GroupDO::getId, gid);
            if (baseMapper.selectOne(queryWrapper) == null)
                break;
        }

        GroupDO groupDO = GroupDO
                .builder()
                .gid(gid)
                .name(param.getName())
                .username(username)
                .build();
        int insert = baseMapper.insert(groupDO);
        if (insert < 1)
            throw new ServiceException("短链接分组创建失败");
        return true;
    }

    @Override
    public Boolean addGroup(GroupSaveReqDTO param) {
        return addGroup(UserContext.getUsername(), param);
    }

    @Override
    public List<ListGroupRespDTO> listGroupByUsername() {
        String username = UserContext.getUsername();
        LambdaQueryWrapper<GroupDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(GroupDO::getUsername, username)
                .orderByDesc(GroupDO::getSortOrder)
                .orderByDesc(GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);

        List<String> gidList = groupDOList.stream().map(GroupDO::getGid).toList();
        List<ShortLinkGroupCountRespDTO> groupCountMaps = shortLinkRemoteService.groupCountShortLink(gidList).getData();

        List<ListGroupRespDTO> listGroupRespDTOS = new ArrayList<>();
//        BeanUtil.convert(groupDOList, listGroupRespDTOS);
        groupDOList.forEach(groupDO -> {
            listGroupRespDTOS.add(
                    ListGroupRespDTO.builder()
                            .gid(groupDO.getGid())
                            .name(groupDO.getName())
                            .sortOrder(groupDO.getSortOrder())
                            .build());
        });

        listGroupRespDTOS.forEach(each -> {
            Optional<ShortLinkGroupCountRespDTO> first = groupCountMaps.stream().filter(groupCountRespDTO ->
                    Objects.equals(groupCountRespDTO.getGid(), each.getGid())
            ).findFirst();
            first.ifPresent(useless -> each.setShortLinkCount(first.get().getShortLinkCount()));

        });
        return listGroupRespDTOS;
    }

    @Override
    public Boolean updateGroup(GroupUpdateReqDTO param) {
        LambdaQueryWrapper<GroupDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GroupDO::getId, param.getGid());
        queryWrapper.eq(GroupDO::getUsername, UserContext.getUsername());

        GroupDO groupDO = new GroupDO();
        groupDO.setName(param.getName());

        int update = baseMapper.update(groupDO, queryWrapper);
        if (update < 1) {
            throw new ServiceException("更新分组失败");
        }
        return true;
    }

    @Override
    public Boolean deleteGroup(GroupDeleteReqDTO param) {
        LambdaQueryWrapper<GroupDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GroupDO::getId, param.getGid());
        queryWrapper.eq(GroupDO::getUsername, UserContext.getUsername());

        int delete = baseMapper.delete(queryWrapper);
        if (delete < 1) {
            throw new ClientException("删除分组失败");
        }
        return true;
    }

    @Override
    public void sortGroup(List<GroupSortReqDTO> param) {
        LambdaQueryWrapper<GroupDO> queryWrapper = new LambdaQueryWrapper<>();
        for (GroupSortReqDTO groupSortReqDTO : param) {
            queryWrapper.eq(GroupDO::getGid, groupSortReqDTO.getGid());
            queryWrapper.eq(GroupDO::getUsername, UserContext.getUsername());

            GroupDO groupDO = new GroupDO();
            groupDO.setSortOrder(groupSortReqDTO.getSortOrder());

            baseMapper.update(groupDO, queryWrapper);
        }
    }
}
