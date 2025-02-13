package com.shuzhi.system_order.Controller;

import com.shuzhi.system_order.Entity.ObjectEntity;
import com.shuzhi.system_order.Entity.ObjectWithBussVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Author: SHUZHI
 * Date: 2024/2/18
 *
 * @version 1.0
 */
@FeignClient(value = "system-object")
public interface ObjectFeign {
    /**
     * 判断商品是否足够
     * @param objectId
     * @param orderCout
     * @return
     */
    @GetMapping("/Object/Feign/hasObject")
    Boolean hasObject(@RequestParam("objectId") Long objectId, @RequestParam("orderCout") Long orderCout);

    /**
     * 根据Id查找商品数据
     * @param objectId
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
