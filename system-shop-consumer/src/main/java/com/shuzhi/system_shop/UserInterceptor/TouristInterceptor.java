package com.shuzhi.system_shop.UserInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/9/20
 *
 * @version 1.0
 */

@Component
public class TouristInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(TouristInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        try {
            boolean b = (httpServletRequest.getCookies() == null);
            if(!b == true) {
                System.out.println("tourist: " + "true");
                return true;
            } else {
                logger.info("用户权限等级：Tourist");
                return false;
            }

        }catch (Exception e) {
            logger.error("ERROR: " + e);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        try {

        } catch (Exception e) {

        }
    }


}
