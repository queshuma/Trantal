package com.shuzhi.system_objclasses.Mapper;

import com.shuzhi.system_objclasses.Entity.ObjClassesEntity;
import com.shuzhi.system_objclasses.Info.ObjClassesInfo;
import com.shuzhi.system_objclasses.Info.ObjClassesShow;
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
            @Result(property = "weightId", column = "weight_id"),
            @Result(property = "listStatus", column = "list_status"),
            @Result(property = "objectStatus", column = "object_Status")
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
     * @param objClassesId
     * @param status
     * @return
     */
    @Update("UPDATE object_classes SET classes_status = #{status} WHERE classes_id = #{objClassesId}")
    public int updObjectClassesStatus(Long objClassesId, int status);

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

    /**
     * 修改商品分类在列表中的显示状态
     * @param objClassesId
     * @param listStatus
     * @return
     */
    @Update("UPDATE object_classes SET list_status = #{listStatus} WHERE classes_id = #{objClassesId}")
    Boolean updateListStatus(Long objClassesId, int listStatus);

    /**
     * 修改商品分类在商品模块的显示状态
     * @param objClassesId
     * @param objectStatus
     * @return
     */
    @Update("UPDATE object_classes SET object_status = #{objectStatus} WHERE classes_id = #{objClassesId}")
    Boolean updateObjectStatus(Long objClassesId, int objectStatus);

    /**
     * 查询列表显示的商品分类
     * @param listStatus
     * @return
     */
    @Select("SELECT * FROM object_classes WHERE list_status = #{listStatus} ORDER BY weight_id ASC")
    @ResultMap("objectClassesResultMap")
    List<ObjClassesShow> getInfoListStatus(int listStatus);

    /**
     * 查询商品显示的商品分类
     * @param objectStatus
     * @return
     */
    @Select("SELECT * FROM object_classes WHERE object_status = #{objectStatus} ORDER BY weight_id ASC")
    @ResultMap("objectClassesResultMap")
    List<ObjClassesShow> getInfoObjectStatus(int objectStatus);
}
