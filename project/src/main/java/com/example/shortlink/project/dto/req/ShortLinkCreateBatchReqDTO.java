package com.example.shortlink.project.dto.req;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShortLinkCreateBatchReqDTO {
    /**
     * 域名
     */
    private String domain;

    /**
     * 原始链接
     */
    private List<String> originUrl;

    /**
     * gid
     */
    private String gid;

    /**
     * 创建类型 0:接口 1:控制台
     */
    private int createdType;

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
    private List<String> description;
}
