package com.example.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description t_user
 * @author bonfire479
 * @date 2024-02-19
 */
@TableName("t_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDO extends BaseDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 真实姓名
    */
    private String realName;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 邮箱
    */
    private String mail;

    /**
    * 注销时间戳
    */
    private Long deletionTime;

    /**
    * 删除标识 0：未删除 1：已删除
    */
    @TableLogic
    private int delFlag;

}