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
 * @date	2017-08-09
 */
public class UserSilentInfo extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.util.Date statisDate;
	private java.lang.Integer silentType;
	private java.lang.String xgh;
	//columns END
	
	//用于查询的时间列
	private java.util.Date statisDateBegin;
	private java.util.Date statisDateEnd;

	public UserSilentInfo(){
	}

	public UserSilentInfo(
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
	public void setStatisDateBegin(java.util.Date value) {
		this.statisDateBegin = value;
	}
	
	public java.util.Date getStatisDateBegin() {
		return this.statisDateBegin;
	}
	public void setStatisDateEnd(java.util.Date value) {
		this.statisDateEnd = value;
	}
	
	public java.util.Date getStatisDateEnd() {
		return this.statisDateEnd;
	}
	public void setStatisDate(java.util.Date value) {
		this.statisDate = value;
	}
	
	public java.util.Date getStatisDate() {
		return this.statisDate;
	}
	public void setSilentType(java.lang.Integer value) {
		this.silentType = value;
	}
	
	public java.lang.Integer getSilentType() {
		return this.silentType;
	}
	public void setXgh(java.lang.String value) {
		this.xgh = value;
	}
	
	public java.lang.String getXgh() {
		return this.xgh;
	}

}