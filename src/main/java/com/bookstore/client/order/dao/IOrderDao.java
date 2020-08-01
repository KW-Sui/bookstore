package com.bookstore.client.order.dao;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.Product;
import org.apache.ibatis.annotations.Param;

public interface IOrderDao {
    //创建订单信息
    int createOrder(Order order);

    //生成订单项
    void createOrderItem(OrderItem orderItem);

    //修改商品库存
    void modifyPNum(OrderItem orderItem);

    //修改订单状态为—已支付
    void updatePayState(String id);
}
