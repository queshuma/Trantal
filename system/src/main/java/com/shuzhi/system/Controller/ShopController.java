package com.shuzhi.system.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.entity.OrderEntity;
import com.shuzhi.entity.ShopEntity;
import com.shuzhi.result.code.OrderCode;
import com.shuzhi.result.code.ShopCode;
import com.shuzhi.system.Info.ShopInfo;
import com.shuzhi.system.Service.OrderService;
import com.shuzhi.system.Service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api("购物车模块")
@RestController
@RequestMapping("/Shop")
public class ShopController {

    private final ShopService shopService;
    private final Logger logger = LoggerFactory.getLogger(ShopController.class);
    private Long LONG_ZERO = 0L;
    private Float FLOAT_ZERO = Float.valueOf(0);

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }


    /**
     * 添加购物车
     * @param shopInfo
     * @return
     */
    @ApiOperation("添加购物车")
    @PostMapping("/add")
    public ResponseResult add(ShopInfo shopInfo) {

        Boolean b = false;
        logger.info("========== TRANTAL SHOP CONTROLLER ADD ORDER START ! ==========");

        if ((shopInfo.getUserId() == LONG_ZERO)) {
            logger.error("TRANTAL SHOP CONTROLLER ORDER INFO --USER ID-- INPUT IS NULL ! ");
            outWorkInfomation("ADD SHOP", shopInfo);
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_ERROR_ADD_FAIL_USER_ID_NULL);
        }
        if (shopInfo.getObjectId() == LONG_ZERO) {
            logger.error("TRANTAL SHOP CONTROLLER ORDER INFO --OBJECT ID-- INPUT IS NULL ! ");
            outWorkInfomation("ADD SHOP", shopInfo);
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_ERROR_ADD_FAIL_OBJECT_ID_NULL);
        }
        if (shopInfo.getObjectCount() == LONG_ZERO) {
            logger.error("TRANTAL SHOP CONTROLLER ORDER INFO --USER ID-- INPUT IS NULL ! ");
            outWorkInfomation("ADD SHOP", shopInfo);
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_ERROR_ADD_FAIL_OBJECT_COUNT_NULL);
        }

        b =  shopService.addShop(shopInfo);

        if (b) {
            outWorkInfomation("ADD ORDER", shopInfo);
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_ADD_SUCCESS);
        }

        logger.info("TRANTAL ALL SHOP INFO: " + shopInfo);
        logger.info("RETURN");
        logger.info("========== TRANTAL SHOP CONTROLLER ADD SHOP END ! ==========");
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL);
    }

    /**
     * 查询购物车
     * @param userId
     * @return
     */
    @ApiOperation("查询购物车")
    @PostMapping("/find/userId")
    public ResponseResult find(int userId) {
        logger.info("========== TRANTAL SHOP CONTROLLER SELECT ALL SHOP BY USER ID START! ==========");
        List<ShopEntity> shopEntityList = null;
        shopEntityList = shopService.getShopUserId(userId);
        if (shopEntityList == null) {
            logger.warn("SELECT ALL ORDER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL SHOP INFO: " + shopEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL SHOP CONTROLLER SELECT ALL SHOP BY USER ID END! ==========");
        return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_FIND_SUCCESS, shopEntityList);
    }


    @ApiOperation("修改购物车_商品数量")
    @PostMapping("/update/count")
    public ResponseResult updateShopCount(int shopId, int objectCount) {
        logger.info("========== TRANTAL SHOP CONTROLLER UPDATE OBJECT COUNT START! ==========");
        Boolean b = false;
        b = shopService.updShopCount(shopId, objectCount);
        System.out.println(b);
        if (!b) {
            logger.info("TRANTAL SHOP INFO: userId" + shopId + "objectCount" + objectCount);
            logger.info("RETURN");
            logger.info("========== TRANTAL SHOP CONTROLLER UPDATE OBJECT COUNT END! ==========");
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_UPD_ERROE);
        }
        logger.info("TRANTAL SHOP INFO: userId = " + shopId + "objectCount = " + objectCount);
        logger.info("RETURN");
        logger.info("========== TRANTAL SHOP CONTROLLER UPDATE OBJECT COUNT END! ==========");
        return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_UPD_SUCCESS);
    }

    //
    //订单接口层功能模块
    //
    /**
     * 抽离结尾输出代码
     * @param toDo
     * @param t
     */
    public <T> void outWorkInfomation(String toDo, T t) {
        logger.info("TRANTAL ALL OBJECT INFO: " + t);
        logger.info("RETURN");
        logger.info("========== TRANTAL OBJECT CONTROLLER " + toDo + " OBJECT INFO END! ==========");
    }
}
