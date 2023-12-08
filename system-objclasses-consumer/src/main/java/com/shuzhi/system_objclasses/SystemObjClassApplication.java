package com.shuzhi.system_objclasses;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shuzhi.system_objclasses.Mapper")
public class SystemObjClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemObjClassApplication.class, args);
    }

}
