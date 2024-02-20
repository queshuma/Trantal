package com.shuzhi.system_order.Service;

import com.alibaba.nacos.shaded.com.google.gson.annotations.Until;
import com.shuzhi.entity.ObjectEntity;
import com.shuzhi.entity.OrderEntity;
import com.shuzhi.objectVO.ObjectWithBussVO;
import com.shuzhi.system_order.Info.OrderInfo;
import com.shuzhi.system_order.Info.OrderWithObjectUser;
import com.shuzhi.system_order.Mapper.ObjectMapper;
import com.shuzhi.system_order.Controller.ObjectFeign;
import com.shuzhi.system_order.Mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class OrderService {

    private static final int SERVICE_UPD_ORDER_INFO_NUMBER = 1;
    private final OrderMapper orderMapper;
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private ObjectFeign objectFeign;
    //    新建商品修改的互斥锁表
    final Map<Long, ReentrantLock> productLocks = new ConcurrentHashMap<>();

    @Autowired
    public OrderService(OrderMapper orderMapper, ObjectMapper objectMapper) {
        this.orderMapper = orderMapper;
        this.objectMapper = objectMapper;
    }

    /**
     * 添加订单
     *
     * @param o
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean addOrder(List<OrderWithObjectUser> o) throws Exception {

        boolean b = true;
        String order_uuid = "od" + System.currentTimeMillis();
        for (int i = 0; i < o.size(); i++) {
            OrderWithObjectUser Info = o.get(i);
            OrderInfo orderInfo = new OrderInfo();
            Date date = new Date();


            //提取商品属性
            ObjectWithBussVO objVO = Info.getObjectWithBussVO();
            ObjectEntity objectEntity = objVO;

            BeanUtils.copyProperties(objectEntity, orderInfo);
            BeanUtils.copyProperties(Info, orderInfo);
            orderInfo.setOrderCout(Info.getShopCout());
            orderInfo.setItemCost(orderInfo.getObjectPrice() * orderInfo.getOrderCout());
            orderInfo.setOrderTime(new Date());
            orderInfo.setOrderStatus(0);
            orderInfo.setOrder_uuid(order_uuid);
            orderInfo.setOrderAddress(Info.getReceiveAddress());
            orderInfo.setOrderPhone(Info.getReceivePhone());
            orderInfo.setOrderName(Info.getReceiveName());
            System.out.println(objectFeign.hasObject(objVO.getObjectId(), Info.getShopCout()));
            //判断该商品是否有库存
            if (!objectFeign.hasObject(objVO.getObjectId(), Info.getShopCout())) {
                System.out.println("没有库存");
                return false;
            }
            // 获取商品对应的锁对象
            ReentrantLock productLock = productLocks.computeIfAbsent(orderInfo.getObjectId(), id -> new ReentrantLock());
            //判断互斥锁当前的状态
            if (productLock.tryLock()) {
                try {
                    //执行相关的持久层函数
                    //更新产品信息
                    objectMapper.updObjectReduce(orderInfo.getObjectId(), orderInfo.getOrderCout());
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
                    productLock.unlock();
                    //判断执行数量是否为1
                    if (!b) {
                        return false;
                    }
                }
        }
    }
    return true;
}


    /**
     * 修改订单
     * @param orderNumber
     * @param orderName
     * @param orderAddress
     * @param orderPhone
     * @param orderInfo
     * @return
     */
    @Transactional
    public Boolean updOrder(Long orderNumber, String orderName, String orderAddress, String orderPhone, String orderInfo) {

        int b = 0;

        logger.info("OBJECT SERVICE UPD OBJECT PHONE START");
        try {
            b = orderMapper.updOrder(orderNumber, orderName, orderAddress, orderPhone, orderInfo);
            logger.info("ORDER SERVICE UPDATE ORDER INFO SUCCESS!");
        } catch (Exception e) {
            logger.error("ORDER SERVICE UPDATE ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
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
     * 修改订单状态_申请退货
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
     * 修改订单状态_允许退货(商家)
     * @param orderId
     * @return
     */
    @Transactional
    public Boolean updOrderBackOk(int orderId) {

        int b = 0;
        int orderStatus = 4;

        logger.info("OBJECT SERVICE UPD OBJECT PHONE START");
        try {
            orderMapper.updOrderStatus(orderId, orderStatus);
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
     * @param orderNumber
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updOrderCancel(int orderNumber) throws Exception{

        Boolean b = true;
        int orderStatus = 5;

        logger.info("OBJECT SERVICE UPD OBJECT PHONE START");

        try {
            //获取该订单的产品Id
            Long objectId = orderMapper.getObjectIdByOrderNumber(orderNumber);
            // 获取商品对应的锁对象
            ReentrantLock productLock = productLocks.computeIfAbsent(objectId, id -> new ReentrantLock());
            if (productLock.tryLock()) {
                orderMapper.updOrderStatus(orderNumber, orderStatus);
                objectMapper.updObjectAdd(orderNumber);
                logger.info("ORDER SERVICE UPDATE ORDER INFO SUCCESS!");
                logger.info("result: " + orderNumber + "退货确认");
            }
            productLock.unlock();
        } catch (Exception e) {
            logger.error("ORDER SERVICE UPDATE ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + orderNumber);
            b = false;
            throw e;
        } finally {
            logger.info("ORDER SERVICE UPDATE ORDER INFO END");
            if (b) {
                return true;
            }
            return false;
        }
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
