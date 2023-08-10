package com.shuzhi.system.Service;

import com.shuzhi.entity.ObjClassesEntity;
import com.shuzhi.system.Info.ObjClassesInfo;
import com.shuzhi.system.Mapper.ObjClassesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjClassesService {

    private final ObjClassesMapper objClassesMapper;
    private Logger logger = LoggerFactory.getLogger(ObjClassesService.class);

    @Autowired
    public ObjClassesService(ObjClassesMapper objClassesMapper) {
        this.objClassesMapper = objClassesMapper;
    }

    /**
     * 获取所有分类
     * @return
     */
    @Transactional
    public List<ObjClassesEntity> getAllClasses() {

        logger.info("-------TRANTAL OBJECT CLASSES SELECT SERVICE START-------");
        int b = 0;
        List<ObjClassesEntity> objClassesEntityList = new ArrayList<>();

        logger.info("OBJECT CLASSES SERVICE FIND OBJECT CLASSES INFO START");
        try {
            objClassesEntityList = objClassesMapper.findAllClasses();
            logger.info("OBJECT CLASSES SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objClassesEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT CLASSES SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROR: " + e);
            logger.error("result: " + objClassesEntityList.toString());
        }
        logger.info("OBJECT CLASSES SERVICE FIND OBJECT CLASSES INFO END");

        logger.info("-------TRANTAL OBJECT CLASSES SELECT SERVICE END-------");
        return objClassesEntityList;

    }

    @Transactional
    public Boolean addObjectClasses(ObjClassesInfo objClassesInfo) {
        Boolean b = false;
        try {
            b = objClassesMapper.addObjectClasses(objClassesInfo);
            logger.info("OBJECT CLASSES SERVICE ADD OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objClassesInfo.toString());
        } catch (Exception e) {
            logger.error("OBJECT CLASSES SERVICE ADD OBJECT CLASSES ERROR!");
            logger.error("ERROR: " + e);
            logger.error("result: " + objClassesInfo.toString());
        }
        logger.info("OBJECT CLASSES SERVICE FIND OBJECT CLASSES INFO END");

        logger.info("-------TRANTAL OBJECT CLASSES SELECT SERVICE END-------");
        return b;
    }

}
