package com.bookstore.client.cart.handler;

import com.bookstore.client.products.service.IProductService;
import com.bookstore.commons.beans.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/client/cart")
public class CartHandler {
    @Autowired
    IProductService iProductService;

    @RequestMapping("/addCart")
    public String addCart(String id, HttpSession session){
        System.out.println(id);
        //通过id获取图书信息
        Product product = iProductService.findProductById(id);
        System.out.println("product的值是：---" + product + ",当前方法=CartHandler.addCart()");
        //从session中获取购物车
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if(cart==null){//如果购物车不存在，则创建购物车
            cart = new HashMap<Product,Integer>();
        }
        //map.put()返回值为之前的value值
        Integer count = cart.put(product,1);
        if(count!=null){
            if(count<product.getPnum()){//已加购数小于库存数才可继续添加
                cart.put(product,count+1);
            }else {
                cart.put(product,count);
            }
        }

        //将购物车cart放入session
        session.setAttribute("cart",cart);
        return "redirect:/client/cart.jsp";
    }

    //改变购物车商品数目
    @RequestMapping("/changeCart")
    public String changeCart(String id,int count,HttpSession session){
        //获取id对应的商品对象信息
        Product product = iProductService.findProductById(id);
        //获取购物车
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if(count == 0){
            //购买数为零，则移除商品
            cart.remove(product);
        } else{
            cart.put(product,count);
        }
        return "redirect:/client/cart.jsp";
    }
}
