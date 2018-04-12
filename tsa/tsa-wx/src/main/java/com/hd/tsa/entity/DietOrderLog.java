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
public class DietOrderLog extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String orderNum;
	private java.lang.String orderState;
	private java.lang.String operName;
	private java.lang.String operReason;
	private java.lang.String operUser;
	private java.util.Date operDate;
	private java.lang.String operContent;
	//columns END
	
	//用于查询的时间列
	private java.util.Date operDateBegin;
	private java.util.Date operDateEnd;

	public DietOrderLog(){
	}

	public DietOrderLog(
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
	public void setOrderState(java.lang.String value) {
		this.orderState = value;
	}
	
	public java.lang.String getOrderState() {
		return this.orderState;
	}
	public void setOperName(java.lang.String value) {
		this.operName = value;
	}
	
	public java.lang.String getOperName() {
		return this.operName;
	}
	public void setOperReason(java.lang.String value) {
		this.operReason = value;
	}
	
	public java.lang.String getOperReason() {
		return this.operReason;
	}
	public void setOperUser(java.lang.String value) {
		this.operUser = value;
	}
	
	public java.lang.String getOperUser() {
		return this.operUser;
	}
	public void setOperDateBegin(java.util.Date value) {
		this.operDateBegin = value;
	}
	
	public java.util.Date getOperDateBegin() {
		return this.operDateBegin;
	}
	public void setOperDateEnd(java.util.Date value) {
		this.operDateEnd = value;
	}
	
	public java.util.Date getOperDateEnd() {
		return this.operDateEnd;
	}
	public void setOperDate(java.util.Date value) {
		this.operDate = value;
	}
	
	public java.util.Date getOperDate() {
		return this.operDate;
	}
	public void setOperContent(java.lang.String value) {
		this.operContent = value;
	}
	
	public java.lang.String getOperContent() {
		return this.operContent;
	}

}