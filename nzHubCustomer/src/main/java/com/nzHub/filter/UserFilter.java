package com.nzHub.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

//        if(session.getAttribute("user") == null){
//            response.sendRedirect("/login");
//            return;//修改
//        }else{
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
        String path = request.getRequestURI();
        if (!path.startsWith("/productCategory/main") && session.getAttribute("user") == null) {
            response.sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


//        if (session.getAttribute("user") == null) {
//            String requestType = request.getHeader("X-Requested-With");
//            if ("XMLHttpRequest".equals(requestType)) { // AJAX 请求
//                response.setContentType("application/json;charset=UTF-8");
//                response.getWriter().write("{\"error\": \"未登录\"}");
//            } else { // 普通浏览器请求
//                response.sendRedirect("/login");
//            }
//            return;
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
    }
}
