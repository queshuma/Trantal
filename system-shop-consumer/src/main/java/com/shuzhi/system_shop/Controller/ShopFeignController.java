package com.shuzhi.system_shop.Controller;

import com.shuzhi.system_shop.Service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ShopFeignController {

    private final ShopService shopService;
    @Autowired
    private ObjectFeign objectFeign;
    private final Logger logger = LoggerFactory.getLogger(ShopFeignController.class);
    private final Long LONG_ZERO = 0L;
    private final int ZERO = 0;
    private final Float FLOAT_ZERO = Float.valueOf(0);

    public ShopFeignController(ShopService shopService) {
        this.shopService = shopService;
    }

    /**
     * 删除购物车内单个商品
     * @param shopId
     * @return
     */
    @PutMapping("/Shop/Feign/removeObject")
    public Boolean removeObject(@RequestParam("shopId") Long shopId) {
        Boolean b = false;
        Long objectStatus = 2L;
        b = shopService.updShopStatus(shopId, objectStatus);
        return b;
    }

}
