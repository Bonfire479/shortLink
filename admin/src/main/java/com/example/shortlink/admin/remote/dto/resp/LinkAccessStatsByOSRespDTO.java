package com.example.shortlink.admin.remote.dto.resp;


import lombok.Data;

@Data
public class LinkAccessStatsByOSRespDTO {
    private String os;
    private long cnt;
    private double ratio;
}
