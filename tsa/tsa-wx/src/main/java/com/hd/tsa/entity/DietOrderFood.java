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
public class DietOrderFood extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String orderNum;
	private java.lang.Long foodId;
	private java.lang.String foodName;
	private java.lang.Integer count;
	private java.lang.String price;
	//columns END
	
	//用于查询的时间列

	public DietOrderFood(){
	}

	public DietOrderFood(
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
	public void setOrderNum(java.lang.String value) {
		this.orderNum = value;
	}
	
	public java.lang.String getOrderNum() {
		return this.orderNum;
	}
	public void setFoodId(java.lang.Long value) {
		this.foodId = value;
	}
	
	public java.lang.Long getFoodId() {
		return this.foodId;
	}
	public void setFoodName(java.lang.String value) {
		this.foodName = value;
	}
	
	public java.lang.String getFoodName() {
		return this.foodName;
	}
	public void setCount(java.lang.Integer value) {
		this.count = value;
	}
	
	public java.lang.Integer getCount() {
		return this.count;
	}
	public void setPrice(java.lang.String value) {
		this.price = value;
	}
	
	public java.lang.String getPrice() {
		return this.price;
	}

}