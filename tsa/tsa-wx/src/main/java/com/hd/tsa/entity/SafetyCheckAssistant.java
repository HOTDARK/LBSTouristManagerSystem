/*
 * Copyright © 2018, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import java.util.List;

import com.hd.sfw.core.model.BaseModel;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2018-01-23
 */
public class SafetyCheckAssistant extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long mainId;
	private java.lang.String parentCode;
	private java.lang.String projectCode;
	private java.lang.Integer checkResult;
	private java.util.Date createTime;
	private java.lang.String createUser;
	private java.lang.Integer deleteFlag;
	private java.lang.String extend1;
	private java.lang.String extend2;
	private java.lang.String extend3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	
	private String projectName;
	private String parentName;
	
	private List<SafetyCheckAssistant> list;

	public SafetyCheckAssistant(){
	}

	public SafetyCheckAssistant(
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
	public void setMainId(java.lang.Long value) {
		this.mainId = value;
	}
	
	public java.lang.Long getMainId() {
		return this.mainId;
	}
	public void setParentCode(java.lang.String value) {
		this.parentCode = value;
	}
	
	public java.lang.String getParentCode() {
		return this.parentCode;
	}
	public void setProjectCode(java.lang.String value) {
		this.projectCode = value;
	}
	
	public java.lang.String getProjectCode() {
		return this.projectCode;
	}
	public void setCheckResult(java.lang.Integer value) {
		this.checkResult = value;
	}
	
	public java.lang.Integer getCheckResult() {
		return this.checkResult;
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
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
	}
	public void setDeleteFlag(java.lang.Integer value) {
		this.deleteFlag = value;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return this.deleteFlag;
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

	public List<SafetyCheckAssistant> getList() {
		return list;
	}

	public void setList(List<SafetyCheckAssistant> list) {
		this.list = list;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}