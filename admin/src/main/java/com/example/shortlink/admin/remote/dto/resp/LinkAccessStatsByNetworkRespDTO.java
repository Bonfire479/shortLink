package com.example.shortlink.admin.remote.dto.resp;

import lombok.Data;

@Data
public class LinkAccessStatsByNetworkRespDTO {
    private String network;
    private long cnt;
    private double ratio;
}
