package com.shuzhi.system.Service;

import com.shuzhi.entity.ObjectEntity;
import com.shuzhi.result.common;
import com.shuzhi.system.Info.ObjectInfo;
import com.shuzhi.system.Mapper.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户服务模块
 */
@Service
public class ObjectService {

    private final ObjectMapper objectMapper;
    final Logger logger = LoggerFactory.getLogger(ObjectService.class);

    final int SERVICE_ADD_OBJECT_INFO_NUMBER = 1;
    final int SERVICE_UPDATE_OBJECT_INFO_NUMBER = 1;

    @Autowired
    public ObjectService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 添加产品
     * @param objectInfo
     * @return
     */
    @Transactional
    public Boolean addObject(ObjectInfo objectInfo) {

        logger.info("-------TRANTAL OBJECT ADD SERVICE START-------");
        int b = 0;
        objectInfo.setObjectTime(new Date());
        objectInfo.setObjectStatus((long) common.ONE);

        logger.info("OBJECT SERVICE ADD OBJECT PHONE START");
        try {
            b = objectMapper.addObject(objectInfo);
            logger.info("OBJECT SERVICE ADD OBJECT INFO SUCCESS!");
            logger.info("result: " + objectInfo);
        } catch (Exception e) {
            logger.error("OBJECT SERVICE ADD OBJECT INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectInfo);
        }
        logger.info("OBJECT SERVICE ADD OBJECT INFO END");
        if (b == SERVICE_ADD_OBJECT_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改产品信息
     * @param objectEntity
     * @return
     */
    @Transactional
    public Boolean updObject(ObjectEntity objectEntity) {

        logger.info("-------TRANTAL OBJECT UPDATE SERVICE START-------");
        int b = 0;

        logger.info("OBJECT SERVICE UPDATE OBJECT INFO START");
        try {
            b = objectMapper.updObject(objectEntity);
            logger.info("OBJECT SERVICE UPDATE OBJECT INFO SUCCESS!");
            logger.info("result: " + objectEntity);
        } catch (Exception e) {
            logger.error("OBJECT SERVICE UPDATE OBJECT INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectEntity.toString());
        }
        logger.info("OBJECT SERVICE UPDATE OBJECTR INFO END");
        if (b == SERVICE_UPDATE_OBJECT_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改商品状态
     * @param objectId
     * @param objectStatus
     * @return
     */
    @Transactional
    public Boolean updObjectStatus(int objectId, int objectStatus) {
        logger.info("-------TRANTAL OBJECT UPDATE SERVICE START-------");
        int b = 0;

        logger.info("OBJECT SERVICE UPDATE OBJECT STATUS INFO START");
        try {
            b = objectMapper.updObjectStatus(objectId, objectStatus);
            logger.info("OBJECT SERVICE UPDATE OBJECT INFO SUCCESS!");
            logger.info("result: objectId" + objectId + ",objectStatus " + objectStatus);
        } catch (Exception e) {
            logger.error("OBJECT SERVICE UPDATE OBJECT INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: objectId" + objectId + ",objectStatus " + objectStatus);
        }
        logger.info("OBJECT SERVICE UPDATE USEOBJECT INFO END");
        logger.info("-------TRANTAL OBJECT UPDATE SERVICE END-------");
        if (b == SERVICE_UPDATE_OBJECT_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有用户
     * @parm null
     * @return List<UserEntity>
     */
    @Transactional
    public List<ObjectEntity> getAllObject() {
        logger.info("-------TRANTAL OBJECT SELECT SERVICE START-------");
        List<ObjectEntity> objectEntityList = null;
        logger.info("OBJECT SERVICE SELECT ALL OBJECT START");

        try {
            objectEntityList = objectMapper.getAllObject();
            logger.info("OBJECT SERVICE SELECT ALL OBJECT SUCCESS!");
            logger.info("result: " + objectEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT AOBJECT ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectEntityList.toString());
        }
        logger.info("-------TRANTAL OBJECT SELECT SERVICE END-------");
        return objectEntityList;
    }

    /**
     * 根据分类查询用户信息
     * @param ObjectClasses
     * @return ResponseResult<List<ObjectEntity>>
     */
    @Transactional
    public List<ObjectEntity> getObjectClasses(String ObjectClasses) {
        logger.info("-------TRANTAL OBJECT SELECT SERVICE START-------");
        List<ObjectEntity> objectEntityList = null;
        logger.info("OBJECT SERVICE SELECT OBJECT CLASSES START");

        try {
            objectEntityList = objectMapper.getObjectClasses(ObjectClasses);
            logger.info("OBJECT SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objectEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectEntityList.toString());
        }

        logger.info("-------TRANTAL OBJECT SELECT SERVICE END-------");
        return objectEntityList;
    }

    /**
     * 根据添加用户查询产品信息
     * @param ObjectUserAccount
     * @return ResponseResult<List<ObjectEntity>>
     */
    @Transactional
    public List<ObjectEntity> getObjectUserName(String ObjectUserAccount) {
        logger.info("-------TRANTAL OBJECT SELECT SERVICE START-------");
        List<ObjectEntity> objectEntityList = null;
        logger.info("OBJECT SERVICE SELECT OBJECT CLASSES START");

        try {
            objectEntityList = objectMapper.getObjectUserAccount(ObjectUserAccount);
            logger.info("OBJECT SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objectEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectEntityList.toString());
        }

        logger.info("-------TRANTAL OBJECT SELECT SERVICE END-------");
        return objectEntityList;
    }

    /**
     * 将ObjectInfo数据转换成ObjectEntity
     * @param objectInfo
     * @return
     */
    public ObjectEntity InfoToEntity(ObjectInfo objectInfo) {
        ObjectEntity objectEntity = null;
        objectEntity.setObjectName(objectInfo.getObjectName());
        objectEntity.setObjectInfo(objectInfo.getObjectInfo());
        objectEntity.setObjectCost(objectInfo.getObjectCost());
        objectEntity.setObjectCount(objectInfo.getObjectCount());
        objectEntity.setObjectPrice(objectInfo.getObjectPrice());
        objectEntity.setObjectTitle(objectInfo.getObjectTitle());
        objectEntity.setObjectId(objectInfo.getUserId());
        objectEntity.setObjectImage(objectInfo.getObjectImage());
        return objectEntity;
    }

}
