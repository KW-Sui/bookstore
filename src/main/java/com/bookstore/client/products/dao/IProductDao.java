package com.bookstore.client.products.dao;

import com.bookstore.commons.beans.Notice;
import com.bookstore.commons.beans.Product;
import com.bookstore.utils.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IProductDao {
    //根据种类查找书籍信息
    List<Product> selectProductByCategory(@Param("category") String category, @Param("pageModel") PageModel pageModel);

    //获取符合条件的书籍个数-用于分页显示-种类
    int selectProductCount(String category);

    //根据书名查找书籍信息
    List<Product> selectProductByName(@Param("name") String name, @Param("pageModel") PageModel pageModel);

    //获取符合条件的书籍个数-用于分页显示-书名
    int selectProductCountByName(String name);

    //根据id查询书籍详细信息
    Product selectProductById(String id);

    //最新公告查询
    Notice selectNoticeRecent();

    //本周热卖查询
    List<Product> selectWeekHotProduct();
}
