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
public class ComplaintInfo extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long storeId;
	private java.lang.String user;
	private java.lang.String userName;
	private java.lang.String content;
	private java.util.Date date;
	private java.lang.Integer handle;
	private java.lang.Integer deleteFlag;
	private java.lang.String handleUser;
	private java.util.Date handleDate;
	private java.lang.String handleContent;
	private java.lang.String extend1;
	private java.lang.String extend2;
	private java.lang.String extend3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date dateBegin;
	private java.util.Date dateEnd;
	private java.util.Date handleDateBegin;
	private java.util.Date handleDateEnd;

	public ComplaintInfo(){
	}

	public ComplaintInfo(
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
	public void setUser(java.lang.String value) {
		this.user = value;
	}
	
	public java.lang.String getUser() {
		return this.user;
	}
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setDateBegin(java.util.Date value) {
		this.dateBegin = value;
	}
	
	public java.util.Date getDateBegin() {
		return this.dateBegin;
	}
	public void setDateEnd(java.util.Date value) {
		this.dateEnd = value;
	}
	
	public java.util.Date getDateEnd() {
		return this.dateEnd;
	}
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	public java.util.Date getDate() {
		return this.date;
	}
	public void setHandle(java.lang.Integer value) {
		this.handle = value;
	}
	
	public java.lang.Integer getHandle() {
		return this.handle;
	}
	public void setDeleteFlag(java.lang.Integer value) {
		this.deleteFlag = value;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	public void setHandleUser(java.lang.String value) {
		this.handleUser = value;
	}
	
	public java.lang.String getHandleUser() {
		return this.handleUser;
	}
	public void setHandleDateBegin(java.util.Date value) {
		this.handleDateBegin = value;
	}
	
	public java.util.Date getHandleDateBegin() {
		return this.handleDateBegin;
	}
	public void setHandleDateEnd(java.util.Date value) {
		this.handleDateEnd = value;
	}
	
	public java.util.Date getHandleDateEnd() {
		return this.handleDateEnd;
	}
	public void setHandleDate(java.util.Date value) {
		this.handleDate = value;
	}
	
	public java.util.Date getHandleDate() {
		return this.handleDate;
	}
	public void setHandleContent(java.lang.String value) {
		this.handleContent = value;
	}
	
	public java.lang.String getHandleContent() {
		return this.handleContent;
	}
	public void setExtend1(java.lang.String value) {
		this.extend1 = value;
	}
	
	public java.lang.String getExtend1() {
		return this.extend1;
	}
	public void setExtend2(java.lang.String value) {
		this.extend2 = value;
	}
	
	public java.lang.String getExtend2() {
		return this.extend2;
	}
	public void setExtend3(java.lang.String value) {
		this.extend3 = value;
	}
	
	public java.lang.String getExtend3() {
		return this.extend3;
	}

}