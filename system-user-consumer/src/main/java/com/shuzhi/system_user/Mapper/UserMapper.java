package com.shuzhi.system_user.Mapper;

import com.shuzhi.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.Date;
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
    @Insert("INSERT INTO trantal_user (user_name, user_account, user_email, user_password, user_phone, user_time, user_last, user_level) VALUES (#{userName}, #{userAccount}, #{userEmail}, #{userPassword}, #{userPhone}, #{userTime}, #{userLast}, #{userLevel})")
    int addUser(UserEntity userEntity);

    /**
     * 修改用户信息
     * @param userEntity
     * @return
     */
    @Update("UPDATE trantal_user SET user_name = #{userName}, user_account = #{userAccount}, user_email = #{userEmail}, user_phone = #{userPhone}, user_time = #{userTime}, user_last = #{userLast}, user_level = #{userLevel} WHERE user_id = #{userId}")
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

    /**
     * 根据等级查询用户操作
     * @param userLevel
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_level= #{userLevel}")
    @ResultMap("userResultMap")
    List<UserEntity> getUserLevel(int userLevel);

    /**
     * 根据用户Id查询用户信息
     * @param userId
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_id= #{userId}")
    @ResultMap("userResultMap")
    UserEntity getUserId(Long userId);

    /**
     * 根据手机号码进行登陆校验
     * @param userPhone
     * @param userPassword
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_phone = #{userPhone} AND user_password = #{userPassword}")
    @ResultMap("userResultMap")
    UserEntity loginByPhone(String userPhone, String userPassword);

    /**
     * 根据进行登陆校验
     * @param userEmail
     * @param userPassword
     * @return
     */
    @Select("SELECT * FROM trantal_user WHERE user_email = #{userEmail} AND user_password = #{userPassword}")
    @ResultMap("userResultMap")
    UserEntity loginByEmail(String userEmail, String userPassword);

    /**
     * 更新登录时间
     * @param userId
     * @param date
     * @return
     */
    @Update("UPDATE trantal_user set user_last = #{date} WHERE user_id = #{userId}")
    int updUserLoginTime(Long userId, Date date);

    /**
     * 更新用户状态
     * @param userId
     * @param userStatus
     * @return
     */
    @Update("UPDATE trantal_user set user_status = #{userStatus} WHERE user_id = #{userId}")
    int updUserStatus(int userId, int userStatus);

    /**
     * 获取用户数量
     * @return
     */
    @Select("SELECT COUNT(user_id) FROM trantal_user")
    int getUserCout();
}
