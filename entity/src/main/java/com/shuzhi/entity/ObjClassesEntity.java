package com.shuzhi.entity;

import javax.persistence.Column;

/**
 * object_classes
 * @author SHUZHI
 * @date 2023-08-11 22:12:17 
 */

public class ObjClassesEntity {

	@Column(name = "classes_id")
	private Long classesId;

	@Column(name = "classes_parent_id")
	private int classesParentId;

	@Column(name = "classes_name")
	private String classesName;

	@Column(name = "classes_status")
	private int classesStatus;

	public Long getClassesId() {
		return classesId;
	}

	public void setClassesId(Long classesId) {
		this.classesId = classesId;
	}

	public int getClassesParentId() {
		return classesParentId;
	}

	public void setClassesParentId(int classesParentId) {
		this.classesParentId = classesParentId;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	public int getClassesStatus() {
		return classesStatus;
	}

	public void setClassesStatus(int classesStatus) {
		this.classesStatus = classesStatus;
	}
}
