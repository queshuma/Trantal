package com.shuzhi.result.code;

public interface ObjectCode {
    /**
     * 错误
     */
    //商品管理  020000-020999
    //10000-10499   错误的提示

    //添加商品错误状态码
    String SYSTEM_OBJECT_ERROR_ADD_FAIL = "020001";
    String SYSTEM_OBJECT_ERROR_ADD_NAME_NULL = "020002" ;
    String SYSTEM_OBJECT_ERROR_ADD_NAME_SIZE = "030003";

    //删除商品错误状态码
    String SYSTEM_OBJECT_INFO_DEL_FAIL = "020050";   //商品删除成功

    //更新商品错误状态码
    String SYSTEM_OBJECT_ERROR_UPDATE_FAIL = "020101";
    String SYSTEM_OBJECT_ERROR_UPDATE_FAIL_NAME_NULL = "020102" ;
    String SYSTEM_OBJECT_ERROR_UPDATE_FAIL_NAME_SIZE = "020103";

    //查找商品错误状态码


    //10500-10999   成功的提示
    String SYSTEM_OBJECT_INFO_ADD_SUCCESS = "020000";   //商品添加成功
    String SYSTEM_OBJECT_INFO_DEL_SUCCESS = "020050";   //商品删除成功
    String SYSTEM_OBJECT_INFO_UPD_SUCCESS = "020100";   //商品更新成功
    String SYSTEM_OBJECT_INFO_FIND_SUCCESS = "020150";   //商品查询成功
}