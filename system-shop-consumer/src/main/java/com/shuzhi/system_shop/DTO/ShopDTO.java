package com.shuzhi.system_shop.DTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:购物车输出格式封装
 * Author: SHUZHI
 * Date: 2023/8/24
 *
 * @version 1.0
 */

public class ShopDTO {

    private int shopId;
    private int userId;
    private String objectName;
    private String objectTitle;
    private String objectImage;
    private BigDecimal objectOldPrice;
    private BigDecimal objectPrice;
    private int objectCout;
    private int shopCout;
    private BigDecimal shopPay;
    private int objectStatus;
    private Date shopTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectTitle() {
        return objectTitle;
    }

    public void setObjectTitle(String objectTitle) {
        this.objectTitle = objectTitle;
    }

    public BigDecimal getObjectOldPrice() {
        return objectOldPrice;
    }

    public void setObjectOldPrice(BigDecimal objectOldPrice) {
        this.objectOldPrice = objectOldPrice;
    }

    public int getObjectCout() {
        return objectCout;
    }

    public void setObjectCout(int objectCout) {
        this.objectCout = objectCout;
    }

    public int getShopCout() {
        return shopCout;
    }

    public void setShopCout(int shopCout) {
        this.shopCout = shopCout;
    }

    public BigDecimal getShopPay() {
        return shopPay;
    }

    public void setShopPay(BigDecimal shopPay) {
        this.shopPay = shopPay;
    }

    public int getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(int objectStatus) {
        this.objectStatus = objectStatus;
    }

    public Date getShopTime() {
        return shopTime;
    }

    public void setShopTime(Date shopTime) {
        this.shopTime = shopTime;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getObjectImage() {
        return objectImage;
    }

    public void setObjectImage(String objectImage) {
        this.objectImage = objectImage;
    }

    public BigDecimal getObjectPrice() {
        return objectPrice;
    }

    public void setObjectPrice(BigDecimal objectPrice) {
        this.objectPrice = objectPrice;
    }

    @Override
    public String toString() {
        return "ShopDTO{" +
                "shopId=" + shopId +
                ", userId=" + userId +
                ", objectName='" + objectName + '\'' +
                ", objectTitle='" + objectTitle + '\'' +
//                ", ObjectImage='" + objectImage + '\'' +
                ", objectOldPrice=" + objectOldPrice +
                ", ObjectPrice=" + objectPrice +
                ", objectCout=" + objectCout +
                ", shopCout=" + shopCout +
                ", shopPay=" + shopPay +
                ", objectStatus=" + objectStatus +
                ", shopTime=" + shopTime +
                '}';
    }
}
