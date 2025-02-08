package com.shuzhi.system_object.Service;

import com.shuzhi.system_object.Mapper.ObjectDataMapper;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/3/2
 *
 * @version 1.0
 */

@Service
public class ObjectCenterService {

    private final ObjectDataMapper objectDataMapper;

    public ObjectCenterService(ObjectDataMapper objectDataMapper) {
        this.objectDataMapper = objectDataMapper;
    }

    /**
     * 根据商家查询上架商品数量
     * @param userId
     * @return
     */
    public Long getCoutByBuss(Long userId) {
        return objectDataMapper.getCoutByBuss(userId);
    }

    /**
     * 根据商家查询上架商品数量
     * @param userId
     * @return
     */
    public Long getNorCoutByBuss(Long userId) {
        int objectStatus = 1;
        return objectDataMapper.getCoutByBussByStatus(userId, objectStatus);
    }

    /**
     * 查询所有上架商品数量
     * @return
     */
    public Long getNorCout() {
        int objectStatus = 1;
        return objectDataMapper.getCoutAll(objectStatus);
    }

    /**
     * 根据商家查询下架商品数量
     * @param userId
     * @return
     */
    public Long getErrCoutByBuss(Long userId) {
        int objectStatus = 2;
        return objectDataMapper.getCoutByBussByStatus(userId, objectStatus);
    }

    /**
     * 查询所有下架商品数量
     * @return
     */
    public Long getErrCout() {
        int objectStatus = 2;
        return objectDataMapper.getCoutAll(objectStatus);
    }

}
