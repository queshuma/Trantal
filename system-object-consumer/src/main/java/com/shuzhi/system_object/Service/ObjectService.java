package com.shuzhi.system_object.Service;

import com.github.pagehelper.PageHelper;
import com.shuzhi.system_object.Entity.ObjectEntity;
import com.shuzhi.system_object.Info.ObjectInfo;
import com.shuzhi.system_object.Mapper.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
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

        int b = 0;
        objectInfo.setObjectTime(new Date());
        objectInfo.setObjectStatus((long) Common.ONE);

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

        if (b == SERVICE_UPDATE_OBJECT_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有商品
     * @parm null
     * @return List<UserEntity>
     */
    @Transactional
    public List<ObjectEntity> getAllObject(int pageNum, int pageSize) {

        List<ObjectEntity> objectEntityList = null;
        logger.info("OBJECT SERVICE SELECT ALL OBJECT START");

        try {
            PageHelper.startPage(pageNum, pageSize);
            objectEntityList = objectMapper.getAllObject();
            logger.info("OBJECT SERVICE SELECT ALL OBJECT SUCCESS!");
            logger.info("result: " + objectEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT AOBJECT ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectEntityList.toString());
        }

        return objectEntityList;
    }

    /**
     * 根据分类查询商品信息
     * @param ClassesId
     * @return ResponseResult<List<ObjectEntity>>
     */
    @Transactional
    public List<ObjectEntity> getObjectClasses(Long ClassesId, int pageNum, int pageSize) {
        logger.info("-------TRANTAL OBJECT SELECT SERVICE START-------");
        List<ObjectEntity> objectEntityList = null;
        logger.info("OBJECT SERVICE SELECT OBJECT CLASSES START");

        try {
            //设置分类查询
            PageHelper.startPage(pageNum, pageSize);
            objectEntityList = objectMapper.getObjectClasses(ClassesId);
            logger.info("OBJECT SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objectEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectEntityList.toString());
        }


        return objectEntityList;
    }

    /**
     * 根据添加用户查询产品信息
     * @param userId
     * @return ResponseResult<List<ObjectEntity>>
     */
    @Transactional
    public List<ObjectEntity> getObjectUserId(Long userId, int pageNum, int pageSize) {

        List<ObjectEntity> objectEntityList = null;
        logger.info("OBJECT SERVICE SELECT OBJECT CLASSES START");

        try {
            PageHelper.startPage(pageNum, pageSize);
            objectEntityList = objectMapper.getObjectUserId(userId);
            System.out.println(objectEntityList.toString());
            logger.info("OBJECT SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objectEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectEntityList.toString());
        }

        return objectEntityList;
    }

    /**
     * 根据商品Id查询商品
     * @param objectId
     * @return
     */
    public ObjectEntity getObject(Long objectId) {
        ObjectEntity objectEntity = null;
        logger.info("OBJECT SERVICE SELECT OBJECT START");

        try {
            objectEntity = objectMapper.getObject(objectId);
            logger.info("OBJECT SERVICE SELECT OBJECT SUCCESS!");
            logger.info("result: " + objectEntity);
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT OBJECT ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectEntity);
        }

        return objectEntity;
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
        objectEntity.setObjectCout(objectInfo.getObjectCout());
        objectEntity.setObjectPrice(objectInfo.getObjectPrice());
        objectEntity.setObjectTitle(objectInfo.getObjectTitle());
        objectEntity.setObjectId(objectInfo.getUserId());
        objectEntity.setObjectImage(objectInfo.getObjectImage());
        return objectEntity;
    }


    /**
     * 上传商品缩略图的接口
     * @param multipartFiles
     * @param uuid
     * @return
     */
    public HashMap<String, String> uploadImage(MultipartFile multipartFiles, String uuid){
        // 处理文件上传逻辑
        // 可以使用 file.getInputStream() 获取文件内容进行进一步处理
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String dirname = "/Users/shuzhi/project/Trantal/system-object-consumer/src/main/resources/static/" + uuid + "_" + "/image/";
        File d = new File(dirname);
        if (!d.exists()) {
            d.mkdirs();
        }
        int length = 25; // 设置要生成的随机字符串的长度为10
        String randomStr = RandomStringUtils.randomAlphanumeric(length);
        File targetFile = new File(dirname, randomStr + System.currentTimeMillis() + multipartFiles.getOriginalFilename().substring(multipartFiles.getOriginalFilename().indexOf('.')));

        try {
            // 使用Spring的FileCopyUtils将文件内容从MultipartFile复制到目标文件
            FileCopyUtils.copy(multipartFiles.getBytes(), new FileOutputStream(targetFile));
        }catch (IOException e) {
            logger.error("ERROR:" + e);
            return null;
        }
        hashMap.put("name", targetFile.getName());
        hashMap.put("url", "http://localhost:8898/"+ uuid + "_" + "/image/" + targetFile.getName());
        return hashMap;

    }

    /**
     * 上传海报图的接口
     * @param multipartFiles
     * @param uuid
     * @return
     */
    public HashMap<String, String> uploadBanner(MultipartFile multipartFiles, String uuid) {
        // 处理文件上传逻辑
        // 可以使用 file.getInputStream() 获取文件内容进行进一步处理
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String dirname = "/Users/shuzhi/project/Trantal/system-object-consumer/src/main/resources/static/" + uuid + "_" + "/banner/";
        File d = new File(dirname);
        if (!d.exists()) {
            d.mkdirs();
        }
        int length = 25; // 设置要生成的随机字符串的长度为10
        String randomStr = RandomStringUtils.randomAlphanumeric(length);
        File targetFile = new File(dirname, randomStr + System.currentTimeMillis() + multipartFiles.getOriginalFilename().substring(multipartFiles.getOriginalFilename().indexOf('.')));

        try {
            // 使用Spring的FileCopyUtils将文件内容从MultipartFile复制到目标文件
            FileCopyUtils.copy(multipartFiles.getBytes(), new FileOutputStream(targetFile));
        }catch (IOException e) {
            logger.error("ERROR:" + e);
            return null;
        }
        hashMap.put("name", targetFile.getName());
        hashMap.put("url", "http://localhost:8898/"+ uuid + "_" + "/banner/" + targetFile.getName());
        return hashMap;
    }

    /**
     * 上传文件详情图的接口
     * @param multipartFiles
     * @param uuid
     * @return
     */
    public HashMap<String, String> uploadImg(MultipartFile multipartFiles, String uuid) {
        // 处理文件上传逻辑
        // 可以使用 file.getInputStream() 获取文件内容进行进一步处理
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String dirname = "/Users/shuzhi/project/Trantal/system-object-consumer/src/main/resources/static/" + uuid + "_" + "/info/";
        File d = new File(dirname);
        if (!d.exists()) {
            d.mkdirs();
        }
        int length = 25; // 设置要生成的随机字符串的长度为10
        String randomStr = RandomStringUtils.randomAlphanumeric(length);
        File targetFile = new File(dirname, randomStr + System.currentTimeMillis() + multipartFiles.getOriginalFilename().substring(multipartFiles.getOriginalFilename().indexOf('.')));

        try {
            // 使用Spring的FileCopyUtils将文件内容从MultipartFile复制到目标文件
            FileCopyUtils.copy(multipartFiles.getBytes(), new FileOutputStream(targetFile));
        }catch (IOException e) {
            logger.error("ERROR:" + e);
            return null;
        }
        hashMap.put("name", targetFile.getName());
        hashMap.put("url", "http://localhost:8898/"+ uuid + "_" + "/info/" + targetFile.getName());
        return hashMap;
    }

    /**
     * 判断是否商品是否有库存
     * @param objectId
     * @param orderCout
     */
    public Boolean hasObject(Long objectId, Long orderCout) {
        ObjectEntity objectEntity= objectMapper.getObject(objectId);
        return (objectEntity.getObjectCout() - orderCout) >= 0?true:false;
    }

    /**
     * 获取数据表的数据数据总数
     * @return
     */
    public int getObjectCout() {
        return objectMapper.getDataCout();
    }

    /**
     * 根据Title信息查找商品
     * @param objectTitle
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<ObjectEntity> getObjectTitle(String objectTitle, int pageNum, int pageSize) {
        List<ObjectEntity> objectEntityList = null;
        logger.info("OBJECT SERVICE SELECT OBJECT CLASSES START");

        try {
            System.out.println(objectTitle);
            objectEntityList = objectMapper.getObjectByTitle(objectTitle);
            logger.info("OBJECT SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objectEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectEntityList.toString());
        }

        return objectEntityList;
    }

    /**
     * 根据商家Id查询所有商品
     * @param bussId
     * @return
     */
    public List<ObjectEntity> getObjectId(Long bussId) {
        List<ObjectEntity> objectEntityList = null;
        logger.info("OBJECT SERVICE SELECT OBJECT CLASSES START");

        try {
            objectEntityList = objectMapper.getObjectId(bussId);
            logger.info("OBJECT SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objectEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + objectEntityList.toString());
        }

        return objectEntityList;
    }

    /**
     * 根据商品标题获取总素
     * @param objectTitle
     * @return
     */
    public int getObjectTitleCout(String objectTitle) {
        return objectMapper.getDataTitleCout(objectTitle);
    }
}
