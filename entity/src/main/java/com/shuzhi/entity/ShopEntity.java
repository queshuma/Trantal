package com.shuzhi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;
import java.math.BigDecimal;

/**
 * trantal_shop
 * @author SHUZHI
 * @date 2023-08-24 21:40:10 
 */

public class ShopEntity {

	@Column(name = "shop_id")
	private Long shopId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "object_id")
	private Long objectId;

	@Column(name = "object_old_price")
	private Float objectOldPrice;

	@Column(name = "object_price")
	private Float objectPrice;

	@Column(name = "object_count")
	private Long objectCount;

	@Column(name = "object_Cost")
	private Float objectOldCost;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "shop_time")
	private Date shopTime;

	@Column(name = "object_status")
	private Long objectStatus;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(Long objectCount) {
		this.objectCount = objectCount;
	}

	public Date getShopTime() {
		return shopTime;
	}

	public void setShopTime(Date shopTime) {
		this.shopTime = shopTime;
	}

	public Long getObjectStatus() {
		return objectStatus;
	}

	public void setObjectStatus(Long objectStatus) {
		this.objectStatus = objectStatus;
	}

	public Float getObjectOldPrice() {
		return objectOldPrice;
	}

	public void setObjectOldPrice(Float objectOldPrice) {
		this.objectOldPrice = objectOldPrice;
	}

	public Float getObjectPrice() {
		return objectPrice;
	}

	public void setObjectPrice(Float objectPrice) {
		this.objectPrice = objectPrice;
	}

	public Float getObjectOldCost() {
		return objectOldCost;
	}

	public void setObjectOldCost(Float objectOldCost) {
		this.objectOldCost = objectOldCost;
	}

	public ShopEntity() {

	}
}
