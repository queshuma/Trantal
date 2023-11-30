package com.shuzhi.system_order.Info;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrderInfo {

    private String orderNumber;

    private Long userId;

    private Long objectId;

    private Float objectPrice;

    private Long objectCout;

    private Float orderCost;

    private String orderInfo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;

    private String orderTrack;

    private String orderAddress;

    private String orderName;

    private String orderPhone;

    private int orderStatus;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Float getObjectPrice() {
        return objectPrice;
    }

    public void setObjectPrice(Float objectPrice) {
        this.objectPrice = objectPrice;
    }

    public Float getObjectCost() {
        return orderCost;
    }

    public void setObjectCost(Float orderCost) {
        this.orderCost = orderCost;
    }

    public Long getObjectCout() {
        return objectCout;
    }

    public void setObjectCout(Long objectCout) {
        this.objectCout = objectCout;
    }

    public Float getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Float orderCost) {
        this.orderCost = orderCost;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTrack() {
        return orderTrack;
    }

    public void setOrderTrack(String orderTrack) {
        this.orderTrack = orderTrack;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderNumber='" + orderNumber + '\'' +
                ", userId=" + userId +
                ", objectId=" + objectId +
                ", objectPrice=" + objectPrice +
                ", objectCout=" + objectCout +
                ", orderCost=" + orderCost +
                ", orderInfo='" + orderInfo + '\'' +
                ", orderTime=" + orderTime +
                ", orderTrack='" + orderTrack + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }

    public OrderInfo() {

    }
}
