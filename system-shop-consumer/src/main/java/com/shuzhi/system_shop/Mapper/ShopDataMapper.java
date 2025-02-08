package com.shuzhi.system_shop.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author: SHUZHI
 * Date: 2024/3/2
 *
 * @version 1.0
 */
@Mapper
public interface ShopDataMapper {
    /**
     * 根据商家查询购物车商品数量
     * @return
     */
    @Select("SELECT SUM(shop_cout) FROM trantal_shop WHERE shop_status = 1 AND object_id IN (${objIdString})")
    Long getCoutByBuss(String objIdString);

    /**
     * 根据商家查询加入购物车的消费者数量
     * @param objIdString
     * @return
     */
    @Select("SELECT COUNT(DISTINCT user_id) FROM trantal_shop WHERE shop_status = 1 AND object_id IN (${objIdString}) ")
    Long getPersonCoutByBuss(String objIdString);

    /**
     * 查询所有购物车商品数量
     * @return
     */
    @Select("SELECT SUM(shop_cout) FROM trantal_shop WHERE shop_status = 1")
    Long getCout();

    @Select("SELECT COUNT(DISTINCT user_id) FROM trantal_shop WHERE shop_status = 1 ")
    Long getPersonCout();
}
