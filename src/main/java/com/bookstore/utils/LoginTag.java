package com.bookstore.utils;

import com.bookstore.commons.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

//用户访问权限自定义标签类
public class LoginTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        //获取pageContext对象
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
        //通pageContext
        HttpSession session = pageContext.getSession();
        //获取当前登录用户信息
        User login_user = (User) session.getAttribute("login_user");
        if(login_user==null){//用户未登录
            //response发送重定向响应，到权限不足提示页面
            response.sendRedirect(request.getContextPath()+"/client/error/privilege.jsp");
            super.doTag();
        }
    }
}
