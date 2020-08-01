package com.bookstore.client.user.dao;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {
    //用户注册，添加用户
    int addUser(User user);

    //用户激活
    int updateActiveCode(String activeCode);

    //检测邮箱是否已被注册
    User selectEmail(String email);

    //检测会员名是否已存在
    User selectUsername(String username);

    //用户登录
    User selectUserByLogin(User user);

    //修改用户信息
    int updateUser(User user);

    //用户订单查询
    List<Order> selectOrderByUser(Integer id);

    //查询订单详细信息
    List<OrderItem> selectOrderItemById(String id);

    //商品加回库存
    void updateProductNum(OrderItem item);

    //删除订单项ById
    void deleteOrderItemsById(String id);

    //删除订单ById
    void deleteOrderById(String id);
}
