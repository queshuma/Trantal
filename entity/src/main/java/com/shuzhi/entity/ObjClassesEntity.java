package com.shuzhi.entity;

/**
 * object_classes
 * @author SHUZHI
 * @date 2023-08-08 16:36:23 
 */

public class ObjClassesEntity {

	private Long classes_id;

	private Long classes_parent_id;

	private String classes_name;

	public Long getClasses_id() {
		return classes_id;
	}

	public void setClasses_id(Long classes_id) {
		this.classes_id = classes_id;
	}

	public Long getClasses_parent_id() {
		return classes_parent_id;
	}

	public void setClasses_parent_id(Long classes_parent_id) {
		this.classes_parent_id = classes_parent_id;
	}

	public String getClasses_name() {
		return classes_name;
	}

	public void setClasses_name(String classes_name) {
		this.classes_name = classes_name;
	}

	@Override
	public String toString() {
		return "ObjClassesEntity{" +
				"classes_id=" + classes_id +
				", classes_parent_id=" + classes_parent_id +
				", classes_name='" + classes_name + '\'' +
				'}';
	}

	public ObjClassesEntity() {

	}
}
