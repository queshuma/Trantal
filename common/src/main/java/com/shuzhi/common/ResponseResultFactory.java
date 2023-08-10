package com.shuzhi.common;

import com.shuzhi.result.code.SystemCode;

public class ResponseResultFactory<T> {

    /**
     *  构建一个通用的成功的结果
     */

    public static ResponseResult buildResponseFactory() {
        return new  ResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS);
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

    public static ResponseResult buildResponseFactory(String resultCode, String resultMsg, Object obj) {
        return new  ResponseResult(resultCode, resultMsg, obj);
    }
}
