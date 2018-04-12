/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import com.hd.sfw.core.model.BaseModel;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-12-11
 */
public class StoreCuisineRel extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long storeId;
	private java.lang.Long cuisineId;
	private java.lang.Integer state;
	private java.util.Date createDate;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;

	public StoreCuisineRel(){
	}

	public StoreCuisineRel(
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
	public void setStoreId(java.lang.Long value) {
		this.storeId = value;
	}
	
	public java.lang.Long getStoreId() {
		return this.storeId;
	}
	public void setCuisineId(java.lang.Long value) {
		this.cuisineId = value;
	}
	
	public java.lang.Long getCuisineId() {
		return this.cuisineId;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setCreateDateBegin(java.util.Date value) {
		this.createDateBegin = value;
	}
	
	public java.util.Date getCreateDateBegin() {
		return this.createDateBegin;
	}
	public void setCreateDateEnd(java.util.Date value) {
		this.createDateEnd = value;
	}
	
	public java.util.Date getCreateDateEnd() {
		return this.createDateEnd;
	}
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

}