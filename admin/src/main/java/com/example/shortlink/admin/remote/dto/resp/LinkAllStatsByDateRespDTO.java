package com.example.shortlink.admin.remote.dto.resp;

import com.example.shortlink.admin.remote.dto.req.LinkAccessStatsByDayOfWeekRespDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LinkAllStatsByDateRespDTO {
    private long pvSum;
    private long uvSum;
    private long uipSum;
    //基础访问数据pv,uv,uip
    private List<LinkAccessStatsByDateRespDTO> accessStatsByDateList;
    //地区访问数据
    private List<LinkLocaleStatsByDateRespDTO> localeStatsByDateList;
    //24小时访问数据
    private List<LinkAccessStatsByHourRespDTO> accessStatsByHourList;
    //高频访问ip
    private List<LinkAccessHighFrequencyStatsByIPRespDTO> highFrequencyStatsByIPList;
    //周分布访问数据
    private List<LinkAccessStatsByDayOfWeekRespDTO> accessStatsByDayOfWeekList;
    //操作系统访问数据
    private List<LinkAccessStatsByOSRespDTO> accessStatsByOSList;
    //浏览器访问数据
    private List<LinkAccessStatsByBrowserRespDTO> accessStatsByBrowserList;
    //设备访问数据
    private List<LinkAccessStatsByDeviceRespDTO> accessStatsByDeviceList;
    //网络环境访问数据
    private List<LinkAccessStatsByNetworkRespDTO> accessStatsByNetworkList;
    //访客类型访问数据
    private List<LinkAccessStatsUserTypeRespDTO> userTypeList;

}
