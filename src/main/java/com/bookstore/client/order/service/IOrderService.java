package com.bookstore.client.order.service;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.Product;

import java.util.Map;

public interface IOrderService {
    //创建订单信息和订单项
    void createOrder(Order order, Map<Product, Integer> cart);

    //修改订单状态为—已支付
    void paySuccess(String order_id);
}
