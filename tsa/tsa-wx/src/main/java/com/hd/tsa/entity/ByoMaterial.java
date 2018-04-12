/*
 * Copyright © 2018, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import com.hd.sfw.core.model.BaseModel;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2018-01-24
 */
public class ByoMaterial extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long maintainId;
	private java.lang.String materialCode;
	private java.lang.Integer count;
	private java.lang.String expand1;
	private java.lang.String expand2;
	//columns END
	
	private String materialName;
	
	//用于查询的时间列

	public ByoMaterial(){
	}

	public ByoMaterial(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setMaintainId(java.lang.Long value) {
		this.maintainId = value;
	}
	
	public java.lang.Long getMaintainId() {
		return this.maintainId;
	}
	public void setMaterialCode(java.lang.String value) {
		this.materialCode = value;
	}
	
	public java.lang.String getMaterialCode() {
		return this.materialCode;
	}
	public void setCount(java.lang.Integer value) {
		this.count = value;
	}
	
	public java.lang.Integer getCount() {
		return this.count;
	}
	public void setExpand1(java.lang.String value) {
		this.expand1 = value;
	}
	
	public java.lang.String getExpand1() {
		return this.expand1;
	}
	public void setExpand2(java.lang.String value) {
		this.expand2 = value;
	}
	
	public java.lang.String getExpand2() {
		return this.expand2;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

}