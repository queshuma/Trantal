package com.shuzhi.system_order.Info;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

public class OrderInfo {
    private String order_uuid;
    private Long userId;
    private Long objectId;
    private float objectPrice;
    //
    private Long orderCout;
    //
    private float itemCost;
    private String Info;
    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyy-MM-dd HH:mm")
    private Date orderTime;
    private String orderTrack;
    private String orderAddress;
    private String orderName;
    private String orderPhone;
    private int orderStatus;

    public String getOrder_uuid() {
        return order_uuid;
    }

    public void setOrder_uuid(String order_uuid) {
        this.order_uuid = order_uuid;
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

    public float getObjectPrice() {
        return objectPrice;
    }

    public void setObjectPrice(float objectPrice) {
        this.objectPrice = objectPrice;
    }

    public Long getOrderCout() {
        return orderCout;
    }

    public void setOrderCout(Long orderCout) {
        this.orderCout = orderCout;
    }

    public float getItemCost() {
        return itemCost;
    }

    public void setItemCost(float itemCost) {
        this.itemCost = itemCost;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
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
                "userId=" + userId +
                ", objectId=" + objectId +
                ", objectPrice=" + objectPrice +
                ", orderCout=" + orderCout +
                ", objectCost=" + itemCost +
                ", objectInfo='" + Info + '\'' +
                ", orderTime=" + orderTime +
                ", orderTrack='" + orderTrack + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
