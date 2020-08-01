package com.bookstore.commons.beans;

public class Product {
    private String id;
    private String name;
    private double price;
    private String category;
    private int pnum;
    private String imgURL;
    private String description;

    public Product() {
    }

    public Product(String id, String name, double price, String category, int pnum, String imgURL, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.pnum = pnum;
        this.imgURL = imgURL;
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 :id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        //如果两个对象是同一个对象，则返回true
        if(this == obj )
            return true;
        //参数若为空，则返回false
        if(obj == null)
            return false;
        //两个对象类型是否相同
        if(this.getClass() != obj.getClass())
            return false;
        //将obj强制类型转换为product
        Product other = (Product) obj;
        //如果equals前的product的主键为null
        if(id == null){
            //equals后的product的主键不为null，则返回false
            if(other.id != null){
                return false;
            }
        } else if(!id.equals(other.id)){//判断product主键是否相同，不通则返回false
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", pnum=" + pnum +
                ", imgURL='" + imgURL + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
