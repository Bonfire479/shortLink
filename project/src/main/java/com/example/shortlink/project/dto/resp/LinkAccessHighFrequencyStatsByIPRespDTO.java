package com.example.shortlink.project.dto.resp;

import lombok.Data;

@Data
public class LinkAccessHighFrequencyStatsByIPRespDTO {
    private String ip;
    private long cnt;
}
