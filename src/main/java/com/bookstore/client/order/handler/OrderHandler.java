package com.bookstore.client.order.handler;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.bookstore.client.order.service.IOrderService;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.User;
import com.bookstore.utils.AlipayConfig;
import com.bookstore.utils.UUidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/client/orders")
public class OrderHandler {
    @Autowired
    private IOrderService iOrderService;

    //生成订单
    @RequestMapping("/createOrder")
    public String createOrder(Order order, HttpSession session, Model model){

        //为order的id和关联属性（用户）赋值
        order.setId(UUidUtil.getUUID());
        order.setUser((User) session.getAttribute("login_user"));

        //获取购物车信息
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        //创建订单信息和订单项
        iOrderService.createOrder(order,cart);
        //订单完成，清除购物车信息
        session.removeAttribute("cart");
        //将订单信息保存到Model
        model.addAttribute("order",order);
        return "/client/confirm.jsp";
    }

    //alipay沙箱支付
    @RequestMapping("/aliPay")
    public void aliPay(String id, String money, HttpServletResponse response) throws AlipayApiException, IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                                    AlipayConfig.merchant_private_key, "json", AlipayConfig.charset,
                                    AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = id;
        //付款金额，必填
        String total_amount = money;
        //订单名称，必填
        String subject = id;
        //商品描述，可空
        String body = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //通过response将result以HTML的形式返回回去
        response.setContentType("text/html");
        response.getWriter().println(result);
    }

    //支付成功，获取支付的GET的反馈信息返回书城页面，并修改订单状态
    @RequestMapping("/paySuccess")
    public String paySuccess(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String order_id = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            //String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            //String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            //out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);

            //修改订单状态为—已支付
            iOrderService.paySuccess(order_id);

            //支付成功，跳转到支付成功页面
            return "redirect:/client/paysuccess.jsp";
        }else {
            //out.println("验签失败");
            return "redirect:/client/fail.jsp";
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
    }
}
