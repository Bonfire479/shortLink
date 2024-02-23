package com.example.shortlink.admin.remote.dto.req;

import lombok.Data;

@Data
public class ShortLinkRecycleBinRemoveReqDTO {
    private String gid;
    private String fullShortUrl;
}
