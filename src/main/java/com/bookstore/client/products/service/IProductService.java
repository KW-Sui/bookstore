package com.bookstore.client.products.service;

import com.bookstore.commons.beans.Notice;
import com.bookstore.commons.beans.Product;
import com.bookstore.utils.PageModel;

import java.util.List;

public interface IProductService {
    //根据种类查找书籍信息
    List<Product> findProductByCategory(String category, PageModel pageModel);

    //获取符合条件的书籍个数-用于分页显示-种类
    int findProductCount(String category);

    //根据书名查找书籍信息
    List<Product> findProductByName(String textfield, PageModel pageModel);

    //获取符合条件的书籍个数-用于分页显示-书名
    int findProductCountByName(String textfield);

    //根据id查询书籍详细信息
    Product findProductById(String id);

    //最新公告查询
    Notice findNoticeRecent();

    //本周热卖查询
    List<Product> findWeekHotProduct();
}
