package com.example.shotlink.project.dto.req;


import com.baomidou.mybatisplus.annotation.TableField;
import com.example.shotlink.project.common.enums.ValidDateTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateShortLinkReqDTO {

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 网站图标
     */
    private String favicon;

    /**
     * 启用标识 0:启用 1:未启用
     */
    private int enableStatus;


    /**
     * 有效期类型
     */
    private ValidDateTypeEnum validDateType;

    /**
     * 有效期
     */
    private Date validDate;

    /**
     * 描述
     */
    private String description;
}
