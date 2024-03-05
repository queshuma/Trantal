package com.shuzhi.system_objclasses.Mapper;

import com.shuzhi.entity.ObjClassesEntity;
import com.shuzhi.system_objclasses.Info.ObjClassesInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ObjClassesMapper {

    /**
     * 查询所有商品分类
     * @return
     */
    @Select("SELECT * FROM object_classes ORDER BY weight_id ASC")
    @Results(id = "objectClassesResultMap", value = {
            @Result(property = "classesId", column = "classes_id"),
            @Result(property = "classesParentId", column = "classes_parent_id"),
            @Result(property = "classesName", column = "classes_name"),
            @Result(property = "classesStatus", column = "classes_status"),
            @Result(property = "weightId", column = "weight_id")
    })
    public List<ObjClassesEntity> findAllClasses();

    /**
     * 根据Id查询分类信息
     * @param classesId
     * @return
     */
    @Select("SELECT * FROM object_classes WHERE classes_id = #{classesId}")
    @ResultMap("objectClassesResultMap")
    public ObjClassesEntity findClassesById(Long classesId);

    /**
     * 添加产品分类
     * @param objClassesInfo
     * @return
     */
    @Insert("INSERT INTO object_classes(classes_parent_id, classes_name, weight_id) VALUES (#{classesParentId}, #{classesName}, #{weightId})")
    public Boolean addObjectClasses(ObjClassesInfo objClassesInfo);

    /**
     * 修改商品分类状态
     * @param ObjClassesId
     * @param ObjClassesStatus
     * @return
     */
    @Update("UPDATE object_classes SET classes_status = #{ObjClassesStatus} WHERE classes_id = #{ObjClassesId}")
    public int updObjectClassesStatus(Long ObjClassesId, int ObjClassesStatus);

    /**
     * 修改商品分类
     * @param objClassesEntity
     * @return
     */
    @Update("UPDATE object_classes SET classes_name = #{classesName}, weight_id = #{weightId} WHERE classes_id = #{classesId}")
    public int updObjectClasses(ObjClassesEntity objClassesEntity);

    @Select("SELECT * FROM object_classes WHERE classes_name = #{classesName}")
    @ResultMap("objectClassesResultMap")
    ObjClassesEntity findClassesByName(String classesName);
}
