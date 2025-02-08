package com.shuzhi.system_order.DTO;

import java.util.Date;

/**
 * Description:购物车输出格式封装
 * Author: SHUZHI
 * Date: 2023/8/24
 *
 * @version 1.0
 */

public class OrderDTO {

    private Long shopId;
    private Long userId;
    private Long objectId;
    private Long shopCout;

    private Date shopTime;

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

    public Long getShopCout() {
        return shopCout;
    }

    public void setShopCout(Long shopCout) {
        this.shopCout = shopCout;
    }

    public Date getShopTime() {
        return shopTime;
    }

    public void setShopTime(Date shopTime) {
        this.shopTime = shopTime;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "shopId=" + shopId +
                ", userId=" + userId +
                ", objectId=" + objectId +
                ", shopCout=" + shopCout +
                ", shopTime=" + shopTime +
                '}';
    }
}
