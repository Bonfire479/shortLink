package com.example.shotlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import groovy.time.BaseDuration;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description t_link
 * @author bonfire479
 * @date 2024-02-20
 */
@Data
@TableName("t_link")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShortLinkDO extends BaseDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
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
    * 点击量
    */
    private Integer clickNum;

    /**
    * gid
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
    @TableField("description")
    private String description;

}