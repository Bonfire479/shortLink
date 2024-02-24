package com.example.shortlink.admin.remote.dto.resp;

import lombok.Data;

@Data
public class LinkAccessStatsByBrowserRespDTO {
    private String browser;
    private long cnt;
    private double ratio;
}
