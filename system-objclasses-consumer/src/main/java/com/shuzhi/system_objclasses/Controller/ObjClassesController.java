package com.shuzhi.system_objclasses.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.entity.ObjClassesEntity;
import com.shuzhi.result.code.ResultCode;
import com.shuzhi.system_objclasses.Info.ObjClassesInfo;
import com.shuzhi.system_objclasses.Info.ObjClassesShow;
import com.shuzhi.system_objclasses.Service.ObjClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ObjClasses")
public class ObjClassesController {

    private static final int SYSTEM_OBJECT_CLASSES_DEFAULT_CLASSES_STATUS = 0;
    private final Long SYSTEM_OBJECT_CLASSES_DEFAULT_CLASSES_PARENT_ID = 0L;
    private final ObjClassesService objClassesService;
    private final Logger logger = (Logger) LoggerFactory.getLogger(ObjClassesController.class);

    @Autowired
    public ObjClassesController(ObjClassesService objClassesService) {
        this.objClassesService = objClassesService;
    }

    /**
     * 查找所有商品分类
     * @return
     */
    @GetMapping("/info/all")
    public ResponseResult findAll(){

        List<ObjClassesEntity> objectEntityList = null;
        List<ObjClassesShow> objClassesShowList = objClassesService.getAllClasses();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, objClassesShowList);

    }

    /**
     * 查询显示在列表的商品分类
     * @return
     */
    @GetMapping("/info/listStatus")
    public ResponseResult findInfoListStatus() {
        List<ObjClassesEntity> objectEntityList = null;
        List<ObjClassesShow> objClassesShowList = objClassesService.getInfoListStatus();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, objClassesShowList);
    }

    /**
     * 查询显示在商品表的商品分类
     * @return
     */
    @GetMapping("/info/objectStatus")
    public ResponseResult findInfoObjectStatus() {
        List<ObjClassesEntity> objectEntityList = null;
        List<ObjClassesShow> objClassesShowList = objClassesService.getInfoObjectStatus();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, objClassesShowList);
    }

    /**
     * 新增分类
     * @param objClassesInfo
     * @return ResponseResult
     */
    @PostMapping("/classes")
    public ResponseResult add(ObjClassesInfo objClassesInfo) {

        Boolean b = false;

        b = objClassesService.addObjectClasses(objClassesInfo);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }

        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --ADD FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);

    }

    /**
     * 修改商品分类状态
     * @param objClassesId
     * @return
     */
    @PutMapping("/status")
    public ResponseResult Status(Long objClassesId, int status) {
        Boolean b = false;
            b = objClassesService.updObjectClasses(objClassesId, status);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }
        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
    }

    /**
     * 修改分类在列表状态
     * @param objClassesId
     * @param listStatus
     * @return
     */
    @PutMapping ("/listStatus")
    public ResponseResult updateListStatus(Long objClassesId, int listStatus) {
        Boolean b = false;

        b = objClassesService.updateListStatus(objClassesId, listStatus);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }
        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
    }

    /**
     * 修改分类在商品列表状态
     * @param objClassesId
     * @param objectStatus
     * @return
     */
    @PutMapping ("/objectStatus")
    public ResponseResult updateObjecttatus(Long objClassesId, int objectStatus) {
        Boolean b = false;

        b = objClassesService.updateObjectStatus(objClassesId, objectStatus);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }
        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
    }
}
