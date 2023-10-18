package com.shuzhi.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import static com.shuzhi.result.parmSetting.Authority.*;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/10/7
 *
 * @version 1.0
 */

//@Configuration
public class UserInterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        System.out.println("进入拦截器");
//        registry.addInterceptor(new TouristInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/User/login", "/User/logout", "/User/findAll")
//                .order(1);

        registry.addInterceptor(new ClientInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(TouristAuthority)
                .order(1);

        registry.addInterceptor(new BusinessInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(ClientAuthority)
                .order(2);

        registry.addInterceptor(new MasterInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(BusinessAuthority)
                .order(3);


    }


}
