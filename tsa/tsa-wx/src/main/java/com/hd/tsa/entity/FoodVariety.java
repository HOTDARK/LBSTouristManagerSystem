/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import com.hd.sfw.core.model.Pagination;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-12-11
 */
public class FoodVariety extends Pagination<FoodVariety>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long storeId;
	private java.lang.String foodName;
	private java.lang.String synopsis;
	private java.lang.String detailInfo;
	private java.lang.String price;
	private java.lang.String coverPhoto;
	private java.lang.String createUser;
	private java.lang.String updateUser;
	private java.util.Date updateDate;
	private java.util.Date createDate;
	private java.lang.Integer state;
	private java.lang.Integer deleted;
	private java.lang.String extends1;
	private java.lang.String extends2;
	private java.lang.String extends3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date updateDateBegin;
	private java.util.Date updateDateEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	
	private java.lang.String productsIds;

	public FoodVariety(){
	}

	public FoodVariety(
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
	public void setFoodName(java.lang.String value) {
		this.foodName = value;
	}
	
	public java.lang.String getFoodName() {
		return this.foodName;
	}
	public void setSynopsis(java.lang.String value) {
		this.synopsis = value;
	}
	
	public java.lang.String getSynopsis() {
		return this.synopsis;
	}
	public void setDetailInfo(java.lang.String value) {
		this.detailInfo = value;
	}
	
	public java.lang.String getDetailInfo() {
		return this.detailInfo;
	}
	public void setPrice(java.lang.String value) {
		this.price = value;
	}
	
	public java.lang.String getPrice() {
		return this.price;
	}
	public void setCoverPhoto(java.lang.String value) {
		this.coverPhoto = value;
	}
	
	public java.lang.String getCoverPhoto() {
		return this.coverPhoto;
	}
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
	}
	public void setUpdateUser(java.lang.String value) {
		this.updateUser = value;
	}
	
	public java.lang.String getUpdateUser() {
		return this.updateUser;
	}
	public void setUpdateDateBegin(java.util.Date value) {
		this.updateDateBegin = value;
	}
	
	public java.util.Date getUpdateDateBegin() {
		return this.updateDateBegin;
	}
	public void setUpdateDateEnd(java.util.Date value) {
		this.updateDateEnd = value;
	}
	
	public java.util.Date getUpdateDateEnd() {
		return this.updateDateEnd;
	}
	public void setUpdateDate(java.util.Date value) {
		this.updateDate = value;
	}
	
	public java.util.Date getUpdateDate() {
		return this.updateDate;
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
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setDeleted(java.lang.Integer value) {
		this.deleted = value;
	}
	
	public java.lang.Integer getDeleted() {
		return this.deleted;
	}
	public void setExtends1(java.lang.String value) {
		this.extends1 = value;
	}
	
	public java.lang.String getExtends1() {
		return this.extends1;
	}
	public void setExtends2(java.lang.String value) {
		this.extends2 = value;
	}
	
	public java.lang.String getExtends2() {
		return this.extends2;
	}
	public void setExtends3(java.lang.String value) {
		this.extends3 = value;
	}
	
	public java.lang.String getExtends3() {
		return this.extends3;
	}

	public java.lang.String getProductsIds() {
		return productsIds;
	}

	public void setProductsIds(java.lang.String productsIds) {
		this.productsIds = productsIds;
	}

}