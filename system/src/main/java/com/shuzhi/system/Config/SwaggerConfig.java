package com.shuzhi.system.Config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@Configuration
@EnableOpenApi
public class SwaggerConfig {
        @Bean
        public Docket docket(){
            return new Docket(DocumentationType.OAS_30)
                    .apiInfo(apiInfo())
                    .enable(true)
                    .groupName("shuzhi")
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.shuzhi.system.Controller"))
//                    .paths(PathSelectors.ant("/Controller/**"))
                    .build();
        }


        @SuppressWarnings("all")
        public ApiInfo apiInfo(){
            return new ApiInfo(
                    "SHUZHI WEB API",
                    "Trantal WebSite！",
                    "v1.0",
                    "1428767438@qq.com",
                    "SHUZHI",
                    "Apache 2.0",  //许可证
                    "http://www.apache.org/licenses/LICENSE-2.0" //许可证链接
            );
        }
}
