package com.example.shortlink.admin.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.shortlink.admin.common.biz.user.UserContext;
import com.example.shortlink.admin.dao.entity.GroupDO;
import com.example.shortlink.admin.dao.mapper.GroupMapper;
import com.example.shortlink.admin.remote.RecycleBinRemoteService;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkPageRecycleBinRespDTO;
import com.example.shortlink.admin.service.RecycleBinService;
import com.example.shortlink.admin.common.convention.result.Result;
import com.example.shortlink.admin.remote.dto.req.ShortLinkPageRecycleBinReqDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {
    private final GroupMapper groupMapper;
    private final RecycleBinRemoteService recycleBinRemoteService = new RecycleBinRemoteService() {
    };


    public Result<IPage<ShortLinkPageRecycleBinRespDTO>> pageShortLinkInRecycleBin(ShortLinkPageRecycleBinReqDTO param) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername());
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);

        ShortLinkPageRecycleBinReqDTO shortLinkPageRecycleBinReqDTO = new ShortLinkPageRecycleBinReqDTO(groupDOList.stream().map(GroupDO::getGid).toList());

        Result<IPage<ShortLinkPageRecycleBinRespDTO>> iPageResult = recycleBinRemoteService.pageShortLinkInRecycleBin(shortLinkPageRecycleBinReqDTO);
        return iPageResult;

    }

}