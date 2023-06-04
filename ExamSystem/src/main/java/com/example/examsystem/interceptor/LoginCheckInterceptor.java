package com.example.examsystem.interceptor;

import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.utils.JwtUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

@Slf4j
@Component  //spring注解，将其实例化为一个bean
public class LoginCheckInterceptor implements HandlerInterceptor {

    //ctrl+o
    @Override   //目标方法运行前运行，返回true：放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求uri
        String uri = request.getRequestURI();
        log.info(uri);

        if(uri.contains("/pages/")||uri.contains("error")||uri.contains("favicon.ico")) {
            return true;
        }

        // 2.登录请求直接过
        if (uri.contains("/login")) {
            return true;
        }
        // 3.注册请求直接过
        if (request.getMethod().equals("POST") && (uri.equals("/student") || uri.equals("/teacher") || uri.equals(("/admin")))) {
            return true;
        }

        // 4.获取请求头的令牌
        String token = request.getHeader("token");
        // 5.判断令牌是否为空
        if (token == null) {
            throw new DaoException(ResponseEnum.Login_Failure);
        }
        // 6.判断令牌是否正确
        try {
            Claims claim = JwtUtils.parseJWT(token);
            Integer roleId = claim.get("roleId", Integer.class);
            if (roleId == 1) {
                //用户是管理员，有全部权限，直接放行
                log.info("token身份为管理员");
                return true;
            }
            //用户是教师，对于访问卷子、问题、批改、注册的请求放行
            else if (roleId == 2 && (uri.contains("paper") ||
                    uri.contains("question") ||
                    uri.contains("doandcheck") ||
                    (uri.contains("teacher") && request.getMethod().equals("POST"))
            )) {
                log.info("token身份为教师");
                return true;
            }
            //用户是学生，对于访问卷子、问题的get请求放行，对于访问doandcheck的post请求也放行，对于学生注册、报名考试的请求放行
            else if (roleId == 3 &&
                    ((uri.contains("paper") || uri.contains("question")) && (Objects.equals(request.getMethod(), "GET"))) ||
                    (uri.contains("doandcheck") && !Objects.equals(request.getMethod(), "DELETE")) ||
                    (uri.contains("student") && Objects.equals(request.getMethod(), "POST")) ||
                    uri.contains("signup")) {
                log.info("token身份为学生");
                return true;
            }
        } catch (Exception e) {
            log.info("token解析失败，返回未登录错误信息");
            throw new DaoException(ResponseEnum.Login_Failure);
        }

        return false;

    }

    @Override   //目标方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override   //视图渲染完毕后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
