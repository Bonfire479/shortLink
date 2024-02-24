package com.example.shortlink.admin.remote.dto.resp;

import lombok.Data;

@Data
public class LinkAccessStatsByHourRespDTO {
    private int hour;
    private long cnt;
}
