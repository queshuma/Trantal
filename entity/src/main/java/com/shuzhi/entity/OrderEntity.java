package com.shuzhi.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

/**
 * trantal_order
 * @author SHUZHI
 * @date 2023-08-18 11:31:38
 */

public class OrderEntity {

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "order_number")
	private Long orderNumber;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "object_id")
	private Long objectId;

	@Column(name = "object_price")
	private Float objectPrice;

	@Column(name = "object_cost")
	private Float objectCost;

	@Column(name = "object_count")
	private Long objectCount;

	@Column(name = "order_info")
	private String orderInfo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "order_time")
	private Date orderTime;

	@Column(name = "order_track")
	private String orderTrack;

	@Column(name = "order_address")
	private String orderAddress;

	@Column(name = "order_name")
	private String orderName;

	@Column(name = "order_phone")
	private String orderPhone;

	/**
	 * 0:待寄出
	 1:寄出
	 2:确认收货
	 3:退货
	 4.确认退货
	 */
	@Column(name = "order_status")
	private Long orderStatus;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
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

	public Float getObjectPrice() {
		return objectPrice;
	}

	public void setObjectPrice(Float objectPrice) {
		this.objectPrice = objectPrice;
	}

	public Float getObjectCost() {
		return objectCost;
	}

	public void setObjectCost(Float objectCost) {
		this.objectCost = objectCost;
	}

	public Long getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(Long objectCount) {
		this.objectCount = objectCount;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderTrack() {
		return orderTrack;
	}

	public void setOrderTrack(String orderTrack) {
		this.orderTrack = orderTrack;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public Long getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Long orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderEntity() {

	}
}
