package com.bookstore.commons.beans;

import java.sql.Timestamp;

public class Order {
    private String id;
    private double money;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private int payState;
    private Timestamp orderTime;
    //管理属性，order为多方，user为一方
    private User user;

    public Order() {
    }

    public Order(String id, double money, String receiverAddress, String receiverName, String receiverPhone, int payState, Timestamp orderTime, User user) {
        this.id = id;
        this.money = money;
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.payState = payState;
        this.orderTime = orderTime;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", money=" + money +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", payState=" + payState +
                ", orderTime=" + orderTime +
                ", user=" + user +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getPayState() {
        return payState;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
