package com.shuzhi.system_user.Service;

import com.shuzhi.entity.UserEntity;
import com.shuzhi.system_user.Config.JwtConfig;
import com.shuzhi.system_user.Info.UserInfo;
import com.shuzhi.system_user.Mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        int b = 0;
        UserEntity userEntity = null;
        userEntity = InfoToEntity(userInfo);
        userEntity.setUserTime(new Date());
        userEntity.setUserLast(new Date());

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

        return userEntityList;
    }

    /**
     * 根据用户手机号查询用户信息
     * @param userPhone
     * @return UserEntity
     */
    @Transactional
    public UserEntity getUserPhone(String userPhone) {

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

        return userEntity;
    }

    /**
     * 根据用户昵称查询用户信息
     * @param userAccount
     * @return UserEntity
     */
    @Transactional
    public UserEntity getUserAccount(String userAccount) {

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

        return userEntity;
    }

    /**
     * 根据用户邮箱查询用户信息
     * @param userEmail
     * @return UserEntity
     */
    @Transactional
    public UserEntity getUserEmail(String userEmail) {

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

        return userEntity;
    }

    /**
     * 根据用户姓名查询信息
     * @param userName
     * @return List<UserEntity>
     */
    @Transactional
    public List<UserEntity> getUserName(String userName) {

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

        return userEntityList;
    }

    /**
     * 根据手机号进行校验
     * @param phone
     * @param password
     * @return
     */
    @Transactional
    public String userPhoneCheck(String phone, String password) {
        String token = null;
        try {
            UserEntity userEntity = userMapper.loginByPhone(phone, password);
            userEntity = setPwdIsNull(userEntity);
            //String userId
            Long userId = userEntity.getUserId();
            System.out.println("生成token");
            token = JwtConfig.jwtGenerator(String.valueOf(userId), userEntity.getUserName(),
                    userEntity.getUserAccount(), userEntity.getUserLevel());
            logger.info("token: " + token);
            logger.info("USER SERVICE SELECT USER NAME SUCCESS!");
            Date date = new Date();
            int updDate = userMapper.updUserLoginTime(userEntity.getUserId(), date);
            logger.info("用户登录时间更新" + userEntity.getUserId() + " " + updDate);
            logger.info("result: " + userEntity.toString());
        }catch (Exception e) {
            logger.error("USER SERVICE SELECT USER NAME ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + phone + "/" + password);
            return token;
        }
        return token;
    }

    /**
     * 根据邮箱校验
     * @param email
     * @param password
     * @return
     */
    @Transactional
    public String userEmailCheck(String email, String password) {
        String token = null;
        try {
            UserEntity userEntity = userMapper.loginByPhone(email, password);
            userEntity = setPwdIsNull(userEntity);
            //String userId
            Long userId = userEntity.getUserId();
            token = JwtConfig.jwtGenerator(String.valueOf(userId), userEntity.getUserName(),
                    userEntity.getUserAccount(), userEntity.getUserLevel());
            logger.info("token: " + token);
            logger.info("USER SERVICE SELECT USER NAME SUCCESS!");
            Date date = new Date();
            int updDate = userMapper.updUserLoginTime(userEntity.getUserId(), date);
            logger.info("用户登录时间更新" + userEntity.getUserId() + " " + updDate);
            logger.info("result: " + userEntity.toString());
        }catch (Exception e) {
            logger.error("USER SERVICE SELECT USER NAME ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + email + "/" + password);
            return token;
        }


        return token;
    }

    /**
     * 更新用户状态
     * @param userId
     * @param userStatus
     * @return
     */
    public Boolean updUserStatus(int userId, int userStatus) {
        int b = 0;

        logger.info("USER SERVICE UPDATE USER INFO START");
        try {
            b = userMapper.updUserStatus(userId, userStatus);
            logger.info("USER SERVICE UPDATE USER INFO SUCCESS!");
            logger.info("result: userId" + userId + ",userStatus " + userStatus);
        } catch (Exception e) {
            logger.error("USER SERVICE UPDATE USER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: userId" + userId + ",userStatus " + userStatus);
        }
        logger.info("USER SERVICE UPDATE USER INFO END");

        if (b == SERVICE_UPDATE_USER_INFO_NUMBER) {
            return true;
        }
        return false;
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
        userEntity.setUserAccount(userInfo.getUserAccount());
        userEntity.setUserName(userInfo.getUserName());
        userEntity.setUserPhone(userInfo.getUserPhone());
        userEntity.setUserTime(new Date());
        userEntity.setUserLast(new Date());
        return userEntity;
    }
}
