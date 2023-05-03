package com.example.examsystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final Key signKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static Long expire = 365*30*43200000L;

    /**
     * 生成JWT令牌
     *
     * @param claims,JWT第二部分payload中存储的内容
     * @return
     */
    public static String generateToken(Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(signKey)
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey)//指定签名密钥
                .build()
                .parseClaimsJws(jwt)//指定令牌Token
                .getBody();
    }
}

