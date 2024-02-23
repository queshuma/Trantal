package com.shuzhi.system_shop.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.common.TokenFunction;
import com.shuzhi.result.code.OrderCode;
import com.shuzhi.result.code.ShopCode;
import com.shuzhi.system_shop.DTO.ShopDTO;
import com.shuzhi.system_shop.Info.ShopWithObjectUser;
import com.shuzhi.system_shop.Service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shuzhi.system_shop.Controller.ObjectFeign;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/Shop")
public class ShopController {

    private final ShopService shopService;
    @Autowired
    private ObjectFeign objectFeign;
    private final Logger logger = LoggerFactory.getLogger(ShopController.class);
    private final Long LONG_ZERO = 0L;
    private final int ZERO = 0;
    private final Float FLOAT_ZERO = Float.valueOf(0);

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }


    /**
     * 添加商品至购物车
     * @param httpServletRequest
     * @param objectId
     * @param shopCout
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(HttpServletRequest httpServletRequest, Long objectId, Long shopCout) throws ParseException {

        Boolean b = false;
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);

//        if (userId == ZERO) {
//            logger.error("TRANTAL SHOP CONTROLLER ORDER INFO --USER ID-- INPUT IS NULL ! ");
//            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_ERROR_ADD_FAIL_USER_ID_NULL);
//        }
//        if (objectId == ZERO) {
//            logger.error("TRANTAL SHOP CONTROLLER ORDER INFO --OBJECT ID-- INPUT IS NULL ! ");
//            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_ERROR_ADD_FAIL_OBJECT_ID_NULL);
//        }
//        if (shopCout == ZERO) {
//            logger.error("TRANTAL SHOP CONTROLLER ORDER INFO --USER ID-- INPUT IS NULL ! ");
//            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_ERROR_ADD_FAIL_OBJECT_COUNT_NULL);
//        }

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
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/find/userId")
    public ResponseResult findById(HttpServletRequest httpServletRequest) throws ParseException {
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);
        List<ShopDTO> shopDTOList = null;
        List<ShopWithObjectUser> shopWithObjectUserList = new ArrayList<>();
        shopDTOList = shopService.getShopUserId(userId);
        for (ShopDTO shopDTO:shopDTOList) {
            ShopWithObjectUser shopWithObjectUser = new ShopWithObjectUser();
            BeanUtils.copyProperties(shopDTO, shopWithObjectUser);
            shopWithObjectUser.setObjectWithBussVO(objectFeign.findObject(shopDTO.getObjectId()));
            shopWithObjectUserList.add(shopWithObjectUser);
        }

        if (SystemUtils.isNull(shopDTOList)) {
            logger.warn("SELECT ALL ORDER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL SHOP INFO: " + shopDTOList);
        logger.info("RETURN");
        return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_FIND_SUCCESS, shopWithObjectUserList);
    }

    /**
     * 更新商品数量
     * @param shopId
     * @param objectCout
     * @return
     */
    @PostMapping("/update/cout")
    public ResponseResult updateShopCout(Long shopId, Long objectCout, HttpServletRequest httpServletRequest) throws ParseException {
        Boolean b = false;
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);
        System.out.println("商品数量： " + objectCout);
        if (objectCout > 0 ) {
            b = shopService.updShopCout(shopId, userId, objectCout);
        } else {
            Long shopStatus = 0L;
            b = shopService.updShopStatus(shopId, shopStatus);
        }

        if (!b) {
            logger.info("TRANTAL SHOP INFO: userId" + shopId + "objectCout" + objectCout);
            logger.info("RETURN");
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_UPD_ERROE);
        }
        logger.info("TRANTAL SHOP INFO: userId = " + shopId + "objectCout = " + objectCout);
        return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_UPD_SUCCESS);
    }

    /**
     * 删除购物车内单个商品
     * @param shopId
     * @return
     */
    @PutMapping("/update/delete")
    public ResponseResult updateShopStatus(Long shopId) {
        Boolean b = false;
        Long objectStatus = 1L;
        b = shopService.updShopStatus(shopId, objectStatus);
        if (!b) {
            logger.info("TRANTAL SHOP INFO: userId" + shopId + "objectStatus" + objectStatus);
            return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_UPD_ERROE);
        }
        logger.info("TRANTAL SHOP INFO: userId = " + shopId + "objectStatus" + objectStatus);
        return ResponseResultFactory.buildResponseFactory(ShopCode.SYSTEM_SHOP_INFO_UPD_SUCCESS);
    }

}
