package com.example.shortlink.project.dto.resp;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class LinkAccessStatsByDeviceRespDTO {
    private String device;
    private long cnt;
    private double ratio;
}
