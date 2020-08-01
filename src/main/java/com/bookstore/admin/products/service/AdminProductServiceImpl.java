package com.bookstore.admin.products.service;

import com.bookstore.admin.products.dao.IAdminProductDao;
import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminProductServiceImpl implements IAdminProductService {
    @Autowired
    private IAdminProductDao iAdminProductDao;

    //查询商品列表
    @Override
    public List<Product> findAllProduct() {
        return iAdminProductDao.selectAllProduct();
    }

    //符合条件查询商品信息
    @Override
    public List<Product> findProductByManyCondition(Product product, Double minprice, Double maxprice) {
        Map map = new HashMap();
        map.put("product",product);
        map.put("minprice",minprice);
        map.put("maxprice",maxprice);
        return iAdminProductDao.selectProductByManyCondition(map);
    }

    //添加商品信息到数据库
    @Override
    public void addProduct(Product product) {
        iAdminProductDao.insertProduct(product);
    }

    //按id查询商品信息
    @Override
    public Product findProductById(String id) {
        return iAdminProductDao.selectProductById(id);
    }

    //更改商品数据库信息
    @Override
    public void editProduct(Product product) {
        iAdminProductDao.updateProductInfo(product);
    }

    @Override
    public int removeProduct(String id) {
        return iAdminProductDao.deleteProduct(id);
    }

    @Override
    public List<ProductList> findProductSalList(String year, String month) {
        return iAdminProductDao.selectProductSalList(year,month);
    }
}
