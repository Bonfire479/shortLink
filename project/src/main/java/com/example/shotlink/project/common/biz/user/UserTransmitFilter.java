package com.example.shotlink.project.common.biz.user;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class UserTransmitFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String userIDStr = request.getHeader("id");
        String username = request.getHeader("username");
        String realName = request.getHeader("realName");

        if (userIDStr != null && username != null && realName != null){
            UserInfoDTO userInfoDTO = new UserInfoDTO(Long.parseLong(userIDStr), username, realName);
            UserContext.setUser(userInfoDTO);
        }

        try{
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            UserContext.clean();
        }
    }


}
