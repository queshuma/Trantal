package com.shuzhi.result.code;

public interface ObjClassesCode {

    /**
     * 错误
     */
    //商品分类管理  030000-030999

    //添加商品分类错误状态码
    String SYSTEM_OBJECT_CLASSES_ERROR_ADD_FAIL = "030001";
    String SYSTEM_OBJECT_CLASSES_ERROR_ADD_FAIL_NAME_NULL = "030002";

    //查找商品分类错误状态码
    String SYSTEM_OBJECT_CLASSES_ERROR_FIND_FAIL = "030051";

    //修改商品分类错误状态码
    String SYSTEM_OBJECT_CLASSES_ERROR_UPD_FAIL = "030101";
    String SYSTEM_OBJECT_CLASSES_ERROR_UPD_FAIL_CLASSES_ID_NULL = "030102";
    String SYSTEM_OBJECT_CLASSES_ERROR_UPD_FAIL_CLASSES_NAME_NULL = "030103";
    String SYSTEM_OBJECT_CLASSES_ERROR_UPD_FAIL_CLASSES_STATUS_SIZE = "030104";

    //删除商品分类错误状态吗
    String SYSTEM_OBJECT_CLASSES_ERROR_DEL_FAIL = "030151";

    String SYSTEM_OBJECT_CLASSES_ADD_SUCCESS = "030000";
    String SYSTEM_OBJECT_CLASSES_FIND_SUCCESS = "030050";
    String SYSTEM_OBJECT_CLASSES_UPD_SUCCESS = "030100";
    String SYSTEM_OBJECT_CLASSES_DEL_SUCCESS = "030150";
}
