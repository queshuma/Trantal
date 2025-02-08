package com.shuzhi.system_shop.Service;

import com.shuzhi.system_shop.Mapper.ShopDataMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/3/2
 *
 * @version 1.0
 */

@Service
public class ShopDataService {

    private final ShopDataMapper dataCenterMapper;

    public ShopDataService(ShopDataMapper dataCenterMapper) {
        this.dataCenterMapper = dataCenterMapper;
    }

    /**
     * 根据商家查询购物车商品数量
     * @param objIdString
     * @return
     */
    public Long getCoutByBuss(String objIdString) {
        return dataCenterMapper.getCoutByBuss(objIdString);
    }

    /**
     * 根据商家查询加入购物车的人数
     * @param objIdString
     * @return
     */
    public Long getPersonCoutByBuss(String objIdString) {
        return dataCenterMapper.getPersonCoutByBuss(objIdString);
    }

    /**
     * 查找所有购物车商品数量
     * @return
     */
    public Long getCout() {
        return dataCenterMapper.getCout();
    }

    /**
     * 根据商家查询加入购物车的人数
     * @return
     */
    public Long getPersonCout() {
        return dataCenterMapper.getPersonCout();
    }
}
