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
 * @date	2017-11-08
 */
public class DriveModelSub extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String driveCode;
	private java.lang.String subCode;
	private java.lang.String modelCode;
	private java.lang.Integer unitTime;
	private java.lang.Integer limitTime;
	private java.lang.Integer peopleNum;
	private java.lang.Integer allowDays;
	private java.lang.Integer maxTime;
	private java.lang.Integer state;
	private java.lang.Integer seqNum;
	private java.lang.String createUser;
	private java.util.Date cereteDate;
	private java.lang.String updateUser;
	private java.util.Date updateDate;
	private java.lang.String remarks;
	//columns END
	
	//用于查询的时间列
	private java.util.Date cereteDateBegin;
	private java.util.Date cereteDateEnd;
	private java.util.Date updateDateBegin;
	private java.util.Date updateDateEnd;
	private int maxAllowDays;
	private String modelCodeName;
	public DriveModelSub(){
	}

	public DriveModelSub(
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
	public void setDriveCode(java.lang.String value) {
		this.driveCode = value;
	}
	
	public java.lang.String getDriveCode() {
		return this.driveCode;
	}
	public void setSubCode(java.lang.String value) {
		this.subCode = value;
	}
	
	public java.lang.String getSubCode() {
		return this.subCode;
	}
	public void setModelCode(java.lang.String value) {
		this.modelCode = value;
	}
	
	public java.lang.String getModelCode() {
		return this.modelCode;
	}
	public void setUnitTime(java.lang.Integer value) {
		this.unitTime = value;
	}
	
	public java.lang.Integer getUnitTime() {
		return this.unitTime;
	}
	public void setLimitTime(java.lang.Integer value) {
		this.limitTime = value;
	}
	
	public java.lang.Integer getLimitTime() {
		return this.limitTime;
	}
	public void setPeopleNum(java.lang.Integer value) {
		this.peopleNum = value;
	}
	
	public java.lang.Integer getPeopleNum() {
		return this.peopleNum;
	}
	public void setAllowDays(java.lang.Integer value) {
		this.allowDays = value;
	}
	
	public java.lang.Integer getAllowDays() {
		return this.allowDays;
	}
	public void setMaxTime(java.lang.Integer value) {
		this.maxTime = value;
	}
	
	public java.lang.Integer getMaxTime() {
		return this.maxTime;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setSeqNum(java.lang.Integer value) {
		this.seqNum = value;
	}
	
	public java.lang.Integer getSeqNum() {
		return this.seqNum;
	}
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
	}
	public void setCereteDateBegin(java.util.Date value) {
		this.cereteDateBegin = value;
	}
	
	public java.util.Date getCereteDateBegin() {
		return this.cereteDateBegin;
	}
	public void setCereteDateEnd(java.util.Date value) {
		this.cereteDateEnd = value;
	}
	
	public java.util.Date getCereteDateEnd() {
		return this.cereteDateEnd;
	}
	public void setCereteDate(java.util.Date value) {
		this.cereteDate = value;
	}
	
	public java.util.Date getCereteDate() {
		return this.cereteDate;
	}
	public void setUpdateUser(java.lang.String value) {
		this.updateUser = value;
	}
	
	public java.lang.String getUpdateUser() {
		return this.updateUser;
	}
	public void setUpdateDateBegin(java.util.Date value) {
		this.updateDateBegin = value;
	}
	
	public java.util.Date getUpdateDateBegin() {
		return this.updateDateBegin;
	}
	public void setUpdateDateEnd(java.util.Date value) {
		this.updateDateEnd = value;
	}
	
	public java.util.Date getUpdateDateEnd() {
		return this.updateDateEnd;
	}
	public void setUpdateDate(java.util.Date value) {
		this.updateDate = value;
	}
	
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}

	public int getMaxAllowDays() {
		return maxAllowDays;
	}

	public void setMaxAllowDays(int maxAllowDays) {
		this.maxAllowDays = maxAllowDays;
	}

	public String getModelCodeName() {
		return modelCodeName;
	}

	public void setModelCodeName(String modelCodeName) {
		this.modelCodeName = modelCodeName;
	}

}