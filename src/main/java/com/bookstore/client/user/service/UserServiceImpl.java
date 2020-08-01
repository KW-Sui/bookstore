package com.bookstore.client.user.service;

import com.bookstore.client.user.dao.IUserDao;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.User;
import com.bookstore.utils.MailUtils;
import com.bookstore.utils.UUidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    //用户注册
    public int addUser(User user) {
        //设置激活链接
        String massage = "点击此链接即可"
                +"<a href='http://localhost:8080/bookstore/client/user/activeUser?activeCode="+user.getActiveCode()+"'>激活</a>"
                +"账户！";
        try {
            //发送账户激活邮件
            MailUtils.sendMail(user.getEmail(),massage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return userDao.addUser(user);
    }

    //用户激活
    @Override
    public int activeUser(String activeCode) {
        return userDao.updateActiveCode(activeCode);
    }

    //检测邮箱是否已被注册
    @Override
    public User findEmail(String email) {
        return userDao.selectEmail(email);
    }
    //检测会员名是否已存在
    @Override
    public User findUsername(String username) {
        return userDao.selectUsername(username);
    }

    //用户登录
    @Override
    public User findUserByLogin(User user) {
        return userDao.selectUserByLogin(user);
    }

    //修改用户信息
    @Override
    public int modifyUser(User user) {
        return userDao.updateUser(user);
    }

    //用户订单查询
    @Override
    public List<Order> findOrderByUser(Integer id) {
        return userDao.selectOrderByUser(id);
    }

    //查询订单详细信息
    @Override
    public List<OrderItem> findOrderItemById(String id) {
        return userDao.selectOrderItemById(id);
    }

    //用户订单删除
    @Override
    public void removeOrderById(String id, String flag) {
        if(flag==null){//flag==null,表示当前订单为进行支付
            List<OrderItem> orderItems = userDao.selectOrderItemById(id);
            for(OrderItem items:orderItems){
                //商品加回库存
                userDao.updateProductNum(items);
            }
        }
        //删除订单项
        userDao.deleteOrderItemsById(id);
        //删除订单
        userDao.deleteOrderById(id);
    }
}
