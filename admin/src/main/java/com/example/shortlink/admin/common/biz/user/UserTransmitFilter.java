package com.example.shortlink.admin.common.biz.user;


import cn.hutool.core.util.StrUtil;
import com.example.shortlink.admin.util.JWTUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

public class UserTransmitFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String token = request.getHeader("token");
        if (!StrUtil.isEmpty(token)){
            Map<String, Object> map = JWTUtil.parseToken(token);
            if (!map.isEmpty()){
                String userIDStr = map.get("id").toString();
                String username = map.get("username").toString();
                String realName = map.get("realName").toString();

                UserInfoDTO userInfoDTO = new UserInfoDTO(Long.parseLong(userIDStr), username, realName);
                UserContext.setUser(userInfoDTO);
            }
        }


        try{
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            UserContext.clean();
        }
    }


}
