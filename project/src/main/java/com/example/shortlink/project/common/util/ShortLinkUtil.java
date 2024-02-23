package com.example.shortlink.project.common.util;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.Optional;

import static com.example.shortlink.project.common.constants.ShortLinkConstant.SHORT_LINK_PERMANENT_CACHE_TIMEOUT;

public class ShortLinkUtil {
    /**
     * 获取短链接有效期在缓存redis的过期时间
     * 如果是永久有效则缓存一个月
     * 如果是已经有有效期则拿到当前时间date和传入的date的差值以毫秒为单位插入redis
     *
     * @param valid
     * @return
     */
    public static long getCacheValidDate(Date valid){
        return Optional
                .ofNullable(valid)
                .map(each -> DateUtil.between(new Date(), valid, DateUnit.MS))
                .orElse(SHORT_LINK_PERMANENT_CACHE_TIMEOUT);
    }
}
