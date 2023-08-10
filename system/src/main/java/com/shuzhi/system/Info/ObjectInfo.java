package com.shuzhi.system.Info;

import java.math.BigDecimal;
import java.util.Date;

public class ObjectInfo {

    private String objectName;
    private String objectTitle;    //原价
    private float objectCost;      //售价
    private float objectPrice;
    private String objectInfo;
    private int objectCount;
    private String objectImage;
    private int objectStatus;
    private Date objectTime;
    private int objectClasses;
    private int userId;

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

    public int getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(int objectCount) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(int objectStatus) {
        this.objectStatus = objectStatus;
    }

    public int getObjectClasses() {
        return objectClasses;
    }

    public void setObjectClasses(int objectClasses) {
        this.objectClasses = objectClasses;
    }

    public ObjectInfo() {

    }
}