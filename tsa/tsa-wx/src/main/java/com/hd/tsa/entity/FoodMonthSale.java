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
public class FoodMonthSale extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long storeId;
	private java.lang.Long foodId;
	private java.lang.String dateStr;
	private java.lang.Integer count;
	private java.util.Date finalDate;
	//columns END
	
	//用于查询的时间列
	private java.util.Date finalDateBegin;
	private java.util.Date finalDateEnd;

	public FoodMonthSale(){
	}

	public FoodMonthSale(
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
	public void setFoodId(java.lang.Long value) {
		this.foodId = value;
	}
	
	public java.lang.Long getFoodId() {
		return this.foodId;
	}
	public void setDateStr(java.lang.String value) {
		this.dateStr = value;
	}
	
	public java.lang.String getDateStr() {
		return this.dateStr;
	}
	public void setCount(java.lang.Integer value) {
		this.count = value;
	}
	
	public java.lang.Integer getCount() {
		return this.count;
	}
	public void setFinalDateBegin(java.util.Date value) {
		this.finalDateBegin = value;
	}
	
	public java.util.Date getFinalDateBegin() {
		return this.finalDateBegin;
	}
	public void setFinalDateEnd(java.util.Date value) {
		this.finalDateEnd = value;
	}
	
	public java.util.Date getFinalDateEnd() {
		return this.finalDateEnd;
	}
	public void setFinalDate(java.util.Date value) {
		this.finalDate = value;
	}
	
	public java.util.Date getFinalDate() {
		return this.finalDate;
	}

}