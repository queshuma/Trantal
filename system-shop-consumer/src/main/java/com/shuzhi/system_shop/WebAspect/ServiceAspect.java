package com.shuzhi.system_shop.WebAspect;

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
public class ServiceAspect {

    private final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    @Before("execution(public * com.shuzhi.system.Service.*.*(..))")
    public void ControllerBefore() throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //记录Service层获取的的infomation
        logger.info("========== TRANTAL SERVICE START! ==========");
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name= (String) enumeration.nextElement();
            logger.info("{} : {}", name, request.getParameter(name));
        }

    }

    @AfterReturning(returning = "obj", pointcut = "execution(public * com.shuzhi.system.Controller.*.*(..))")
    public void ControllerAfter(ResponseResult obj) throws Throwable {
        logger.info("========== TRANTAL SERVICE END! ==========");
    }
}
