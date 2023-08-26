package com.shuzhi.system.Info;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/8/24
 *
 * @version 1.0
 */

public class ShopInfo {

    private Long shopId;
    private Long userId;
    private Long objectId;
    private Float objectOldPrice;
    private Float ObjectPrice;
    private Long objectCount;
    private Float objectCost;
    private Date shopTime;
    private Long objectStatus;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Long getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(Long objectCount) {
        this.objectCount = objectCount;
    }

    public Date getShopTime() {
        return shopTime;
    }

    public void setShopTime(Date shopTime) {
        this.shopTime = shopTime;
    }

    public Long getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(Long objectStatus) {
        this.objectStatus = objectStatus;
    }

    public Float getObjectOldPrice() {
        return objectOldPrice;
    }

    public void setObjectOldPrice(Float objectOldPrice) {
        this.objectOldPrice = objectOldPrice;
    }


    public Float getObjectPrice() {
        return ObjectPrice;
    }

    public void setObjectPrice(Float objectPrice) {
        ObjectPrice = objectPrice;
    }

    public Float getObjectCost() {
        return objectCost;
    }

    public void setObjectCost(Float objectCost) {
        this.objectCost = objectCost;
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "shopId=" + shopId +
                ", userId=" + userId +
                ", objectId=" + objectId +
                ", objectOldPrice=" + objectOldPrice +
                ", ObjectPrice=" + ObjectPrice +
                ", objectCount=" + objectCount +
                ", objectCost=" + objectCost +
                ", shopTime=" + shopTime +
                ", objectStatus=" + objectStatus +
                '}';
    }

    public ShopInfo() {

    }
}
