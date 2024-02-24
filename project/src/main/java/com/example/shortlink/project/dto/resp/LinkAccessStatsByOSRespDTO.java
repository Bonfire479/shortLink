package com.example.shortlink.project.dto.resp;


import lombok.Data;

@Data
public class LinkAccessStatsByOSRespDTO {
    private String os;
    private long cnt;
    private double ratio;
}
