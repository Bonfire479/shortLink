package com.example.shortlink.project.dto.req;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ShortLinkCreateReqDTO {
    /**
     * 域名
     */
    private String domain;

    /**
     * 原始链接
     */
    private String originUrl;

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
    @JsonFormat(pattern = "yyyy:MM::dd HH::mm::ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * 描述
     */
    private String description;
}
