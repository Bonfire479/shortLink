package com.example.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shortlink.project.dao.entity.LinkBrowserStatsDO;
import com.example.shortlink.project.dto.req.LinkAccessStatsByDayOfWeekRespDTO;
import com.example.shortlink.project.dto.req.LinkAllStatsByDateReqDTO;
import com.example.shortlink.project.dto.resp.LinkAccessStatsByBrowserRespDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LinkBrowserStatsMapper extends BaseMapper<LinkBrowserStatsDO> {

    @Insert("INSERT INTO t_link_browser_stats (full_short_url, gid, date, browser, cnt, create_time, update_time, del_flag)" +
            "VALUES(#{linkBrowserStatsDO.fullShortUrl}, #{linkBrowserStatsDO.gid}, #{linkBrowserStatsDO.date}, #{linkBrowserStatsDO.browser}, #{linkBrowserStatsDO.cnt}, NOW(), NOW(), 0) ON DUPLICATE KEY " +
            "UPDATE cnt = cnt + #{linkBrowserStatsDO.cnt};")
    public void shortLinkStats(@Param("linkBrowserStatsDO")LinkBrowserStatsDO linkBrowserStatsDO);

    @Select("select browser, sum(cnt) as cnt from t_link_browser_stats " +
            "where date BETWEEN #{linkAllStatsByDateReqDTO.startDate} and #{linkAllStatsByDateReqDTO.endDate} " +
            "and gid = #{linkAllStatsByDateReqDTO.gid} and full_short_url = #{linkAllStatsByDateReqDTO.fullShortUrl} " +
            "group by browser")
    public List<LinkAccessStatsByBrowserRespDTO> selectAccessStatsByBrowser(@Param("linkAllStatsByDateReqDTO") LinkAllStatsByDateReqDTO linkAllStatsByDateReqDTO);

}