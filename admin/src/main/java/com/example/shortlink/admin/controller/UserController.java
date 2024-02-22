package com.example.shortlink.admin.controller;

import cn.hutool.core.lang.hash.Hash;
import com.example.shortlink.admin.common.enums.BaseErrorCode;
import com.example.shortlink.admin.common.convention.exception.ClientException;
import com.example.shortlink.admin.common.convention.result.Result;
import com.example.shortlink.admin.common.convention.result.Results;
import com.example.shortlink.admin.dto.req.UserLoginReqDTO;
import com.example.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.example.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.example.shortlink.admin.dto.resp.UnDesensitizedUserRespDTO;
import com.example.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.example.shortlink.admin.dto.resp.UserRespDTO;
import com.example.shortlink.admin.service.UserService;
import com.example.shortlink.admin.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名获取用户
     */
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable String username){
        UserRespDTO user = userService.getUserByUsername(username);
        return Results.success(user);
    }

    /**
     * 根据用户名获取用户的真实信息(未脱敏)
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UnDesensitizedUserRespDTO> getUnDesensitizedUserByUsername(@PathVariable String username){
        UserRespDTO user = userService.getUserByUsername(username);
        return Results.success(BeanUtil.convert(user, UnDesensitizedUserRespDTO.class));
    }

    /**
     * 检查用户名是否已存在
     *
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(String username){
        return Results.success(userService.hasUsername(username));
    }


    /**
     * 新增用户
     * @param param 用户注册信息
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/user")
    public Result<Void> register(UserRegisterReqDTO param){
        userService.register(param);
        return Results.success();
    }


    /**
     * 修改用户信息
     * @param param
     * @return
     */
    @PutMapping("/api/short-link/admin/v1/user")
    public Result<Void> updateUser(UserUpdateReqDTO param){
        userService.updateUser(param);
        return Results.success();
    }

    /**
     * 用户登录
     * @param param 包含用户名和密码
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO param){
        UserLoginRespDTO resp = userService.login(param);
        return Results.success(resp);
    }

    /**
     * 检查用户是否登录
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(String username){
        return Results.success(userService.checkLogin(username));
    }

    /**
     * 用户退出登录
     * @param username
     * @return
     */
    @DeleteMapping("/api/short-link/admin/v1/user/logout")
    public Result<Boolean> logout(String username){
        return Results.success(userService.logout(username));
    }
}
