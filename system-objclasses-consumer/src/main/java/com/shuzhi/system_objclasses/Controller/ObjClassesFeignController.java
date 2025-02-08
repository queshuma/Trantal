package com.shuzhi.system_objclasses.Controller;

import com.shuzhi.system_objclasses.Entity.ObjClassesEntity;
import com.shuzhi.system_objclasses.Service.ObjClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ObjClasses/Feign")
public class ObjClassesFeignController {

    private final ObjClassesService objClassesService;
    private final Logger logger = (Logger) LoggerFactory.getLogger(ObjClassesFeignController.class);

    @Autowired
    public ObjClassesFeignController(ObjClassesService objClassesService) {
        this.objClassesService = objClassesService;
    }

    /**
     * 根据Id获取分类
     * @return
     */
    @GetMapping("/findById")
    public String findById(@RequestParam("classesId") Long classesId){

        ObjClassesEntity objClassesEntity = null;
        objClassesEntity = objClassesService.getClassesById(classesId);
        System.out.println(classesId);
        return objClassesEntity.getClassesName();

    }

    @GetMapping("/findByName")
    public Long findByName(@RequestParam("classesName") String classesName){

        ObjClassesEntity objClassesEntity = null;
        objClassesEntity = objClassesService.getClassesByName(classesName);
        System.out.println(objClassesEntity);
        return objClassesEntity.getClassesId();
//        return ResponseResultFactory.buildResponseFactory(ObjClassesCode.SYSTEM_OBJECT_CLASSES_FIND_SUCCESS, objClassesEntity.getClassesName());

    }

}
