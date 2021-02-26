package com.mybatis.spring.user.interceptor;

import com.mybatis.spring.common.ResourcesUtil;
import com.mybatis.spring.user.pojo.ActiveUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 对请求的处理：
        // 1. 请求放过，如果存在其他拦截器，则继续进入下一个拦截器
        String requestURI = request.getRequestURI();
        if (requestURI.indexOf("/user/login") > -1) {
            return true;
        }
        HttpSession session = request.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        if (activeUser != null) {
            return true;
        } else {
            System.out.println("登录拦截的url: " + request.getRequestURL());
            request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
            return false;
        }
    }
}
