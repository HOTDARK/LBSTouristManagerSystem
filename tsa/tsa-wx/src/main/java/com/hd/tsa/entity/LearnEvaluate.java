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
 * @date	2017-11-02
 */
public class LearnEvaluate extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long applyId;
	private java.lang.Integer starNum;
	private java.lang.String appraiseInfo;
	private java.lang.String createUser;
	private java.lang.String createUserName;
	private java.lang.String telephone;
	private java.lang.String personSex;
	private java.util.Date createTime;
	private java.lang.Integer deletedFlag;
	private java.lang.String extends1;
	private java.lang.String extends2;
	private java.lang.String extends3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;

	public LearnEvaluate(){
	}

	public LearnEvaluate(
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
	public void setApplyId(java.lang.Long value) {
		this.applyId = value;
	}
	
	public java.lang.Long getApplyId() {
		return this.applyId;
	}
	public void setStarNum(java.lang.Integer value) {
		this.starNum = value;
	}
	
	public java.lang.Integer getStarNum() {
		return this.starNum;
	}
	public void setAppraiseInfo(java.lang.String value) {
		this.appraiseInfo = value;
	}
	
	public java.lang.String getAppraiseInfo() {
		return this.appraiseInfo;
	}
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
	}
	public void setCreateUserName(java.lang.String value) {
		this.createUserName = value;
	}
	
	public java.lang.String getCreateUserName() {
		return this.createUserName;
	}
	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}
	
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	public void setPersonSex(java.lang.String value) {
		this.personSex = value;
	}
	
	public java.lang.String getPersonSex() {
		return this.personSex;
	}
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	public void setCreateTimeEnd(java.util.Date value) {
		this.createTimeEnd = value;
	}
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setDeletedFlag(java.lang.Integer value) {
		this.deletedFlag = value;
	}
	
	public java.lang.Integer getDeletedFlag() {
		return this.deletedFlag;
	}
	public void setExtends1(java.lang.String value) {
		this.extends1 = value;
	}
	
	public java.lang.String getExtends1() {
		return this.extends1;
	}
	public void setExtends2(java.lang.String value) {
		this.extends2 = value;
	}
	
	public java.lang.String getExtends2() {
		return this.extends2;
	}
	public void setExtends3(java.lang.String value) {
		this.extends3 = value;
	}
	
	public java.lang.String getExtends3() {
		return this.extends3;
	}

}