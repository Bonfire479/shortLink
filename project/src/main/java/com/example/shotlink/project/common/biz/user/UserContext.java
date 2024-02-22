package com.example.shotlink.project.common.biz.user;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.example.shotlink.project.common.convention.exception.ClientException;

public class UserContext {
    private static final ThreadLocal<UserInfoDTO> context = new TransmittableThreadLocal<>();

    public static void setUser(UserInfoDTO userInfoDTO){
        context.set(userInfoDTO);
    }

    public static UserInfoDTO getUser(){
        return context.get();
    }

    public static long getUserID(){
        UserInfoDTO user = getUser();
        if (user == null)
            throw new ClientException("用户未登录");
        return user.getId();
    }

    public static String getUsername(){
        UserInfoDTO user = getUser();
        if (user == null)
            throw new ClientException("用户未登录");
        return user.getUsername();
    }

    public static String getRealName(){
        UserInfoDTO user = getUser();
        if (user == null)
            throw new ClientException("用户未登录");
        return user.getRealName();
    }

    public static void clean(){
        context.remove();
    }


}
