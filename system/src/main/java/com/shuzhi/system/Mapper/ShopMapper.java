package com.shuzhi.system.Mapper;

import com.shuzhi.entity.ShopEntity;
import com.shuzhi.system.Info.ShopInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Author: SHUZHI
 * Date: 2023/8/25
 *
 * @version 1.0
 */

@Mapper
public interface ShopMapper {
    @Insert("INSERT INTO trantal_shop (user_id, object_id, object_old_price, object_price, object_count, object_cost, shop_time, object_status) VALUES (#{userId}, #{objectId}, #{objectOldPrice}, #{objectPrice}, #{objectCount}, #{objectCost}, #{shopTime}, #{objectStatus})")
    int addShop(ShopInfo shopInfo);

    @Select("SELECT * FROM trantal_shop")
    @Results(id = "shopResultMap", value = {
            @Result(property = "shopId", column = "shop_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "objectId", column = "object_id"),
            @Result(property = "objectOldPrice", column = "object_old_price"),
            @Result(property = "objectPrice", column = "object_price"),
            @Result(property = "objectCount", column = "object_count"),
            @Result(property = "objectCost", column = "object_cost"),
            @Result(property = "shopTime", column = "shop_time"),
            @Result(property = "objectStatus", column = "object_status")
    })
    List<ShopEntity> getShopUserId(int userId);

    @Update("UPDATE trantal_shop SET object_count = #{objectCount} WHERE shop_id = #{shopId}")
    Boolean updShopCount(int shopId, int objectCount);

    @Update("UPDATE trantal_shop SET object_status = #{objectStatus} WHERE shop_id = #{shopId}")
    Boolean updShopStatus(int shopId, int objectStatus);
}
