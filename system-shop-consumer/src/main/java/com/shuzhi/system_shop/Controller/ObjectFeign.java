package com.shuzhi.system_shop.Controller;

import com.shuzhi.system_shop.Entity.ObjectEntity;
import com.shuzhi.system_shop.Entity.ObjectWithBussVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Author: SHUZHI
 * Date: 2024/2/14
 *
 * @version 1.0
 */
@FeignClient(value = "system-object")
public interface ObjectFeign {

    /**
     * 根据商品id查询商品
     * @return
     */
    @GetMapping("/Object/Feign/find/id")
    ObjectWithBussVO findObject(@RequestParam("objectId") Long objectId);

    /**
     * 根据用户Id获取所有商品信息
     * @param bussId
     * @return
     */
    @GetMapping("/Object/Feign/get/objectId/list")
    List<ObjectEntity> findObjectIdList(@RequestParam("bussId") Long bussId);

}
