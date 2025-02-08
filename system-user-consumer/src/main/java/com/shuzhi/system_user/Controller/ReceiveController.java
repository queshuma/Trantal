package com.shuzhi.system_user.Controller;

import com.shuzhi.common.ResponseResult;
import com.shuzhi.common.ResponseResultFactory;
import com.shuzhi.common.TokenFunction;
import com.shuzhi.system_user.Entity.ReceiveInfo;
import com.shuzhi.system_user.code.ResultCode;
import com.shuzhi.system_user.Service.ReceiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * 用户接口模块
 */

@RestController
@RequestMapping("/Receive")
public class ReceiveController {

    //依赖注入UserService服务，用于处理操作调用
    private final ReceiveService receiveService;

    //依赖注入Logger服务，用于日志输出
    private final Logger logger = LoggerFactory.getLogger(ReceiveController.class);

    public ReceiveController(ReceiveService receiveService) {
        this.receiveService = receiveService;
    }


    /**
     * 新增用户收件信息
     * @param receiveInfo
     * @return
     */
    @PostMapping("/add")
    public ResponseResult addReceive(ReceiveInfo receiveInfo, HttpServletRequest httpServletRequest) throws ParseException {

        Boolean b = false;
        Boolean hasReceive = false;

        //判断是否存在相同有效地址
        hasReceive = receiveService.hasReceive(receiveInfo, httpServletRequest);
        if (hasReceive) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS , "hasReceive");
        }
        //通过Token获取用户Id
        receiveInfo.setUserId(TokenFunction.tokenGetUserId(httpServletRequest));
        b = receiveService.addReceiveAddress(receiveInfo);

        //插入数据正反馈，向前端返回正确码
        if (b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }

        logger.error("TRANTAL USER CONTROLLER USER INFO --ADD FAIL-- ! ");
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_ERROR);

    }

    /**
     * 根据用户Id查找收件信息
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/findByUserId")
    public ResponseResult findReceiveInfoByUserId(HttpServletRequest httpServletRequest) throws ParseException {
        Long userId = TokenFunction.tokenGetUserId(httpServletRequest);

        List<ReceiveInfo> receiveInfoList =  receiveService.findReceiveInfoByUserId(userId);

        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS, receiveInfoList);
    }

    /**
     * 删除用户的收件信息
     * @param receiveId
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/remove")
    public ResponseResult removeReceive(Long receiveId, HttpServletRequest httpServletRequest) {
        Boolean b = false;
        b = receiveService.receiveId(receiveId, httpServletRequest);
        if(b) {
            return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_SUCCESS);
        }
        return ResponseResultFactory.buildResponseFactory(ResultCode.REQUEST_ERROR);
    }
}
