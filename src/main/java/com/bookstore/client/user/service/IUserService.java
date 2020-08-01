package com.bookstore.client.user.service;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.User;

import java.util.List;

public interface IUserService {
    //用户注册
    int addUser(User user);

    //用户激活
    int activeUser(String activeCode);

    //检测邮箱是否已被注册
    User findEmail(String email);

    //检测会员名是否已存在
    User findUsername(String username);

    //用户登录
    User findUserByLogin(User user);

    //修改用户信息
    int modifyUser(User user);

    //用户订单查询
    List<Order> findOrderByUser(Integer id);

    //查询订单详细信息
    List<OrderItem> findOrderItemById(String id);

    //用户订单删除
    void removeOrderById(String id, String flag);
}
