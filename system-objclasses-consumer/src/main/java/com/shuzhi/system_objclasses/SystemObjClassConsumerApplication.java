package com.shuzhi.system_objclasses;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

//@EnableOpenApi
@SpringBootApplication
@MapperScan("com.shuzhi.system_objclasses.Mapper")
public class SystemObjClassConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemObjClassConsumerApplication.class, args);
    }

}
