package com.bookstore.Interceptor;

import com.bookstore.commons.beans.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if(url.endsWith("/login")){
            return true;
        }

        HttpSession session = request.getSession();
        User login_user = (User) session.getAttribute("login_user");
        if(login_user != null){
            if("超级管理员".equals(login_user.getRole())){
                return true;
            } else {//不属于超级管理员
                response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
                return false;
            }
        } else{//用户名或密码错误
            response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
            return false;
        }
    }
}
