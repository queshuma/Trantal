package com.shuzhi.system.Mapper;

import com.shuzhi.entity.OrderEntity;
import com.shuzhi.system.Info.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 添加订单
     * @param orderInfo
     * @return
     */
    @Insert("INSERT INTO trantal_order (order_number, user_id, object_id, object_price, object_count, order_cost, order_info, order_time, order_track, order_address, order_name, order_phone, order_status) VALUES (#{orderNumber}, #{userId}, #{objectId}, #{objectPrice}, #{objectCount}, #{orderCost}, #{orderInfo}, #{orderTime}, #{orderTrack}, #{orderAddress}, #{orderName}, #{orderPhone}, #{orderStatus})")
    int addOrder(OrderInfo orderInfo);

    /**
     * 查询所有订单
     * @return
     */
    @Select("SELECT * FROM trantal_order")
    @Results(id = "orderResultMap", value = {
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderNumber", column = "order_number"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "objectId", column = "object_id"),
            @Result(property = "objectPrice", column = "object_price"),
            @Result(property = "objectCost", column = "object_cost"),
            @Result(property = "objectCount", column = "object_count"),
            @Result(property = "orderInfo", column = "order_info"),
            @Result(property = "orderTime", column = "order_time"),
            @Result(property = "orderTrack", column = "order_track"),
            @Result(property = "orderAddress", column = "order_address"),
            @Result(property = "orderName", column = "order_name"),
            @Result(property = "orderPhone", column = "order_phone"),
            @Result(property = "orderStatus", column = "order_status")
    })
    List<OrderEntity> getAllOrder();

    /**
     * 修改订单信息
     * @param orderEntity
     * @return
     */
    @Update("UPDATE trantal_order " +
            "SET order_info = #{orderInfo}, " +
            "order_address = #{orderAddress}, " +
            "order_name = #{orderName}, " +
            "order_phone = #{orderPhone} " +
            "WHERE order_id = #{orderId}")
    int updOrder(OrderEntity orderEntity);

    /**
     * 修改订单快递编号
     * @param orderId
     * @param orderTrack
     * @param orderStatus
     * @return
     */
    @Update("UPDATE trantal_order SET order_track = #{orderTrack}, order_status = #{orderStatus} WHERE order_id = #{orderId}")
    int updOrderTrack(int orderId, String orderTrack, int orderStatus);

    /**
     * 修改订单快递状态
     * @param orderId
     * @param orderStatus
     * @return
     */
    @Update("UPDATE trantal_order SET order_status = #{orderStatus} WHERE order_id = #{orderId}")
    int updOrderStatus(int orderId, int orderStatus);
}
