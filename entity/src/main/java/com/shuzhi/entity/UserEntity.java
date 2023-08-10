package com.shuzhi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * trantal_user
 * @author SHUZHI
 * @date 2023-08-04 17:19:28
 */
@Table ( name ="trantal_user")
public class UserEntity {

    private int user_id;

    private String user_account;

    private String user_name;

    private String user_phone;

    private String user_email;

    private String user_password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date user_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date user_last;

    private int user_level;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Date getUser_time() {
        return user_time;
    }

    public void setUser_time(Date user_time) {
        this.user_time = user_time;
    }

    public Date getUser_last() {
        return user_last;
    }

    public void setUser_last(Date user_last) {
        this.user_last = user_last;
    }

    public int getUser_level() {
        return user_level;
    }

    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "user_id=" + user_id +
                ", user_account='" + user_account + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_time=" + user_time +
                ", user_last=" + user_last +
                ", user_level=" + user_level +
                '}';
    }

    public UserEntity() {

    }
}
