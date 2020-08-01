package com.bookstore.client.user.handler;

import com.bookstore.client.user.service.IUserService;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.User;
import com.bookstore.utils.UUidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/client/user")
public class UserHandler {

    @Autowired
    private IUserService userService;
    //用户注册
    @RequestMapping("/register")
    public String register(User user,String checkCode, HttpSession session, HttpServletRequest request){
        //获取校验码
        String checkCode_session = (String) session.getAttribute("checkcode_session");
        //设置激活码
        user.setActiveCode(UUidUtil.getUUID());
        //验证校验码是否正确
        if(checkCode_session.equals(checkCode)){
            int row = userService.addUser(user);
            if(row>0){
                return "redirect:/client/registersuccess.jsp";
            }else {
                request.setAttribute("register_error","注册失败，请重试！");
                return "/client/register.jsp";
            }
        }else {
            request.setAttribute("check_error","校验码错误，请重试！");
            return "/client/register.jsp";
        }
    }

    //用户激活-改变用户state为1
    @RequestMapping("activeUser")
    public String activeUser(String activeCode){
        int row = userService.activeUser(activeCode);
        if(row > 0 ){
            return "redirect:/client/activesuccess.jsp";
        } else {
            return "redirect:/client/activeFail.jsp";
        }
    }

    //检测邮箱是否已被注册
    @RequestMapping("/emailTest")
    @ResponseBody
    public String emailTest(String email){
        User user = userService.findEmail(email);
        if(user != null){
            return "EXIST";
        }else {
            return "OK";
        }
    }

    //检测会员名是否已存在
    @RequestMapping("/usernameTest")
    @ResponseBody
    public String usernameTest(String username){
        User user = userService.findUsername(username);
        if(user!=null){
            return "EXIST";
        } else {
            return "OK";
        }
    }

    //我的账户-提供用户登录入口
    @RequestMapping("/myAccount")
    public String myAccount(HttpSession session,HttpServletRequest request){
        //获取session中的用户登录信息
        User login_user = (User) session.getAttribute("login_user");
        if(login_user != null){//用户已登录
            //显示我的账户
            return "/client/myAccount.jsp";
        } else {//用户未登录
            //进行自动登录
            login_user = autoLogin(request);
            if(login_user != null){//自动登录成功
                //将用户信息放入session中
                session.setAttribute("login_user",login_user);
                return "/client/myAccount.jsp";
            }
            //若仍未登录则到登录页面
            return "/client/login.jsp";
        }
    }

    //用户登录
    @RequestMapping("/login")
    public String login(User user,String remember,String autoLogin,HttpServletRequest request,HttpServletResponse response,HttpSession session){
        User login_user = userService.findUserByLogin(user);
        if(login_user != null){
            if(login_user.getState() == 1){//用户名密码正确，且用户已激活
                if("1".equals(autoLogin)){
                    //记住用户信息，将user信息保存到cookie
                    addCookie(autoLogin,login_user,request,response);
                }else if("1".equals(remember)){
                    //记住用户名，将username保存到cookie；
                    addCookie(autoLogin,login_user,request,response);
                }
                //将用户保存到session域中
                session.setAttribute("login_user",login_user);
                return "/client/myAccount.jsp";
            }else {//用户未激活
                request.setAttribute("login_error","用户未激活，请激活后再进行登录！");
                return "/client/login.jsp";
            }
        }else{//用户名或密码错误
            request.setAttribute("login_error","用户名或密码错误，请重新输入！");
            return "/client/login.jsp";
        }
    }

    //将用户名保存到cookie-用于自动登录和记住用户名
    private void addCookie(String autoLogin, User user, HttpServletRequest request, HttpServletResponse response){
        //定义cookie对象-保存用户名到cookie
        Cookie cookie1 = new Cookie("bookstore_username",user.getUsername());
        //设置cookie保存时间
        cookie1.setMaxAge(60*60*24*3);
        //设置cookie的作用路径
        cookie1.setPath(request.getContextPath()+"/");
        //将定义好的cookie响应回客户端
        response.addCookie(cookie1);
        if ("1".equals(autoLogin)){
            Cookie cookie2 = new Cookie("bookstore_password",user.getPassword());
            cookie2.setMaxAge(60*60*24*3);
            cookie2.setPath(request.getContextPath()+"/");
            response.addCookie(cookie2);
        }
    }

    //自动登录
    private User autoLogin(HttpServletRequest request) {
        String username = null;
        String password = null;
        //从request中获取所有的cookie信息
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie: cookies){
            //从cookies中获取书城用户名的cookie的值
            if("bookstore_username".equals(cookie.getName())){
                username = cookie.getValue();
            }
            //从cookies中获取书城用户密码的cookie的值
            if("bookstore_password".equals(cookie.getName())){
                password = cookie.getValue();
            }
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //返回数据库的查询结果，看用户名和密码是否依然正确，防止密码修改。
        return userService.findUserByLogin(user);
    }

    //用户退出-从session和cookie中移除用户相关信息
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response){
        //从session中移除用户信息
        session.removeAttribute("login_user");
        //从cookie中移除用户名
        Cookie cookie1 = new Cookie("bookstore_username",null);
        cookie1.setMaxAge(0);
        cookie1.setPath(request.getContextPath()+"/");
        response.addCookie(cookie1);
        //从cookie中移除用户密码
        Cookie cookie2 = new Cookie("bookstore_password",null);
        cookie2.setMaxAge(0);
        cookie2.setPath(request.getContextPath()+"/");
        response.addCookie(cookie2);
        model.addAttribute("login_error","用户退出成功，可重新登录！");
        return "/client/login.jsp";
    }

    //修改用户信息，
    @RequestMapping("/modifyUser")
    public String modifyUser(User user,HttpSession session,Model model){
        //获取登录用户信息
        User login_user = (User) session.getAttribute("login_user");
        //设置id值
        user.setId(login_user.getId());
        int row = userService.modifyUser(user);
        if(row>0){
            model.addAttribute("login_error","修改信息成功，请重新登录！");
            //从session中除去login_user信息，防止未重新登录时点击我的账户而直接显示账户信息
            session.removeAttribute("login_user");
            return "/client/login.jsp";
        }else {
            model.addAttribute("modify_error","修改信息失败，请重试！");
            return "/client/modifyuserinfo.jsp";
        }
    }

    //用户订单查询
    @RequestMapping("/findOrderByUser")
    public String findOrderByUser(HttpSession session,Model model){
        //获取登录用户信息
        User login_user = (User) session.getAttribute("login_user");
        //获取当前用户的所有订单
        List<Order> orders = userService.findOrderByUser(login_user.getId());
        model.addAttribute("orders",orders);
        return "/client/orderlist.jsp";
    }

    //查看订单详细信息
    @RequestMapping("/findOrderById")
    public String findOrderById(String id,Model model){
        //根据id获取指定订单
        List<OrderItem> orderItems = userService.findOrderItemById(id);
        model.addAttribute("orderItems",orderItems);
        return "/client/orderInfo.jsp";
    }

    //用户订单删除
    @RequestMapping("/delOrderById")
    public String delOrderById(String id,String flag){
        //flag用于区分订单是否支付，支付flag值为1，否则为null
        userService.removeOrderById(id,flag);
        return "redirect:/client/user/findOrderByUser";
    }
}
