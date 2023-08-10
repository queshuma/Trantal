package com.shuzhi.system.Service;

import com.shuzhi.entity.UserEntity;
import com.shuzhi.system.Info.UserInfo;
import com.shuzhi.system.Mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户服务模块
 */
@Service
public class UserService {

    private final UserMapper userMapper;
    final Logger logger = LoggerFactory.getLogger(UserService.class);

    final int SERVICE_ADD_USER_INFO_NUMBER = 1;
    final int SERVICE_UPDATE_USER_INFO_NUMBER = 1;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    @Transactional
    public Boolean addUser(UserInfo userInfo) {

        logger.info("-------TRANTAL USER ADD SERVICE START-------");
        int b = 0;
        UserEntity userEntity = null;
        userEntity = InfoToEntity(userInfo);

        logger.info("USER SERVICE ADD USER PHONE START");
        try {
            b = userMapper.addUser(userEntity);
            logger.info("USER SERVICE ADD USER INFO SUCCESS!");
            logger.info("result: " + userEntity);
        } catch (Exception e) {
            logger.error("USER SERVICE ADD USER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + userEntity);
        }
        logger.info("USER SERVICE ADD USER PHONE END");
        if (b == SERVICE_ADD_USER_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改用户信息
     * @param userEntity
     * @return
     */
    @Transactional
    public Boolean updUser(UserEntity userEntity) {

        logger.info("-------TRANTAL USER UPDATE SERVICE START-------");
        int b = 0;

        logger.info("USER SERVICE UPDATE USER PHONE START");
        try {
            b = userMapper.updUser(userEntity);
            logger.info("USER SERVICE UPDATE USER INFO SUCCESS!");
            logger.info("result: " + userEntity);
        } catch (Exception e) {
            logger.error("USER SERVICE UPDATE USER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + userEntity.toString());
        }
        logger.info("USER SERVICE UPDATE USER PHONE END");
        if (b == SERVICE_UPDATE_USER_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 修改用户等级
     * @param userId
     * @param userLevel
     * @return
     */
    @Transactional
    public Boolean updUserLevel(int userId, int userLevel) {
        logger.info("-------TRANTAL USER UPDATE SERVICE START-------");
        int b = 0;

        logger.info("USER SERVICE UPDATE USER INFO START");
        try {
            b = userMapper.updUserLevel(userId, userLevel);
            logger.info("USER SERVICE UPDATE USER INFO SUCCESS!");
            logger.info("result: userId" + userId + ",userLevel " + userLevel);
        } catch (Exception e) {
            logger.error("USER SERVICE UPDATE USER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: userId" + userId + ",userLevel " + userLevel);
        }
        logger.info("USER SERVICE UPDATE USER INFO END");
        logger.info("-------TRANTAL USER UPDATE SERVICE END-------");
        if (b == SERVICE_UPDATE_USER_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有用户
     * @parm null
     * @return List<UserEntity>
     */
    @Transactional
    public List<UserEntity> getAllUser() {
        logger.info("-------TRANTAL USER SELECT SERVICE START-------");
        List<UserEntity> userEntityList = null;
        logger.info("USER SERVICE SELECT ALL USER START");

        try {
            userEntityList = userMapper.getAllUser();
            logger.info("USER SERVICE SELECT ALL USER SUCCESS!");
            logger.info("result: " + userEntityList.toString());
        } catch (Exception e) {
            logger.error("USER SERVICE SELECT ALL USER ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + userEntityList.toString());
        }
        userEntityList = setListPwdIsNull(userEntityList);
        logger.info("-------TRANTAL USER SELECT SERVICE END-------");
        return userEntityList;
    }

    /**
     * 根据用户手机号查询用户信息
     * @param userPhone
     * @return UserEntity
     */
    @Transactional
    public UserEntity getUserPhone(String userPhone) {
        logger.info("-------TRANTAL USER SELECT SERVICE START-------");
        UserEntity userEntity = null;
        logger.info("USER SERVICE SELECT USER PHONE START");

        try {
            userEntity = userMapper.getUserPhone(userPhone);
            userEntity = setPwdIsNull(userEntity);
            logger.info("USER SERVICE SELECT USER PHONE SUCCESS!");
            logger.info("result: " + userEntity.toString());
        } catch (Exception e) {
            logger.error("USER SERVICE SELECT USER PHONE ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + userEntity);
        }

        logger.info("-------TRANTAL USER SELECT SERVICE END-------");
        return userEntity;
    }

    /**
     * 根据用户昵称查询用户信息
     * @param userAccount
     * @return UserEntity
     */
    @Transactional
    public UserEntity getUserAccount(String userAccount) {
        logger.info("-------TRANTAL USER SELECT SERVICE START-------");
        UserEntity userEntity = null;
        logger.info("USER SERVICE SELECT USER ACCOUNT START");

        try {
            userEntity = userMapper.getUserAccount(userAccount);
            userEntity = setPwdIsNull(userEntity);
            logger.info("USER SERVICE SELECT USER ACCOUNT SUCCESS!");
            logger.info("result: " + userEntity.toString());
        } catch (Exception e) {
            logger.error("USER SERVICE SELECT USER ACCOUNT ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + userEntity.toString());
        }

        logger.info("-------TRANTAL USER SELECT SERVICE END-------");
        return userEntity;
    }

    /**
     * 根据用户邮箱查询用户信息
     * @param userEmail
     * @return UserEntity
     */
    @Transactional
    public UserEntity getUserEmail(String userEmail) {
        logger.info("-------TRANTAL USER SELECT SERVICE START-------");
        UserEntity userEntity = null;
        logger.info("USER SERVICE SELECT USER EMAIL START");

        try {
            userEntity = userMapper.getUserEmail(userEmail);
            userEntity = setPwdIsNull(userEntity);
            logger.info("USER SERVICE SELECT USER EMAIL SUCCESS!");
            logger.info("result: " + userEntity.toString());
        } catch (Exception e) {
            logger.error("USER SERVICE SELECT USER EMAIL ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + userEntity.toString());
        }

        logger.info("-------TRANTAL USER SELECT SERVICE END-------");
        return userEntity;
    }

    /**
     * 根据用户姓名查询信息
     * @param userName
     * @return List<UserEntity>
     */
    @Transactional
    public List<UserEntity> getUserName(String userName) {
        logger.info("-------TRANTAL USER SELECT SERVICE START-------");
        List<UserEntity> userEntityList = null;
        logger.info("USER SERVICE SELECT USER NAME START");

        try {
            userEntityList = userMapper.getUserName(userName);
            userEntityList = setListPwdIsNull(userEntityList);
            logger.info("USER SERVICE SELECT USER NAME SUCCESS!");
            logger.info("result: " + userEntityList.toString());
        } catch (Exception e) {
            logger.error("USER SERVICE SELECT USER NAME ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + userEntityList.toString());
        }

        logger.info("-------TRANTAL USER SELECT SERVICE END-------");
        return userEntityList;
    }

    //
    //用户服务层功能区域
    //
    /**
     * 隐藏单个UserEntity的密码
     * @param userEntity
     * @return
     */
    public UserEntity setPwdIsNull(UserEntity userEntity) {
        userEntity.setUserPassword(null);
        return userEntity;
    }
    /**
     * 隐藏列表用户的UserEntity的密码
     * @param userEntityList
     * @return
     */
    public List<UserEntity> setListPwdIsNull(List<UserEntity> userEntityList) {
        for (UserEntity userEntity: userEntityList) {
            userEntity = setPwdIsNull(userEntity);
        }
        return userEntityList;
    }

    /**
     * 将UserInfo数据转换成UserEntity
     * @param userInfo
     * @return
     */
    public UserEntity InfoToEntity(UserInfo userInfo) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserPassword(userInfo.getUserPassword());
        userEntity.setUserEmail(userInfo.getUserEmail());
        userEntity.setUserPassword(userInfo.getUserPhone());
        userEntity.setUserAccount(userInfo.getUserAccount());
        userEntity.setUserName(userInfo.getUserName());
        userEntity.setUserPhone(userInfo.getUserPhone());
        userEntity.setUserTime(new Date());
        userEntity.setUserLast(new Date());
        return userEntity;
    }

}
