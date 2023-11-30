package com.shuzhi.system_shop.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.result.code.OrderCode;
import com.shuzhi.result.code.ShopCode;
import com.shuzhi.system_shop.DTO.ShopDTO;
import com.shuzhi.system_shop.Service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api("购物车模块")
@RestController
@RequestMapping("/Shop")
public class ShopController {

    private final ShopService shopService;
    private final Logger logger = LoggerFactory.getLogger(ShopController.class);
    private final Long LONG_ZERO = 0L;
    private final int ZERO = 0;
    private final Float FLOAT_ZERO = Float.valueOf(0);

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }


    /**
     * 添加商品至购物车
     * @param userId
     * @param objectId
     * @param shopCout
     * @return
     */
    @ApiOperation("添加购物车")
    @PostMapping("/add")
    public ResponseResult add(int userId, int objectId, int shopCout) {

        Boolean b = false;

        if (userId == ZERO) {
            logger.error("TRANTAL SHOP CONTROLLER ORDER INFO --USER ID-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_ERROR_ADD_FAIL_USER_ID_NULL);
        }
        if (objectId == ZERO) {
            logger.error("TRANTAL SHOP CONTROLLER ORDER INFO --OBJECT ID-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_ERROR_ADD_FAIL_OBJECT_ID_NULL);
        }
        if (shopCout == ZERO) {
            logger.error("TRANTAL SHOP CONTROLLER ORDER INFO --USER ID-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_ERROR_ADD_FAIL_OBJECT_COUNT_NULL);
        }

        b =  shopService.addShop(userId, objectId, shopCout);

        if (b) {
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_ADD_SUCCESS);
        }

//        logger.info("TRANTAL ALL SHOP INFO: " + shopInfo);
        logger.info("RETURN");
        return ResponseResultFactory.buildResponseFactory(OrderCode.SYSTEM_ORDER_ERROR_ADD_FAIL);
    }

    /**
     * 查询购物车
     * @param userId
     * @return
     */
    @ApiOperation("查询购物车")
    @PostMapping("/find/userId")
    public ResponseResult findById(int userId) {
        List<ShopDTO> shopDTOList = null;
        shopDTOList = shopService.getShopUserId(userId);
        if (SystemUtils.isNull(shopDTOList)) {
            logger.warn("SELECT ALL ORDER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL SHOP INFO: " + shopDTOList);
        logger.info("RETURN");
        return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_FIND_SUCCESS, shopDTOList);
    }

//    @ApiOperation("修改购物车_商品数量")
//    @PostMapping("/update/count")
//    public ResponseResult updateShopCount(int shopId, int objectCount) {
//        Boolean b = false;
//        b = shopService.updShopCount(shopId, objectCount);
//        System.out.println(b);
//        if (!b) {
//            logger.info("TRANTAL SHOP INFO: userId" + shopId + "objectCount" + objectCount);
//            logger.info("RETURN");
//            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_UPD_ERROE);
//        }
//        logger.info("TRANTAL SHOP INFO: userId = " + shopId + "objectCount = " + objectCount);
//        return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_UPD_SUCCESS);
//    }

    @ApiOperation("修改购物车_删除商品")
    @PutMapping("/update/delete")
    public ResponseResult updateShopStatus(int shopId) {
        Boolean b = false;
        int objectStatus = 1;
        b = shopService.updShopStatus(shopId, objectStatus);
        if (!b) {
            logger.info("TRANTAL SHOP INFO: userId" + shopId + "objectStatus" + objectStatus);
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_UPD_ERROE);
        }
        logger.info("TRANTAL SHOP INFO: userId = " + shopId + "objectStatus" + objectStatus);
        return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_UPD_SUCCESS);
    }

}
