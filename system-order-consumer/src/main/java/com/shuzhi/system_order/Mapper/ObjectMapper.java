package com.shuzhi.system_order.Mapper;

import com.shuzhi.entity.ObjectEntity;

import com.shuzhi.system_object.Info.ObjectInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * User_数据库操作模块
 */
@Mapper
public interface ObjectMapper {

    /**
     * 获取所有产品信息
     * @return
     */
    @Select("SELECT * FROM trantal_object")
    @Results(id = "objectResultMap", value = {
            @Result(property = "objectId", column = "object_id"),
            @Result(property = "objectName", column = "object_name"),
            @Result(property = "objectTitle", column = "object_title"),
            @Result(property = "objectCost", column = "object_oldprice"),
            @Result(property = "objectPrice", column = "object_price"),
            @Result(property = "objectInfo", column = "object_info"),
            @Result(property = "objectCount", column = "object_count"),
            @Result(property = "objectImage", column = "object_image"),
            @Result(property = "objectStatus", column = "object_status"),
            @Result(property = "objectTime", column = "object_time"),
            @Result(property = "objectClasses", column = "object_classes"),
            @Result(property = "userId", column = "user_id")
    })
    List<ObjectEntity> getAllObject();

    /**
     * 添加商品
     * @param objectInfo
     * @return
     */
    @Insert("INSERT INTO trantal_object (object_name, " +
            "object_title, object_oldprice, object_price, " +
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
            "SET object_name = #{objectName}, " +
            "object_title = #{objectTitle}, " +
            "object_oldprice = #{objectCost}, " +
            "object_price = #{objectPrice}, " +
            "object_info = #{objectInfo}, " +
            "object_count = #{objectCount}, " +
            "object_image = #{objectImage}, " +
            "object_status = #{objectStatus}, " +
            "object_classes = #{objectClasses}, " +
            "object_time = #{objectTime}" +
            "WHERE object_id = #{objectId}")
    int updObject(ObjectEntity objectEntity);

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
    @ResultMap("objectResultMap")
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
    @ResultMap("objectResultMap")
    List<ObjectEntity> getObjectUserAccount(String ObjectUserAccount);

    /**
     * 提交订单，修改产品的总数
     * @param ObjectId
     * @param ObjectCout
     * @return
     */
    @Update("UPDATE trantal_object SET object_cout = object_cout - #{ObjectCout} WHERE object_id = #{ObjectId};")
    int updObjectReduce(long ObjectId, long ObjectCout);

    /**
     * 根据产品id获取产品库存
     * @param ObjectId
     * @return
     */
    @Select("SELECT object_cout FROM trantal_object WHERE object_id = #{ObjectId}")
    int getObjectCout(long ObjectId);

    /**
     * 取消订单，修改商品的总数
     * @param orderId
     */
    @Update("UPDATE trantal_object AS obj\n" +
            "JOIN trantal_order AS ord ON obj.object_id = ord.object_id\n" +
            "SET obj.object_cout = obj.object_cout + ord.object_cout\n" +
            "WHERE ord.order_id = #{orderId};")
    void updObjectAdd(int orderId);
}
