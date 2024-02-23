package com.example.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @description t_link_access_logs
 * @author zhengkai.blog.csdn.net
 * @date 2024-02-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_link_access_stats")
public class LinkAccessLogsDO extends BaseDO implements Serializable {

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
    * 用户信息
    */
    private String user;

    /**
    * 浏览器
    */
    private String browser;

    /**
    * 操作系统
    */
    private String os;

    /**
    * ip
    */
    private String ip;

    /**
    * 删除标识 0：未删除 1：已删除
    */
    @TableLogic
    private int delFlag;

}