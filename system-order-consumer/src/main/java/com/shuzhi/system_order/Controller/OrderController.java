package com.shuzhi.system_order.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.common.TokenFunction;
import com.shuzhi.entity.ObjectEntity;
import com.shuzhi.entity.OrderEntity;
import com.shuzhi.result.code.OrderCode;
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
    @PostMapping("/add")
    public ResponseResult add(@RequestBody List<OrderWithObjectUser> orderWithObjectUserList) throws Exception {
        Boolean b = false;
        b =  orderService.addOrder(orderWithObjectUserList);
        if (b == true) {
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_UPD_SUCCESS);
        }
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL);

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
    @PostMapping("/update")
    public ResponseResult updateOrder(Long orderNumber, String orderName, String orderAddress, String orderPhone, String orderInfo) {

        Boolean b = false;

        if (SystemUtils.isNullOrEmpty(orderNumber.toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER ID-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_OBJECT_COST_NULL);
        }
        //收件信息
        if (SystemUtils.isNullOrEmpty(orderName.toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER NAME-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_ORDER_NAME_NULL);
        }
        if (SystemUtils.isNullOrEmpty(orderAddress.toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER ADDRESS-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_ORDER_ADDRESS_NULL);
        }
        if (SystemUtils.isNullOrEmpty(orderPhone.toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER PHONE-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_ORDER_PHONE_NULL);
        }

        b =  orderService.updOrder(orderNumber, orderName, orderAddress, orderPhone, orderInfo);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_UPD_SUCCESS);
        }

        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_UPD_FAIL);
    }

    /**
     * 修改订单的快递单号
     * @param orderUUID
     * @param objectId
     * @param orderTrack
     * @return
     */
    @PostMapping("/update/track")
    public ResponseResult updateOrderTrack(String orderUUID, Long objectId, String orderTrack) {
        Boolean b = false;

        b = orderService.updOrderTrack(orderUUID, objectId, orderTrack);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_UPD_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + "用户Id：" + orderUUID + "快递单号为:" + orderTrack);
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_UPD_FAIL);
    }

    /**
     * 修改订单状态
     * @param orderUUID
     * @param objectId
     * @param orderStatus
     * @return
     */
    @PostMapping("/update/status")
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
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_UPD_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + "用户Id：" + orderUUID + "当前状态为:确认收货");
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_UPD_FAIL);
    }

    /**
     * 查找所有订单
     * @return
     */
    @GetMapping("/findAll")
    public ResponseResult findAll() {

        List<OrderEntity> orderEntityList = null;
        orderEntityList = orderService.getAllOrder();
        if (orderEntityList == null) {
            logger.warn("SELECT ALL ORDER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL ORDER INFO: " + orderEntityList);
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_FIND_SUCCESS, orderEntityList);
    }

    /**
     * 根据商家查找所有订单
     * @return
     */
    @GetMapping("/find/buss")
    public ResponseResult findByBuss(HttpServletRequest httpServletRequest) throws ParseException {

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
        orderEntityList = orderService.getOrderByObjectId(objIdString);
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
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_FIND_SUCCESS, orderIWO);
        }
        logger.info("TRANTAL ALL ORDER INFO: " + orderEntityList);
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_FIND_SUCCESS, orderEntityList);
    }

    /**
     * 根据登录用户查找所有订单
     * @return
     */
    @GetMapping("/find/user")
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
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_FIND_SUCCESS, orderIWO);
        }
        logger.info("TRANTAL ALL ORDER INFO: " + orderEntityList);
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_FIND_SUCCESS, orderEntityList);
    }

    /**
     * 根据订单的UUID查找所有订单
     * @return
     */
    @GetMapping("/find/orderUUID")
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
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_FIND_SUCCESS, orderInfoWithObject);
        }
        logger.info("TRANTAL ALL ORDER INFO: " + orderEntity);
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_FIND_SUCCESS, orderEntity);
    }

}
