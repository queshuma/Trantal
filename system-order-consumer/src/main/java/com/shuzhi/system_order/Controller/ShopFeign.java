package com.shuzhi.system_order.Controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: SHUZHI
 * Date: 2024/2/18
 *
 * @version 1.0
 */
@FeignClient(value = "system-shop")
public interface ShopFeign {
    /**
     * 删除购物车内单个商品
     * @param shopId
     * @return
     */
    @PutMapping("/removeObject")
    Boolean removeObject(@RequestParam("shopId") Long shopId);
}
