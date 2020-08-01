package com.bookstore.admin.orders.service;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;

import java.util.List;

public interface IAdminOrderService {
    List<Order> findAllOrders();

    List<Order> findOrderByManyCondition(Order order);

    Order findOrderById(String id);

    List<OrderItem> findOrderItemByOrderId(String id);

    void removeOrderById(String id);
}
