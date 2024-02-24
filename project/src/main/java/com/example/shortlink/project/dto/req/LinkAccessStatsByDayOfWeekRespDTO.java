package com.example.shortlink.project.dto.req;

import cn.hutool.core.date.Week;
import lombok.Data;

import java.util.Calendar;

@Data
public class LinkAccessStatsByDayOfWeekRespDTO {
    private int weekday;
    private long cnt;
}
