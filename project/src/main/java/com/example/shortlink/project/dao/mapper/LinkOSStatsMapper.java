package com.example.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shortlink.project.dao.entity.LinkOSStatsDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LinkOSStatsMapper extends BaseMapper<LinkOSStatsDO> {
    @Insert("INSERT INTO t_link_os_stats (full_short_url, gid, date, os, cnt, create_time, update_time, del_flag)" +
            "VALUES(#{linkOSStatsDO.fullShortUrl}, #{linkOSStatsDO.gid}, #{linkOSStatsDO.date}, #{linkOSStatsDO.os}, #{linkOSStatsDO.cnt},  NOW(), NOW(), 0) ON DUPLICATE KEY " +
            "UPDATE cnt = cnt + #{linkOSStatsDO.cnt};")
    public void shortLinkStats(@Param("linkOSStatsDO") LinkOSStatsDO linkOSStatsDO);

}
