package com.example.examsystem;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ExamSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void genJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","Tom");
        String jwt = Jwts.builder()
                .setClaims(claims) //自定义内容(载荷)
                .signWith(SignatureAlgorithm.HS256, "itheimafailingtestsSethereportatfileDpersonalprojectjavaEnglishLevel6TestRegistrationSystemExamSystemvaEnglishLevel6TestRegistrationSystemExamSyste") //签名算法
                .setExpiration(new Date(System.currentTimeMillis() +
                        24*3600*1000)) //有效期
                .compact();
        System.out.println(jwt);
    }
    @Test
    public void parseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheimafailingtestsSethereportatfileDpersonalprojectjavaEnglishLevel6TestRegistrationSystemExamSystemvaEnglishLevel6TestRegistrationSystemExamSyste")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJUb20iLCJleHAiOjE2ODI5MjgxNTB9.U1k8iW1lABppaqX4VUed6UleNJvJQNZh77B93g7KjCY")
                        .getBody();
        System.out.println(claims);
    }
}
