package com.shuzhi.system_object.Controller;

import com.shuzhi.system_object.Entity.ObjectEntity;
import com.shuzhi.objectVO.ObjectWithBussVO;

import com.shuzhi.system_object.Info.ObjectInfo;
import com.shuzhi.system_object.Service.ObjectService;
import com.shuzhi.system_object.code.ResultCode;
import com.shuzhi.system_object.common.ResponseResult;
import com.shuzhi.system_object.common.ResponseResultFactory;
import com.shuzhi.system_object.common.TokenFunction;
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
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, "",hashMap.get("url"));
        } else {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
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
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, "",hashMap.get("url"));
        } else {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
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
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, "",hashMap.get("url"));
        } else {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
        }

    }

    /**
     * 新增商品
     * @param objectInfo
     * @return ResponseResult
     */
    @PostMapping("/object")
    public ResponseResult add(ObjectInfo objectInfo) {

        Boolean b = false;

        b = objectService.addObject(objectInfo);
        System.out.println(objectInfo);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }

        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --ADD FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);

    }

    /**
     * 修改商品信息
     * @param objectEntity
     * @return ResponseResult
     */
    @PutMapping("/object")
    public ResponseResult update(ObjectEntity objectEntity) {

        Boolean b = false;

        b = objectService.updObject(objectEntity);

        //更新数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, objectEntity);
        }

        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
    }

    /**
     * 查找所有产品
     * @return ResponseResult<List<ObjectEntity>>
     */
    @GetMapping("/info/all")
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
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, objectInfoWithUserNameList);
    }

    /**
     * 下架商品状态
     * @param objectId
     * @return
     */
    @PutMapping("/status")
    public ResponseResult updateObject(int objectId, int objectStatus) {
        Boolean b = false;

        b = objectService.updObjectStatus(objectId, objectStatus);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }
        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
    }

    /**
     * 根据产品分类查询商品
     * @param classesName
     * @return
     */
    @GetMapping("/info/classes")
    public ResponseResult findObjectClasses(String classesName, int pageNum, int pageSize) {
        List<ObjectEntity> objectEntityList = null;
        Long classesId = objClassesFeign.findByName(classesName);
        objectEntityList = objectService.getObjectClasses(classesId, pageNum, pageSize);

        logger.info("TRANTAL ALL OBJECT INFO: " + objectEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL OBJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, classesName, objectEntityList);
    }

    /**
     * 根据商品标题信息查询商品
     * @param objectTitle
     * @return
     */
    @GetMapping("/info/title")
    public ResponseResult findObjectTitle(String objectTitle, int pageNum, int pageSize) {
        List<ObjectEntity> objectEntityList = null;
        List<ObjectWithBussVO> objectInfoWithUserNameList = new ArrayList<>();
        objectEntityList = objectService.getObjectTitle(objectTitle, pageNum, pageSize);
        for (ObjectEntity obj:objectEntityList) {
            ObjectWithBussVO objectInfoWithUserName =new ObjectWithBussVO();
            BeanUtils.copyProperties(obj, objectInfoWithUserName);
            objectInfoWithUserName.setBussAccount(userFeign.findById(obj.getUserId()).getUserAccount());
            objectInfoWithUserName.setClassesName(objClassesFeign.findById(obj.getObjectClasses()));
            objectInfoWithUserNameList.add(objectInfoWithUserName);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + objectInfoWithUserNameList);
        logger.info("RETURN");
        logger.info("========== TRANTAL OBJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, objectTitle, objectInfoWithUserNameList);
    }

    /**
     * 根据上架用户查询商品
     * @param
     * @return
     */
    @GetMapping("/info/userId")
    public ResponseResult findObjectUserAccount(HttpServletRequest httpServletRequest, int pageNum, int pageSize) throws ParseException {
        String objectUserAccount = null;
        List<ObjectEntity> objectEntityList = null;
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);
        objectEntityList = objectService.getObjectUserId(userId, pageNum, pageSize);
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
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, objectUserAccount, objectInfoWithUserNameList);
    }

    /**
     * 根据商品id查询商品
     * @param objectId
     * @return
     */
    @GetMapping("/info/id")
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
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, String.valueOf(objectId), objectInfoWithUserName);
    }

}