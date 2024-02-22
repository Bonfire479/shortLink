package com.example.shotlink.project.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shotlink.project.dao.entity.ShortLinkDO;
import com.example.shotlink.project.dao.mapper.RecycleBinMapper;
import com.example.shotlink.project.dto.req.ShortLinkPageRecycleBinReqDTO;
import com.example.shotlink.project.dto.req.ShortLinkSaveToRecycleBinReqDTO;
import com.example.shotlink.project.dto.resp.ShortLinkPageRecycleBinRespDTO;
import com.example.shotlink.project.service.RecycleBinService;
import com.example.shotlink.project.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.example.shotlink.project.common.constants.RedisKeyConstant.SHORT_LINK_KEY;


@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl extends ServiceImpl<RecycleBinMapper, ShortLinkDO> implements RecycleBinService {
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void saveShortLinkToRecycleBin(ShortLinkSaveToRecycleBinReqDTO param) {
        LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, param.getGid())
                .eq(ShortLinkDO::getFullShortUrl, param.getFullShortUrl())
                .eq(ShortLinkDO::getEnableStatus, 0);

        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .enableStatus(1)
                .build();

        int update = baseMapper.update(shortLinkDO, updateWrapper);

        stringRedisTemplate.delete(SHORT_LINK_KEY + param.getFullShortUrl());
    }

    @Override
    public IPage<ShortLinkPageRecycleBinRespDTO> pageShortLinkInRecycleBin(ShortLinkPageRecycleBinReqDTO param) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .in(ShortLinkDO::getGid, param.getGidList())
                .eq(ShortLinkDO::getEnableStatus, 1);
        IPage<ShortLinkDO> page = baseMapper.selectPage(param, queryWrapper);
        return page.convert(each -> {
            ShortLinkPageRecycleBinRespDTO converted = BeanUtil.convert(each, ShortLinkPageRecycleBinRespDTO.class);
            converted.setFullShortUrl("http://" + converted.getFullShortUrl());
            return converted;
        });
    }

    @Override
    public void recoverShortLinkFromRecycleBin(ShortLinkSaveToRecycleBinReqDTO param) {
        return;
    }

    @Override
    public void removeShortLinkFromRecycleBin(ShortLinkSaveToRecycleBinReqDTO param) {
        return;
    }
}
