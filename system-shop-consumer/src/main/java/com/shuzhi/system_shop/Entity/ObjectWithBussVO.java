package com.shuzhi.system_shop.Entity;


/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/2/14
 *
 * @version 1.0
 */

public class ObjectWithBussVO extends ObjectEntity {

    public String bussAccount;
    public String classesName;

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
