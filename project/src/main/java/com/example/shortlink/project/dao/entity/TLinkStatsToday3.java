package com.example.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import groovy.transform.NamedParam;
import lombok.*;

import java.io.Serializable;

/**
 * @description t_link_stats_today_3
 * @author zhengkai.blog.csdn.net
 * @date 2024-02-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_link_stats_today")
public class LinkStatsTodayDO extends BaseDO implements Serializable {

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
    * 短链接
    */
    private String fullShortUrl;

    /**
    * 日期
    */
    private Date date;

    /**
    * 今日pv
    */
    private Integer todayPv;

    /**
    * 今日uv
    */
    private Integer todayUv;

    /**
    * 今日ip数
    */
    private Integer todayUip;

    /**
    * 删除标识 0：未删除 1：已删除
    */
    private int delFlag;
}