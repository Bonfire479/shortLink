package com.example.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shortlink.project.dao.entity.LinkAccessLogsDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LinkAccessLogsMapper extends BaseMapper<LinkAccessLogsDO> {

    @Insert("INSERT INTO t_link_access_logs (full_short_url, gid, user, os, browser, ip, create_time, update_time, del_flag)" +
            "VALUES(#{linkAccessLogsDO.fullShortUrl}, #{linkAccessLogsDO.gid}, #{linkAccessLogsDO.user}, #{linkAccessLogsDO.os}, #{linkAccessLogsDO.browser}, #{linkAccessLogsDO.ip}, NOW(), NOW(), 0)")
    public void shortLinkStats(@Param("linkAccessLogsDO")LinkAccessLogsDO linkAccessLogsDO);

}