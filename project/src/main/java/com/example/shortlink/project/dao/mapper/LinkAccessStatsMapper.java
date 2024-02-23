package com.example.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shortlink.project.dao.entity.LinkAccessStatsDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LinkAccessStatsMapper extends BaseMapper<LinkAccessStatsDO> {

    @Insert("INSERT INTO t_link_access_stats (full_short_url, gid, date, pv, uv, uip, hour, weekday, create_time, update_time, del_flag)" +
            "VALUES(#{linkAccessStatsDO.fullShortUrl}, #{linkAccessStatsDO.gid}, #{linkAccessStatsDO.date}, #{linkAccessStatsDO.pv}, #{linkAccessStatsDO.uv}, #{linkAccessStatsDO.uip}, #{linkAccessStatsDO.hour}, #{linkAccessStatsDO.weekday}, NOW(), NOW(), 0) ON DUPLICATE KEY " +
            "UPDATE pv = pv + #{linkAccessStatsDO.pv}, uv = uv + #{linkAccessStatsDO.uv}, uip = uip + #{linkAccessStatsDO.uip};")
    public void shortLinkStats(@Param("linkAccessStatsDO")LinkAccessStatsDO linkAccessStatsDO);

}
