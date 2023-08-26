package com.shuzhi.system.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.entity.ObjectEntity;
import com.shuzhi.result.code.ObjectCode;
import com.shuzhi.result.parmSetting.ObjectSetting;
import com.shuzhi.system.Info.ObjectInfo;
import com.shuzhi.system.Service.ObjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.shuzhi.result.common.ZERO;


/**
 * 商品分类接口模块
 */
@Api("商品分类接口模块")
@RestController
@RequestMapping("/Object")
public class ObjectController {

    //依赖注入UserService服务，用于处理操作调用
    private final ObjectService objectService;
    //依赖注入Logger服务，用于日志输出
    private final Logger logger = LoggerFactory.getLogger(ObjectController.class);

    @Autowired
    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    /**
     * 新增商品
     * @param objectInfo
     * @return ResponseResult
     */
    @ApiOperation("新增商品")
    @PostMapping("/add")
    public ResponseResult add(ObjectInfo objectInfo) {

        logger.info("========== TRANTAL OBJECT CONTROLLER ADD OBJECT INFO START! ==========");
        Boolean b = false;

        //输入产品名称为空
        if(SystemUtils.isNullOrEmpty(objectInfo.getObjectName())) {
            logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --NAME-- INPUT IS NULL ! ");
            outWorkInfomation("ADD OBJECT", objectInfo);
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_ERROR_ADD_NAME_NULL);
        }
        //输入产品名称长度越界
        if (objectInfo.getObjectName().length() < ObjectSetting.OBJECT_INFO_NAME_SIZE_MIN || objectInfo.getObjectName().length() > ObjectSetting.OBJECT_INFO_NAME_SIZE_MAX) {
            logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --NAME SIZE-- TO LONG/SHORT ! ");
            outWorkInfomation("ADD OBJECT", objectInfo);
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_ERROR_ADD_NAME_SIZE);
        }
        //输入原价为空(警告)
        if (objectInfo.getObjectCost() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --COST-- INPUT IS WARNING ! ");
        }
        //输入售价为空(警告)
        if(objectInfo.getObjectPrice() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --PRICE-- INPUT IS WARNING ! ");
        }
        //输入详情为空(警告)
        if(SystemUtils.isNullOrEmpty(objectInfo.getObjectInfo())) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --INFO-- INPUT IS WARNING ! ");
        }
        //输入数量为空(警告)
        if(objectInfo.getObjectCount() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --EMAIL-- INPUT IS WARNING ! ");
        }
        //输入图片为空
        if(SystemUtils.isNullOrEmpty(objectInfo.getObjectImage())) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --IMAGE-- INPUT IS WARNING ! ");
        }
        //输入类别为空
        if(objectInfo.getObjectClasses() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --CLASSES-- INPUT IS WARNING ! ");
        }
        //输入用户id为空
        if(objectInfo.getUserId() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --USER ID-- INPUT IS WARNING ! ");
        }

        b = objectService.addObject(objectInfo);
        System.out.println(objectInfo);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            outWorkInfomation("ADD", objectInfo);
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_ADD_SUCCESS);
        }

        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --ADD FAIL-- ! ");
        outWorkInfomation("ADD", objectInfo);
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_ERROR_ADD_FAIL);

    }

    /**
     * 修改商品信息
     * @param objectEntity
     * @return ResponseResult
     */
    @ApiOperation("修改商品信息")
    @PostMapping("/update")
    public ResponseResult update(ObjectEntity objectEntity) {

        logger.info("========== TRANTAL OBJECT CONTROLLER UPDATE OBJECT INFO START! ==========");
        Boolean b = false;

        //输入产品名称为空
        if(SystemUtils.isNullOrEmpty(objectEntity.getObjectName())) {
            logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --NAME-- INPUT IS NULL ! ");
            outWorkInfomation("UPDATE OBJECT", objectEntity);
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_ERROR_UPDATE_FAIL_NAME_NULL);
        }
        //输入产品名称长度越界
        if (objectEntity.getObjectName().length() < ObjectSetting.OBJECT_INFO_NAME_SIZE_MIN || objectEntity.getObjectName().length() > ObjectSetting.OBJECT_INFO_NAME_SIZE_MAX) {
            logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --NAME SIZE-- TO LONG/SHORT ! ");
            outWorkInfomation("UPDATE OBJECT", objectEntity);
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_ERROR_UPDATE_FAIL_NAME_SIZE);
        }
        //输入原价为空(警告)
        if (objectEntity.getObjectCost() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --COST-- INPUT IS WARNING ! ");
        }
        //输入售价为空(警告)
        if(objectEntity.getObjectPrice() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --PRICE-- INPUT IS WARNING ! ");
        }
        //输入详情为空(警告)
        if(SystemUtils.isNullOrEmpty(objectEntity.getObjectInfo())) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --INFO-- INPUT IS WARNING ! ");
        }
        //输入数量为空(警告)
        if(objectEntity.getObjectCount() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --COUNT-- INPUT IS WARNING ! ");
        }
        //输入图片为空
        if(SystemUtils.isNullOrEmpty(objectEntity.getObjectImage())) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --IMAGE-- INPUT IS WARNING ! ");
        }
        //输入类别为空
        if(objectEntity.getObjectClasses() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --CLASSES-- INPUT IS WARNING ! ");
        }

        b = objectService.updObject(objectEntity);

        //更新数据正反馈，向前端返回正确码
        if (b) {
            outWorkInfomation("UPDATE", objectEntity);
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_UPD_SUCCESS, objectEntity);
        }

        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE FAIL-- ! ");
        outWorkInfomation("UPDATE", objectEntity);
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_ERROR_UPDATE_FAIL   );
    }

    /**
     * 查找所有产品
     * @return ResponseResult<List<ObjectEntity>>
     */
    @ApiOperation("查找所有产品")
    @GetMapping("/find/all")
    public ResponseResult findAll() {

        logger.info("========== TRANTAL OBJECT CONTROLLER SELECT ALL OBJECT START ! ==========");

        List<ObjectEntity> objectEntityList = null;
        objectEntityList = objectService.getAllObject();

        logger.info("TRANTAL ALL OBJECT INFO: " + objectEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL BJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_FIND_SUCCESS, objectEntityList);
    }

    /**
     * 修改商品状态(删除)
     * @param objectId
     * @return
     */
    @ApiOperation("修改商品状态(删除)")
    @PostMapping("/update/delete")
    public ResponseResult updateDelete(int objectId) {
        logger.info("========== TRANTAL OBJECT CONTROLLER UPDATE OBJECT STATUS INFO START! ==========");
        Boolean b = false;

        b = objectService.updObjectStatus(objectId, ZERO);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            outWorkInfomation("UPDATE", null);
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_DEL_SUCCESS);
        }
        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE STATUS FAIL-- ! ");
        outWorkInfomation("UPDATE", null);
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_DEL_FAIL);
    }

    /**
     * 根据产品分类查询商品
     * @param objectClasses
     * @return
     */
    @PostMapping("/find/classes")
    public ResponseResult findObjectClasses(String objectClasses) {
        logger.info("========== TRANTAL OBJECT CONTROLLER SELECT ALL OBJECT START ! ==========");

        List<ObjectEntity> objectEntityList = null;
        objectEntityList = objectService.getObjectClasses(objectClasses);

        logger.info("TRANTAL ALL OBJECT INFO: " + objectEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL OBJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_FIND_SUCCESS, objectClasses, objectEntityList);
    }

    /**
     * 根据上架用户查询商品
     * @param objectUserAccount
     * @return
     */
    @PostMapping("/find/userAccount")
    public ResponseResult findObjectUserAccount(String objectUserAccount) {
        logger.info("========== TRANTAL USER CONTROLLER SELECT ALL OBJECT START ! ==========");

        List<ObjectEntity> objectEntityList = null;
        objectEntityList = objectService.getObjectUserName(objectUserAccount);

        logger.info("TRANTAL ALL OBJECT INFO: " + objectEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL OBJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_FIND_SUCCESS, objectUserAccount, objectEntityList);
    }


    //
    //商品接口层功能模块
    //
    /**
     * 抽离结尾输出代码
     * @param toDo
     * @param t
     */
    public <T> void outWorkInfomation(String toDo, T t) {
        logger.info("TRANTAL ALL OBJECT INFO: " + t);
        logger.info("RETURN");
        logger.info("========== TRANTAL OBJECT CONTROLLER " + toDo + " OBJECT INFO END! ==========");
    }
}