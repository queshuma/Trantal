package com.shuzhi.result.parmSetter;


public interface ObjectSetting {
    //用户姓名最小长度
    public final int OBJECT_INFO_NAME_SIZE_MIN = 2;
    //用户姓名最大长度
    public final int OBJECT_INFO_NAME_SIZE_MAX = 8;

    //用户昵称最小长度
    public final int OBJECT_INFO_ACCOUNT_SIZE_MIN = 2;
    //用户昵称最大长度
    public final int OBJECT_INFO_ACCOUNT_SIZE_MAX = 8;

    //用户密码最小长度
    public final int OBJECT_INFO_PASSWORD_SIZE_MIN = 6;
    //用户密码最大长度
    public final int OBJECT_INFO_PASSWORD_SIZE_MAX = 15;

    //用户邮箱最小长度
    public final int OBJECT_INFO_EMAIL_SIZE_MIN = 6;
    ////用户邮箱最大长度
    public final int OBJECT_INFO_EMAIL_SIZE_MAX = 15;

    //用户手机号最小长度
    public final int OBJECT_INFO_PHONE_SIZE_MIN = 5;
    //用户手机号最大长度
    public final int OBJECT_INFO_PHONE_SIZE_MAX = 15;

    //用户等级最大限制
    public final int OBJECT_INFO_LEVEL_MAX = 3;
    //用户等级最小限制
    public final int OBJECT_INFO_LEVER_MIN = 1;

    //用户状态最大限制
    public final int OBJECT_INFO_STATUS_MAX = 1;
    //用户等级最小限制
    public final int OBJECT_INFO_STATUS_MIN = 0;
}