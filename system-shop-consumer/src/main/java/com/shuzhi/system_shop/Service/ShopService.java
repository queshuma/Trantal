package com.shuzhi.system_shop.Service;

import com.shuzhi.result.Common;
import com.shuzhi.system_shop.DTO.ShopDTO;
import com.shuzhi.system_shop.Mapper.ShopMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/8/25
 *
 * @version 1.0
 */

@Service
public class ShopService {

    private final int SERVICE_ADD_SHOP_INFO_NUMBER = 1;
    private final Logger logger = LoggerFactory.getLogger(ShopService.class);
    private final ShopMapper shopMapper;
    private Long ZERO = 0L;

    public ShopService(ShopMapper shopMapper) {
        this.shopMapper = shopMapper;
    }

    /**
     * 添加至购物车
     * @param userId
     * @param objectId
     * @param shopCout
     * @return
     */
    @Transactional
    public Boolean addShop(int userId, int objectId, int shopCout) {

        int b = 0;
        int ShopLength = 0;

        Date shopTime = new Date();

        logger.info("OBJECT SERVICE ADD OBJECT PHONE START");
        try {
            ShopLength = shopMapper.getShopUserIdObjectId(userId, objectId);
            System.out.println("shop" + ShopLength);
            if (ShopLength == Common.ZERO) {
                shopMapper.addShop(userId, objectId, shopCout, shopTime);
            } else if (ShopLength == Common.ONE){
                shopMapper.updShopCoutAddShop(userId, objectId, shopCout, shopTime);
            }
            b = 1;
            logger.info("SHOP SERVICE ADD ORDER INFO SUCCESS!");
//            logger.info("result: " + shopInfo.toString());
        } catch (Exception e) {
            logger.error("SHOP SERVICE ADD ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
//            logger.error("result: " + shopInfo.toString());
        }
        logger.info("SHOP SERVICE ADD ORDER INFO END");
        if (b == SERVICE_ADD_SHOP_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 根据用户ID、查询购物车信息
     * @param userId
     * @return
     */
    @Transactional
    public List<ShopDTO> getShopUserId(int userId) {

        List<ShopDTO> shopDTOList = null;
        logger.info("OBJECT SERVICE SELECT SHOP USER ID START");

        try {
            shopDTOList = shopMapper.getShopUserId(userId);
            logger.info("OBJECT SERVICE SELECT SHOP USER ID SUCCESS!");
            logger.info("result: " + shopDTOList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT SHOP USER ID ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + shopDTOList);
            throw e;

        }

        return shopDTOList;
    }

    /**
     * 更新购物车商品数量
     * @param userId
     * @param shopId
     * @param objectCout
     * @return
     */
    public Boolean updShopCout(int shopId, int userId, int objectCout) {

        Boolean b = false;
        Date shopTime = new Date();
        logger.info("OBJECT SERVICE SELECT SHOP USER ID START");

        try {
            b = shopMapper.updShopCout(shopId, userId, objectCout, shopTime);
            logger.info("OBJECT SERVICE UPDATE SHOP COUNT SUCCESS!");
//            logger.info("result: shopId = " + shopId + "objectCount = " + objectCount);
        } catch (Exception e) {
            logger.error("OBJECT SERVICE UPDATE SHOP COUNT ERROR!");
            logger.error("ERROE:" + e);
//            logger.error("result: shopId = " + shopId + "objectCount = " + objectCount);
        }

        return b;
    }

    public Boolean updShopStatus(int shopId, int objectStatus) {

        Boolean b = false;
        logger.info("OBJECT SERVICE SELECT SHOP USER ID START");

        try {
            b = shopMapper.updShopStatus(shopId, objectStatus);
            logger.info("OBJECT SERVICE UPDATE SHOP STATUS SUCCESS!");
            logger.info("result: shopId = " + shopId + "objectStatus = " + objectStatus);
        } catch (Exception e) {
            logger.error("OBJECT SERVICE UPDATE SHOP STATUS ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: shopId = " + shopId + "objectStatus = " + objectStatus);
        }

        return b;
    }
}
