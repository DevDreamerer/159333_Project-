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

        // Login Related Requests
        if (request.getRequestURI().startsWith("/admin/login")) {
            return true;
        }

        // Check Administrator Login Status
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("/admin/login");
            return false;
        }
        return true;
    }
}
