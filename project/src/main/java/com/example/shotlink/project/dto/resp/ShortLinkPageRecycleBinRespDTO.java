package com.example.shotlink.project.dto.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ShortLinkPageRecycleBinRespDTO {
    /**
     * id
     */
    private Integer id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 短链接
     */
    private String shortUri;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * gid
     */
    private String gid;

    /**
     * 网站图标
     */
    private String favicon;

    /**
     * 有效期类型
     */
    private int validDateType;

    /**
     * 有效期
     */
    private Date validDate;

    /**
     * 描述
     */
    private String description;
}
