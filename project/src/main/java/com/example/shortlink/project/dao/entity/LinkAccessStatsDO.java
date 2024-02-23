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
 * @description t_link_access_stats
 * @author zhengkai.blog.csdn.net
 * @date 2024-02-23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_link_access_stats")
public class LinkAccessStatsDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * id
    */
    private Long id;

    /**
    * 分组标识
    */
    private String gid;

    /**
    * 完整短链接
    */
    private String fullShortUrl;

    /**
    * 日期
    */
    private Date date;

    /**
    * 访问量
    */
    private Integer pv;

    /**
    * 独立访问数
    */
    private Integer uv;

    /**
    * 独立ip数
    */
    private Integer uip;

    /**
    * 小时
    */
    private Integer hour;

    /**
    * 星期
    */
    private Integer weekday;


    /**
    * 删除标识：0 未删除 1 已删除
    */
    @TableLogic
    private int delFlag;

}