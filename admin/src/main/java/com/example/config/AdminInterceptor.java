package com.example.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();

        // 放行登录相关请求
        if (request.getRequestURI().startsWith("/admin/login")) {
            return true;
        }

        // 检查管理员登录状态
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("/admin/login");
            return false;
        }
        return true;
    }
}
