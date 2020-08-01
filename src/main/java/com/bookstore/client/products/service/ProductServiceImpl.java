package com.bookstore.client.products.service;

import com.bookstore.client.products.dao.IProductDao;
import com.bookstore.commons.beans.Notice;
import com.bookstore.commons.beans.Product;
import com.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao iproductDao;
    //根据种类查找书籍信息
    @Override
    public List<Product> findProductByCategory(String category, PageModel pageModel) {
        return iproductDao.selectProductByCategory(category,pageModel);
    }

    //获取符合条件的书籍个数-用于分页显示-种类
    @Override
    public int findProductCount(String category) {
        return iproductDao.selectProductCount(category);
    }

    //根据书名查找书籍信息
    @Override
    public List<Product> findProductByName(String name, PageModel pageModel) {
        return iproductDao.selectProductByName(name,pageModel);
    }

    //获取符合条件的书籍个数-用于分页显示-书名
    @Override
    public int findProductCountByName(String name) {
        return iproductDao.selectProductCountByName(name);
    }

    //根据id查询书籍详细信息
    @Override
    public Product findProductById(String id) {
        return iproductDao.selectProductById(id);
    }

    //最新公告查询
    @Override
    public Notice findNoticeRecent() {
        return iproductDao.selectNoticeRecent();
    }

    //本周热卖查询
    @Override
    public List<Product> findWeekHotProduct() {
        return iproductDao.selectWeekHotProduct();
    }
}
