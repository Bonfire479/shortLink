package com.example.shortlink.admin.remote.dto.req;

import lombok.Data;

@Data
public class LinkAllStatsByDateReqDTO {
    private String gid;
    private String fullShortUrl;
    private String startDate;
    private String endDate;
}
