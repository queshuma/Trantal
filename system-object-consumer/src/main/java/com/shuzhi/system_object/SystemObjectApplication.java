package com.shuzhi.system_object;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shuzhi.system_object.Mapper")
public class SystemObjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemObjectApplication.class, args);
    }

}
