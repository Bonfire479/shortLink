package com.example.shortlink.admin.remote.dto.req;

import lombok.Data;

@Data
public class LinkAccessStatsByDayOfWeekRespDTO {
    private int weekday;
    private long cnt;
}
