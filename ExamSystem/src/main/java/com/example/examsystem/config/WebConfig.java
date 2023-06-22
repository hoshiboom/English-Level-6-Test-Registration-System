package com.example.examsystem.config;

import com.example.examsystem.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private LoginCheckInterceptor loginCheckInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**");
    }

    //解决跨域问题
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .exposedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS)
                .exposedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }*/

    private Map<String, String> parameterMap = new HashMap<>();
    @PostConstruct
    public void init() {
        // 在初始化方法中添加参数和对应的值到哈希表中
        parameterMap.put("Page_Size", "50");
    }
    @Bean(name = "globalVariable")
    public Function<String, String> globalVariable(){
        return key -> parameterMap.get(key);
    }

}
