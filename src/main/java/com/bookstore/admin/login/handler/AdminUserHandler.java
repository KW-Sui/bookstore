package com.bookstore.admin.login.handler;

import com.bookstore.admin.login.service.IAdminUserService;
import com.bookstore.commons.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/login")
public class AdminUserHandler {
    @Autowired
    private IAdminUserService iAdminUserService;

    //管理员登录验证
    @RequestMapping("/login")
    public String login(User user, HttpSession session, Model model){
        User login_user = iAdminUserService.findUserByLogin(user);
        System.out.println("login_user的值是：---" + login_user);
        if(login_user!=null){
            //用户名和密码正确
            if("超级管理员".equals(login_user.getRole())){//当前用户属于管理员
                session.setAttribute("login_user",login_user);
                return "/admin/login/home.jsp";
            } else{//当前用户不是管理员
                return "redirect:/error/privilege.jsp";
            }
        } else{
            //用户名或密码输入错误
            model.addAttribute("fail","用户名或密码错误，请重试！");
            return "/admin/login/login.jsp";
        }
    }

    //管理员退出
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("login_user");
        return "/admin/login/login.jsp";
    }
}
