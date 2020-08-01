package com.bookstore.commons.beans;

public class OrderItem {
    //关联属性，订单-订单项：一对多
    private Order order;
    //关联属性，商品-订单项：一对多
    private Product product;
    int buyNum;

    //主键：order_id+product_id

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, int buyNum) {
        this.order = order;
        this.product = product;
        this.buyNum = buyNum;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order=" + order +
                ", product=" + product +
                ", buyNum=" + buyNum +
                '}';
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }
}
