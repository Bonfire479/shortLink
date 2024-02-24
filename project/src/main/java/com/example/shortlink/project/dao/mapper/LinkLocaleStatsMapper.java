package com.example.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shortlink.project.dao.entity.LinkAccessStatsDO;
import com.example.shortlink.project.dao.entity.LinkLocaleStatsDO;
import com.example.shortlink.project.dto.req.LinkAllStatsByDateReqDTO;
import com.example.shortlink.project.dto.resp.LinkLocaleStatsByDateRespDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LinkLocaleStatsMapper extends BaseMapper<LinkLocaleStatsDO> {
    @Insert("INSERT INTO t_link_locale_stats (full_short_url, gid, date, cnt, province, city, adcode, country, create_time, update_time, del_flag)" +
            "VALUES(#{ linkLocaleStatsDO.fullShortUrl}, #{ linkLocaleStatsDO.gid}, #{ linkLocaleStatsDO.date}, #{ linkLocaleStatsDO.cnt}, #{ linkLocaleStatsDO.province}, #{ linkLocaleStatsDO.city}, #{ linkLocaleStatsDO.adcode}, #{ linkLocaleStatsDO.country}, NOW(), NOW(), 0) ON DUPLICATE KEY " +
            "UPDATE cnt = cnt + #{linkLocaleStatsDO.cnt};")
    public void shortLinkStats(@Param("linkLocaleStatsDO") LinkLocaleStatsDO linkLocaleStatsDO);

    @Select("SELECT province, sum(cnt) as cnt FROM t_link_locale_stats " +
            "WHERE gid = #{linkAllStatsByDateReqDTO.gid} and full_short_url = #{linkAllStatsByDateReqDTO.fullShortUrl} " +
            "AND date between #{linkAllStatsByDateReqDTO.startDate} and #{linkAllStatsByDateReqDTO.endDate} " +
            "group by province")
    public List<LinkLocaleStatsByDateRespDTO> selectLocalStatsByDate(@Param("linkAllStatsByDateReqDTO") LinkAllStatsByDateReqDTO linkAllStatsByDateReqDTO);
}
