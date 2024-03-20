package com.shuzhi.system_objclasses.WebAspect;

import com.shuzhi.common.ResponseResult;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/9/1
 *
 * @version 1.0
 */

@Aspect
@Component
public class ControlllerAspect {

    private final Logger logger = LoggerFactory.getLogger(ControlllerAspect.class);

    @Before("execution(public * com.shuzhi.system_objclasses.Controller.*.*(..))")
    public void ControllerBefore() throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //记录客户端的infomation
        logger.info("==============================================");
        logger.info("===========System Client Infomation===========");
        logger.info("==============================================");
        logger.info("url: " + request.getRequestURL());
        logger.info("method: " + request.getMethod());
        logger.info("headerName: " + request.getHeaderNames());
        logger.info("header: " + request.getHeader(String.valueOf(request.getHeaderNames())));
        logger.info("IP: " + request.getRemoteAddr());
        logger.info("========== TRANTAL CONTROLLER START! ==========");
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name= (String) enumeration.nextElement();
            logger.info("{} : {}", name, request.getParameter(name));
        }

    }

    @AfterReturning(returning = "obj", pointcut = "execution(public * com.shuzhi.system_objclasses.Controller.*.*(..))")
    public void ControllerAfter(ResponseResult obj) throws Throwable {
        logger.info("Response: " + obj.getResultCode());
        logger.info("========== TRANTAL CONTROLLER END! ==========");
    }
}
