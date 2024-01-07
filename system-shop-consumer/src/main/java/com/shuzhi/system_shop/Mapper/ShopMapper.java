package com.shuzhi.system_shop.Mapper;

import com.shuzhi.entity.ShopEntity;
import com.shuzhi.system_shop.DTO.ShopDTO;
import com.shuzhi.system_shop.Info.ShopInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
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
    @Insert("INSERT INTO trantal_shop (user_id, object_id, shop_cout, shop_time) VALUES (#{userId}, #{objectId}, #{shopCout},#{shopTime})")
    int addShop(int userId, int objectId, int shopCout, Date shopTime);

    //    @Select("SELECT tshop.*,  FROM trantal_shop tshop")
    @Select("SELECT shop.shop_id, shop.user_id, obj.object_name, obj.object_title, obj.object_image, obj.object_oldprice,obj.object_price, obj.object_cout, shop.shop_cout, shop.shop_cout * obj.object_price as shop_pay, obj.object_status, shop.shop_time \n" +
            "FROM trantal_shop AS shop LEFT JOIN trantal_object AS obj on shop.object_id = obj.object_id \n" +
            "WHERE shop.user_id = #{userId} AND shop.shop_cout > 0;")
    @Results(id = "shopResultMap", value = {
            @Result(property = "shopId", column = "shop_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "objectName", column = "object_name"),
            @Result(property = "objectTitle", column = "object_title"),
            @Result(property = "objectImage", column = "object_image"),
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
     * @param userId
     * @param objectId
     * @param shopTime
     * @return
     */
    @Update("UPDATE trantal_shop SET shop_cout = #{shopCout} + shop_cout, shop_time = #{shopTime} WHERE user_id = #{userId} AND object_id = #{objectId}")
    Boolean updShopCoutAddShop(int userId, int objectId, int shopCout, Date shopTime);

    @Update("UPDATE trantal_shop SET shop_cout = #{objectCout} + shop_cout, shop_time = #{shopTime} WHERE user_id = #{userId} AND shop_id = #{shopId}")
    Boolean updShopCout(int shopId, int userId, int objectCout, Date shopTime);

    /**
     * 设置订单状态
     * @param shopId
     * @param objectStatus
     * @return
     */
    @Update("UPDATE trantal_shop SET object_status = #{objectStatus} WHERE shop_id = #{shopId}")
    Boolean updShopStatus(int shopId, int objectStatus);

    /**
     * 根据用户Id、产品Id判断商品是否存在购物车
     * @param userId
     * @param objectId
     * @return
     */
    @Select("SELECT COUNT(object_id) as FindShopCout FROM trantal_shop WHERE user_id = #{userId} AND object_id = #{objectId};")
    int getShopUserIdObjectId(int userId, int objectId);
}
