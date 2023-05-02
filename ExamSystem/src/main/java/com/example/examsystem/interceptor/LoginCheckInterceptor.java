package com.example.examsystem.interceptor;

import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.utils.JwtUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component  //spring注解，将其实例化为一个bean
public class LoginCheckInterceptor implements HandlerInterceptor {
    //ctrl+o
    @Override   //目标方法运行前运行，返回true：放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求url
        String url = request.getRequestURL().toString();
        //2.判断url是login则放行
//        if(url.contains("login/admin") || url.contains("login/teacher") || url.contains("login/student")){//有漏洞：被恶意构造?login=...
//            return true;
//        }

        //<editor-fold desc="调试阶段全部放行">
        if(true){
            return true;
        }
        //</editor-fold>
        log.info("继续返回执行");
        //3.获取请求头的令牌
        String token = request.getHeader("token");
        //4.判断令牌是否为空
        if(token == null){
            log.info("token为空");
            Response response1 = new Response(ResponseEnum.Login_Failure);
            Gson gson = new GsonBuilder().serializeNulls().create();//用于有参数为null时的正确序列化，如果不需要为null初始化则直接new Gson()即可
            response.setCharacterEncoding("UTF-8");//将输出字符流的编码设置为UTF-8
            response.getWriter().write(gson.toJson(response1));
            return false;
        }
        //5.判断令牌是否正确
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("token解析失败，返回未登录错误信息");
            Response response1 = new Response(ResponseEnum.Login_Failure);
            Gson gson = new GsonBuilder().serializeNulls().create();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(response1));
            return false;
        }

        //6.放行
        return true;

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