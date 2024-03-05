package com.shuzhi.system_order.Mapper;

import com.shuzhi.entity.OrderEntity;
import com.shuzhi.system_order.Info.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 添加订单
     * @param orderInfo
     * @return
     */
    @Insert("INSERT INTO trantal_order (order_uuid, user_id, object_id, object_price, order_cout, order_cost, order_info, order_time, order_track, order_address, order_name, order_phone, order_status) \n" +
            " VALUES (#{order_uuid}, #{userId}, #{objectId}, #{objectPrice}, #{orderCout}, #{itemCost}, #{Info}, #{orderTime}, #{orderTrack}, #{orderAddress}, #{orderName}, #{orderPhone}, #{orderStatus} ) ")
    int addOrder(OrderInfo orderInfo);

    /**
     * 查询所有订单
     * @return
     */
    @Select("SELECT * FROM trantal_order")
    @Results(id = "orderResultMap", value = {
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderUUID", column = "order_uuid"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "objectId", column = "object_id"),
            @Result(property = "objectPrice", column = "object_price"),
            @Result(property = "objectCost", column = "object_oldprice"),
            @Result(property = "orderCout", column = "order_cout"),
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
     * @param orderNumber
     * @param orderName
     * @param orderAddress
     * @param orderPhone
     * @return
     */
    @Update("UPDATE trantal_order " +
            "SET order_info = #{orderInfo}, " +
            "order_address = #{orderAddress}, " +
            "order_name = #{orderName}, " +
            "order_phone = #{orderPhone}, " +
            "order_info = #{orderInfo} " +
            "WHERE order_number = #{orderNumber}")
    int updOrder(Long orderNumber, String orderName, String orderAddress, String orderPhone, String orderInfo);

    /**
     * 修改订单快递编号
     * @param orderUUID
     * @param objectId
     * @param orderTrack
     * @param orderStatus
     * @return
     */
    @Update("UPDATE trantal_order SET order_track = #{orderTrack}, order_status = #{orderStatus} WHERE order_uuid = #{orderUUID} AND object_id = #{objectId}")
    int updOrderTrack(String orderUUID, Long objectId, String orderTrack, int orderStatus);

    /**
     * 修改订单快递状态
     * @param orderUUID
     * @param objectId
     * @param orderStatus
     * @return
     */
    @Update("UPDATE trantal_order SET order_status = #{orderStatus} WHERE order_uuid = #{orderUUID} AND object_id = #{objectId}")
    int updOrderStatus(String orderUUID, Long objectId, int orderStatus);

    @Select("SELECT order_cout FROM trantal_order WHERE order_uuid = #{orderUUID} AND object_id = #{objectId}")
//    @ResultMap("orderResultMap")
    int getOrderCout(String orderUUID, Long objectId);

    /**
     * 根据用户Id查找订单
     * @param userId
     * @return
     */
    @Select("SELECT * FROM trantal_order WHERE user_id = #{userId}")
    @ResultMap("orderResultMap")
    List<OrderEntity> getOrderByUserId(Long userId);

    @Select("SELECT order_id FROM trantal_order WHERE order_uuid = #{orderUUID}")
    Long getObjectIdByOrderNumber(int orderUUID);

    /**
     * 根据商家Id查找订单
     * @param objIdList
     * @return
     */
    @Select("SELECT * FROM trantal_order WHERE object_id IN (${objIdList})")
    @ResultMap("orderResultMap")
    List<OrderEntity> getOrderByObjectId(String objIdList);

    @Select("SELECT * FROM trantal_order WHERE order_uuid = #{orderUUID} AND object_id = #{objectId}")
    @ResultMap("orderResultMap")
    OrderEntity getOrderByOrderUUID(String orderUUID, Long objectId);
}
