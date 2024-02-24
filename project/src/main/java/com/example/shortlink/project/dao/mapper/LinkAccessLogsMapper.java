package com.example.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shortlink.project.dao.entity.LinkAccessLogsDO;
import com.example.shortlink.project.dto.req.LinkAllStatsByDateReqDTO;
import com.example.shortlink.project.dto.resp.LinkAccessHighFrequencyStatsByIPRespDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface LinkAccessLogsMapper extends BaseMapper<LinkAccessLogsDO> {

    @Insert("INSERT INTO t_link_access_logs (full_short_url, gid, user, os, browser, ip, device, network, locale, create_time, update_time, del_flag)" +
            "VALUES(#{linkAccessLogsDO.fullShortUrl}, #{linkAccessLogsDO.gid}, #{linkAccessLogsDO.user}, #{linkAccessLogsDO.os}, #{linkAccessLogsDO.browser}, #{linkAccessLogsDO.deivce}, #{linkAccessLogsDO.network}, #{linkAccessLogsDO.locale},  #{linkAccessLogsDO.ip}, NOW(), NOW(), 0)")
    public void shortLinkStats(@Param("linkAccessLogsDO")LinkAccessLogsDO linkAccessLogsDO);


    @Select("select ip, count(1) as cnt from t_link_access_logs " +
            "where create_time BETWEEN #{linkAllStatsByDateReqDTO.startDate} and #{linkAllStatsByDateReqDTO.endDate} " +
            "and gid = #{linkAllStatsByDateReqDTO.gid} and full_short_url = #{linkAllStatsByDateReqDTO.fullShortUrl} " +
            "group by ip " +
            "order by cnt desc")
    public List<LinkAccessHighFrequencyStatsByIPRespDTO> selectHighFrequencyStatsByIP(@Param("linkAllStatsByDateReqDTO") LinkAllStatsByDateReqDTO linkAllStatsByDateReqDTO);

    @Select("select sum(oldUser) as oldUserCount, sum(newUser) as newUserCount " +
            "from(" +
            "SELECT case when count(1) > 1 then 1 else 0 end as oldUser, " +
            "case when count(1) = 1 and max(create_time) between #{linkAllStatsByDateReqDTO.startDate} and #{linkAllStatsByDateReqDTO.endDate} then 1 else 0 end as newUser " +
            "from t_link_access_logs " +
            "where create_time BETWEEN #{linkAllStatsByDateReqDTO.startDate} and #{linkAllStatsByDateReqDTO.endDate} " +
            "and gid = #{linkAllStatsByDateReqDTO.gid} and full_short_url = #{linkAllStatsByDateReqDTO.fullShortUrl} " +
            "group by `user`" +
            ") as tOldNew"
    )
    public HashMap<String, Object> selectAccessStatsByUserType(@Param("linkAllStatsByDateReqDTO") LinkAllStatsByDateReqDTO linkAllStatsByDateReqDTO);


    /*@Select("<script> " +
            "select user, case when min(create_time) between #{startDate} and #{endDate} then 'newUser' else 'oldUser' end as userType " +
            "from t_link_access_logs " +
            "where gid = #{gid} " +
            "and full_short_url = #{fullShortUrl} " +
            "and user in " +
            "<foreach item = 'item' index = 'index' collection = 'userList' open = '(' seperator = ',' close = ')'> " +
            "#{item} " +
            "</foreach>" +
            "group by user;" +
            "</script>"
    )
    Map<String, Object> getUserTypeByUsers(@Param("userList") List<String> userList,
                                           @Param("gid") String gid,
                                           @Param("fullShortUrl") String fullShortUrl,
                                           @Param("startDate") String startDate,
                                           @Param("endDate") String endDate);*/


    @Select("<script> " +
            "SELECT " +
            "    user, " +
            "    CASE " +
            "        WHEN MIN(create_time) BETWEEN #{startDate} AND #{endDate} THEN '新访客' " +
            "        ELSE '老访客' " +
            "    END AS userType " +
            "FROM " +
            "    t_link_access_logs " +
            "WHERE " +
            "    full_short_url = #{fullShortUrl} " +
            "    AND gid = #{gid} " +
            "    AND user IN " +
            "    <foreach item='item' index='index' collection='userAccessLogsList' open='(' separator=',' close=')'> " +
            "        #{item} " +
            "    </foreach> " +
            "GROUP BY " +
            "    user;" +
            "    </script>"
    )
    List<Map<String, Object>> getUserTypeByUsers(
            @Param("userAccessLogsList") List<String> userAccessLogsList,
            @Param("gid") String gid,
            @Param("fullShortUrl") String fullShortUrl,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

}