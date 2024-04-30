package com.shuzhi.system_user.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.SystemUtils;
import com.shuzhi.common.TokenFunction;
import com.shuzhi.system_user.Entity.UserEntity;
import com.shuzhi.code.ResultCode;
import com.shuzhi.parmSetting.UserSetting;
import com.shuzhi.system_user.Config.CookieConfig;
import com.shuzhi.system_user.Config.Sha256Utils;
import com.shuzhi.system_user.Info.UserInfo;
import com.shuzhi.system_user.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

import static com.shuzhi.Common.ZERO;


/**
 * 用户接口模块
 */

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
    @PostMapping("/user")
    public ResponseResult add(UserInfo userInfo) {

        Boolean b = false;
        System.out.println(userInfo.getUserPassword());
        userInfo.setUserPassword(Sha256Utils.sha256(userInfo.getUserPassword()));
        b = userService.addUser(userInfo);
        System.out.println(userInfo.toString());

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }

        logger.error("TRANTAL USER CONTROLLER USER INFO --ADD FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_ERROR);

    }

    /**
     * 修改用户信息
     * @param userEntity
     * @return ResponseResult
     */
    @PutMapping("/user")
    public ResponseResult update(@RequestBody UserEntity userEntity) {

        Boolean b = false;

        b = userService.updUser(userEntity);

        //更新数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, userEntity);
        }

        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_ERROR);
    }

    /**
     * 修改用户权限等级
     * @param userId
     * @param userLevel
     * @return
     */
    @PutMapping("/level")
    public ResponseResult updateLevel(int userId, int userLevel) {
        Boolean b = false;

        b = userService.updUserLevel(userId, userLevel);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }
        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE LEVEL FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_ERROR);
    }

    /**
     * 修改用户状态
     * @param userId
     * @param userStatus
     * @return
     */
    @PutMapping("/status")
    public ResponseResult updateStatus(int userId, int userStatus) {
        Boolean b = false;

        b = userService.updUserStatus(userId, userStatus);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }
        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_ERROR);
    }
    //删除用户
    public ResponseResult updateStatus(int userId) {
        Boolean b = false;

        int userStatus = ZERO;

        b = userService.updUserLevel(userId, userStatus);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }
        logger.error("TRANTAL USER CONTROLLER USER INFO --UPDATE STATUS FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_ERROR);
    }

    /**
     * 查找所有用户信息
     * @return
     */
    @GetMapping("/info/all")
    public ResponseResult findAll(int pageNum, int pageSize) {
        List<UserEntity> userEntityList = null;
        userEntityList = userService.getAllUser(pageNum, pageSize);
        if (userEntityList == null) {
            logger.warn("SELECT ALL USER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL USER INFO: " + userEntityList.toString());
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, userEntityList);
    }

    /**
     * 获取用户数量
     * @return
     */
    @GetMapping("/cout")
    public ResponseResult getCout() {
        int userCout = 0;
        userCout = userService.getUserCout();
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, userCout);
    }

    /**
     * 查找用户信息
     * @param httpServletRequest
     * @return
     * @throws ParseException
     */
    @GetMapping("/info")
    public ResponseResult find(HttpServletRequest httpServletRequest) throws ParseException {
        Long userId = new TokenFunction().tokenGetUserId(httpServletRequest);

        UserEntity userEntity = null;
        String resultCode = ResultCode.REQUEST_SUCCESS;
        userEntity = userService.getUserId(userId);
        logger.info("TRANTAL ALL USER INFO: " + userEntity);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT USER BY NAME END! ==========");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, "SUCCESS", userEntity);
    }

    /**
     * 根据用户手机号查找(唯一)
     * @param userPhone
     * @return
     */
    @GetMapping("/info/phone")
    public ResponseResult findByPhone(String userPhone) {
        UserEntity userEntity = null;
        String resultCode = ResultCode.REQUEST_SUCCESS;
        userEntity = userService.getUserPhone(userPhone);
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
    @PostMapping("/info/account")
    public ResponseResult findByAccount(String userAccount) {

        UserEntity userEntity = null;
        String resultCode = ResultCode.REQUEST_SUCCESS;
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
    @PostMapping("/info/email")
    public ResponseResult findByEmail(String userEmail) {

        UserEntity userEntity = null;
        String resultCode = ResultCode.REQUEST_SUCCESS;
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
    @GetMapping("/info/name")
    public ResponseResult findByName(String userName) {

        List<UserEntity> userEntityList = null;
        String resultCode = ResultCode.REQUEST_SUCCESS;
        logger.info("TRANTAL ALL USER INFO: " + userEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT USER BY NAME END! ==========");
        return ResponseResultFactory.buildResponseFactory(resultCode, userEntityList);
    }

    /**
     * 根据用户ID查找(唯一)
     * @param userId
     * @return
     */
    @GetMapping("/info/id")
    public ResponseResult findById(@RequestParam("userId") Long userId) {

        UserEntity userEntity = null;
        String resultCode = ResultCode.REQUEST_SUCCESS;
        userEntity = userService.getUserId(userId);
        logger.info("TRANTAL ALL USER INFO: " + userEntity);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT USER BY NAME END! ==========");
        return ResponseResultFactory.buildResponseFactory(resultCode, userEntity);
    }

    /**
     * 根据用户权限等级查找(不唯一)
     * @param userLevel
     * @return
     */
    @GetMapping("/info/level")
    public ResponseResult findByName(int userLevel) {

        List<UserEntity> userEntityList = null;
        String resultCode = ResultCode.REQUEST_SUCCESS;
        userEntityList = userService.getUserLevel(userLevel);
        logger.info("TRANTAL ALL USER INFO: " + userEntityList);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT USER BY NAME END! ==========");
        return ResponseResultFactory.buildResponseFactory(resultCode, userEntityList);
    }

    /**
     * 修改用户密码
     * @param userId
     * @param password
     * @return
     */
    @PutMapping("/password")
    public ResponseResult updatePassword(Long userId, String password) {
        password = Sha256Utils.sha256(password);
        Boolean b = userService.updatePassword(userId, password);
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, b);
    }

    /**
     * 用户登陆接口
     * @param info
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseResult loginByPhoneAndEmail(String info, String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        //定义返回的状态码
        String resultCode;

        System.out.println("info" + info);
        System.out.println("info" + password);
        //初步筛选，邮箱和手机号是否为空

        //区分邮箱登陆或者是手机号码登陆
        String token = null;
        Long level = null;
        if (info.contains("@")) {
            if (info.length() > UserSetting.USER_INFO_EMAIL_SIZE_MAX || info.length() < UserSetting.USER_INFO_EMAIL_SIZE_MIN) {
                resultCode = ResultCode.REQUEST_SUCCESS;
                return ResponseResultFactory.buildResponseFactory(resultCode);
            }
            System.out.println("邮箱登录：");
            token = userService.userEmailCheck(info, password);
            level = userService.getUserEmail(info).getUserLevel();
        } else {
            if (info.length() > UserSetting.USER_INFO_PHONE_SIZE_MAX || info.length() < UserSetting.USER_INFO_PHONE_SIZE_MIN) {
                resultCode = ResultCode.REQUEST_ERROR;
                return ResponseResultFactory.buildResponseFactory(resultCode);
            }
            System.out.println("手机号登录：");
            token = userService.userPhoneCheck(info, password);
            level = userService.getUserPhone(info).getUserLevel();
        }

        if (!SystemUtils.isNullOrEmpty(token)) {
            //设置添加cookie
            CookieConfig.setClientCookie(httpServletRequest, httpServletResponse, "token", token);
            System.out.println("data: " + httpServletRequest.getCookies());
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, level);
        } else {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS_NOT_DO);
        }

    }

    /**
     * 用户退出接口
     * @param httpServletResponse
     * @return
     */
    @GetMapping("/logout")
    public ResponseResult logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        CookieConfig.delClientCookie(httpServletRequest, httpServletResponse);
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
    }
}
