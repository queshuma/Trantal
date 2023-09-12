package com.shuzhi.system.Service;

import com.shuzhi.entity.OrderEntity;
import com.shuzhi.entity.ShopEntity;
import com.shuzhi.system.Info.ShopInfo;
import com.shuzhi.system.Mapper.ShopMapper;
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
     * @param shopInfo
     * @return
     */
    @Transactional
    public Boolean addShop(ShopInfo shopInfo) {

        int b = 0;

        Date date = new Date();
        shopInfo.setShopTime(date);
        shopInfo.setObjectStatus(ZERO);

        logger.info("OBJECT SERVICE ADD OBJECT PHONE START");
        try {
            b = shopMapper.addShop(shopInfo);
            logger.info("SHOP SERVICE ADD ORDER INFO SUCCESS!");
            logger.info("result: " + shopInfo.toString());
        } catch (Exception e) {
            logger.error("SHOP SERVICE ADD ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + shopInfo.toString());
        }
        logger.info("SHOP SERVICE ADD ORDER INFO END");
        if (b == SERVICE_ADD_SHOP_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    @Transactional
    public List<ShopEntity> getShopUserId(int userId) {

        List<ShopEntity> shopEntityList = null;
        logger.info("OBJECT SERVICE SELECT SHOP USER ID START");

        try {
            shopEntityList = shopMapper.getShopUserId(userId);
            logger.info("OBJECT SERVICE SELECT SHOP USER ID SUCCESS!");
            logger.info("result: " + shopEntityList.toString());
        } catch (Exception e) {
            logger.error("OBJECT SERVICE SELECT SHOP USER ID ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + shopEntityList);
        }

        return shopEntityList;
    }

    public Boolean updShopCount(int shopId, int objectCount) {

        Boolean b = false;
        logger.info("OBJECT SERVICE SELECT SHOP USER ID START");

        try {
            b = shopMapper.updShopCount(shopId, objectCount);
            logger.info("OBJECT SERVICE UPDATE SHOP COUNT SUCCESS!");
            logger.info("result: shopId = " + shopId + "objectCount = " + objectCount);
        } catch (Exception e) {
            logger.error("OBJECT SERVICE UPDATE SHOP COUNT ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: shopId = " + shopId + "objectCount = " + objectCount);
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
