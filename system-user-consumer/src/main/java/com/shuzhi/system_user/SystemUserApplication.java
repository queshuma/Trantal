package com.shuzhi.system_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

//@EnableOpenApi
@SpringBootApplication
@MapperScan("com.shuzhi.system_user.Mapper")
public class SystemUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemUserApplication.class, args);
    }

}
