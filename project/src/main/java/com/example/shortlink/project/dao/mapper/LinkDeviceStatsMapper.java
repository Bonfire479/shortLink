package com.example.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shortlink.project.dao.entity.LinkDeviceStatsDO;
import com.example.shortlink.project.dto.req.LinkAccessStatsByDayOfWeekRespDTO;
import com.example.shortlink.project.dto.req.LinkAllStatsByDateReqDTO;
import com.example.shortlink.project.dto.resp.LinkAccessStatsByDeviceRespDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LinkDeviceStatsMapper extends BaseMapper<LinkDeviceStatsDO> {

    @Insert("INSERT INTO t_link_device_stats (full_short_url, gid, date, cnt, device, create_time, update_time, del_flag)" +
            "VALUES(#{linkDeviceStatsDO.fullShortUrl}, #{linkDeviceStatsDO.gid}, #{linkDeviceStatsDO.date}, #{linkDeviceStatsDO.cnt}, #{linkDeviceStatsDO.device},  NOW(), NOW(), 0) ON DUPLICATE KEY " +
            "UPDATE cnt = cnt + #{linkDeviceStatsDO.cnt};")
    public void shortLinkStats(@Param("linkDeviceStatsDO") LinkDeviceStatsDO linkDeviceStatsDO);

    @Select("select device, sum(cnt) as cnt from t_link_device_stats " +
            "where date BETWEEN #{linkAllStatsByDateReqDTO.startDate} and #{linkAllStatsByDateReqDTO.endDate} " +
            "and gid = #{linkAllStatsByDateReqDTO.gid} and full_short_url = #{linkAllStatsByDateReqDTO.fullShortUrl} " +
            "group by device")
    public List<LinkAccessStatsByDeviceRespDTO> selectAccessStatsByDevice(@Param("linkAllStatsByDateReqDTO") LinkAllStatsByDateReqDTO linkAllStatsByDateReqDTO);
}
