package com.shuzhi.system.Mapper;

import com.shuzhi.entity.ObjectEntity;
import com.shuzhi.system.Info.ObjectInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * User_数据库操作模块
 */
@Mapper
public interface ObjectMapper {

    /**
     * 添加商品
     * @param objectInfo
     * @return
     */
    @Insert("INSERT INTO trantal_object (object_name, " +
            "object_title, object_cost, object_price, " +
            "object_info, object_count, object_image, " +
            "object_status, object_time, object_classes, " +
            "user_id) VALUES (#{objectName}, #{objectTitle}," +
            " #{objectCost}, #{objectPrice}, #{objectInfo}," +
            " #{objectCount}, #{objectImage}, #{objectStatus}, " +
            "#{objectTime}, #{objectClasses}, #{userId})")
    int addObject(ObjectInfo objectInfo);

    /**
     * 更新商品信息
     * @param objectEntity
     * @return
     */
    @Update("UPDATE trantal_object " +
            "SET object_name = #{object_name}, " +
            "object_title = #{object_title}, " +
            "object_cost = #{object_cost}, " +
            "object_price = #{object_price}, " +
            "object_info = #{object_info}, " +
            "object_count = #{object_count}, " +
            "object_image = #{object_image}, " +
            "object_status = #{object_status}, " +
            "object_classes = #{object_classes}, " +
            "object_time = #{object_time}" +
            "WHERE object_id = #{object_id}")
    int updObject(ObjectEntity objectEntity);

    /**
     * 获取所有产品信息
     * @return
     */
    @Select("SELECT * FROM trantal_object")
    List<ObjectEntity> getAllObject();

    /**
     * 修改产品状态
     * @param ObjectId
     * @param ObjectStatus
     * @return
     */
    @Update("UPDATE trantal_object SET object_status = #{ObjectStatus} WHERE object_id = #{ObjectId}")
    int updObjectStatus(int ObjectId, int ObjectStatus);

    /**
     * 根据产品分类查找产品
     * @param ObjectClasses
     * @return
     */
    @Select("SELECT * FROM trantal_object object " +
            "JOIN object_classes classes ON object." +
            "object_classes = classes.classes_id WHERE " +
            "classes.classes_name = #{ObjectClasses}")
    List<ObjectEntity> getObjectClasses(String ObjectClasses);

    /**
     * 根据产品上架用户查找产品
     * @param ObjectUserAccount
     * @return
     */
    @Select("SELECT * FROM trantal_object object " +
            "JOIN trantal_user user ON object." +
            "user_id = user.user_id WHERE " +
            "user.user_account = #{ObjectUserAccount} ORDER BY object.object_classes")
    List<ObjectEntity> getObjectUserAccount(String ObjectUserAccount);
}
