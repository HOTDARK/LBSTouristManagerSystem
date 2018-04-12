/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.entity;
import com.hd.sfw.core.model.BaseModel;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-10-25
 */
public class UserBackPermit extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long backId;
	private java.lang.String backFuncCode;
	private java.util.Date bindDate;
	//columns END
	
	//用于查询的时间列
	private java.util.Date bindDateBegin;
	private java.util.Date bindDateEnd;

	public UserBackPermit(){
	}

	public UserBackPermit(
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
	public void setBackId(java.lang.Long value) {
		this.backId = value;
	}
	
	public java.lang.Long getBackId() {
		return this.backId;
	}
	public void setBackFuncCode(java.lang.String value) {
		this.backFuncCode = value;
	}
	
	public java.lang.String getBackFuncCode() {
		return this.backFuncCode;
	}
	public void setBindDateBegin(java.util.Date value) {
		this.bindDateBegin = value;
	}
	
	public java.util.Date getBindDateBegin() {
		return this.bindDateBegin;
	}
	public void setBindDateEnd(java.util.Date value) {
		this.bindDateEnd = value;
	}
	
	public java.util.Date getBindDateEnd() {
		return this.bindDateEnd;
	}
	public void setBindDate(java.util.Date value) {
		this.bindDate = value;
	}
	
	public java.util.Date getBindDate() {
		return this.bindDate;
	}

}