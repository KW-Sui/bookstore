package com.bookstore.admin.orders.handler;

import com.bookstore.admin.orders.service.IAdminOrderService;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderHandler {
    @Autowired
    private IAdminOrderService iAdminOrderService;

    @RequestMapping("/findOrders")
    public String findOrders(Model model){
        List<Order> orders = iAdminOrderService.findAllOrders();
        model.addAttribute("orders",orders);
        return "/admin/orders/list.jsp";
    }

    //条件查询
    @RequestMapping("/findOrderByManyCondition")
    public String findOrderByManyCondition(Order order,Model model){
        List<Order> orders = iAdminOrderService.findOrderByManyCondition(order);
        model.addAttribute("order",order);
        model.addAttribute("orders",orders);
        return "/admin/orders/list.jsp";
    }

    //查询订单详细信息
    @RequestMapping("/findOrderById")
    public String findOrderById(String id,Model model){
        Order order = iAdminOrderService.findOrderById(id);
        List<OrderItem> orderItems = iAdminOrderService.findOrderItemByOrderId(id);
        model.addAttribute("order",order);
        model.addAttribute("orderItems",orderItems);
        return "/admin/orders/view.jsp";
    }

    //删除订单
    @RequestMapping("/removeOrderById")
    public String removeOrderById(String id){
        iAdminOrderService.removeOrderById(id);
        return "/admin/order/findOrders";
    }
}
