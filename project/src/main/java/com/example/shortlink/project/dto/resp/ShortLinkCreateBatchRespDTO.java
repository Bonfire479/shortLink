package com.example.shortlink.project.dto.resp;

import lombok.Data;

import java.util.List;

@Data
public class ShortLinkCreateBatchRespDTO {
    /**
     * 原始链接
     */
    private List<String> originUrl;

    /**
     * 完整短链接
     */
    private List<String> fullShortUrl;

    /**
     * gid
     */
    private String gid;
}
