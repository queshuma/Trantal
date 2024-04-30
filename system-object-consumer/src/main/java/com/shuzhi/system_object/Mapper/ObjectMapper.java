package com.shuzhi.system_object.Mapper;

import com.shuzhi.system_object.Entity.ObjectEntity;
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
            @Result(property = "objectUUID", column = "object_uuid"),
            @Result(property = "objectName", column = "object_name"),
            @Result(property = "objectTitle", column = "object_title"),
            @Result(property = "objectCost", column = "object_oldprice"),
            @Result(property = "objectPrice", column = "object_price"),
            @Result(property = "objectInfo", column = "object_info"),
            @Result(property = "objectCout", column = "object_cout"),
            @Result(property = "objectImage", column = "object_image"),
            @Result(property = "objectStatus", column = "object_status"),
            @Result(property = "objectTime", column = "object_time"),
            @Result(property = "objectClasses", column = "object_classes"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "objectBanner", column = "object_banner"),
            @Result(property = "objectImg", column = "object_img"),
    })
    List<ObjectEntity> getAllObject();

    /**
     * 添加商品
     * @param objectInfo
     * @return
     */
    @Insert("INSERT INTO trantal_object (object_name, " +
            "object_title, object_oldprice, object_price, " +
            "object_info, object_cout, object_image, object_banner, object_img, " +
            "object_status, object_time, object_classes, " +
            "user_id, object_uuid) VALUES (#{objectName}, #{objectTitle}," +
            " #{objectCost}, #{objectPrice}, #{objectInfo}," +
            " #{objectCout}, #{objectImage}, #{objectBanner}, #{objectImg}, #{objectStatus}, " +
            "#{objectTime}, #{objectClasses}, #{userId}, #{objectUUID})")
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
            "object_cout = #{objectCout}, " +
            "object_image = #{objectImage}, " +
            "object_img = #{objectImg}, " +
            "object_banner = #{objectBanner}, " +
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
     * @param ClassesId
     * @return
     */
    @Select("SELECT * FROM trantal_object WHERE object_classes = #{ClassesId} ")
    @ResultMap("objectResultMap")
    List<ObjectEntity> getObjectClasses(Long ClassesId);

    /**
     * 根据产品上架用户查找产品
     * @param userId
     * @return
     */
    @Select("SELECT * FROM trantal_object WHERE user_id = #{userId}")
    @ResultMap("objectResultMap")
    List<ObjectEntity> getObjectUserId(Long userId);

    /**
     * 提交订单，修改产品的总数
     * @param ObjectId
     * @param ObjectCout
     * @return
     */
    @Update("UPDATE trantal_object SET object_cout = object_cout - #{ObjectCout} WHERE object_id = #{ObjectId};")
    int updObjectReduce(long ObjectId, long ObjectCout);

    /**
     * 获取商品总数
     * @return
     */
    @Select("SELECT COUNT(object_id) FROM trantal_object")
    int getDataCout();

    /**
     * 取消订单，修改商品的总数
     * @param orderId
     */
    @Update("UPDATE trantal_object AS obj\n" +
            "JOIN trantal_order AS ord ON obj.object_id = ord.object_id\n" +
            "SET obj.object_cout = obj.object_cout + ord.object_cout\n" +
            "WHERE ord.order_id = #{orderId};")
    void updObjectAdd(int orderId);

    /**
     * 根据Id获取商品数据
     * @param objectId
     * @return
     */
    @Select("SELECT * FROM trantal_object WHERE object_id = #{objectId}")
    @ResultMap("objectResultMap")
    ObjectEntity getObject(Long objectId);

    /**
     * 根据Title模糊查找商品
     * @param objectTitle
     * @return
     */
    @Select("SELECT * FROM trantal_object WHERE object_title LIKE CONCAT('%',#{objectTitle},'%')")
    @ResultMap("objectResultMap")
    List<ObjectEntity> getObjectByTitle(String objectTitle);

    /**
     * 根据用户Id获取旗下的商品
     * @param bussId
     * @return
     */
    @Select("SELECT * FROM trantal_object WHERE user_id = #{bussId}")
    @ResultMap("objectResultMap")
    List<ObjectEntity> getObjectId(Long bussId);

    /**
     * 根据商品title获取商品总数
     * @param objectTitle
     * @return
     */
    @Select("SELECT COUNT(object_id) FROM trantal_object WHERE object_title LIKE CONCAT('%',#{objectTitle},'%')")
    int getDataTitleCout(String objectTitle);
}
