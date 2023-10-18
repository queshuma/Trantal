package com.shuzhi.system.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.entity.UserEntity;
import com.shuzhi.result.code.UserCode;
import com.shuzhi.result.parmSetting.UserSetting;
import com.shuzhi.system.Info.UserInfo;
import com.shuzhi.system.Service.UserService;
import com.shuzhi.system.config.CookieConfig;
import com.shuzhi.system.config.JwtConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.shuzhi.common.SystemUtils.*;
import static com.shuzhi.result.common.ZERO;


/**
 * 用户接口模块
 */
@Api("用户接口模块")
@RestController
@RequestMapping("/User")
public class UserController {

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
    @ApiOperation("新增用户")
    @PostMapping("/add")
    public ResponseResult add(UserInfo userInfo) {

        Boolean b = false;

        //输入姓名为空
        if(SystemUtils.isNullOrEmpty(userInfo.getUserName())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --NAME-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_NAME_NULL);
        }
        if (userInfo.getUserName().length() < UserSetting.USER_INFO_NAME_SIZE_MIN || userInfo.getUserName().length() > UserSetting.USER_INFO_NAME_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --NAME SIZE-- TO LONG/SHORT ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_NAME_SIZE);
        }
        //输入昵称为空
        if (SystemUtils.isNullOrEmpty(userInfo.getUserAccount())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --ACCOUNT-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_ACCOUNT_NULL);
        }
        if (userInfo.getUserAccount().length() < UserSetting.USER_INFO_ACCOUNT_SIZE_MIN || userInfo.getUserName().length() > UserSetting.USER_INFO_ACCOUNT_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --ACCOUNT SIZE-- TO LONG/SHORT ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_ACCOUNT_SIZE);
        }
        //输入密码为空
        if(SystemUtils.isNullOrEmpty(userInfo.getUserPassword())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PASSWORD-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_PASSWORD_NULL);
        }
        if (userInfo.getUserPassword().length() < UserSetting.USER_INFO_PASSWORD_SIZE_MIN || userInfo.getUserName().length() > UserSetting.USER_INFO_PASSWORD_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PASSWORD SIZE-- TO LONG/SHORT ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_PASSWORD_SIZE);
        }
        //输入手机号为空
        if(SystemUtils.isNullOrEmpty(userInfo.getUserPhone())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PHONE-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_PHONE_NULL);
        }
        if (userInfo.getUserPhone().length() < UserSetting.USER_INFO_PHONE_SIZE_MIN || userInfo.getUserPhone().length() > UserSetting.USER_INFO_PHONE_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PHONE SIZE-- TO LONG/SHORT ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_PHONE_SIZE);
        }
        //输入邮箱为空
        if(SystemUtils.isNullOrEmpty(userInfo.getUserEmail())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --EMAIL-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_EMAIL_SIZE);
        }
        if (userInfo.getUserEmail().length() < UserSetting.USER_INFO_EMAIL_SIZE_MIN || userInfo.getUserName().length() > UserSetting.USER_INFO_EMAIL_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --EMAIL SIZE-- TO LONG/SHORT ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL_EMAIL_SIZE);
        }
        b = userService.addUser(userInfo);
        System.out.println(userInfo);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_ADD_SUCCESS);
        }

        logger.error("TRANTAL USER CONTROLLER USER INFO --ADD FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_ADD_FAIL);

    }

    /**
     * 修改用户信息
     * @param userEntity
     * @return ResponseResult
     */
    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public ResponseResult update(UserEntity userEntity, HttpServletRequest httpServletRequest) {

        Boolean b = false;

        //输入姓名为空
        if(SystemUtils.isNullOrEmpty(userEntity.getUserName())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --NAME-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_NAME_NULL);
        }
        //输入姓名长度不合规
        if (userEntity.getUserName().length() < UserSetting.USER_INFO_NAME_SIZE_MIN || userEntity.getUserName().length() > UserSetting.USER_INFO_NAME_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --NAME SIZE-- TOO LONG/SHORT ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_NAME_SIZE);
        }
        //输入昵称为空
        if (SystemUtils.isNullOrEmpty(userEntity.getUserAccount())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --ACCOUNT-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_ACCOUNT_NULL);
        }
        //输入昵称长度不合规
        if (userEntity.getUserAccount().length() < UserSetting.USER_INFO_ACCOUNT_SIZE_MIN || userEntity.getUserAccount().length() > UserSetting.USER_INFO_ACCOUNT_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --ACCOUNT SIZE-- TOO LONG/SHORT ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_ACCOUNT_SIZE);
        }
        //输入密码为空
        if(SystemUtils.isNullOrEmpty(userEntity.getUserPassword())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PASSWORD-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_PASSWORD_NULL);
        }
        //输入密码长度不合规
        if (userEntity.getUserPassword().length() < UserSetting.USER_INFO_PASSWORD_SIZE_MIN || userEntity.getUserPassword().length() > UserSetting.USER_INFO_PASSWORD_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PASSWORD SIZE-- TOO LONG/SHORT ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_PASSWORD_SIZE);
        }
        //输入手机号为空
        if(SystemUtils.isNullOrEmpty(userEntity.getUserPhone())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PHONE-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_PHONE_NULL);
        }
        //输入手机号长度不合规
        if (userEntity.getUserPhone().length() < UserSetting.USER_INFO_PHONE_SIZE_MIN || userEntity.getUserPhone().length() > UserSetting.USER_INFO_PHONE_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --PHONE SIZE-- TOO LONG/SHORT ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_PHONE_SIZE);
        }
        //输入邮箱为空
        if(SystemUtils.isNullOrEmpty(userEntity.getUserEmail())) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --EMAIL-- INPUT IS NULL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_EMAIL_SIZE);
        }
        //输入邮箱长度不合规
        if (userEntity.getUserEmail().length() < UserSetting.USER_INFO_EMAIL_SIZE_MIN || userEntity.getUserName().length() > UserSetting.USER_INFO_EMAIL_SIZE_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --EMAIL SIZE-- TOO LONG/SHORT ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_EMAIL_SIZE);
        }
        b = userService.updUser(userEntity);

        //更新数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_UPD_SUCCESS, userEntity);
        }

        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL);
    }

    /**
     * 修改用户权限等级
     * @param userId
     * @param userLevel
     * @return
     */
    @ApiOperation("修改用户权限等级")
    @PostMapping("/update/level")
    public ResponseResult updateLevel(int userId, int userLevel) {
        Boolean b = false;

        if (userId < UserSetting.USER_INFO_LEVER_MIN || userLevel > UserSetting.USER_INFO_LEVEL_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --LEVEL SIZE-- TOO LARGE/SMALL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL_LEVEL_OVER_LINE);
        }

        b = userService.updUserLevel(userId, userLevel);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_UPD_SUCCESS);
        }
        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE LEVEL FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL);
    }

    /**
     * 修改用户状态
     * @param userId
     * @param userStatus
     * @return
     */
    @ApiOperation("修改用户状态")
    @PostMapping("/update/status")
    public ResponseResult updateStatus(int userId, int userStatus) {
        Boolean b = false;

        if (userId < UserSetting.USER_INFO_STATUS_MIN || userStatus > UserSetting.USER_INFO_STATUS_MAX) {
            logger.error("TRANTAL USER CONTROLLER USER INFO --STATUS SIZE-- TOO LARGE/SMALL ! ");
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_STATUS_OVER_LINE);
        }

        b = userService.updUserLevel(userId, userStatus);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_UPD_SUCCESS);
        }
        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL);
    }
    //删除用户
    public ResponseResult updateStatus(int userId) {
        Boolean b = false;

        int userStatus = ZERO;

        b = userService.updUserLevel(userId, userStatus);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_DEL_SUCCESS);
        }
        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_UPD_FAIL);
    }

    /**
     * 查找所有用户信息
     * @return
     */
    @ApiOperation("查找所有用户")
    @GetMapping("/findAll")
    public ResponseResult findAll(HttpServletRequest httpServletRequest) {
        List<UserEntity> userEntityList = null;
        userEntityList = userService.getAllUser();
        if (userEntityList == null) {
            logger.warn("SELECT ALL USER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL USER INFO: " + userEntityList.toString());
        return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_INFO_FIND_SUCCESS, userEntityList);
    }

    /**
     * 根据用户手机号查找(唯一)
     * @param userPhone
     * @return
     */
    @ApiOperation("根据手机号查找")
    @PostMapping("/findByPhone")
    public ResponseResult findByPhone(String userPhone) {
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
    @ApiOperation("根据昵称查找")
    @PostMapping("/findByAccount")
    public ResponseResult findByAccount(String userAccount) {

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
    @ApiOperation("根据邮箱查找")
    @PostMapping("/findByEmail")
    public ResponseResult findByEmail(String userEmail) {

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
    @ApiOperation("根据姓名查找")
    @PostMapping("/findByName")
    public ResponseResult findByName(String userName) {

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

    /**
     * 用户登陆接口
     * @param info
     * @param password
     * @return
     */
    @ApiOperation("根据邮箱/手机登陆")
    @PostMapping("/login")
    public ResponseResult loginByPhoneAndEmail(String info, String password, HttpServletResponse httpServletResponse) {
        //定义返回的状态码
        String resultCode;

        //初步筛选，邮箱和手机号是否为空
        if (SystemUtils.isNull(info) || SystemUtils.isNull(password)) {
            resultCode = UserCode.SYSTEM_USER_ERROR_LOGIN_INFO_NULL;
            return ResponseResultFactory.buildResponseFactory(resultCode);
        }

        //区分邮箱登陆或者是手机号码登陆
        String token = null;
        if (info.contains("@")) {
            if (info.length() > UserSetting.USER_INFO_EMAIL_SIZE_MAX || info.length() < UserSetting.USER_INFO_EMAIL_SIZE_MIN) {
                resultCode = UserCode.SYSTEM_USER_ERROR_LOGIN_INFO_ERROR;
                return ResponseResultFactory.buildResponseFactory(resultCode);
            }
            token = userService.userEmailCheck(info, password);
        } else {
            if (info.length() > UserSetting.USER_INFO_PHONE_SIZE_MAX || info.length() < UserSetting.USER_INFO_PHONE_SIZE_MIN) {
                resultCode = UserCode.SYSTEM_USER_ERROR_LOGIN_INFO_ERROR;
                return ResponseResultFactory.buildResponseFactory(resultCode);
            }
            token = userService.userPhoneCheck(info, password);
        }

        if (!SystemUtils.isNullOrEmpty(token)) {
            //设置添加cookie
            CookieConfig.setClientCookie(httpServletResponse, info, token);
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_LOGIN_SUCCESS, token);
        } else {
            return ResponseResultFactory.buildResponseFactory(UserCode.SYSTEM_USER_ERROR_LOGIN_FAIL);
        }

    }


    /**
     * 用户退出接口
     * @param httpServletResponse
     * @return
     */
    @ApiOperation("退出登陆")
    @GetMapping("/logout")
    public ResponseResult logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        CookieConfig.delClientCookie(httpServletRequest, httpServletResponse);
        return ResponseResultFactory.buildResponseFactory("200");
    }
}
