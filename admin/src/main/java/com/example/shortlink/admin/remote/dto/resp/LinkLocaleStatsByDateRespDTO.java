package com.example.shortlink.admin.remote.dto.resp;

import lombok.Data;

@Data
public class LinkLocaleStatsByDateRespDTO {
    private String province;
    private double ratio;
    private long cnt;
}
