package com.shuzhi.system.Service;

import com.shuzhi.entity.OrderEntity;
import com.shuzhi.result.Common;
import com.shuzhi.system.Info.OrderInfo;
import com.shuzhi.system.Mapper.ObjectMapper;
import com.shuzhi.system.Mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    private static final int SERVICE_ADD_ORDER_INFO_NUMBER = 1;
    private static final int SERVICE_UPD_ORDER_INFO_NUMBER = 1;
    private final OrderMapper orderMapper;
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderMapper orderMapper, ObjectMapper objectMapper) {
        this.orderMapper = orderMapper;
        this.objectMapper = objectMapper;
    }

    /**
     * 添加订单
     * @param orderInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean addOrder(OrderInfo orderInfo) throws Exception {

        Boolean b = true;
        Random random = new Random(System.currentTimeMillis());
        String orderNumber = String.valueOf(-random.nextLong());
        orderInfo.setOrderNumber(orderNumber);
        Date date = new Date();
        orderInfo.setOrderTime(date);
        orderInfo.setOrderStatus(Common.ZERO);

        logger.info("ORDER SERVICE ADD ORDER START");
        //判断提交订单之后，产品数量是否低于0
        if(objectMapper.getObjectCount(orderInfo.getObjectId())  - orderInfo.getObjectCount() < Common.ZERO) {
            logger.error("OBJECT COUNT LOWER!");
            logger.error("result: " + orderInfo);
            logger.info("ORDER SERVICE ADD ORDER INFO END");
            return false;
        }
        try {
            //执行相关的持久层函数
            //更新产品信息
            objectMapper.updObjectReduce(orderInfo.getObjectId(), orderInfo.getObjectCount());
            //更新订单信息
            orderMapper.addOrder(orderInfo);
            logger.info("ORDER SERVICE ADD ORDER INFO SUCCESS!");
            logger.info("result: " + orderInfo);
        } catch (Exception e) {
            logger.error("ORDER SERVICE ADD ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + orderInfo);
            b = false;
            throw e;
        } finally {
            logger.info("ORDER SERVICE ADD ORDER INFO END");
        }
        //判断执行数量是否为1
        if (b) {
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

        return orderEntityList;
    }

}
