package com.shuzhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.cache.annotation.CachePut;
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
	private Long objectId;

	@Column(name = "object_name")
	private String objectName;

	@Column(name = "object_title")
	private String objectTitle;

	/**
	 * 原价
	 */
	@Column(name = "object_oldprice")
	private float objectCost;

	/**
	 * 售价
	 */
	@Column(name = "object_price")
	private float objectPrice;

	@Column(name = "object_info")
	private String objectInfo;

	@Column(name = "object_cout")
	private int objectCout;

	@Column(name = "object_image")
	private String objectImage;

	@Column(name = "object_status")
	private Long objectStatus;

	@DateTimeFormat(pattern = "yyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyy-MM-dd HH:mm")
	@Column(name = "object_time")
	private Date objectTime;

	@Column(name = "object_classes")
	private Long objectClasses;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "object_banner")
	private String objectBanner;

	@Column(name = "object_img")
	private String objectImg;

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
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

	public ObjectEntity(Long objectId, String objectName, String objectTitle, float objectCost, float objectPrice, String objectInfo, int objectCout, String objectImage, Long objectStatus, Date objectTime, Long objectClasses, Long userId, String objectBanner, String objectImg) {
		this.objectId = objectId;
		this.objectName = objectName;
		this.objectTitle = objectTitle;
		this.objectCost = objectCost;
		this.objectPrice = objectPrice;
		this.objectInfo = objectInfo;
		this.objectCout = objectCout;
		this.objectImage = objectImage;
		this.objectStatus = objectStatus;
		this.objectTime = objectTime;
		this.objectClasses = objectClasses;
		this.userId = userId;
		this.objectBanner = objectBanner;
		this.objectImg = objectImg;
	}

	public ObjectEntity() {

	}

}
