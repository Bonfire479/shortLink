package com.example.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shortlink.project.dao.entity.LinkAccessStatsDO;
import com.example.shortlink.project.dto.req.LinkAccessStatsByDayOfWeekRespDTO;
import com.example.shortlink.project.dto.resp.LinkAccessStatsByDateRespDTO;
import com.example.shortlink.project.dto.req.LinkAllStatsByDateReqDTO;
import com.example.shortlink.project.dto.resp.LinkAccessStatsByHourRespDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface LinkAccessStatsMapper extends BaseMapper<LinkAccessStatsDO> {

    @Insert("INSERT INTO t_link_access_stats (full_short_url, gid, date, pv, uv, uip, hour, weekday, create_time, update_time, del_flag)" +
            "VALUES(#{linkAccessStatsDO.fullShortUrl}, #{linkAccessStatsDO.gid}, #{linkAccessStatsDO.date}, #{linkAccessStatsDO.pv}, #{linkAccessStatsDO.uv}, #{linkAccessStatsDO.uip}, #{linkAccessStatsDO.hour}, #{linkAccessStatsDO.weekday}, NOW(), NOW(), 0) ON DUPLICATE KEY " +
            "UPDATE pv = pv + #{linkAccessStatsDO.pv}, uv = uv + #{linkAccessStatsDO.uv}, uip = uip + #{linkAccessStatsDO.uip};")
    public void shortLinkStats(@Param("linkAccessStatsDO")LinkAccessStatsDO linkAccessStatsDO);

    @Select("SELECT date,sum(pv) as pv, sum(uv) as uv, sum(uip) as uip FROM `t_link_access_stats` " +
            "where date BETWEEN #{linkAllStatsByDateReqDTO.startDate} and #{linkAllStatsByDateReqDTO.endDate} " +
            "and gid = #{linkAllStatsByDateReqDTO.gid} and full_short_url = #{linkAllStatsByDateReqDTO.fullShortUrl} " +
            "group by date")
    public List<LinkAccessStatsByDateRespDTO> selectBaseAccessStatsByDate(@Param("linkAllStatsByDateReqDTO") LinkAllStatsByDateReqDTO linkAllStatsByDateReqDTO);


    @Select("select hour, sum(pv) as cnt from t_link_access_stats " +
            "where date BETWEEN #{linkAllStatsByDateReqDTO.startDate} and #{linkAllStatsByDateReqDTO.endDate} " +
            "and gid = #{linkAllStatsByDateReqDTO.gid} and full_short_url = #{linkAllStatsByDateReqDTO.fullShortUrl} " +
            "group by hour")
    public List<LinkAccessStatsByHourRespDTO> selectAccessStatsByHour(@Param("linkAllStatsByDateReqDTO") LinkAllStatsByDateReqDTO linkAllStatsByDateReqDTO);


    @Select("select weekday, sum(pv) as cnt from t_link_access_stats " +
            "where date BETWEEN #{linkAllStatsByDateReqDTO.startDate} and #{linkAllStatsByDateReqDTO.endDate} " +
            "and gid = #{linkAllStatsByDateReqDTO.gid} and full_short_url = #{linkAllStatsByDateReqDTO.fullShortUrl} " +
            "group by weekday")
    public List<LinkAccessStatsByDayOfWeekRespDTO> selectAccessStatsByDayOfWeek(@Param("linkAllStatsByDateReqDTO") LinkAllStatsByDateReqDTO linkAllStatsByDateReqDTO);
}
