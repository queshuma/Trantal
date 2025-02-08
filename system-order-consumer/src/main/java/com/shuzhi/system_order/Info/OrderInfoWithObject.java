package com.shuzhi.system_order.Info;

import com.shuzhi.system_order.Entity.OrderEntity;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/2/25
 *
 * @version 1.0
 */

public class OrderInfoWithObject extends OrderEntity {
    private String objectName;
    private String objectTitle;
    private Float objectPrice;
    private String objectBanner;
    public String bussAccount;
    public String classesName;

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

    @Override
    public Float getObjectPrice() {
        return objectPrice;
    }

    @Override
    public void setObjectPrice(Float objectPrice) {
        this.objectPrice = objectPrice;
    }

    public String getObjectBanner() {
        return objectBanner;
    }

    public void setObjectBanner(String objectBanner) {
        this.objectBanner = objectBanner;
    }

    public String getBussAccount() {
        return bussAccount;
    }

    public void setBussAccount(String bussAccount) {
        this.bussAccount = bussAccount;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }
}
