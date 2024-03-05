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
 * Description: 购物车功能服务
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
    public Boolean addShop(Long userId, Long objectId, Long shopCout) {

        int b = 0;
        int ShopLength = 0;

        Date shopTime = new Date();

        logger.info("OBJECT SERVICE ADD OBJECT PHONE START");
        try {
            Long shopStatus = 1L;
            ShopLength = shopMapper.getShopUserIdObjectId(userId, objectId, shopStatus);
            if (ShopLength == Common.ZERO) {
                //购物车添加商品
                shopMapper.addShop(userId, objectId, shopCout, shopTime, shopStatus);
            } else if (ShopLength == Common.ONE){
                //购物车已存在商品
                shopMapper.updShopCoutAddShop(userId, objectId, shopCout, shopTime);
            }
            b = 1;
            logger.info("SHOP SERVICE ADD ORDER INFO SUCCESS!");
        } catch (Exception e) {
            logger.error("SHOP SERVICE ADD ORDER INFO ERROR!");
            logger.error("ERROE:" + e);
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
    public List<ShopDTO> getShopUserId(Long userId) {

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
    public Boolean updShopCout(Long shopId, Long userId, Long objectCout) {

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

    /**
     * 更新购物车状态
     * @param shopId
     * @param shopStatus
     * @return
     */
    public Boolean updShopStatus(Long shopId, Long shopStatus) {

        Boolean b = false;
        logger.info("OBJECT SERVICE SELECT SHOP USER ID START");

        try {
            b = shopMapper.updShopStatus(shopId, shopStatus);
            logger.info("OBJECT SERVICE UPDATE SHOP STATUS SUCCESS!");
            logger.info("result: shopId = " + shopId + "objectStatus = " + shopStatus);
        } catch (Exception e) {
            logger.error("OBJECT SERVICE UPDATE SHOP STATUS ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: shopId = " + shopId + "objectStatus = " + shopStatus);
        }

        return b;
    }

    /**
     * 查找所有购物车商品
     * @return
     */
    public List<ShopDTO> getShopAll() {
        List<ShopDTO> shopDTOList = null;
        logger.info("OBJECT SERVICE SELECT SHOP USER ID START");
        int shopStatus = 1;

        try {
            shopDTOList = shopMapper.getShopAll(shopStatus);
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
}
