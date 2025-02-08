package com.shuzhi.system_shop.Entity;

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

	@Column(name = "shop_cout")
	private int shopCout;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "shop_time")
	private Date shopTime;

	@Column(name = "shop_status")
	private Long shopStatus;

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

	public int getShopCout() {
		return shopCout;
	}

	public void setShopCout(int shopCout) {
		this.shopCout = shopCout;
	}

	public Date getShopTime() {
		return shopTime;
	}

	public void setShopTime(Date shopTime) {
		this.shopTime = shopTime;
	}

	public Long getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(Long shopStatus) {
		this.shopStatus = shopStatus;
	}

	public ShopEntity() {

	}
}
