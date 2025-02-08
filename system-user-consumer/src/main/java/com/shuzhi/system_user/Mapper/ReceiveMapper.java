package com.shuzhi.system_user.Mapper;

/**
 * Author: SHUZHI
 * Date: 2024/2/19
 *
 * @version 1.0
 */

import com.shuzhi.system_user.Entity.ReceiveInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Receive_收件信息模块
 */
@Mapper
public interface ReceiveMapper {

    /**
     * 插入用户收件地址
     * @param receiveInfo
     * @return
     */
    @Insert("INSERT INTO receive_info (user_id, receive_address, receive_phone, receive_name, receive_status, receive_add_time )" +
            "VALUES (#{userId}, #{receiveAddress}, #{receivePhone}, #{receiveName}, #{receiveStatus}, #{receiveAddTime})")
    int addReceive(ReceiveInfo receiveInfo);

    /**
     * 根据用户的Id查找收件地址
     * @param userId
     * @return
     */
    @Select("SELECT * FROM receive_info WHERE user_id=#{userId} AND receive_status=#{receiveStatus}")
    @Results(id = "receiveResultMap", value = {
            @Result(property = "receiveId", column = "receive_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "receiveAddress", column = "receive_address"),
            @Result(property = "receivePhone", column = "receive_phone"),
            @Result(property = "receiveName", column = "receive_name"),
            @Result(property = "receiveStatus", column = "receive_status"),
            @Result(property = "receiveAddTime", column = "receive_add_time")
    })
    List<ReceiveInfo> findReceiveInfoByUserId(Long userId, Long receiveStatus);

    /**
     * 删除收件信息
     * @param receiveId
     * @param userId
     * @return
     */
    @Update("UPDATE receive_info SET receive_status = #{receiveStatus} WHERE user_id = #{userId} AND receive_id = #{receiveId}")
    ReceiveInfo removeReceive(Long receiveId, Long userId, Long receiveStatus);

    /**
     * 判断是否有重复的生效的地址
     * @param receiveInfo
     * @param receiveStatus
     * @return
     */
    @Select("SELECT * FROM receive_info WHERE (receive_address = #{receiveInfo.receiveAddress} AND receive_phone = #{receiveInfo.receivePhone} AND receive_name = #{receiveInfo.receiveName})")
    @ResultMap("receiveResultMap")
    List<ReceiveInfo> hashReceive(ReceiveInfo receiveInfo, Long receiveStatus);
}
