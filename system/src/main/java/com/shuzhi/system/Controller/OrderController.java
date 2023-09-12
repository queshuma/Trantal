package com.shuzhi.system.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.entity.OrderEntity;
import com.shuzhi.entity.UserEntity;
import com.shuzhi.result.code.ObjectCode;
import com.shuzhi.result.code.OrderCode;
import com.shuzhi.result.code.UserCode;
import com.shuzhi.system.Info.OrderInfo;
import com.shuzhi.system.Service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.alibaba.druid.util.FnvHash.Constants.READ;
import static com.alibaba.druid.util.FnvHash.Constants.T;


@Api("订单模块")
@RestController
@RequestMapping("/Order")
public class OrderController {

    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 添加订单
     * @param orderInfo
     * @return
     */
    @ApiOperation("添加订单")
    @PostMapping("/add")
    public ResponseResult add(OrderInfo orderInfo) {

        Boolean b = false;

        if (SystemUtils.isNullOrEmpty(orderInfo.getObjectId().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --OBJECT ID-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_OBJECT_ID_NULL);
        }
        if (SystemUtils.isNullOrEmpty(orderInfo.getUserId().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --USER ID-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_USER_ID_NULL);
        }
        //商品信息
        if (SystemUtils.isNullOrEmpty(orderInfo.getObjectPrice().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --OBJECT PRICE-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_OBJECT_PRICE_NULL);
        }
        if (SystemUtils.isNullOrEmpty(orderInfo.getObjectCost().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --OBJECT COST-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_OBJECT_COST_NULL);
        }
        if (SystemUtils.isNullOrEmpty(orderInfo.getObjectCount().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --OBJECT COUNT-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_OBJECT_COUNT_NULL);
        }
        //收件信息
        if (SystemUtils.isNullOrEmpty(orderInfo.getOrderName().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER NAME-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_ORDER_NAME_NULL);
        }
        if (SystemUtils.isNullOrEmpty(orderInfo.getOrderAddress().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER ADDRESS-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_ORDER_ADDRESS_NULL);
        }
        if (SystemUtils.isNullOrEmpty(orderInfo.getOrderPhone().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER PHONE-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_ORDER_PHONE_NULL);
        }

        b =  orderService.addOrder(orderInfo);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(ObjectCode.SYSTEM_OBJECT_INFO_ADD_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + orderInfo);
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL);
    }

    /**
     * 修改订单
     * @param orderEntity
     * @return
     */
    @ApiOperation("修改订单")
    @PostMapping("/update")
    public ResponseResult add(OrderEntity orderEntity) {

        Boolean b = false;

        if (SystemUtils.isNullOrEmpty(orderEntity.getOrderId().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER ID-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_OBJECT_COST_NULL);
        }
        //收件信息
        if (SystemUtils.isNullOrEmpty(orderEntity.getOrderName().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER NAME-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_ORDER_NAME_NULL);
        }
        if (SystemUtils.isNullOrEmpty(orderEntity.getOrderAddress().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER ADDRESS-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_ORDER_ADDRESS_NULL);
        }
        if (SystemUtils.isNullOrEmpty(orderEntity.getOrderPhone().toString())) {
            logger.error("TRANTAL ORDER CONTROLLER ORDER INFO --ORDER PHONE-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL_ORDER_PHONE_NULL);
        }

        b =  orderService.updOrder(orderEntity);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_UPD_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + orderEntity);
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_UPD_FAIL);
    }

    /**
     * 修改订单的快递单号
     * @param orderId
     * @param orderTrack
     * @return
     */
    @ApiOperation("修改订单编号")
    @PostMapping("/update/track")
    public ResponseResult updateOrderTrack(int orderId, String orderTrack) {
        Boolean b = false;

        b = orderService.updOrderTrack(orderId, orderTrack);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_UPD_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + "用户Id：" + orderId + "快递单号为:" + orderTrack);
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_UPD_FAIL);
    }

    /**
     * 修改订单状态_确认收货
     * @param orderId
     * @return
     */
    @ApiOperation("修改订单状态")
    @PostMapping("/update/status")
    public ResponseResult updateOrderStatus(int orderId) {
        Boolean b = false;

        b = orderService.updOrderStatus(orderId);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_UPD_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + "用户Id：" + orderId + "当前状态为:确认收货");
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_UPD_FAIL);
    }

    @ApiOperation("退回订单")
    @PostMapping("/update/back")
    public ResponseResult updateOrderBack(int orderId) {
        Boolean b = false;

        b = orderService.updOrderBack(orderId);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_UPD_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + "用户Id：" + orderId + "当前状态为:确认收货");
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_UPD_FAIL);
    }

    @ApiOperation("确认退货订单")
    @PostMapping("/update/BackOK")
    public ResponseResult updateOrderBackOk(int orderId) {
        Boolean b = false;

        b = orderService.updOrderBackOk(orderId);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_UPD_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + "用户Id：" + orderId + "当前状态为:确认收货");
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_UPD_FAIL);
    }

    @ApiOperation("取消订单")
    @PostMapping("/update/Cancel")
    public ResponseResult updateOrderCancel(int orderId) {
        Boolean b = false;

        b = orderService.updOrderCancel(orderId);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_INFO_UPD_SUCCESS);
        }

        logger.info("TRANTAL ALL OBJECT INFO: " + "用户Id：" + orderId + "当前状态为:确认收货");
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_UPD_FAIL);
    }


    /**
     * 查找所有订单
     * @return
     */
    @ApiOperation("查找所有订单")
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


}
