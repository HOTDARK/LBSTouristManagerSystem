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
public class DietOrderNum extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String currentDateStr;
	private java.lang.Integer total;
	//columns END
	
	//用于查询的时间列

	public DietOrderNum(){
	}

	public DietOrderNum(
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
	public void setCurrentDateStr(java.lang.String value) {
		this.currentDateStr = value;
	}
	
	public java.lang.String getCurrentDateStr() {
		return this.currentDateStr;
	}
	public void setTotal(java.lang.Integer value) {
		this.total = value;
	}
	
	public java.lang.Integer getTotal() {
		return this.total;
	}

}