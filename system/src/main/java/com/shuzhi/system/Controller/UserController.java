package com.shuzhi.system.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.result.code.UserCode;
import com.shuzhi.result.parmSetter.UserSetting;
import com.shuzhi.entity.UserEntity;
import com.shuzhi.system.Info.UserInfo;
import com.shuzhi.system.Service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.shuzhi.common.SystemUtils.*;
import static com.shuzhi.result.common.ZERO;


/**
 * 用户接口模块
 */
@RestController
@RequestMapping("/User")
public class UserController<T> {

    //依赖注入UserService服务，用于处理操作调用
    private final UserService userService;
    //依赖注入Logger服务，用于日志输出
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 新增用户
     * @param userInfo
     * @return ResponseResult
     */
    @PostMapping("/add")
    public ResponseResult add(UserInfo userInfo) {

        logger.info("========== TRANTAL USER CONTROLLER ADD USER INFO START! ==========");
        Boolean b = false;

        //输入姓名为空
        if(SystemUtils.isNullOrEmpty(userInfo.getUserName())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --NAME-- INPUT IS NULL ! ");
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_NAME_NULL);
        }
        if (userInfo.getUserName().length() < UserSetting.USER_INFO_NAME_SIZE_MIN || userInfo.getUserName().length() > UserSetting.USER_INFO_NAME_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --NAME SIZE-- TO LONG/SHORT ! ");
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_NAME_SIZE);
        }
        //输入昵称为空
        if (SystemUtils.isNullOrEmpty(userInfo.getUserAccount())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --ACCOUNT-- INPUT IS NULL ! ");
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_ACCOUNT_NULL);
        }
        if (userInfo.getUserAccount().length() < UserSetting.USER_INFO_ACCOUNT_SIZE_MIN || userInfo.getUserName().length() > UserSetting.USER_INFO_ACCOUNT_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --ACCOUNT SIZE-- TO LONG/SHORT ! ");
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_ACCOUNT_SIZE);
        }
        //输入密码为空
        if(SystemUtils.isNullOrEmpty(userInfo.getUserPassword())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PASSWORD-- INPUT IS NULL ! ");
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_PASSWORD_NULL);
        }
        if (userInfo.getUserPassword().length() < UserSetting.USER_INFO_PASSWORD_SIZE_MIN || userInfo.getUserName().length() > UserSetting.USER_INFO_PASSWORD_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PASSWORD SIZE-- TO LONG/SHORT ! ");
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_PASSWORD_SIZE);
        }
        //输入手机号为空
        if(SystemUtils.isNullOrEmpty(userInfo.getUserPhone())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PHONE-- INPUT IS NULL ! ");
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_PHONE_NULL);
        }
        if (userInfo.getUserPhone().length() < UserSetting.USER_INFO_PHONE_SIZE_MIN || userInfo.getUserPhone().length() > UserSetting.USER_INFO_PHONE_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PHONE SIZE-- TO LONG/SHORT ! ");
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_PHONE_SIZE);
        }
        //输入邮箱为空
        if(SystemUtils.isNullOrEmpty(userInfo.getUserEmail())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --EMAIL-- INPUT IS NULL ! ");
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_EMAIL_SIZE);
        }
        if (userInfo.getUserEmail().length() < UserSetting.USER_INFO_EMAIL_SIZE_MIN || userInfo.getUserName().length() > UserSetting.USER_INFO_EMAIL_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --EMAIL SIZE-- TO LONG/SHORT ! ");
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_EMAIL_SIZE);
        }
        b = userService.addUser(userInfo);
        System.out.println(userInfo);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            outWorkInfomation("ADD", (T) userInfo);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_ADD_SUCCESS);
        }

        logger.error("TRANTAL USER CONTROLLER USER INFO --ADD FAIL-- ! ");
        outWorkInfomation("ADD", (T) userInfo);
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL);

    }

    /**
     * 修改用户信息
     * @param userEntity
     * @return ResponseResult
     */
    @PostMapping("/update")
    public ResponseResult update(UserEntity userEntity) {

        logger.info("========== TRANTAL USER CONTROLLER UPDATE USER INFO START! ==========");
        Boolean b = false;

        //输入姓名为空
        if(SystemUtils.isNullOrEmpty(userEntity.getUser_name())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --NAME-- INPUT IS NULL ! ");
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_NAME_NULL);
        }
        //输入姓名长度不合规
        if (userEntity.getUser_name().length() < UserSetting.USER_INFO_NAME_SIZE_MIN || userEntity.getUser_name().length() > UserSetting.USER_INFO_NAME_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --NAME SIZE-- TOO LONG/SHORT ! ");
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_NAME_SIZE);
        }
        //输入昵称为空
        if (SystemUtils.isNullOrEmpty(userEntity.getUser_account())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --ACCOUNT-- INPUT IS NULL ! ");
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_ACCOUNT_NULL);
        }
        //输入昵称长度不合规
        if (userEntity.getUser_account().length() < UserSetting.USER_INFO_ACCOUNT_SIZE_MIN || userEntity.getUser_account().length() > UserSetting.USER_INFO_ACCOUNT_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --ACCOUNT SIZE-- TOO LONG/SHORT ! ");
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_ACCOUNT_SIZE);
        }
        //输入密码为空
        if(SystemUtils.isNullOrEmpty(userEntity.getUser_password())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PASSWORD-- INPUT IS NULL ! ");
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_PASSWORD_NULL);
        }
        //输入密码长度不合规
        if (userEntity.getUser_password().length() < UserSetting.USER_INFO_PASSWORD_SIZE_MIN || userEntity.getUser_name().length() > UserSetting.USER_INFO_PASSWORD_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PASSWORD SIZE-- TOO LONG/SHORT ! ");
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_PASSWORD_SIZE);
        }
        //输入手机号为空
        if(SystemUtils.isNullOrEmpty(userEntity.getUser_phone())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PHONE-- INPUT IS NULL ! ");
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_PHONE_NULL);
        }
        //输入手机号长度不合规
        if (userEntity.getUser_phone().length() < UserSetting.USER_INFO_PHONE_SIZE_MIN || userEntity.getUser_phone().length() > UserSetting.USER_INFO_PHONE_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PHONE SIZE-- TOO LONG/SHORT ! ");
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_PHONE_SIZE);
        }
        //输入邮箱为空
        if(SystemUtils.isNullOrEmpty(userEntity.getUser_email())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --EMAIL-- INPUT IS NULL ! ");
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_EMAIL_SIZE);
        }
        //输入邮箱长度不合规
        if (userEntity.getUser_email().length() < UserSetting.USER_INFO_EMAIL_SIZE_MIN || userEntity.getUser_name().length() > UserSetting.USER_INFO_EMAIL_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --EMAIL SIZE-- TOO LONG/SHORT ! ");
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_EMAIL_SIZE);
        }
        b = userService.updUser(userEntity);

        //更新数据正反馈，向前端返回正确码
        if (b) {
            outWorkInfomation("UPDATE", (T) userEntity);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_UPD_SUCCESS, userEntity);
        }

        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE FAIL-- ! ");
        outWorkInfomation("UPDATE", (T) userEntity);
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL);
    }

    /**
     * 修改用户权限等级
     * @param userId
     * @param userLevel
     * @return
     */
    @PostMapping("/update/level")
    public ResponseResult updateLevel(int userId, int userLevel) {
        logger.info("========== TRANTAL USER CONTROLLER UPDATE USER LEVEL INFO START! ==========");
        Boolean b = false;

        if (userId < UserSetting.USER_INFO_LEVER_MIN || userLevel > UserSetting.USER_INFO_LEVEL_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --LEVEL SIZE-- TOO LARGE/SMALL ! ");
            outWorkInfomation("UPDATE LEVEL", null);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_LEVEL_OVER_LINE);
        }

        b = userService.updUserLevel(userId, userLevel);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            outWorkInfomation("UPDATE LEVEL", null);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_UPD_SUCCESS);
        }
        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE LEVEL FAIL-- ! ");
        outWorkInfomation("UPDATE LEVEL", null);
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL);
    }

    /**
     * 修改用户状态
     * @param userId
     * @param userStatus
     * @return
     */
    @PostMapping("/update/status")
    public ResponseResult updateStatus(int userId, int userStatus) {
        logger.info("========== TRANTAL USER CONTROLLER UPDATE USER STATUS INFO START! ==========");
        Boolean b = false;

        if (userId < UserSetting.USER_INFO_STATUS_MIN || userStatus > UserSetting.USER_INFO_STATUS_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --STATUS SIZE-- TOO LARGE/SMALL ! ");
            outWorkInfomation("UPDATE STATUS", null);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_STATUS_OVER_LINE);
        }

        b = userService.updUserLevel(userId, userStatus);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            outWorkInfomation("UPDATE", null);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_UPD_SUCCESS);
        }
        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE STATUS FAIL-- ! ");
        outWorkInfomation("UPDATE", null);
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL);
    }
    //删除用户
    public ResponseResult updateStatus(int userId) {
        logger.info("========== TRANTAL USER CONTROLLER UPDATE USER STATUS INFO START! ==========");
        Boolean b = false;

        int userStatus = ZERO;

        b = userService.updUserLevel(userId, userStatus);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            outWorkInfomation("UPDATE", null);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_DEL_SUCCESS);
        }
        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE STATUS FAIL-- ! ");
        outWorkInfomation("UPDATE", null);
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL);
    }

    /**
     * 查找所有用户信息
     * @return
     */
    @GetMapping("/findAll")
    public ResponseResult<List<UserEntity>> findAll() {

        logger.info("========== TRANTAL USER CONTROLLER SELECT ALL USER START! ==========");
        List<UserEntity> userEntityList = null;
        userEntityList = userService.getAllUser();
        if (userEntityList == null) {
            logger.warn("SELECT ALL USER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL USER INFO: " + userEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT ALL USER END! ==========");
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_FIND_SUCCESS, userEntityList);
    }

    /**
     * 根据用户手机号查找(唯一)
     * @param userPhone
     * @return
     */
    @PostMapping("/findByPhone")
    public ResponseResult<UserEntity> findByPhone(String userPhone) {

        logger.info("========== TRANTAL USER CONTROLLER SELECT USER PHONE START! ==========");
        UserEntity userEntity = null;
        String resultCode = UserCode.SYSTEM_USER_INFO_FIND_SUCCESS;
        if(!SystemUtils.isNull(userPhone)) {
            userEntity = userService.getUserPhone(userPhone);
        } else {
            logger.error("ERROR : THE USER PHONE INPUT IS NULL! ");
            resultCode = UserCode.SYSTEM_USER_ERROR_FIND_PHONE_NULL;
        }
        if (userEntity == null) {
            logger.info("SELECT BY PHONE USER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL USER INFO: " + userEntity);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT USER BY PHONE END! ==========");
        return ResponseResultFactory.buildResponseFactory(resultCode, userEntity);
    }

    /**
     * 根据昵称查找用户(唯一)
     * @param userAccount
     * @return
     */
    @PostMapping("/findByAccount")
    public ResponseResult<UserEntity> findByAccount(String userAccount) {

        logger.info("========== TRANTAL USER CONTROLLER SELECT USER ACCOUNT START! ==========");
        UserEntity userEntity = null;
        String resultCode = UserCode.SYSTEM_USER_INFO_FIND_SUCCESS;
        if(!SystemUtils.isNull(userAccount)) {
            userEntity = userService.getUserAccount(userAccount);
        } else {
            logger.error("ERROR : THE USER ACCOUNT INPUT IS NULL! ");
            resultCode = UserCode.SYSTEM_USER_ERROR_FIND_ACCOUNT_NULL;
        }
        if (userEntity == null) {
            logger.info("SELECT BY ACCOUNT USER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL USER INFO: " + userEntity);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT USER BY ACCOUNT END! ==========");
        return ResponseResultFactory.buildResponseFactory(resultCode, userEntity);
    }

    /**
     * 根据邮箱查找用户(唯一)
     * @param userEmail
     * @return
     */
    @PostMapping("/findByEmail")
    public ResponseResult<UserEntity> findByEmail(String userEmail) {

        logger.info("========== TRANTAL USER CONTROLLER SELECT USER EMAIL START! ==========");
        UserEntity userEntity = null;
        String resultCode = UserCode.SYSTEM_USER_INFO_FIND_SUCCESS;
        if(!SystemUtils.isNull(userEmail)) {
            userEntity = userService.getUserEmail(userEmail);
        } else {
            logger.error("ERROR : THE USER EMAIL INPUT IS NULL! ");
            resultCode = UserCode.SYSTEM_USER_ERROR_FIND_EMAIL_NULL;
        }
        if (userEntity == null) {
            logger.info("SELECT BY EMAIL USER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL USER INFO: " + userEntity);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT USER BY EMAIL END! ==========");
        return ResponseResultFactory.buildResponseFactory(resultCode, userEntity);
    }

    /**
     * 根据用户姓名查找(不唯一)
     * @param userName
     * @return
     */
    @PostMapping("/findByName")
    public ResponseResult<List<UserEntity>> findByName(String userName) {

        logger.info("========== TRANTAL USER CONTROLLER SELECT USER NAME START! ==========");
        List<UserEntity> userEntityList = null;
        String resultCode = UserCode.SYSTEM_USER_INFO_FIND_SUCCESS;
        if(!SystemUtils.isNull(userName)) {
            userEntityList = userService.getUserName(userName);
        } else {
            logger.error("ERROR : THE USER NAME INPUT IS NULL! ");
            resultCode = UserCode.SYSTEM_USER_ERROR_FIND_NAME_NULL;
        }
        if (listIsNull(userEntityList)) {
            logger.info("SELECT BY NAME USER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL USER INFO: " + userEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT USER BY NAME END! ==========");
        return ResponseResultFactory.buildResponseFactory(resultCode, userEntityList);
    }

    //
    //用户接口层功能模块
    //

    /**
     * 抽离结尾输出代码
     * @param toDo
     * @param t
     */
    public void outWorkInfomation(String toDo, T t) {
        logger.info("TRANTAL ALL USER INFO: " + t);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER " + toDo + " USER INFO END! ==========");
    }
}
