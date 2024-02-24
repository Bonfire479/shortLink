package com.example.shortlink.project.dto.resp;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class LinkAccessStatsByNetworkRespDTO {
    private String network;
    private long cnt;
    private double ratio;
}
