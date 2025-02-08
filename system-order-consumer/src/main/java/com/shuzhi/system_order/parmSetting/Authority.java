package com.shuzhi.system_order.parmSetting;

import java.util.Arrays;
import java.util.List;

/**
 * Author: SHUZHI
 * Date: 2023/10/8
 *
 * @version 1.0
 */

public interface Authority {
    //游客权限设置
    public final List<String> TouristAuthority = Arrays.asList(
            "/User/login",
            "/Object/find/all",
            "/Object/find/classes",
            "/Object/find/userAccount",
            "/Object/Classes/findAll",
            "/User/add",
            "/swagger-ui/**",
            "/v3/api-docs"
    );
    //用户权限设置
    public final List<String> ClientAuthority = Arrays.asList(
            "/User/login",
            "/Object/find/all",
            "/Object/find/classes",
            "/Object/find/userAccount",
            "/Object/Classes/findAll",
            "/Order/update/back",
            "/Shop/add",
            "/Shop/find/userId",
            "/Shop/update/count",
            "PUT/Shop/update/delete",
            "/Order/add",
            "/Order/findAll",
            "/User/update",
            "/User/logout"
    );

    //商家权限权限设置
    public final List<String> BusinessAuthority = Arrays.asList(
            "/User/login",
            "/Object/find/all",
            "/Object/find/classes",
            "/Object/find/userAccount",
            "/Object/update/delete",
            "/Object/update",
            "/Order/findAll",
            "/Order/update",
            "/Order/update/BackOK",
            "/Order/update/Cancel",
            "/Order/update/status",
            "/Order/update/track",
            "/Object/Classes/findAll",
            "/Object/add",
            "/User/logout"
    );
}