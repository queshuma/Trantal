package com.shuzhi.system_object.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Author: SHUZHI
 * Date: 2024/3/2
 *
 * @version 1.0
 */
@Mapper
public interface ObjectDataMapper {

    /**
     * 根据商家查询商品数量
     * @param userId
     * @return
     */
    @Select("SELECT COUNT(object_id) FROM trantal_object WHERE user_id = #{userId}")
    Long getCoutByBuss(Long userId);

    /**
     * 根据商品状态和商家查询商品数量
     * @param userId
     * @return
     */
    @Select("SELECT COUNT(object_id) FROM trantal_object WHERE user_id = #{userId} AND object_status = #{objectStatus}")
    Long getCoutByBussByStatus(Long userId, int objectStatus);

    /**
     * 查询所有商品状态数量
     * @param objectStatus
     * @return
     */
    @Select("SELECT COUNT(object_id) FROM trantal_object WHERE object_status = #{objectStatus}")
    Long getCoutAll(int objectStatus);

}
