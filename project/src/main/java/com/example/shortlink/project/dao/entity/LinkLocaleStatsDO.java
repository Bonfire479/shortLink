package com.example.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description t_link_locale_stats
 * @author zhengkai.blog.csdn.net
 * @date 2024-02-23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_link_locale_stats")
public class LinkLocaleStatsDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * id
    */
    private Long id;

    /**
    * 完整短链接
    */
    private String fullShortUrl;

    /**
    * 分组标识
    */
    private String gid;

    /**
    * 日期
    */
    private Date date;

    /**
    * 访问量
    */
    private Integer cnt;

    /**
    * 省份名称
    */
    private String province;

    /**
    * 市名称
    */
    private String city;

    /**
    * 城市编码
    */
    private String adcode;

    /**
    * 国家标识
    */
    private String country;

    /**
     * 删除标识 0表示删除 1表示未删除
     */
    @TableLogic
    private int delFlag;

}