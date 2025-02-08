package com.shuzhi.system_order.Service;

import com.shuzhi.system_order.DTO.OrderBackInfo;
import com.shuzhi.system_order.Mapper.OrderDataMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/3/2
 *
 * @version 1.0
 */

@Service
public class OrderCenterService {

    private final OrderDataMapper orderDataMapper;

    public OrderCenterService(OrderDataMapper orderDataMapper) {
        this.orderDataMapper = orderDataMapper;
    }


    /**
     * 根据商家查询有效订单数量
     * @param objIdString
     * @return
     */
    public Long getCoutByBussValid(String objIdString) {
        return orderDataMapper.getCoutByBussValid(objIdString);
    }

    /**
     * 根据商家查询有效付款金额
     * @param objIdString
     * @return
     */
    public Long getGatherByBuss(String objIdString) {
        return orderDataMapper.getGatherByBuss(objIdString);
    }

    /**
     * 根据商家查询所有订单数量
     * @param objIdString
     * @return
     */
    public Long getCoutByBuss(String objIdString) {
        return orderDataMapper.getCoutByBuss(objIdString);
    }

    /**
     * 根据商家统计待发货信息
     * @param objIdString
     * @return
     */
    public OrderBackInfo getNoTrackByBuss(String objIdString) {
        int orderStatus = 0;
        return orderDataMapper.getInfoByBuss(objIdString, orderStatus);
    }

    /**
     * 根据商家统计待收货信息
     * @param objIdString
     * @return
     */
    public OrderBackInfo getNoReceiveByBuss(String objIdString) {
        int orderStatus = 1;
        return orderDataMapper.getInfoByBuss(objIdString, orderStatus);
    }

    /**
     * 根据商家统计退货申请信息
     * @param objIdString
     * @return
     */
    public OrderBackInfo getOrderBackByBuss(String objIdString) {
        int orderStatus = 3;
        return orderDataMapper.getInfoByBuss(objIdString, orderStatus);
    }

    /**
     * 根据商家统计订单完成信息
     * @param objIdString
     * @return
     */
    public OrderBackInfo getCompleteByBuss(String objIdString) {
        int orderStatus = 2;
        return orderDataMapper.getInfoByBuss(objIdString, orderStatus);
    }

    /**
     * 根据商家统计订单取消信息
     * @param objIdString
     * @return
     */
    public OrderBackInfo getCancelByBuss(String objIdString) {
        int orderStatus = 4;
        return orderDataMapper.getInfoByBuss(objIdString, orderStatus);
    }

    /**
     * [管理员]统计有效订单数量
     * @return
     */
    public Long getCoutValid() {
        return orderDataMapper.getCoutValid();
    }

    /**
     * [管理员]统计所有订单数量
     * @return
     */
    public Long getCout() {
        return orderDataMapper.getCout();
    }

    /**
     * [管理员]统计所有消费金额
     * 不包括取消订单
     * @return
     */
    public Long getGather() {
        return orderDataMapper.getGather();
    }

    /**
     * [管理员]统计所有未发货数量
     * 不包括取消订单
     * @return
     */
    public OrderBackInfo getNoTrack() {
        int orderStatus = 0;
        return orderDataMapper.getInfo(orderStatus);
    }

    /**
     * [管理员]统计所有待收货数量
     * @return
     */
    public OrderBackInfo getNoReceive() {
        int orderStatus = 1;
        return orderDataMapper.getInfo(orderStatus);
    }

    /**
     * [管理员]统计所有完成订单数量
     * @return
     */
    public OrderBackInfo getComplete() {
        int orderStatus = 2;
        return orderDataMapper.getInfo(orderStatus);
    }

    /**
     * [管理员]统计所有发起退货数量
     * @return
     */
    public OrderBackInfo getOrderBack() {
        int orderStatus = 3;
        return orderDataMapper.getInfo(orderStatus);
    }

    /**
     * [管理员]统计所有取消订单数量
     * @return
     */
    public OrderBackInfo getCancel() {
        int orderStatus = 4;
        return orderDataMapper.getInfo(orderStatus);
    }
}
