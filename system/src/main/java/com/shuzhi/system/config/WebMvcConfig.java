package com.shuzhi.system.config;

import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/9/20
 *
 * @version 1.0
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry register) {
        System.out.println("进入拦截器");
        //注册拦截器
        register.addInterceptor(loginInterceptor)
                //添加拦截范围
                .addPathPatterns("/**")
                //添加排除拦截
                .excludePathPatterns("/User/login");
    }
}
