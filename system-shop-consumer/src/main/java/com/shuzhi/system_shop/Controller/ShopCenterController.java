package com.shuzhi.system_shop.Controller;

import com.shuzhi.system_shop.Entity.ObjectEntity;
import com.shuzhi.system_shop.code.ResultCode;
import com.shuzhi.system_shop.Service.ShopDataService;
import com.shuzhi.system_shop.common.ResponseResult;
import com.shuzhi.system_shop.common.ResponseResultFactory;
import com.shuzhi.system_shop.common.TokenFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/Shop")
public class ShopCenterController {

    private final ShopDataService dataCenterService;
    private final ObjectFeign objectFeign;
    private final Logger logger = LoggerFactory.getLogger(ShopCenterController.class);

    public ShopCenterController(ShopDataService dataCenterService, ObjectFeign objectFeign) {
        this.dataCenterService = dataCenterService;
        this.objectFeign = objectFeign;
    }

    /**
     * 根据商家统计购物车内的数量
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/cout/buss")
    public ResponseResult coutByBuss(HttpServletRequest httpServletRequest) throws ParseException {

        Long b = 0L;
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);

        //获取该用户Id下的所有商品信息
        List<ObjectEntity> objectList;
        //转换成商品ID的列表的字符串
        String objIdString = "";
        objectList = objectFeign.findObjectIdList(userId);
        for (ObjectEntity object: objectList) {
            objIdString += object.getObjectId();
            objIdString += ",";
        }
        objIdString = objIdString.substring(0, objIdString.length() - 1);
        b = dataCenterService.getCoutByBuss(objIdString);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }


    /**
     * 统计所有购物车内的数量
     * @return
     * @throws ParseException
     */
    @GetMapping("/cout")
    public ResponseResult coutAll() {

        Long b = 0L;

        b = dataCenterService.getCout();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 根据商家统计加入购物车的人数
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/person/cout/buss")
    public ResponseResult personCoutByBuss(HttpServletRequest httpServletRequest) throws ParseException {

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
        //查询根据列表做IN选择查询
        b = dataCenterService.getPersonCoutByBuss(objIdString);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }


    /**
     * 根据商家统计加入购物车的人数
     * @return
     * @throws ParseException
     */
    @GetMapping("/person/cout")
    public ResponseResult personCout() {

        Long b = 0L;

        //查询根据列表做IN选择查询
        b = dataCenterService.getPersonCout();

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }
}
