package com.example.shortlink.project.dto.resp;


import lombok.Data;

import java.util.Date;

@Data
public class ShortLinkCreateRespDTO {
    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * gid
     */
    private String gid;
}
