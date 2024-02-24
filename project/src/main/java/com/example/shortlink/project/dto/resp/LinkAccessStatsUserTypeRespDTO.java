package com.example.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkAccessStatsUserTypeRespDTO {
    private String userType;
    private long cnt;
    private double ratio;
}
