package com.shuzhi.system_shop.Mapper;

import com.shuzhi.system_shop.DTO.ShopDTO;
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

    /**
     * 添加至购物车
     * @param userId
     * @param objectId
     * @param shopCout
     * @param shopTime
     * @param shopStatus
     * @return
     */
    @Insert("INSERT INTO trantal_shop (user_id, object_id, shop_cout, shop_time, shop_status) VALUES (#{userId}, #{objectId}, #{shopCout},#{shopTime},#{shopStatus})")
    int addShop(Long userId, Long objectId, Long shopCout, Date shopTime, Long shopStatus);

    /**
     * 查询所有购物车数据
     * @return
     */
    @Select("SELECT * FROM trantal_shop WHERE shop_status = 1")
    @Results(id = "shopResultMap", value = {
            @Result(property = "shopId", column = "shop_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "objectId", column = "object_id"),
            @Result(property = "shopCout", column = "shop_cout"),
            @Result(property = "shopTime", column = "shop_time")
    })
    List<ShopDTO> getShopAll(int shopStatus);

    /**
     * 根据用户Id获取购物车数据
     * @param userId
     * @return
     */
    @Select("SELECT * FROM trantal_shop WHERE user_id = #{userId} AND shop_status = 1")
    @ResultMap("shopResultMap")
    List<ShopDTO> getShopUserId(Long userId);

    /**
     * 添加订单后更新产品数据信息
     * @param userId
     * @param objectId
     * @param shopTime
     * @return
     */
    @Update("UPDATE trantal_shop SET shop_cout = #{shopCout} + shop_cout, shop_time = #{shopTime} WHERE user_id = #{userId} AND object_id = #{objectId}")
    Boolean updShopCoutAddShop(Long userId, Long objectId, Long shopCout, Date shopTime);

    @Update("UPDATE trantal_shop SET shop_cout = #{objectCout}, shop_time = #{shopTime} WHERE user_id = #{userId} AND shop_id = #{shopId}")
    Boolean updShopCout(Long shopId, Long userId, Long objectCout, Date shopTime);

    /**
     * 设置购物车状态
     * @param shopId
     * @param shopStatus
     * @return
     */
    @Update("UPDATE trantal_shop SET shop_status = #{shopStatus} WHERE shop_id = #{shopId}")
    Boolean updShopStatus(Long shopId, Long shopStatus);

    /**
     * 根据用户Id、产品Id判断商品是否存在购物车
     * @param userId
     * @param objectId
     * @return
     */
    @Select("SELECT COUNT(object_id) as FindShopCout FROM trantal_shop WHERE user_id = #{userId} AND object_id = #{objectId} AND shop_status = #{shopStatus};")
    int getShopUserIdObjectId(Long userId, Long objectId, Long shopStatus);

}
