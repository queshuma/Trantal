package com.shuzhi.system_object.Controller;

import com.shuzhi.system_object.Entity.ObjectEntity;
import com.shuzhi.system_object.Entity.ObjectWithBussVO;
import com.shuzhi.system_object.Service.ObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/2/14
 *
 * @version 1.0
 */

//@FeignClient(value = "system-object")
@RestController
@RequestMapping("/Object/Feign")
public class ObjectFeignController {

    //依赖注入UserService服务，用于处理操作调用
    @Autowired
    private ObjectService objectService;

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private ObjClassesFeign objClassesFeign;


    //依赖注入Logger服务，用于日志输出
    private final Logger logger = LoggerFactory.getLogger(ObjectController.class);

    /**
     * 根据商品id查询商品
     * @param objectId
     * @return
     */
    @GetMapping("/find/id")
    public ObjectWithBussVO findObject(@RequestParam("objectId") Long objectId) {

        ObjectEntity objectEntity = null;
        objectEntity = objectService.getObject(objectId);
        ObjectWithBussVO objectInfoWithUserName =new ObjectWithBussVO();
        BeanUtils.copyProperties(objectEntity, objectInfoWithUserName);
        objectInfoWithUserName.setBussAccount(userFeign.findById(objectEntity.getUserId()).getUserAccount());
        objectInfoWithUserName.setClassesName(objClassesFeign.findById(objectEntity.getObjectClasses()));

        return objectInfoWithUserName;
    }

    /**
     * 查询库存
     * @param objectId
     * @param orderCout
     * @return
     */
    @GetMapping("/hasObject")
    public Boolean hasObject(@RequestParam("objectId") Long objectId, @RequestParam("orderCout") Long orderCout) {
        return objectService.hasObject(objectId, orderCout);
    }

    /**
     * 根据用户Id获取名下的所有商品Id
     * @param bussId
     * @return
     */
    @GetMapping("/get/objectId/list")
    public List<ObjectEntity> findObjectIdList(@RequestParam("bussId") Long bussId) {
        return objectService.getObjectId(bussId);
    }
}
