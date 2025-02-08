package com.shuzhi.system_user.common;

import java.util.List;

public class SystemUtils {

    /**
     * 判断对象是否为空
     * @param object
     * @return  ture表示是
     */
    public static boolean isNull(Object object) {
        return null == object;
    }

    /**
     * 判断字符串是否是空字符串
     * @param str
     * @return  true表示为空
     */
    public static boolean isNullOrEmpty(String str) {
        return null == str || str.trim().equals("");
    }

    /**
     * 判断列表是否为空
     * @param list
     * @return
     */
    public static boolean listIsNull(List list) {
        return list.size() == 0;
    }

    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if(Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
}
