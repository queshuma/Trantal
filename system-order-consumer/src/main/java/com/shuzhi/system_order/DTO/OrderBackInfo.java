package com.shuzhi.system_order.DTO;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/3/2
 *
 * @version 1.0
 */

public class OrderBackInfo {
    private Long orderCout = 0L;
    private Long orderGather = 0L;

    public Long getOrderCout() {
        return orderCout;
    }

    public void setOrderCout(Long orderCout) {
        this.orderCout = orderCout;
    }

    public Long getOrderGather() {
        return orderGather;
    }

    public void setOrderGather(Long orderGather) {
        this.orderGather = orderGather;
    }
}
