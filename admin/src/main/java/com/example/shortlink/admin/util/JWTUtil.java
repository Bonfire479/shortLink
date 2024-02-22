package com.example.shortlink.admin.util;


import com.example.shortlink.admin.common.convention.exception.ClientException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


@Slf4j
public class JWTUtil {
    private static final String key = "asdxxx1213";

    public static String generateToken(Map<String, Object> map) {
        return Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS256, key)
                .addClaims(map)
                .compact();
    }

    public static Map<String, Object> parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        }
        catch (Throwable ex){
            log.error("token解析发生异常 token:[{}] 异常:[{}]", token, ex.toString());
            throw new ClientException("token异常");
        }
    }
}
