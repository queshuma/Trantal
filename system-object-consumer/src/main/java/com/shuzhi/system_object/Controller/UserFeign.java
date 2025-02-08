package com.shuzhi.system_object.Controller;

import com.shuzhi.system_object.Entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: SHUZHI
 * Date: 2024/1/20
 *
 * @version 1.0
 */
@FeignClient(value = "system-user")
public interface UserFeign {
    @GetMapping("/User/Feign/findById")
    UserEntity findById(@RequestParam("userId") Long userId);

    @GetMapping("/User/Feign/findByAccount")
    UserEntity findByAccount(@RequestParam("userAccount") String userAccount);
}
