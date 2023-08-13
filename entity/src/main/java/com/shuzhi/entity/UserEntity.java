package com.shuzhi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

/**
 * trantal_user
 * @author SHUZHI
 * @date 2023-08-11 22:33:33 
 */

public class UserEntity {

	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_account")
	private String userAccount;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_phone")
	private String userPhone;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_password")
	private String userPassword;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "user_time")
	private Date userTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "user_last")
	private Date userLast;

	@Column(name = "user_level")
	private int userLevel;

	@Column(name = "user_status")
	private int userStatus;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public UserEntity() {

	}
}
