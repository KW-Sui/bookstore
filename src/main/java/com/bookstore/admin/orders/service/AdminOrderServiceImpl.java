package com.bookstore.admin.orders.service;

import com.bookstore.admin.orders.dao.IAdminOrderDao;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminOrderServiceImpl implements IAdminOrderService{
    @Autowired
    private IAdminOrderDao iAdminOrderDao;

    @Override
    public List<Order> findAllOrders() {
        return iAdminOrderDao.selectAllOrders();
    }

    @Override
    public List<Order> findOrderByManyCondition(Order order) {
        return iAdminOrderDao.selectOrderByManyCondition(order);
    }

    //查询订单
    @Override
    public Order findOrderById(String id) {
        return iAdminOrderDao.selectOrderById(id);
    }

    //查询订单项
    @Override
    public List<OrderItem> findOrderItemByOrderId(String id) {
        return iAdminOrderDao.selectOrderItemByOrderId(id);
    }

    @Override
    public void removeOrderById(String id) {
        //删除订单项
        iAdminOrderDao.deleteOrderItemByOrderId(id);
        //删除订单
        iAdminOrderDao.deleteOrderById(id);
    }
}
