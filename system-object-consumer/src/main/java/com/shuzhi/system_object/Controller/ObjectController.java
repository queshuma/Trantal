package com.shuzhi.system_object.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.common.TokenFunction;
import com.shuzhi.entity.ObjectEntity;
import com.shuzhi.objectVO.ObjectWithBussVO;
import com.shuzhi.result.code.ObjectCode;
import com.shuzhi.result.parmSetting.ObjectSetting;

import com.shuzhi.system_object.Info.ObjectInfo;
import com.shuzhi.system_object.Service.ObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static com.shuzhi.result.Common.*;


/**
 * 商品分类接口模块
 */
@RestController
@RequestMapping("/Object")
public class ObjectController {

    //依赖注入UserService服务，用于处理操作调用
    @Autowired
    private ObjectService objectService;

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private ObjClassesFeign objClassesFeign;

    //依赖注入Logger服务，用于日志输出
    private final Logger logger = LoggerFactory.getLogger(ObjectController.class);

//    @GetMapping("/all")
//    public void findByAll(Long userId) {
//        System.out.println(objClassesFeign.findById(userId).getResultMsg());
//    }

    /**
     * 上传商品图片Image
     * @param multipartFiles
     * @param uuid
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadImage")
    public ResponseResult handleFileUploadImage(@RequestParam("file") MultipartFile multipartFiles, String uuid){
        HashMap<String, String> hashMap = objectService.uploadImage(multipartFiles, uuid);
        System.out.println(multipartFiles.getOriginalFilename());
        System.out.println("地址：" + hashMap.get("url"));

        if (hashMap != null) {
            System.out.println(ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_SUCCESS, "", hashMap));
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_SUCCESS, "",hashMap.get("url"));
        } else {
            System.out.println(ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_FAIL));
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_FAIL);
        }

    }


    /**
     * 上传商品海报Banner
     * @param multipartFiles
     * @return
     */
    @PostMapping("/uploadBanner")
    public ResponseResult handleFileUploadBanner(@RequestParam("file") MultipartFile multipartFiles, String uuid){
        HashMap<String, String> hashMap = objectService.uploadBanner(multipartFiles, uuid);
        System.out.println(multipartFiles.getOriginalFilename());
        System.out.println("地址：" + hashMap.get("url"));

        if (hashMap != null) {
            System.out.println(ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_SUCCESS, "", hashMap));
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_SUCCESS, "",hashMap.get("url"));
        } else {
            System.out.println(ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_FAIL));
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_FAIL);
        }

    }

    /**
     * 上传图片详情图Img
     * @param multipartFiles
     * @param uuid
     * @return
     */
    @PostMapping("/uploadImg")
    public ResponseResult handleFileUploadImg(@RequestParam("file") MultipartFile multipartFiles, String uuid){
        HashMap<String, String> hashMap = objectService.uploadImg(multipartFiles, uuid);
        System.out.println(multipartFiles.getOriginalFilename());
        System.out.println("地址：" + hashMap.get("url"));

        if (hashMap != null) {
            System.out.println(ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_SUCCESS, "", hashMap));
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_SUCCESS, "",hashMap.get("url"));
        } else {
            System.out.println(ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_FAIL));
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_UPLOAD_IMAGE_FAIL);
        }

    }

    /**
     * 新增商品
     * @param objectInfo
     * @return ResponseResult
     */
    @PostMapping("/add")
    public ResponseResult add(ObjectInfo objectInfo) {

        Boolean b = false;

        //输入产品名称为空
        if(SystemUtils.isNullOrEmpty(objectInfo.getObjectName())) {
            logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --NAME-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_ERROR_ADD_NAME_NULL);
        }
        //输入产品名称长度越界
        if (objectInfo.getObjectName().length() < ObjectSetting.OBJECT_INFO_NAME_SIZE_MIN || objectInfo.getObjectName().length() > ObjectSetting.OBJECT_INFO_NAME_SIZE_MAX) {
            logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --NAME SIZE-- TO LONG/SHORT ! ");
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
        if(objectInfo.getObjectCout() == ZERO) {
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
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_ADD_SUCCESS);
        }

        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --ADD FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_ERROR_ADD_FAIL);

    }

    /**
     * 修改商品信息
     * @param objectEntity
     * @return ResponseResult
     */
    @PostMapping("/update")
    public ResponseResult update(ObjectEntity objectEntity) {

        Boolean b = false;

        //输入产品名称为空
        if(SystemUtils.isNullOrEmpty(objectEntity.getObjectName())) {
            logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --NAME-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_ERROR_UPDATE_FAIL_NAME_NULL);
        }
        //输入产品名称长度越界
        if (objectEntity.getObjectName().length() < ObjectSetting.OBJECT_INFO_NAME_SIZE_MIN || objectEntity.getObjectName().length() > ObjectSetting.OBJECT_INFO_NAME_SIZE_MAX) {
            logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --NAME SIZE-- TO LONG/SHORT ! ");
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
        if(objectEntity.getObjectCout() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --COUNT-- INPUT IS WARNING ! ");
        }
        //输入图片为空
//        if(SystemUtils.isNullOrEmpty(objectEntity.getObjectImage())) {
//            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --IMAGE-- INPUT IS WARNING ! ");
//        }
        //输入类别为空
        if(objectEntity.getObjectClasses() == ZERO) {
            logger.warn("TRANTAL OBJECT CONTROLLER OBJECT INFO --CLASSES-- INPUT IS WARNING ! ");
        }

        b = objectService.updObject(objectEntity);

        //更新数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_UPD_SUCCESS, objectEntity);
        }

        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_ERROR_UPDATE_FAIL   );
    }

    /**
     * 查找所有产品
     * @return ResponseResult<List<ObjectEntity>>
     */
    @GetMapping("/find/all")
    public ResponseResult findAll(int pageNum, int pageSize) {

        List<ObjectEntity> objectEntityList = null;
        List<ObjectWithBussVO> objectInfoWithUserNameList = new ArrayList<>();
        objectEntityList = objectService.getAllObject(pageNum, pageSize);
        for (ObjectEntity obj:objectEntityList) {
              ObjectWithBussVO objectInfoWithUserName =new ObjectWithBussVO();
              BeanUtils.copyProperties(obj, objectInfoWithUserName);
              objectInfoWithUserName.setBussAccount(userFeign.findById(obj.getUserId()).getUserAccount());
              objectInfoWithUserName.setClassesName(objClassesFeign.findById(obj.getObjectClasses()));
              objectInfoWithUserNameList.add(objectInfoWithUserName);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + objectEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL BJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_FIND_SUCCESS, objectInfoWithUserNameList);
    }

    /**
     * 获取所有产品的数量
     * @return ResponseResult<List<ObjectEntity>>
     */
    @GetMapping("/get/cout")
    public ResponseResult getCout() {

        int dataCout = 0;
        dataCout = objectService.getObjectCout();

        logger.info("TRANTAL ALL OBJECT INFO: " + dataCout);
        logger.info("RETURN");
        logger.info("========== TRANTAL BJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_FIND_SUCCESS, dataCout);
    }

    /**
     * 下架商品状态
     * @param objectId
     * @return
     */
    @PostMapping("/update/status")
    public ResponseResult updateObject(int objectId, int objectStatus) {
        Boolean b = false;

        b = objectService.updObjectStatus(objectId, objectStatus);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_DEL_SUCCESS);
        }
        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_DEL_FAIL);
    }

    /**
     * 根据产品分类查询商品
     * @param classesName
     * @return
     */
    @GetMapping("/find/classes")
    public ResponseResult findObjectClasses(String classesName, int pageNum, int pageSize) {
        List<ObjectEntity> objectEntityList = null;
        Long classesId = objClassesFeign.findByName(classesName);
        objectEntityList = objectService.getObjectClasses(classesId, pageNum, pageSize);

        logger.info("TRANTAL ALL OBJECT INFO: " + objectEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL OBJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_FIND_SUCCESS, classesName, objectEntityList);
    }

    /**
     * 根据商品标题信息查询商品
     * @param objectTitle
     * @return
     */
    @GetMapping("/find/title")
    public ResponseResult findObjectTitle(String objectTitle, int pageNum, int pageSize) {
        List<ObjectEntity> objectEntityList = null;
        objectEntityList = objectService.getObjectTitle(objectTitle, pageNum, pageSize);

        logger.info("TRANTAL ALL OBJECT INFO: " + objectEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL OBJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_FIND_SUCCESS, objectTitle, objectEntityList);
    }

    /**
     * 根据上架用户查询商品
     * @param
     * @return
     */
    @GetMapping("/find/userId")
    public ResponseResult findObjectUserAccount(HttpServletRequest httpServletRequest) throws ParseException {
        String objectUserAccount = null;
        List<ObjectEntity> objectEntityList = null;
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);
        objectEntityList = objectService.getObjectUserId(userId);
        List<ObjectWithBussVO> objectInfoWithUserNameList = new ArrayList<>();
        for (ObjectEntity obj:objectEntityList) {
            ObjectWithBussVO objectInfoWithUserName =new ObjectWithBussVO();
            BeanUtils.copyProperties(obj, objectInfoWithUserName);
            objectInfoWithUserName.setBussAccount(userFeign.findById(obj.getUserId()).getUserAccount());
            objectInfoWithUserName.setClassesName(objClassesFeign.findById(obj.getObjectClasses()));
            System.out.println(objectInfoWithUserName.classesName);
            objectInfoWithUserNameList.add(objectInfoWithUserName);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + objectEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL OBJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_FIND_SUCCESS, objectUserAccount, objectInfoWithUserNameList);
    }

    /**
     * 根据商品id查询商品
     * @param objectId
     * @return
     */
    @GetMapping("/find/id")
    public ResponseResult findObject(Long objectId) {

        ObjectEntity objectEntity = null;
        objectEntity = objectService.getObject(objectId);
        ObjectWithBussVO objectInfoWithUserName =new ObjectWithBussVO();
        BeanUtils.copyProperties(objectEntity, objectInfoWithUserName);
        objectInfoWithUserName.setBussAccount(userFeign.findById(objectEntity.getUserId()).getUserAccount());
        objectInfoWithUserName.setClassesName(objClassesFeign.findById(objectEntity.getObjectClasses()));

        logger.info("TRANTAL ALL OBJECT INFO: " + objectEntity);
        logger.info("RETURN");
        logger.info("========== TRANTAL OBJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_FIND_SUCCESS, String.valueOf(objectId), objectInfoWithUserName);
    }

}