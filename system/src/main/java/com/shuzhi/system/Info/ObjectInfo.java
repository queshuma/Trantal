package com.shuzhi.system.Info;

import java.math.BigDecimal;
import java.util.Date;

public class ObjectInfo {

    private String orderName;
    private String objectName;
    private String objectTitle;    //原价
    private float objectCost;      //售价
    private float objectPrice;
    private String objectInfo;
    private Long objectCount;
    private String objectImage;
    private Long objectStatus;
    private Date objectTime;
    private Long objectClasses;
    private String objectBanner;
    private String objectImg;
    private Long userId;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
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

    public float getObjectCost() {
        return objectCost;
    }

    public void setObjectCost(float objectCost) {
        this.objectCost = objectCost;
    }

    public float getObjectPrice() {
        return objectPrice;
    }

    public void setObjectPrice(float objectPrice) {
        this.objectPrice = objectPrice;
    }

    public String getObjectInfo() {
        return objectInfo;
    }

    public void setObjectInfo(String objectInfo) {
        this.objectInfo = objectInfo;
    }

    public Long getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(Long objectCount) {
        this.objectCount = objectCount;
    }

    public String getObjectImage() {
        return objectImage;
    }

    public void setObjectImage(String objectImage) {
        this.objectImage = objectImage;
    }

    public Date getObjectTime() {
        return objectTime;
    }

    public void setObjectTime(Date objectTime) {
        this.objectTime = objectTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(Long objectStatus) {
        this.objectStatus = objectStatus;
    }

    public Long getObjectClasses() {
        return objectClasses;
    }

    public void setObjectClasses(Long objectClasses) {
        this.objectClasses = objectClasses;
    }

    public String getObjectBanner() {
        return objectBanner;
    }

    public void setObjectBanner(String objectBanner) {
        this.objectBanner = objectBanner;
    }

    public String getObjectImg() {
        return objectImg;
    }

    public void setObjectImg(String objectImg) {
        this.objectImg = objectImg;
    }

    public ObjectInfo() {

    }
}