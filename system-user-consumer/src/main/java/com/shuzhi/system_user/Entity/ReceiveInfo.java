package com.shuzhi.system_user.Entity;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * receive_info
 * @author SHUZHI
 * @date 2024-02-19 10:42:57 
 */

public class ReceiveInfo {

	@Column(name = "receive_id")
	private Long receiveId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "receive_address")
	private String receiveAddress;

	@Column(name = "receive_phone")
	private String receivePhone;

	@Column(name = "receive_name")
	private String receiveName;

	@Column(name = "receive_status")
	private Long receiveStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "receive_add_time")
	private Date receiveAddTime;

	public Long getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Long receiveId) {
		this.receiveId = receiveId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceivePhone() {
		return receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public Long getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(Long receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public Date getReceiveAddTime() {
		return receiveAddTime;
	}

	public void setReceiveAddTime(Date receiveAddTime) {
		this.receiveAddTime = receiveAddTime;
	}

//	@Override
//	public String toString() {
//		return "ReceiveInfo{" +
//				"receiveId=" + receiveId +
//				", userId=" + userId +
//				", receiveAddress='" + receiveAddress + '\'' +
//				", receivePhone='" + receivePhone + '\'' +
//				", receiveName='" + receiveName + '\'' +
//				", receiveStatus=" + receiveStatus +
//				", receiveAddTime=" + receiveAddTime +
//				'}';
//	}

	public ReceiveInfo() {

	}

}
