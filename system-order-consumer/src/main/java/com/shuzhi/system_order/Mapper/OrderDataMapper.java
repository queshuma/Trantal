package com.shuzhi.system_order.Mapper;

import com.shuzhi.system_order.DTO.OrderBackInfo;
import org.apache.ibatis.annotations.*;


/**
 * Author: SHUZHI
 * Date: 2024/3/2
 *
 * @version 1.0
 */
@Mapper
public interface OrderDataMapper {

    /**
     * 根据商家查询有效订单数量
     * @param objIdString
     * @return
     */
    @Select("SELECT COUNT(order_id) FROM trantal_order WHERE order_status < 5 AND object_id IN (${objIdString})")
    Long getCoutByBussValid(String objIdString);

    /**
     * 根据商家查询所有订单数量
     * @param objIdString
     * @return
     */
    @Select("SELECT COUNT(order_id) FROM trantal_order WHERE object_id IN (${objIdString})")
    Long getCoutByBuss(String objIdString);

    /**
     * 根据商家查询消费总金额
     * @param objIdString
     * @return
     */
    @Select("SELECT SUM(order_cost) FROM trantal_order WHERE order_status < 5 AND object_id IN (${objIdString})")
    Long getGatherByBuss(String objIdString);

    /**
     * 根据商家统计订单信息
     * @param objIdString
     * @param orderStatus
     * @return
     */
    @Select("SELECT COUNT(order_id) order_cout, SUM(order_cost) order_gather FROM trantal_order WHERE order_status = #{orderStatus} AND object_id IN (${objIdString})")
    @Results(id = "orderBackMap", value = {
            @Result(property = "orderCout", column = "order_cout"),
            @Result(property = "orderGather", column = "order_gather"),
    })
    OrderBackInfo getInfoByBuss(String objIdString, int orderStatus);

    /**
     * [管理员]统计有效订单数量
     * @return
     */
    @Select("SELECT COUNT(order_id) FROM trantal_order WHERE order_status < 5")
    Long getCoutValid();

    /**
     * [管理员]统计所有订单数量
     * @return
     */
    @Select("SELECT COUNT(order_id) FROM trantal_order")
    Long getCout();

    /**
     * [管理员]统计所有消费金额
     * @return
     */
    @Select("SELECT SUM(order_cost) FROM trantal_order WHERE order_status < 5")
    Long getGather();


    /**
     * [管理员]统计所有订单信息
     * @return
     */
    @Select("SELECT COUNT(order_id) order_cout, SUM(order_cost) order_gather FROM trantal_order WHERE order_status = #{orderStatus}")
    @ResultMap("orderBackMap")
    OrderBackInfo getInfo(int orderStatus);
}
