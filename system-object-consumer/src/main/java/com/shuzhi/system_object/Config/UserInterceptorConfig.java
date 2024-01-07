package com.shuzhi.system_object.Config;

import com.shuzhi.system_object.UserInterceptor.BusinessInterceptor;
import com.shuzhi.system_object.UserInterceptor.ClientInterceptor;
import com.shuzhi.system_object.UserInterceptor.MasterInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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

//        registry.addInterceptor(new ClientInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns(TouristAuthority)
//                .order(1);
//
//        registry.addInterceptor(new BusinessInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns(ClientAuthority)
//                .order(2);
//
//        registry.addInterceptor(new MasterInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns(BusinessAuthority)
//                .order(3);

                registry.addInterceptor(new ClientInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/**")
                .order(1);


    }


}
