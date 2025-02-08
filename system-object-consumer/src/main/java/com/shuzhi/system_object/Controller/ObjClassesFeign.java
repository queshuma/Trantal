package com.shuzhi.system_object.Controller;

import com.shuzhi.system_object.Config.LoadBalancerConfig;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
@FeignClient(name = "system-objclasses", configuration = LoadBalancerConfig.class)
public interface ObjClassesFeign {

    @GetMapping("/ObjClasses/Feign/findById")
    String findById(@RequestParam("classesId") Long classesId);

    @GetMapping("/ObjClasses/Feign/findByName")
    Long findByName(@RequestParam("classesName") String classesName);
}
