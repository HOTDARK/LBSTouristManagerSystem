/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.entity;
import java.util.List;

import com.hd.sfw.core.model.BaseModel;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-10-25
 */
public class UserBackRelation extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String xgh;
	private java.lang.String backAccount;
	private java.lang.String backType;
	private java.lang.String backOrgCode;
	private java.util.Date bindDate;
	//columns END
	
	//用于查询的时间列
	private java.util.Date bindDateBegin;
	private java.util.Date bindDateEnd;
	
	private List<UserBackPermit> permits;

	public UserBackRelation(){
	}

	public UserBackRelation(
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
	public void setXgh(java.lang.String value) {
		this.xgh = value;
	}
	
	public java.lang.String getXgh() {
		return this.xgh;
	}
	public void setBackAccount(java.lang.String value) {
		this.backAccount = value;
	}
	
	public java.lang.String getBackAccount() {
		return this.backAccount;
	}
	public void setBackType(java.lang.String value) {
		this.backType = value;
	}
	
	public java.lang.String getBackType() {
		return this.backType;
	}
	public void setBackOrgCode(java.lang.String value) {
		this.backOrgCode = value;
	}
	
	public java.lang.String getBackOrgCode() {
		return this.backOrgCode;
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

	public List<UserBackPermit> getPermits() {
		return permits;
	}

	public void setPermits(List<UserBackPermit> permits) {
		this.permits = permits;
	}
	

}