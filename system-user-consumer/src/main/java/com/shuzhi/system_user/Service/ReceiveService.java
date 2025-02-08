package com.shuzhi.system_user.Service;

import com.shuzhi.common.TokenFunction;
import com.shuzhi.system_user.Entity.ReceiveInfo;
import com.shuzhi.system_user.Mapper.ReceiveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户收件信息服务模块
 */
@Service
public class ReceiveService {

    private final ReceiveMapper receiveMapper;
    final Logger logger = LoggerFactory.getLogger(ReceiveService.class);

    final int SERVICE_ADD_USER_INFO_NUMBER = 1;

    @Autowired
    public ReceiveService(ReceiveMapper receiveMapper) {
        this.receiveMapper = receiveMapper;
    }


    /**
     * 添加收件信息
     * @param receiveInfo
     * @return
     */
    public Boolean addReceiveAddress(ReceiveInfo receiveInfo) {
        int b = 0;

        receiveInfo.setReceiveAddTime(new Date());
        receiveInfo.setReceiveStatus(1L);

        logger.info("USER SERVICE ADD USER PHONE START");
        try {
            b = receiveMapper.addReceive(receiveInfo);
            logger.info("USER SERVICE ADD USER INFO SUCCESS!");
            logger.info("result: " + receiveInfo.toString());
        } catch (Exception e) {
            logger.error("USER SERVICE ADD USER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + receiveInfo);
        }
        logger.info("USER SERVICE ADD USER PHONE END");
        if (b == SERVICE_ADD_USER_INFO_NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 根据用户ID获取收件地址
     * @param userId
     * @return
     */
    public List<ReceiveInfo> findReceiveInfoByUserId(Long userId) {
        List<ReceiveInfo> receiveInfoList = null;

        logger.info("USER SERVICE ADD USER INFO START");
        try {
            Long receiveStatus = 1L;
            receiveInfoList = receiveMapper.findReceiveInfoByUserId(userId, receiveStatus);
            logger.info("USER SERVICE ADD USER INFO SUCCESS!");
            logger.info("result: " + receiveInfoList.size());
        } catch (Exception e) {
            logger.error("USER SERVICE ADD USER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + receiveInfoList);
        }
        logger.info("USER SERVICE ADD USER PHONE END");
        return receiveInfoList;
    }

    /**(
     * 删除收件信息
     * @param receiveId
     * @param httpServletRequest
     */
    public Boolean receiveId(Long receiveId, HttpServletRequest httpServletRequest) {
        ReceiveInfo receiveInfo = null;

        logger.info("USER SERVICE ADD USER PHONE START");
        try {
            Long userId = TokenFunction.tokenGetUserId(httpServletRequest);
            Long receiveStatus = 0L;
            receiveInfo = receiveMapper.removeReceive(receiveId, userId, receiveStatus);
            logger.info("USER SERVICE ADD USER INFO SUCCESS!");
            logger.info("result: " + receiveId);
        } catch (Exception e) {
            logger.error("USER SERVICE ADD USER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + receiveId);
        }
        logger.info("USER SERVICE ADD USER PHONE END");
        if (receiveInfo == null) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否有重复的生效的地址
     * @param receiveInfo
     */
    public Boolean hasReceive(ReceiveInfo receiveInfo, HttpServletRequest httpServletRequest) {
        int b = 0;
        List<ReceiveInfo> receiveInfoList = new ArrayList<>();

        logger.info("USER SERVICE ADD USER PHONE START");
        try {
            receiveInfo.setUserId(TokenFunction.tokenGetUserId(httpServletRequest));
            System.out.println(receiveInfo);
            Long receiveStatus = 1L;
//            ReceiveInfo r = receiveMapper.hashReceive(receiveInfo.getReceiveAddress(), receiveInfo.getReceivePhone(), receiveInfo.getReceiveName(), receiveStatus);
            receiveInfoList = receiveMapper.hashReceive(receiveInfo, receiveStatus);
            System.out.println(receiveInfoList.size());
            logger.info("USER SERVICE ADD USER INFO SUCCESS!");
            logger.info("result: " + receiveInfo);
        } catch (Exception e) {
            logger.error("USER SERVICE ADD USER INFO ERROR!");
            logger.error("ERROE:" + e);
            logger.error("result: " + receiveInfo);
        }
        logger.info("USER SERVICE ADD USER PHONE END");
        if (receiveInfoList.size() == 1) {
            return true;
        }
        return false;
    }
}
