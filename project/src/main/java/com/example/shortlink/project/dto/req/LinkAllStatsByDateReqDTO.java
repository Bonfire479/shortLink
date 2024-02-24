package com.example.shortlink.project.dto.req;

import lombok.Data;

import java.util.Date;

@Data
public class LinkAllStatsByDateReqDTO {
    private String gid;
    private String fullShortUrl;
    private String startDate;
    private String endDate;
}
