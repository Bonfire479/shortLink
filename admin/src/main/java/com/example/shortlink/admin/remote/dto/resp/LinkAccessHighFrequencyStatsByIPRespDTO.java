package com.example.shortlink.admin.remote.dto.resp;

import lombok.Data;

@Data
public class LinkAccessHighFrequencyStatsByIPRespDTO {
    private String ip;
    private long cnt;
}
