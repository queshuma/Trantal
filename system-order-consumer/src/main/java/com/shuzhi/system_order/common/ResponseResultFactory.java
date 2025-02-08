package com.shuzhi.system_order.common;

import com.shuzhi.system_order.code.*;

public class ResponseResultFactory<T> {

    /**
     *  构建一个通用的成功的结果
     */
    public static ResponseResult buildResponseFactory() {
        return new  ResponseResult(ResultCode.REQUEST_SUCCESS);
    }
    public static ResponseResult buildResponseFactory(String code) {
        return new  ResponseResult(code);
    }
    public static ResponseResult buildResponseFactory(String resultCode, String resultMsg) {
        return new  ResponseResult(resultCode, resultMsg);
    }
    public static <T> ResponseResult buildResponseFactory(String resultCode, T t) {
        return new ResponseResult(resultCode, t);
    }
    public static ResponseResult buildResponseFactory(String resultCode, String resultMsg, Object result) {
        return new  ResponseResult(resultCode, resultMsg, result);
    }
}
