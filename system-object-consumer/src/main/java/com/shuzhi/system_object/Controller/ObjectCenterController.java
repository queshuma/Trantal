package com.shuzhi.system_object.Controller;

import com.shuzhi.system_object.Service.ObjectCenterService;
import com.shuzhi.system_object.Service.ObjectService;
import com.shuzhi.system_object.code.ResultCode;
import com.shuzhi.system_object.common.ResponseResult;
import com.shuzhi.system_object.common.ResponseResultFactory;
import com.shuzhi.system_object.common.TokenFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * Author: SHUZHI
 * Date: 2024/3/2
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/Object")
public class ObjectCenterController {

    private final ObjectService objectService;
    private final ObjectCenterService objectCenterService;
    private final Logger logger = LoggerFactory.getLogger(ObjectCenterController.class);

    public ObjectCenterController(ObjectService objectService, ObjectCenterService objectCenterService) {
        this.objectService = objectService;
        this.objectCenterService = objectCenterService;
    }

    /**
     * 获取所有产品的数量
     * @return ResponseResult<List<ObjectEntity>>
     */
    @GetMapping("/cout")
    public ResponseResult cout() {

        int dataCout = 0;
        dataCout = objectService.getObjectCout();

        logger.info("TRANTAL ALL OBJECT INFO: " + dataCout);
        logger.info("RETURN");
        logger.info("========== TRANTAL BJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, dataCout);
    }

    /**
     * 获取所有产品的数量
     * @return ResponseResult<List<ObjectEntity>>
     */
    @GetMapping("/cout/title")
    public ResponseResult coutTitle(String objectTitle) {

        int dataCout = 0;
        dataCout = objectService.getObjectTitleCout(objectTitle);

        logger.info("TRANTAL ALL OBJECT INFO: " + dataCout);
        logger.info("RETURN");
        logger.info("========== TRANTAL BJECT CONTROLLER SELECT ALL OBJECT END ! ==========");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, dataCout);
    }

    /**
     * 根据商家信息获取商品总数
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/cout/buss")
    public ResponseResult coutByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        Long b = 0L;
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);

        b = objectCenterService.getCoutByBuss(userId);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 根据商家信息获取上架商品总数
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/nor/cout/buss")
    public ResponseResult norCoutByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        Long b = 0L;
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);

        b = objectCenterService.getNorCoutByBuss(userId);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 获取所有上架商品总数
     * @param
     * @return
     */
    @GetMapping("/nor/cout")
    public ResponseResult norCoutAll() {

        Long b = 0L;

        b = objectCenterService.getNorCout();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 根据商家信息获取下架商品总数
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/err/cout/buss")
    public ResponseResult errCoutByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        Long b = 0L;
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);

        b = objectCenterService.getErrCoutByBuss(userId);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 所有获取下架商品总数
     * @param
     * @return
     * @throws ParseException
     */
    @GetMapping("/err/cout")
    public ResponseResult errCoutAll(){

        Long b = 0L;

        b = objectCenterService.getErrCout();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }
}
