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

    private int userId;
    private String objectName;
    private String objectTitle;
    private BigDecimal objectOldPrice;
    private BigDecimal ObjectPrice;
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

    public BigDecimal getObjectPrice() {
        return ObjectPrice;
    }

    public void setObjectPrice(BigDecimal objectPrice) {
        ObjectPrice = objectPrice;
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

    public ShopDTO(int userId, String objectName, String objectTitle, BigDecimal objectOldPrice, BigDecimal objectPrice, int objectCout, int shopCout, BigDecimal shopPay, int objectStatus, Date shopTime) {
        this.userId = userId;
        this.objectName = objectName;
        this.objectTitle = objectTitle;
        this.objectOldPrice = objectOldPrice;
        ObjectPrice = objectPrice;
        this.objectCout = objectCout;
        this.shopCout = shopCout;
        this.shopPay = shopPay;
        this.objectStatus = objectStatus;
        this.shopTime = shopTime;
    }
}
