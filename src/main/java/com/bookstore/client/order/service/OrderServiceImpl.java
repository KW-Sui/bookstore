package com.bookstore.client.order.service;

import com.bookstore.client.order.dao.IOrderDao;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import com.bookstore.commons.beans.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao iOrderDao;

    //创建订单信息
    @Override
    public void createOrder(Order order, Map<Product, Integer> cart) {
        //循环生成订单项，并修改商品库存
        for(Product p:cart.keySet()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(p);
            orderItem.setBuyNum(cart.get(p));
            //生成订单项
            iOrderDao.createOrderItem(orderItem);
            //修改商品库存
            iOrderDao.modifyPNum(orderItem);
        }

        //生成订单
        iOrderDao.createOrder(order);
    }

    //修改订单状态为—已支付
    @Override
    public void paySuccess(String id) {
        System.out.println(id);
         iOrderDao.updatePayState(id);
    }
}
