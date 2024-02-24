package com.example.shortlink.project.dto.resp;

import lombok.Data;

@Data
public class LinkAccessStatsByHourRespDTO {
    private int hour;
    private long cnt;
}
