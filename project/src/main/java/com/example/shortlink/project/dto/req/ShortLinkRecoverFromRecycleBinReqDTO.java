package com.example.shortlink.project.dto.req;

import lombok.Data;

@Data
public class ShortLinkRecoverFromRecycleBinReqDTO {
    private String gid;
    private String fullShortUrl;
}
