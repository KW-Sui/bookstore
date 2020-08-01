package com.bookstore.admin.products.dao;

import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IAdminProductDao {
    //查询商品列表
    List<Product> selectAllProduct();

    //符合条件查询商品信息
    List<Product> selectProductByManyCondition(Map map);

    //添加商品信息到数据库
    void insertProduct(Product product);

    //按id查询商品信息
    Product selectProductById(String id);

    //更改商品数据库信息
    void updateProductInfo(Product product);

    //删除商品
    int deleteProduct(String id);

    List<ProductList> selectProductSalList(@Param("year") String year, @Param("month") String month);
}
