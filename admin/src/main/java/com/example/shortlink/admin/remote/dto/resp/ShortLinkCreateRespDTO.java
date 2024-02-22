package com.example.shortlink.admin.remote.dto.resp;


import lombok.Data;

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
