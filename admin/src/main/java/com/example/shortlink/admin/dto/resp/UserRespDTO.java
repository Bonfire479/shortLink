package com.example.shortlink.admin.dto.resp;

import com.example.shortlink.admin.common.serialize.PhoneSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class UserRespDTO {
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
    @JsonSerialize(using = PhoneSerializer.class)
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}
