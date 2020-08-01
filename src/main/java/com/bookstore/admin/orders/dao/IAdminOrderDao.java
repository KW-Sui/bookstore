package com.bookstore.admin.orders.dao;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;

import java.util.List;

public interface IAdminOrderDao {
    List<Order> selectAllOrders();

    List<Order> selectOrderByManyCondition(Order order);

    Order selectOrderById(String id);

    List<OrderItem> selectOrderItemByOrderId(String id);

    //删除订单项
    void deleteOrderItemByOrderId(String id);

    //删除订单
    void deleteOrderById(String id);
}
