package com.shuzhi.system.Service;

import com.shuzhi.entity.ObjectEntity;
import com.shuzhi.entity.OrderEntity;
import com.shuzhi.result.common;
import com.shuzhi.system.Info.OrderInfo;
import com.shuzhi.system.Mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    private static final int SERVICE_ADD_ORDER_INFO_NUMBER = 1;
    private static final int SERVICE_UPD_ORDER_INFO_NUMBER = 1;
    private final OrderMapper orderMapper;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    /**
     * 添加订单
     * @param orderInfo
     * @return
     */
    @Transactional
    public Boolean addOrder(OrderInfo orderInfo) {
        logger.info("-------TRANTAL OBJECT ADD SERVICE START-------");
        int b = 0;
        Random random = new Random(System.currentTimeMillis());
        String orderNumber = String.valueOf(random.nextLong());
        orderInfo.setOrderNumber(orderNumber);
        Date date = new Date();
        orderInfo.setOrderTime(date);
        orderInfo.setOrderStatus(common.ZERO);

        logger.info("OBJECT SERVICE ADD OBJECT PHONE START");
        logger.info(String.valueOf(orderInfo));
        try {
            b = orderMapper.addOrder(orderInfo);
            logger.info("ORDER SERVICE ADD ORDER INFO SUCCESS!");
            logger.info("result: " + orderInfo);
        } catch (Exception e) {
            logger.error("ORDER SERVICE ADD ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + orderInfo);
        }
        logger.info("ORDER SERVICE ADD ORDER INFO END");
        if (b == SERVICE_ADD_ORDER_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改订单
     * @param orderEntity
     * @return
     */
    @Transactional
    public Boolean updOrder(OrderEntity orderEntity) {
        logger.info("-------TRANTAL OBJECT ADD SERVICE START-------");
        int b = 0;

        logger.info("OBJECT SERVICE UPD OBJECT PHONE START");
        try {
            b = orderMapper.updOrder(orderEntity);
            logger.info("ORDER SERVICE UPDATE ORDER INFO SUCCESS!");
            logger.info("result: " + orderEntity);
        } catch (Exception e) {
            logger.error("ORDER SERVICE UPDATE ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + orderEntity);
        }
        logger.info("ORDER SERVICE UPDATE ORDER INFO END");
        if (b == SERVICE_UPD_ORDER_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改订单的快递单号
     * @param orderId
     * @param orderTrack
     * @return
     */
    @Transactional
    public Boolean updOrderTrack(int orderId, String orderTrack) {
        logger.info("-------TRANTAL OBJECT ADD SERVICE START-------");
        int b = 0;
        int orderStatus = 1;

        logger.info("OBJECT SERVICE UPD OBJECT PHONE START");
        try {
            b = orderMapper.updOrderTrack(orderId, orderTrack, orderStatus);
            logger.info("ORDER SERVICE UPDATE ORDER INFO SUCCESS!");
            logger.info("result: " + orderId + "快递单号为:" + orderTrack);
        } catch (Exception e) {
            logger.error("ORDER SERVICE UPDATE ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + orderId + "快递单号为:" + orderTrack);
        }
        logger.info("ORDER SERVICE UPDATE ORDER INFO END");
        if (b == SERVICE_UPD_ORDER_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改订单的状态(确认收货)
     * @param orderId
     * @return
     */
    @Transactional
    public Boolean updOrderStatus(int orderId) {
        logger.info("-------TRANTAL OBJECT UPDATE SERVICE START-------");
        int b = 0;
        int orderStatus = 2;

        logger.info("OBJECT SERVICE UPD OBJECT PHONE START");
        try {
            b = orderMapper.updOrderStatus(orderId, orderStatus);
            logger.info("ORDER SERVICE UPDATE ORDER INFO SUCCESS!");
            logger.info("result: " + orderId + "确认收货");
        } catch (Exception e) {
            logger.error("ORDER SERVICE UPDATE ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + orderId);
        }
        logger.info("ORDER SERVICE UPDATE ORDER INFO END");
        if (b == SERVICE_UPD_ORDER_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改订单状态_退货
     * @param orderId
     * @return
     */
    @Transactional
    public Boolean updOrderBack(int orderId) {
        logger.info("-------TRANTAL OBJECT UPDATE SERVICE START-------");
        int b = 0;
        int orderStatus = 3;

        logger.info("OBJECT SERVICE UPD OBJECT PHONE START");
        try {
            b = orderMapper.updOrderStatus(orderId, orderStatus);
            logger.info("ORDER SERVICE UPDATE ORDER INFO SUCCESS!");
            logger.info("result: " + orderId + "退货");
        } catch (Exception e) {
            logger.error("ORDER SERVICE UPDATE ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + orderId);
        }
        logger.info("ORDER SERVICE UPDATE ORDER INFO END");
        if (b == SERVICE_UPD_ORDER_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改订单状态_退货确认
     * @param orderId
     * @return
     */
    @Transactional
    public Boolean updOrderBackOk(int orderId) {
        logger.info("-------TRANTAL OBJECT UPDATE SERVICE START-------");
        int b = 0;
        int orderStatus = 4;

        logger.info("OBJECT SERVICE UPD OBJECT PHONE START");
        try {
            b = orderMapper.updOrderStatus(orderId, orderStatus);
            logger.info("ORDER SERVICE UPDATE ORDER INFO SUCCESS!");
            logger.info("result: " + orderId + "退货确认");
        } catch (Exception e) {
            logger.error("ORDER SERVICE UPDATE ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + orderId);
        }
        logger.info("ORDER SERVICE UPDATE ORDER INFO END");
        if (b == SERVICE_UPD_ORDER_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改订单状态_取消订单
     * @param orderId
     * @return
     */
    @Transactional
    public Boolean updOrderCancel(int orderId) {
        logger.info("-------TRANTAL OBJECT UPDATE SERVICE START-------");
        int b = 0;
        int orderStatus = 5;

        logger.info("OBJECT SERVICE UPD OBJECT PHONE START");
        try {
            b = orderMapper.updOrderStatus(orderId, orderStatus);
            logger.info("ORDER SERVICE UPDATE ORDER INFO SUCCESS!");
            logger.info("result: " + orderId + "退货确认");
        } catch (Exception e) {
            logger.error("ORDER SERVICE UPDATE ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + orderId);
        }
        logger.info("ORDER SERVICE UPDATE ORDER INFO END");
        if (b == SERVICE_UPD_ORDER_INFO_NUMBER) {
            return true;
        }
        return false;
    }


    /**
     * 查询所有订单
     * @return
     */
    @Transactional
    public List<OrderEntity> getAllOrder() {
        logger.info("-------TRANTAL OBJECT SELECT SERVICE START-------");
        List<OrderEntity> orderEntityList = null;
        logger.info("OBJECT SERVICE SELECT OBJECT CLASSES START");

        try {
            orderEntityList = orderMapper.getAllOrder();
            logger.info("OBJECT SERVICE SELECT OBJECT CLASSES SUCCESS!");
            logger.info("result: " + orderEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT OBJECT CLASSES ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + orderEntityList.toString());
        }

        logger.info("-------TRANTAL OBJECT SELECT SERVICE END-------");
        return orderEntityList;
    }

}
