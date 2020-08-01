package com.bookstore.admin.products.service;

import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;

import java.util.List;

public interface IAdminProductService {
    //查询商品列表
    List<Product> findAllProduct();

    //符合条件查询商品信息
    List<Product> findProductByManyCondition(Product product, Double minprice, Double maxprice);

    //添加商品信息到数据库
    void addProduct(Product product);

    //按id查询商品信息
    Product findProductById(String id);

    //更改商品数据库信息
    void editProduct(Product product);

    //删除商品
    int removeProduct(String id);

    List<ProductList> findProductSalList(String year, String month);
}
