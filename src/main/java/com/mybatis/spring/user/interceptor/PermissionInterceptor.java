package com.mybatis.spring.user.interceptor;

import com.mybatis.spring.common.ResourcesUtil;
import com.mybatis.spring.user.pojo.ActiveUser;
import com.mybatis.spring.user.pojo.SysPermission;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 对请求的处理：
        // 1. 请求放过，如果存在其他拦截器，则继续进入下一个拦截器
        String requestURI = request.getRequestURI();
        if (requestURI.indexOf("/user/login") > -1 || requestURI.indexOf("/good/index") > -1) {
            return true;
        }

        //2.请求放过，页面跳转
        HttpSession session = request.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        List<SysPermission> permissions = activeUser.getPermissions();
        if (requestURI.indexOf("-") > -1) {
            String replaceUri = requestURI.replace("-", ":");
            for (SysPermission sysPermission : permissions) {
                if (replaceUri.indexOf(sysPermission.getPercode())>-1){
                    return true;
                }
            }
        }

        //3. 有权限放过
        for (SysPermission sysPermission : permissions) {
            String openPerMissionURL = sysPermission.getUrl();
            if (requestURI.indexOf(openPerMissionURL) > -1) {
                return true;
            }
        }

        //4. 请求不放过跳转到拒绝页面，不再进去其他拦截器
        System.out.println("权限拦截的url: " + request.getRequestURL());
        request.getRequestDispatcher("/WEB-INF/jsp/user/refuse.jsp").forward(request, response);
        return false;
    }
}
