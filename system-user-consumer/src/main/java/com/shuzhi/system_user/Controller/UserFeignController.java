package com.shuzhi.system_user.Controller;

import com.shuzhi.system_user.Entity.UserEntity;
import com.shuzhi.system_user.code.ResultCode;
import com.shuzhi.system_user.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户接口模块
 */

@RestController
public class UserFeignController {

    //依赖注入UserService服务，用于处理操作调用
    private final UserService userService;

    //依赖注入Logger服务，用于日志输出
    private final Logger logger = LoggerFactory.getLogger(UserFeignController.class);

    @Autowired
    public UserFeignController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据用户ID查找(唯一)
     * @param userId
     * @return
     */
    @GetMapping("/User/Feign/findById")
    public UserEntity findById(@RequestParam("userId") Long userId) {

        UserEntity userEntity = null;
        String resultCode = ResultCode.REQUEST_SUCCESS;
        System.out.println(userId);
        userEntity = userService.getUserId(userId);
        if (userEntity == null) {
            logger.info("SELECT BY NAME USER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL USER INFO: " + userEntity);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT USER BY NAME END! ==========");
        return userEntity;
    }

    /**
     * 根据用户昵称查找(唯一)
     * @param userAccount
     * @return
     */
    @GetMapping("/User/Feign/findByAccount")
    public UserEntity findByAccount(@RequestParam("userAccount") String userAccount) {

        UserEntity userEntity = null;
        String resultCode = ResultCode.REQUEST_SUCCESS;
        if (userEntity == null) {
            logger.info("SELECT BY NAME USER INFO IS NULL!");
        }
        logger.info("TRANTAL ALL USER INFO: " + userEntity);
        logger.info("RETURN");
        logger.info("========== TRANTAL USER CONTROLLER SELECT USER BY NAME END! ==========");
        return userEntity;
    }

}
