package com.shuzhi.system_order.Controller;

import com.shuzhi.system_order.DTO.OrderBackInfo;
import com.shuzhi.system_order.common.*;
import com.shuzhi.system_order.Entity.ObjectEntity;
import com.shuzhi.system_order.Service.OrderCenterService;
import com.shuzhi.system_order.code.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: SHUZHI
 * Date: 2024/3/2
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/Order")
public class OrderCenterController {

    private final OrderCenterService orderCenterService;
    private final ObjectFeign objectFeign;
    private final Logger logger = LoggerFactory.getLogger(OrderCenterController.class);

    public OrderCenterController(OrderCenterService orderCenterService, ObjectFeign objectFeign) {
        this.orderCenterService = orderCenterService;
        this.objectFeign = objectFeign;
    }


    /**
     * 根据商家统计有效订单数量
     * 计算所有订单数(不包括取消订单)
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/cout/buss/valid")
    public ResponseResult CoutByBussValid(HttpServletRequest httpServletRequest) throws ParseException {

        Long b = 0L;
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
        b = orderCenterService.getCoutByBussValid(objIdString);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * [管理员]统计有效订单数量
     * 计算所有订单数(不包括取消订单)
     * @return
     * @throws ParseException
     */
    @GetMapping("/cout/valid")
    public ResponseResult CoutValid() {

        Long b = 0L;

        b = orderCenterService.getCoutValid();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 根据商家统计所有订单数量
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/cout/buss")
    public ResponseResult CoutByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        Long b = 0L;
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
        b = orderCenterService.getCoutByBuss(objIdString);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 统计所有订单数量
     * @return
     * @throws ParseException
     */
    @GetMapping("/cout")
    public ResponseResult Cout() {

        Long b = 0L;

        b = orderCenterService.getCout();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 根据商家统计消费金额
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/gather/buss")
    public ResponseResult gatherByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        Long b = 0L;
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
        b = orderCenterService.getGatherByBuss(objIdString);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 根据统计所有消费金额
     * @return
     * @throws ParseException
     */
    @GetMapping("/gather")
    public ResponseResult gather() {

        Long b = 0L;

        b = orderCenterService.getGather();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 根据商家统计待发货信息
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/noTrack/buss")
    public ResponseResult noTrackByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        OrderBackInfo orderBackInfo = null;
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
        orderBackInfo = orderCenterService.getNoTrackByBuss(objIdString);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderBackInfo);
    }

    /**
     * 统计所有待发货信息
     * @return
     * @throws ParseException
     */
    @GetMapping("/noTrack")
    public ResponseResult noTrack() {

        OrderBackInfo orderBackInfo = null;

        orderBackInfo = orderCenterService.getNoTrack();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderBackInfo);
    }

    /**
     * 根据商家统计待收货信息
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/noReceive/buss")
    public ResponseResult noReceiveByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        OrderBackInfo orderBackInfo = null;
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
        orderBackInfo = orderCenterService.getNoReceiveByBuss(objIdString);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderBackInfo);
    }

    /**
     * 根据商家统计待收货信息
     * @return
     * @throws ParseException
     */
    @GetMapping("/noReceive")
    public ResponseResult noReceive() {

        OrderBackInfo orderBackInfo = null;

        orderBackInfo = orderCenterService.getNoReceive();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderBackInfo);
    }

    /**
     * 根据商家统计发起退货信息
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/orderBack/buss")
    public ResponseResult orderBackByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        OrderBackInfo orderBackInfo = null;
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
        orderBackInfo = orderCenterService.getOrderBackByBuss(objIdString);
        System.out.println(orderBackInfo.getOrderCout() + " " + orderBackInfo.getOrderGather());

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderBackInfo);
    }

    /**
     * 统计所有发起退货信息
     * @return
     * @throws ParseException
     */
    @GetMapping("/orderBack")
    public ResponseResult orderBack() {
        OrderBackInfo orderBackInfo = null;

        orderBackInfo = orderCenterService.getOrderBack();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderBackInfo);
    }

    /**
     * 根据商家统计订单完成信息
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/complete/buss")
    public ResponseResult completeByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        OrderBackInfo orderBackInfo = null;
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
        orderBackInfo = orderCenterService.getCompleteByBuss(objIdString);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderBackInfo);
    }

    /**
     * 根据商家统计订单完成信息
     * @return
     * @throws ParseException
     */
    @GetMapping("/complete")
    public ResponseResult complete() {
        OrderBackInfo orderBackInfo = null;

        orderBackInfo = orderCenterService.getComplete();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderBackInfo);
    }

    /**
     * 根据商家统计订单取消信息
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/cancel/buss")
    public ResponseResult cancelByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        OrderBackInfo orderBackInfo = null;
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
        orderBackInfo = orderCenterService.getCancelByBuss(objIdString);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderBackInfo);
    }

    /**
     * 统计全部订单取消信息
     * @return
     * @throws ParseException
     */
    @GetMapping("/cancel")
    public ResponseResult cancel() {
        OrderBackInfo orderBackInfo = null;

        orderBackInfo = orderCenterService.getCancel();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, orderBackInfo);
    }
}
