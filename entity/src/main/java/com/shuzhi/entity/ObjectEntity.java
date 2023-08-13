package com.shuzhi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;
import java.math.BigDecimal;

/**
 * trantal_object
 * @author SHUZHI
 * @date 2023-08-11 22:31:54 
 */

public class ObjectEntity {


	@Column(name = "object_id")
	private int objectId;

	@Column(name = "object_name")
	private String objectName;

	@Column(name = "object_title")
	private String objectTitle;

	/**
	 * 原价
	 */
	@Column(name = "object_cost")
	private float objectCost;

	/**
	 * 售价
	 */
	@Column(name = "object_price")
	private float objectPrice;

	@Column(name = "object_info")
	private String objectInfo;

	@Column(name = "object_count")
	private int objectCount;

	@Column(name = "object_image")
	private String objectImage;

	@Column(name = "object_status")
	private int objectStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "object_time")
	private Date objectTime;

	@Column(name = "object_classes")
	private int objectClasses;

	@Column(name = "user_id")
	private int userId;

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
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

	public int getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(int objectCount) {
		this.objectCount = objectCount;
	}

	public String getObjectImage() {
		return objectImage;
	}

	public void setObjectImage(String objectImage) {
		this.objectImage = objectImage;
	}

	public int getObjectStatus() {
		return objectStatus;
	}

	public void setObjectStatus(int objectStatus) {
		this.objectStatus = objectStatus;
	}

	public Date getObjectTime() {
		return objectTime;
	}

	public void setObjectTime(Date objectTime) {
		this.objectTime = objectTime;
	}

	public int getObjectClasses() {
		return objectClasses;
	}

	public void setObjectClasses(int objectClasses) {
		this.objectClasses = objectClasses;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ObjectEntity() {

	}
}
