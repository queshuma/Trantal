package com.shuzhi.system_objclasses.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.entity.ObjClassesEntity;
import com.shuzhi.result.code.ObjClassesCode;
import com.shuzhi.system_objclasses.Info.ObjClassesInfo;
import com.shuzhi.system_objclasses.Info.ObjClassesShow;
import com.shuzhi.system_objclasses.Service.ObjClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shuzhi.result.Common.ONE;
import static com.shuzhi.result.Common.ZERO;

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
    @GetMapping("/findAll")
    public ResponseResult findAll(){

        List<ObjClassesEntity> objectEntityList = null;
        List<ObjClassesShow> objClassesShowList = objClassesService.getAllClasses();

        return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_FIND_SUCCESS, objClassesShowList);

    }

    /**
     * 新增分类
     * @param objClassesInfo
     * @return ResponseResult
     */
    @PostMapping("/add")
    public ResponseResult add(ObjClassesInfo objClassesInfo) {

        Boolean b = false;

        //输入产品分类为空
        if(SystemUtils.isNullOrEmpty(objClassesInfo.getClassesName())) {
            logger.error("TRANTAL OBJECT CLASSES CONTROLLER OBJECT CLASSES INFO --CLASSES NAME-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_ERROR_ADD_FAIL_NAME_NULL);
        }


        b = objClassesService.addObjectClasses(objClassesInfo);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_ADD_SUCCESS);
        }

        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --ADD FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_ERROR_ADD_FAIL);

    }

    /**
     * 修改商品分类状态(删除)
     * @param objClassesId
     * @return
     */
    @PutMapping("/status")
    public ResponseResult status(Long objClassesId, int currentStatus) {
        Boolean b = false;

        if (currentStatus == ZERO) {
            b = objClassesService.updObjectClasses(objClassesId, ONE);
        } else if (currentStatus == ONE) {
            b = objClassesService.updObjectClasses(objClassesId, ZERO);
        }


        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_DEL_SUCCESS);
        }
        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_ERROR_DEL_FAIL);
    }

    /**
     * 修改商品分类信息
     * @param objClassesEntity
     * @return
     */
    @PutMapping ("/update")
    public ResponseResult info(ObjClassesEntity objClassesEntity) {
        Boolean b = false;

        b = objClassesService.updObjectClasses(objClassesEntity);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_UPD_SUCCESS);
        }
        logger.error("TRANTAL OBJECT CONTROLLER OBJECT INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_ERROR_UPD_FAIL);
    }

}
