package com.shuzhi.system_objclasses.Service;

import com.shuzhi.entity.ObjClassesEntity;
import com.shuzhi.system_objclasses.Info.ObjClassesInfo;

import com.shuzhi.system_objclasses.Info.ObjClassesShow;
import com.shuzhi.system_objclasses.Mapper.ObjClassesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ObjClassesService {

    private static final int SERVICE_UPDATE_OBJECT_INFO_NUMBER = 1;
    private static final int ZERO = 0;
    private static final Long ZEROL = 0L;
    private final ObjClassesMapper objClassesMapper;
    private Logger logger = LoggerFactory.getLogger(ObjClassesService.class);

    private final int ONE = 1;

    @Autowired
    public ObjClassesService(ObjClassesMapper objClassesMapper) {
        this.objClassesMapper = objClassesMapper;
    }

    /**
     * 获取所有分类
     * @return
     */
    @Transactional
    public List<ObjClassesShow> getAllClasses() {

        int b = 0;
        List<ObjClassesEntity> objClassesEntityList = new ArrayList<>();
        List<ObjClassesShow> objClassesShowList = new ArrayList<>();

        try {
            objClassesEntityList = objClassesMapper.findAllClasses();
            HashMap<Long, Integer> classesParSon = new HashMap<>();
            int n = 0;
            for (ObjClassesEntity objClassesEntity:objClassesEntityList) {
                ObjClassesShow objClassesShow = new ObjClassesShow();
                objClassesShow.setId(objClassesEntity.getClassesId());
                objClassesShow.setLabel(objClassesEntity.getClassesName());
                classesParSon.put(objClassesEntity.getClassesId(), n);
                if (objClassesEntity.getClassesParentId() == ZERO) {
                    objClassesShowList.add(objClassesShow);
                } else {
                    System.out.println();
                    List<ObjClassesShow> ocsl = new ArrayList<>();
                    if (objClassesShowList.get(0).getChildren() == null) {
                        ocsl.add(objClassesShow);
                    } else {
                        ocsl = objClassesShowList.get(0).getChildren();
                        ocsl.add(objClassesShow);

                    }
                    objClassesShowList.get(0).setChildren(ocsl);
                }
            }
            logger.info("OBJECT CLASSES SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objClassesEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT CLASSES SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROR: " + e);
            logger.error("result: " + objClassesEntityList.toString());
        }
        logger.info("OBJECT CLASSES SERVICE FIND OBJECT CLASSES INFO END");

//        return objClassesEntityList;
        return objClassesShowList;
    }

    /**
     * 根据Id获取分类信息
     * @param classesId
     * @return
     */
    public ObjClassesEntity getClassesById(Long classesId) {
        int b = 0;
        ObjClassesEntity objClassesEntity = new ObjClassesEntity();

        try {
            objClassesEntity = objClassesMapper.findClassesById(classesId);
            logger.info("OBJECT CLASSES SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objClassesEntity);
        } catch (Exception e) {
            logger.error("OBJECT CLASSES SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROR: " + e);
            logger.error("result: " + objClassesEntity);
        }
        logger.info("OBJECT CLASSES SERVICE FIND OBJECT CLASSES INFO END");

        return objClassesEntity;
    }

    /**
     * 添加产品分类
     * @param objClassesInfo
     * @return
     */
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

        return b;
    }

    /**
     * 删除产品分类
     * @param objClassesId
     * @return
     */
    @Transactional
    public Boolean updObjectClasses(int objClassesId) {

        int b = 0;

        logger.info("OBJECT CLASSES SERVICE UPDATE OBJECT CLASSES INFO START");
        try {
            b = objClassesMapper.updObjectClassesStatus(objClassesId, ZERO);
            logger.info("OBJECT CLASSES SERVICE UPDATE OBJECT CLASSES INFO SUCCESS!");
            logger.info("result: classesId" + objClassesId + ",classesStatus " + ONE);
        } catch (Exception e) {
            logger.error("OBJECT CLASSES SERVICE UPDATE OBJECT CLASSES INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: classesId" + objClassesId + ",classesStatus " + ONE);
        }
        logger.info("OBJECT CLASSES SERVICE UPDATE OBJECTR CLASSES INFO END");
        if (b == SERVICE_UPDATE_OBJECT_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改商品分类信息
     * @param objClassesEntity
     * @return
     */
    @Transactional
    public Boolean updObjectClasses(ObjClassesEntity objClassesEntity) {
        int b = 0;

        logger.info("OBJECT CLASSES SERVICE UPDATE OBJECT CLASSES INFO START");
        try {
            b = objClassesMapper.updObjectClasses(objClassesEntity);
            logger.info("OBJECT CLASSES SERVICE UPDATE USER INFO SUCCESS!");
            logger.info("result: " + objClassesEntity);
        } catch (Exception e) {
            logger.error("OBJECT CLASSES SERVICE UPDATE OBJECT CLASSES INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: userId" + objClassesEntity);
        }
        logger.info("OBJECT CLASSES SERVICE UPDATE OBJECT CLASSES INFO END");

        if (b == SERVICE_UPDATE_OBJECT_INFO_NUMBER) {
            return true;
        }
        return false;

    }

    public ObjClassesEntity getClassesByName(String classesName) {
        int b = 0;
        ObjClassesEntity objClassesEntity = new ObjClassesEntity();

        try {
            objClassesEntity = objClassesMapper.findClassesByName(classesName);
            logger.info("OBJECT CLASSES SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + objClassesEntity);
        } catch (Exception e) {
            logger.error("OBJECT CLASSES SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROR: " + e);
            logger.error("result: " + objClassesEntity);
        }
        logger.info("OBJECT CLASSES SERVICE FIND OBJECT CLASSES INFO END");

        return objClassesEntity;
    }
}
