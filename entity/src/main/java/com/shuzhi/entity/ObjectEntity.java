package com.shuzhi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * trantal_object
 * @author SHUZHI
 * @date 2023-08-06 13:11:26
 */

public class ObjectEntity {

	private int object_id;

	private String object_name;

	private String object_title;

	//原价
	private float object_cost;

	//售价
	private float object_price;

	private String object_info;

	private int object_count;

	private String object_image;

	private int object_status;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date object_time;

	private int object_classes;

	private int user_id;

	public int getObject_id() {
		return object_id;
	}

	public void setObject_id(int object_id) {
		this.object_id = object_id;
	}

	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	public String getObject_title() {
		return object_title;
	}

	public void setObject_title(String object_title) {
		this.object_title = object_title;
	}

	public float getObject_cost() {
		return object_cost;
	}

	public void setObject_cost(float object_cost) {
		this.object_cost = object_cost;
	}

	public float getObject_price() {
		return object_price;
	}

	public void setObject_price(float object_price) {
		this.object_price = object_price;
	}

	public String getObject_info() {
		return object_info;
	}

	public void setObject_info(String object_info) {
		this.object_info = object_info;
	}

	public int getObject_count() {
		return object_count;
	}

	public void setObject_count(int object_count) {
		this.object_count = object_count;
	}

	public String getObject_image() {
		return object_image;
	}

	public void setObject_image(String object_image) {
		this.object_image = object_image;
	}

	public int getObject_status() {
		return object_status;
	}

	public void setObject_status(int object_status) {
		this.object_status = object_status;
	}

	public Date getObject_time() {
		return object_time;
	}

	public void setObject_time(Date object_time) {
		this.object_time = object_time;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getObject_classes() {
		return object_classes;
	}

	public void setObject_classes(int object_classes) {
		this.object_classes = object_classes;
	}

	@Override
	public String toString() {
		return "ObjectEntity{" +
				"object_id=" + object_id +
				", object_name='" + object_name + '\'' +
				", object_title='" + object_title + '\'' +
				", object_cost=" + object_cost +
				", object_price=" + object_price +
				", object_info='" + object_info + '\'' +
				", object_count=" + object_count +
				", object_image='" + object_image + '\'' +
				", object_status=" + object_status +
				", object_time=" + object_time +
				", object_classes=" + object_classes +
				", user_id=" + user_id +
				'}';
	}

	public ObjectEntity() {

	}
}