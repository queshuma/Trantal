package com.shuzhi.system_object.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * trantal_user
 * @author SHUZHI
 * @date 2023-08-11 22:33:33
 */

public class UserEntity {

	private Long userId;

	private String userAccount;

	private String userName;

	private String userPhone;

	private String userEmail;

	private String userPassword;

	@DateTimeFormat(pattern = "yyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyy-MM-dd HH:mm")
	private Date userTime;

	@DateTimeFormat(pattern = "yyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyy-MM-dd HH:mm")
	private Date userLast;

	private Long userLevel;

	private Long userStatus;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getUserTime() {
		return userTime;
	}

	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}

	public Date getUserLast() {
		return userLast;
	}

	public void setUserLast(Date userLast) {
		this.userLast = userLast;
	}

	public Long getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Long userLevel) {
		this.userLevel = userLevel;
	}

	public Long getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Long userStatus) {
		this.userStatus = userStatus;
	}

	public UserEntity() {

	}

}
