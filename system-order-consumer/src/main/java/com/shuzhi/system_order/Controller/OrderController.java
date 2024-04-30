package com.shuzhi.system_order.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.TokenFunction;
import com.shuzhi.system_object.Entity.ObjectEntity;
import com.shuzhi.system_order.Entity.OrderEntity;
import com.shuzhi.code.ResultCode;
import com.shuzhi.system_order.Info.OrderInfoWithObject;
import com.shuzhi.system_order.Info.OrderWithObjectUser;
import com.shuzhi.system_order.Service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单接口
 */
@RestController
@RequestMapping("/Order")
public class OrderController {

    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final ObjectFeign objectFeign;

    public OrderController(OrderService orderService, ObjectFeign objectFeign) {
        this.orderService = orderService;
        this.objectFeign = objectFeign;
    }

    /**
     * 添加订单
     * @return
     * @throws Exception
     */
    @PostMapping("/order")
    public ResponseResult add(@RequestBody List<OrderWithObjectUser> orderWithObjectUserList) throws Exception {
        Boolean b = false;
        b =  orderService.addOrder(orderWithObjectUserList);
        if (b == true) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);

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
    @PutMapping("/order")
    public ResponseResult updateOrder(Long orderNumber, String orderName, String orderAddress, String orderPhone, String orderInfo) {

        Boolean b = false;

        b =  orderService.updOrder(orderNumber, orderName, orderAddress, orderPhone, orderInfo);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
    }

    /**
     * 修改订单的快递单号
     * @param orderUUID
     * @param objectId
     * @param orderTrack
     * @return
     */
    @PutMapping("/track")
    public ResponseResult updateOrderTrack(String orderUUID, Long objectId, String orderTrack) {
        Boolean b = false;

        b = orderService.updOrderTrack(orderUUID, objectId, orderTrack);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + "用户Id：" + orderUUID + "快递单号为:" + orderTrack);
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
    }

    /**
     * 修改订单状态
     * @param orderUUID
     * @param objectId
     * @param orderStatus
     * @return
     */
    @PutMapping("/status")
    public ResponseResult updateOrderStatus(String orderUUID, Long objectId, int orderStatus) {
        Boolean b = false;
        //订单取消状态码
        int orderCancalCode = 5;

        if (orderStatus != orderCancalCode) {
            b = orderService.updOrderStatus(orderUUID, objectId, orderStatus);
        } else if (orderStatus == orderCancalCode) {
            b = orderService.updOrderCancal(orderUUID, objectId, orderStatus);
        }

        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + "用户Id：" + orderUUID + "当前状态为:确认收货");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
    }

    /**
     * 查找所有订单
     * @return
     */
    @GetMapping("/info/all")
    public ResponseResult findAll() {

        List<OrderEntity> orderEntityList = null;
        orderEntityList = orderService.getAllOrder();
        //查询数据判断
        if (orderEntityList == null) {
            logger.warn("SELECT ALL ORDER INFO IS NULL!");
        } else {
            List<OrderInfoWithObject> orderIWO = new ArrayList<>();
            for (OrderEntity orderEntity:orderEntityList) {
                OrderInfoWithObject orderInfoWithObject = new OrderInfoWithObject();
                BeanUtils.copyProperties(orderEntity, orderInfoWithObject);
                //远程调用system_object
                System.out.println(objectFeign.findObject(orderEntity.getObjectId()));
                BeanUtils.copyProperties(objectFeign.findObject(orderEntity.getObjectId()), orderInfoWithObject);
                orderIWO.add(orderInfoWithObject);
            }
            logger.info("TRANTAL ALL ORDER INFO: " + orderIWO);
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderIWO);
        }
        logger.info("TRANTAL ALL ORDER INFO: " + orderEntityList);
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderEntityList);
    }

    /**
     * 根据商家查找所有订单
     * @return
     */
    @GetMapping("/info/buss")
    public ResponseResult findByBuss(HttpServletRequest httpServletRequest, int pageNum, int pageSize) throws ParseException {

        List<OrderEntity> orderEntityList = null;
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);
        //获取该用户Id下的所有商品信息
        List<ObjectEntity> objectList = new ArrayList<>();
        //转换成商品ID的列表的字符串
        String objIdString = "";
        objectList = objectFeign.findObjectIdList(userId);
        for (ObjectEntity object: objectList) {
            objIdString += object.getObjectId();
            objIdString += ",";
        }
        objIdString = objIdString.substring(0, objIdString.length() - 1);
        //查询根据列表做IN选择查询
        orderEntityList = orderService.getOrderByObjectId(objIdString,pageNum, pageSize);
        //查询数据判断
        if (orderEntityList == null) {
            logger.warn("SELECT ALL ORDER INFO IS NULL!");
        } else {
            List<OrderInfoWithObject> orderIWO = new ArrayList<>();
            for (OrderEntity orderEntity:orderEntityList) {
                OrderInfoWithObject orderInfoWithObject = new OrderInfoWithObject();
                BeanUtils.copyProperties(orderEntity, orderInfoWithObject);
                //远程调用system_object
                System.out.println(objectFeign.findObject(orderEntity.getObjectId()));
                BeanUtils.copyProperties(objectFeign.findObject(orderEntity.getObjectId()), orderInfoWithObject);
                orderIWO.add(orderInfoWithObject);
            }
            logger.info("TRANTAL ALL ORDER INFO: " + orderIWO);
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderIWO);
        }
        logger.info("TRANTAL ALL ORDER INFO: " + orderEntityList);
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderEntityList);
    }

    /**
     * 根据登录用户查找所有订单
     * @return
     */
    @GetMapping("/info/user")
    public ResponseResult findByUser(HttpServletRequest httpServletRequest) throws ParseException {

        List<OrderEntity> orderEntityList = null;
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);
        orderEntityList = orderService.getOrderByUserId(userId);
        if (orderEntityList == null) {
            logger.warn("SELECT ALL ORDER INFO IS NULL!");
        } else {
            List<OrderInfoWithObject> orderIWO = new ArrayList<>();
            for (OrderEntity orderEntity:orderEntityList) {
                OrderInfoWithObject orderInfoWithObject = new OrderInfoWithObject();
                BeanUtils.copyProperties(orderEntity, orderInfoWithObject);
                //远程调用system_object
                System.out.println(objectFeign.findObject(orderEntity.getObjectId()));
                BeanUtils.copyProperties(objectFeign.findObject(orderEntity.getObjectId()), orderInfoWithObject);
                orderIWO.add(orderInfoWithObject);
            }
            logger.info("TRANTAL ALL ORDER INFO: " + orderIWO);
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderIWO);
        }
        logger.info("TRANTAL ALL ORDER INFO: " + orderEntityList);
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderEntityList);
    }

    /**
     * 根据订单的UUID查找所有订单
     * @return
     */
    @GetMapping("/info/orderUUID")
    public ResponseResult findByOrderUUID(String orderUUID, Long objectId) {

        OrderEntity orderEntity = null;
        orderEntity = orderService.getOrderByOrderUUID(orderUUID, objectId);
        if (orderEntity == null) {
            logger.warn("SELECT ALL ORDER INFO IS NULL!");
        } else {
            OrderInfoWithObject orderInfoWithObject = new OrderInfoWithObject();
            BeanUtils.copyProperties(orderEntity, orderInfoWithObject);
            //远程调用system_object
            BeanUtils.copyProperties(objectFeign.findObject(orderEntity.getObjectId()), orderInfoWithObject);
            logger.info("TRANTAL ALL ORDER INFO: " + orderInfoWithObject);
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderInfoWithObject);
        }
        logger.info("TRANTAL ALL ORDER INFO: " + orderEntity);
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderEntity);
    }

}
