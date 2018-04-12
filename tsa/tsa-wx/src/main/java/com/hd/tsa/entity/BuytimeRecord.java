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
 * @date	2017-10-24
 */
public class BuytimeRecord extends Pagination<BuytimeRecord>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String user;
	private java.lang.String userName;
	private java.lang.String buyTelephone;
	private java.lang.String userSex;
	private java.lang.Integer buyTime;
	private java.lang.String amount;
	private java.lang.Integer hasPay;
	private java.lang.String createUser;
	private java.util.Date createDate;
	private java.lang.String updateUser;
	private java.util.Date updateTime;
	private java.lang.Integer deleted;
	private java.lang.Integer state;
	private java.lang.String menuCode;
	private java.lang.String remarks;
	private java.lang.String expand1;
	private java.lang.String expand2;
	private java.lang.String invoiceNum;
	private java.util.Date invoiceDate;
	//columns END
	
	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getBuyTelephone() {
		return buyTelephone;
	}

	public void setBuyTelephone(java.lang.String buyTelephone) {
		this.buyTelephone = buyTelephone;
	}

	public java.lang.String getUserSex() {
		return userSex;
	}

	public void setUserSex(java.lang.String userSex) {
		this.userSex = userSex;
	}

	public java.lang.String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(java.lang.String menuCode) {
		this.menuCode = menuCode;
	}

	//用于查询的时间列
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;

	public BuytimeRecord(){
	}

	public BuytimeRecord(
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
	public void setUser(java.lang.String value) {
		this.user = value;
	}
	
	public java.lang.String getUser() {
		return this.user;
	}
	public void setBuyTime(java.lang.Integer value) {
		this.buyTime = value;
	}
	
	public java.lang.Integer getBuyTime() {
		return this.buyTime;
	}
	public void setAmount(java.lang.String value) {
		this.amount = value;
	}
	
	public java.lang.String getAmount() {
		return this.amount;
	}
	public void setHasPay(java.lang.Integer value) {
		this.hasPay = value;
	}
	
	public java.lang.Integer getHasPay() {
		return this.hasPay;
	}
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
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
	public void setUpdateUser(java.lang.String value) {
		this.updateUser = value;
	}
	
	public java.lang.String getUpdateUser() {
		return this.updateUser;
	}
	public void setUpdateTimeBegin(java.util.Date value) {
		this.updateTimeBegin = value;
	}
	
	public java.util.Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	public void setUpdateTimeEnd(java.util.Date value) {
		this.updateTimeEnd = value;
	}
	
	public java.util.Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setDeleted(java.lang.Integer value) {
		this.deleted = value;
	}
	
	public java.lang.Integer getDeleted() {
		return this.deleted;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	public void setExpand1(java.lang.String value) {
		this.expand1 = value;
	}
	
	public java.lang.String getExpand1() {
		return this.expand1;
	}
	public void setExpand2(java.lang.String value) {
		this.expand2 = value;
	}
	
	public java.lang.String getExpand2() {
		return this.expand2;
	}

	public java.lang.String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(java.lang.String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public java.util.Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(java.util.Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

}