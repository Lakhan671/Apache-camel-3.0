package com.nike.camelmicroservicea.course1.aggregrator.domain;


public class Order {

    private String orderNum;
    private String item;
    private String orderStatus;

    @Override
    public String toString() {
        return "Order{" +
                "orderNum='" + orderNum + '\'' +
                ", item='" + item + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
