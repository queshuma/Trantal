package com.shuzhi.system.Mapper;

import com.shuzhi.entity.ObjectEntity;
import com.shuzhi.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * User_数据库操作模块
 */
@Mapper
public interface UserMapper {

    /**
     * 添加用户操作
     * @param userEntity
     * @return
     */
    @Insert("INSERT INTO trantal_user (user_name, user_account, user_email, user_password, user_phone, user_time, user_last) VALUES (#{userName}, #{userAccount}, #{userEmail}, #{userPassword}, #{userPhone}, #{userTime}, #{userLast})")
    int addUser(UserEntity userEntity);

    /**
     * 修改用户信息
     * @param userEntity
     * @return
     */
    @Update("UPDATE trantal_user SET user_name = #{userName}, user_account = #{userAccount}, user_email = #{userEmail}, user_password = #{userPassword}, user_phone = #{userPhone}, user_time = #{userTime}, user_last = #{userLast} WHERE user_id = #{userId}")
    int updUser(UserEntity userEntity);
    /**
     * 修改用户登记信息
     * @param userId
     * @param userLevel
     * @return
     */
    @Update("UPDATE trantal_user SET user_level = #{userLevel} WHERE user_id = #{userId}")
    int updUserLevel(int userId, int userLevel);
    /**
     * 修改用户状态信息
     * @param userId
     * @param userStatus
     * @return
     */
    @Update("UPDATE trantal_user SET user_level = #{userLevel} WHERE user_id = #{userId}")
    int delUser(int userId, int userStatus);


    /**
     * 查询所有用户数据库操作
     * @return
     */
    @Select("SELECT * FROM trantal_user")
    @Results(id = "userResultMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userAccount", column = "user_account"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "userPassword", column = "user_password"),
            @Result(property = "userTime", column = "user_time"),
            @Result(property = "userLast", column = "user_last"),
            @Result(property = "userLevel", column = "user_level"),
            @Result(property = "userStatus", column = "user_status")
    })
    List<UserEntity> getAllUser();

    /**
     * 根据手机号查询用户操作
     * @param userPhone
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_phone = #{userPhone}")
    @ResultMap("userResultMap")
    UserEntity getUserPhone(String userPhone);

    /**
     * 根据姓名查询用户操作
     * @param userName
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_name = #{userName}")
    @ResultMap("userResultMap")
    List<UserEntity> getUserName(String userName);

    /**
     * 根据昵称查询用户操作
     * @param userAccount
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_account = #{userAccount}")
    @ResultMap("userResultMap")
    UserEntity getUserAccount(String userAccount);

    /**
     * 根据邮箱查询用户操作
     * @param userEmail
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_email = #{userEmail}")
    @ResultMap("userResultMap")
    UserEntity getUserEmail(String userEmail);
}
