package com.bookstore.client.products.handler;

import com.bookstore.client.products.service.IProductService;
import com.bookstore.commons.beans.Notice;
import com.bookstore.commons.beans.Product;
import com.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client/product")
public class ProductHandler {
    @Autowired
    private IProductService iproductService;

    //按商品类名分页查询
    @RequestMapping("/findProductByCategory")
    public String findProductByCategory(@RequestParam(defaultValue = "1") Integer pageIndex, String category, Model model){
        //获取符合条件的书籍个数-用于分页显示-种类
        int count = iproductService.findProductCount(category);

        //分页设置
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(count);

        //获取符合种类的书籍信息
        List<Product> products = iproductService.findProductByCategory(category,pageModel);
        for(Product product: products){
            System.out.println("product的值是：---" + product + ",当前方法=ProductHandler.findProductByCategory()");
        }
        model.addAttribute("category",category);
        model.addAttribute("products",products);
        model.addAttribute("pageModel",pageModel);
        return "/client/product_list.jsp";
    }

    //按商品名称进行分页查询
    @RequestMapping("/findProductByName")
    public String findProductByName(@RequestParam(defaultValue = "1")int pageIndex, String textfield,Model model){
        //获取符合条件的书籍个数-用于分页显示-书名
        int count = iproductService.findProductCountByName(textfield);

        //分页设置
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(count);
        //根据书名查找书籍信息
        List<Product> products = iproductService.findProductByName(textfield,pageModel);

        model.addAttribute("products",products);
        model.addAttribute("pageModel",pageModel);
        //数据回显
        model.addAttribute("name",textfield);
        return "/client/product_search_list.jsp";
    }

    //按id查询书籍详细信息
    @RequestMapping("/findProductById")
    public String findProductById(String id,Model model){
        Product product = iproductService.findProductById(id);
        model.addAttribute("p",product);
        return "/client/info.jsp";
    }

    //首页相关
    @RequestMapping("/showIndex")
    public String showIndex(Model model){
        //最新公告查询
        Notice notice = iproductService.findNoticeRecent();

        //本周热卖查询
        List<Product> products = iproductService.findWeekHotProduct();

        model.addAttribute("notice",notice);
        model.addAttribute("products",products);
        return "/client/index.jsp";
    }
}
