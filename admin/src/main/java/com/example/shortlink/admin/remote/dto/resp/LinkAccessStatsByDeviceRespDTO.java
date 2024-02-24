package com.example.shortlink.admin.remote.dto.resp;

import lombok.Data;

@Data
public class LinkAccessStatsByDeviceRespDTO {
    private String device;
    private long cnt;
    private double ratio;
}
