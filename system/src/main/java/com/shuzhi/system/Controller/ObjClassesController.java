package com.shuzhi.system.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.entity.ObjClassesEntity;
import com.shuzhi.entity.ObjectEntity;
import com.shuzhi.result.code.ObjClassesCode;
import com.shuzhi.result.code.ObjectCode;
import com.shuzhi.result.code.SystemCode;
import com.shuzhi.result.parmSetter.ObjectSetting;
import com.shuzhi.system.Info.ObjClassesInfo;
import com.shuzhi.system.Info.ObjectInfo;
import com.shuzhi.system.Service.ObjClassesService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import static com.shuzhi.result.common.ZERO;

@RestController
@RequestMapping("/Object/Classes")
public class ObjClassesController {

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
    public ResponseResult<List<ObjClassesInfo>> findAll(){

        logger.info("========== TRANTAL OBJECT CLASSES CONTROLLER SELECT ALL OBJECT CLASSES START ! ==========");

        List<ObjClassesEntity> objectEntityList = null;
        objectEntityList = objClassesService.getAllClasses();

        outWorkInfomation("FIND", objectEntityList);
        return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_FIND_SUCCESS, objectEntityList);

    }

    /**
     * 新增分类
     * @param objClassesInfo
     * @return ResponseResult
     */
    @PostMapping("/add")
    public ResponseResult add(ObjClassesInfo objClassesInfo) {

        logger.info("========== TRANTAL OBJEC CLASSEST CONTROLLER ADD PROJECT CLASSES INFO START! ==========");
        Boolean b = false;

        //输入产品名称为空
        if(SystemUtils.isNullOrEmpty(objClassesInfo.getClassesName())) {
            logger.error("TRANTAL OBJECT CLASSES CONTROLLER OBJECT CLASSES INFO --CLASSES NAME-- INPUT IS NULL ! ");
            outWorkInfomation("ADD OBJECT CLASSES", objClassesInfo);
            return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_ERROR_ADD_FAIL_NAME_NULL);
        }
        //输入上级名称为空
        if(objClassesInfo.getClassesParentId() == ZERO) {
            logger.warn("TRANTAL OBJECT CLASSES CONTROLLER OBJECT CLASSES INFO IS --ROOT DIRECTORY-- ! ");
        }


        b = objClassesService.addObjectClasses(objClassesInfo);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            outWorkInfomation("ADD", objClassesInfo);
            return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_ADD_SUCCESS);
        }

        logger.error("TRANTAL PROJECT CONTROLLER PROJECT INFO --ADD FAIL-- ! ");
        outWorkInfomation("ADD", objClassesService);
        return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_ERROR_ADD_FAIL);

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
        logger.info("TRANTAL ALL PROJECT INFO: " + t);
        logger.info("RETURN");
        logger.info("========== TRANTAL PROJECT CLASSES CONTROLLER " + toDo + " PROJECT CLASSES INFO END! ==========");
    }
}
