package com.shuzhi.system.Mapper;

import com.shuzhi.entity.ShopEntity;
import com.shuzhi.system.DTO.ShopDTO;
import com.shuzhi.system.Info.ShopInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Author: SHUZHI
 * Date: 2023/8/25
 *
 * @version 1.0
 */

@Mapper
public interface ShopMapper {
    @Insert("INSERT INTO trantal_shop (user_id, object_id, object_old_price, object_price, object_count, object_oldprice, shop_time, object_status) VALUES (#{userId}, #{objectId}, #{objectOldPrice}, #{objectPrice}, #{objectCount}, #{objectCost}, #{shopTime}, #{objectStatus})")
    int addShop(ShopInfo shopInfo);

//    @Select("SELECT tshop.*,  FROM trantal_shop tshop")
    @Select("SELECT  shop.user_id, obj.object_name, obj.object_title, obj.object_oldprice,obj.object_price, obj.object_cout, shop.shop_cout, shop.shop_cout * obj.object_price as shop_pay, obj.object_status, shop.shop_time \n" +
            "FROM trantal_shop AS shop LEFT JOIN trantal_object AS obj on shop.object_id = obj.object_id \n" +
            "WHERE shop.user_id = #{userId};")
    @Results(id = "shopResultMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "objectName", column = "object_name"),
            @Result(property = "objectTitle", column = "object_title"),
            @Result(property = "objectOldPrice", column = "object_old_price"),
            @Result(property = "objectPrice", column = "object_price"),
            @Result(property = "objectCout", column = "object_cout"),
            @Result(property = "shopCout", column = "shop_cout"),
            @Result(property = "shopPay", column = "shop_pay"),
            @Result(property = "objectStatus", column = "object_status"),
            @Result(property = "shopTime", column = "shop_time")
    })
    List<ShopDTO> getShopUserId(int userId);

    /**
     * 添加订单后更新产品数据信息
     * @param shopId
     * @param objectCount
     * @return
     */
    @Update("UPDATE trantal_shop SET object_count = #{objectCount} WHERE shop_id = #{shopId}")
    Boolean updShopCount(int shopId, int objectCount);

    /**
     * 设置订单状态
     * @param shopId
     * @param objectStatus
     * @return
     */
    @Update("UPDATE trantal_shop SET object_status = #{objectStatus} WHERE shop_id = #{shopId}")
    Boolean updShopStatus(int shopId, int objectStatus);
}
