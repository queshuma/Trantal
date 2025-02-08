package com.shuzhi.system_shop.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * trantal_object
 * @author SHUZHI
 * @date 2023-08-11 22:31:54
 */
public class ObjectEntity {

	private Long objectId;

	private String objectUUID;

	private String objectName;

	private String objectTitle;

	/**
	 * 原价
	 */
	private float objectCost;

	/**
	 * 售价
	 */
	private float objectPrice;

	private String objectInfo;

	private int objectCout;

	private String objectImage;

	private Long objectStatus;

	@DateTimeFormat(pattern = "yyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyy-MM-dd HH:mm")
	private Date objectTime;

	private Long objectClasses;

	private Long userId;

	private String objectBanner;

	private String objectImg;

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getObjectUUID() {
		return objectUUID;
	}

	public void setObjectUUID(String objectUUID) {
		this.objectUUID = objectUUID;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectTitle() {
		return objectTitle;
	}

	public void setObjectTitle(String objectTitle) {
		this.objectTitle = objectTitle;
	}

	public float getObjectCost() {
		return objectCost;
	}

	public void setObjectCost(float objectCost) {
		this.objectCost = objectCost;
	}

	public float getObjectPrice() {
		return objectPrice;
	}

	public void setObjectPrice(float objectPrice) {
		this.objectPrice = objectPrice;
	}

	public String getObjectInfo() {
		return objectInfo;
	}

	public void setObjectInfo(String objectInfo) {
		this.objectInfo = objectInfo;
	}

	public int getObjectCout() {
		return objectCout;
	}

	public void setObjectCout(int objectCout) {
		this.objectCout = objectCout;
	}

	public String getObjectImage() {
		return objectImage;
	}

	public void setObjectImage(String objectImage) {
		this.objectImage = objectImage;
	}

	public Long getObjectStatus() {
		return objectStatus;
	}

	public void setObjectStatus(Long objectStatus) {
		this.objectStatus = objectStatus;
	}

	public Date getObjectTime() {
		return objectTime;
	}

	public void setObjectTime(Date objectTime) {
		this.objectTime = objectTime;
	}

	public Long getObjectClasses() {
		return objectClasses;
	}

	public void setObjectClasses(Long objectClasses) {
		this.objectClasses = objectClasses;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getObjectBanner() {
		return objectBanner;
	}

	public void setObjectBanner(String objectBanner) {
		this.objectBanner = objectBanner;
	}

	public String getObjectImg() {
		return objectImg;
	}

	public void setObjectImg(String objectImg) {
		this.objectImg = objectImg;
	}


	@Override
	public String toString() {
		return "ObjectEntity{" +
				"objectId=" + objectId +
				", objectUUID='" + objectUUID + '\'' +
				", objectName='" + objectName + '\'' +
				", objectTitle='" + objectTitle + '\'' +
				", objectCost=" + objectCost +
				", objectPrice=" + objectPrice +
				", objectInfo='" + objectInfo + '\'' +
				", objectCout=" + objectCout +
				", objectImage='" + objectImage + '\'' +
				", objectStatus=" + objectStatus +
				", objectTime=" + objectTime +
				", objectClasses=" + objectClasses +
				", userId=" + userId +
				", objectBanner='" + objectBanner + '\'' +
				", objectImg='" + objectImg + '\'' +
				'}';
	}
}
