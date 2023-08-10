package com.shuzhi.system.Mapper;

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
    @Insert("INSERT INTO trantal_user (user_name, user_account, user_email, user_password, user_phone, user_time, user_last) VALUES (#{user_name}, #{user_account}, #{user_email}, #{user_password}, #{user_phone}, #{user_time}, #{user_last})")
    int addUser(UserEntity userEntity);

    /**
     * 修改用户信息
     * @param userEntity
     * @return
     */
    @Update("UPDATE trantal_user SET user_name = #{user_name}, user_account = #{user_account}, user_email = #{user_email}, user_password = #{user_password}, user_phone = #{user_phone}, user_time = #{user_time}, user_last = #{user_last} WHERE user_id = #{user_id}")
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
    List<UserEntity> getAllUser();

    /**
     * 根据手机号查询用户操作
     * @param userPhone
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_phone = #{userPhone}")
    UserEntity getUserPhone(String userPhone);

    /**
     * 根据姓名查询用户操作
     * @param userName
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_name = #{userName}")
    List<UserEntity> getUserName(String userName);

    /**
     * 根据昵称查询用户操作
     * @param userAccount
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_account = #{userAccount}")
    UserEntity getUserAccount(String userAccount);

    /**
     * 根据邮箱查询用户操作
     * @param userEmail
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_email = #{userEmail}")
    UserEntity getUserEmail(String userEmail);
}
