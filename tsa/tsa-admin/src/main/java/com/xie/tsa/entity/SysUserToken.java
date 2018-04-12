/*
 * Copyright © 2018, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.xie.tsa.entity;
import com.hd.sfw.core.model.BaseModel;

import java.util.Date;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2018-03-21
 */
public class SysUserToken extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private String userTelphone;
	private String token;
	private java.util.Date createdTime = new Date();
	//columns END
	
	//用于查询的时间列
	private java.util.Date createdTimeBegin;
	private java.util.Date createdTimeEnd;

	public SysUserToken(String telphone, String token){
		this.userTelphone = telphone;
		this.token = token;
	}
	public SysUserToken(){

	}

	public void setUserTelphone(String value) {
		this.userTelphone = value;
	}
	
	public String getUserTelphone() {
		return this.userTelphone;
	}
	public void setToken(String value) {
		this.token = value;
	}
	
	public String getToken() {
		return this.token;
	}
	public void setCreatedTimeBegin(java.util.Date value) {
		this.createdTimeBegin = value;
	}
	
	public java.util.Date getCreatedTimeBegin() {
		return this.createdTimeBegin;
	}
	public void setCreatedTimeEnd(java.util.Date value) {
		this.createdTimeEnd = value;
	}
	
	public java.util.Date getCreatedTimeEnd() {
		return this.createdTimeEnd;
	}
	public void setCreatedTime(java.util.Date value) {
		this.createdTime = value;
	}
	
	public java.util.Date getCreatedTime() {
		return this.createdTime;
	}

}