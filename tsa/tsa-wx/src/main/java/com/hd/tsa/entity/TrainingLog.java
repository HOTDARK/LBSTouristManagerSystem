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
 * @date	2017-11-27
 */
public class TrainingLog extends Pagination<TrainingLog>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String logName;
	private java.lang.String firstSecurityCheck;
	private java.lang.String lastSecurityCheck;
	private java.lang.String maintenance;
	private java.lang.Integer oilNum;
	private java.lang.Integer oilGasNum;
	private java.lang.String morningRoute;
	private java.lang.String afternoonRoute;
	private java.lang.String morningStuName;
	private java.lang.String afternoonStuName;
	private java.lang.String subjectTwoApplyName;
	private java.lang.String subjectThreeApplyName;
	private java.lang.String subjectTwoQualifiedName;
	private java.lang.String subjectThreeQualifiedName;
	private java.util.Date morningStartTime;
	private java.util.Date morningEndTime;
	private java.util.Date afternoonStartTime;
	private java.util.Date afternoonEndTime;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private java.util.Date createTime;
	private java.lang.String createUser;
	private java.lang.String jurisdictionCode;
	private java.lang.String expand1;
	private java.lang.String expand2;
	private java.lang.String expand3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date morningStartTimeBegin;
	private java.util.Date morningStartTimeEnd;
	private java.util.Date morningEndTimeBegin;
	private java.util.Date morningEndTimeEnd;
	private java.util.Date afternoonStartTimeBegin;
	private java.util.Date afternoonStartTimeEnd;
	private java.util.Date afternoonEndTimeBegin;
	private java.util.Date afternoonEndTimeEnd;
	private java.util.Date startTimeBegin;
	private java.util.Date startTimeEnd;
	private java.util.Date endTimeBegin;
	private java.util.Date endTimeEnd;
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	
	private java.lang.Integer state;

	public TrainingLog(){
	}

	public java.lang.Integer getState() {
		return state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	public java.lang.String getLogName() {
		return logName;
	}

	public void setLogName(java.lang.String logName) {
		this.logName = logName;
	}

	public java.lang.String getJurisdictionCode() {
		return jurisdictionCode;
	}

	public void setJurisdictionCode(java.lang.String jurisdictionCode) {
		this.jurisdictionCode = jurisdictionCode;
	}

	public TrainingLog(
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
	public void setFirstSecurityCheck(java.lang.String value) {
		this.firstSecurityCheck = value;
	}
	
	public java.lang.String getFirstSecurityCheck() {
		return this.firstSecurityCheck;
	}
	public void setLastSecurityCheck(java.lang.String value) {
		this.lastSecurityCheck = value;
	}
	
	public java.lang.String getLastSecurityCheck() {
		return this.lastSecurityCheck;
	}
	public void setMaintenance(java.lang.String value) {
		this.maintenance = value;
	}
	
	public java.lang.String getMaintenance() {
		return this.maintenance;
	}
	public void setOilNum(java.lang.Integer value) {
		this.oilNum = value;
	}
	
	public java.lang.Integer getOilNum() {
		return this.oilNum;
	}
	public void setOilGasNum(java.lang.Integer value) {
		this.oilGasNum = value;
	}
	
	public java.lang.Integer getOilGasNum() {
		return this.oilGasNum;
	}
	public void setMorningRoute(java.lang.String value) {
		this.morningRoute = value;
	}
	
	public java.lang.String getMorningRoute() {
		return this.morningRoute;
	}
	public void setAfternoonRoute(java.lang.String value) {
		this.afternoonRoute = value;
	}
	
	public java.lang.String getAfternoonRoute() {
		return this.afternoonRoute;
	}
	public void setMorningStuName(java.lang.String value) {
		this.morningStuName = value;
	}
	
	public java.lang.String getMorningStuName() {
		return this.morningStuName;
	}
	public void setAfternoonStuName(java.lang.String value) {
		this.afternoonStuName = value;
	}
	
	public java.lang.String getAfternoonStuName() {
		return this.afternoonStuName;
	}
	public void setSubjectTwoApplyName(java.lang.String value) {
		this.subjectTwoApplyName = value;
	}
	
	public java.lang.String getSubjectTwoApplyName() {
		return this.subjectTwoApplyName;
	}
	public void setSubjectThreeApplyName(java.lang.String value) {
		this.subjectThreeApplyName = value;
	}
	
	public java.lang.String getSubjectThreeApplyName() {
		return this.subjectThreeApplyName;
	}
	public void setSubjectTwoQualifiedName(java.lang.String value) {
		this.subjectTwoQualifiedName = value;
	}
	
	public java.lang.String getSubjectTwoQualifiedName() {
		return this.subjectTwoQualifiedName;
	}
	public void setSubjectThreeQualifiedName(java.lang.String value) {
		this.subjectThreeQualifiedName = value;
	}
	
	public java.lang.String getSubjectThreeQualifiedName() {
		return this.subjectThreeQualifiedName;
	}
	public void setMorningStartTimeBegin(java.util.Date value) {
		this.morningStartTimeBegin = value;
	}
	
	public java.util.Date getMorningStartTimeBegin() {
		return this.morningStartTimeBegin;
	}
	public void setMorningStartTimeEnd(java.util.Date value) {
		this.morningStartTimeEnd = value;
	}
	
	public java.util.Date getMorningStartTimeEnd() {
		return this.morningStartTimeEnd;
	}
	public void setMorningStartTime(java.util.Date value) {
		this.morningStartTime = value;
	}
	
	public java.util.Date getMorningStartTime() {
		return this.morningStartTime;
	}
	public void setMorningEndTimeBegin(java.util.Date value) {
		this.morningEndTimeBegin = value;
	}
	
	public java.util.Date getMorningEndTimeBegin() {
		return this.morningEndTimeBegin;
	}
	public void setMorningEndTimeEnd(java.util.Date value) {
		this.morningEndTimeEnd = value;
	}
	
	public java.util.Date getMorningEndTimeEnd() {
		return this.morningEndTimeEnd;
	}
	public void setMorningEndTime(java.util.Date value) {
		this.morningEndTime = value;
	}
	
	public java.util.Date getMorningEndTime() {
		return this.morningEndTime;
	}
	public void setAfternoonStartTimeBegin(java.util.Date value) {
		this.afternoonStartTimeBegin = value;
	}
	
	public java.util.Date getAfternoonStartTimeBegin() {
		return this.afternoonStartTimeBegin;
	}
	public void setAfternoonStartTimeEnd(java.util.Date value) {
		this.afternoonStartTimeEnd = value;
	}
	
	public java.util.Date getAfternoonStartTimeEnd() {
		return this.afternoonStartTimeEnd;
	}
	public void setAfternoonStartTime(java.util.Date value) {
		this.afternoonStartTime = value;
	}
	
	public java.util.Date getAfternoonStartTime() {
		return this.afternoonStartTime;
	}
	public void setAfternoonEndTimeBegin(java.util.Date value) {
		this.afternoonEndTimeBegin = value;
	}
	
	public java.util.Date getAfternoonEndTimeBegin() {
		return this.afternoonEndTimeBegin;
	}
	public void setAfternoonEndTimeEnd(java.util.Date value) {
		this.afternoonEndTimeEnd = value;
	}
	
	public java.util.Date getAfternoonEndTimeEnd() {
		return this.afternoonEndTimeEnd;
	}
	public void setAfternoonEndTime(java.util.Date value) {
		this.afternoonEndTime = value;
	}
	
	public java.util.Date getAfternoonEndTime() {
		return this.afternoonEndTime;
	}
	public void setStartTimeBegin(java.util.Date value) {
		this.startTimeBegin = value;
	}
	
	public java.util.Date getStartTimeBegin() {
		return this.startTimeBegin;
	}
	public void setStartTimeEnd(java.util.Date value) {
		this.startTimeEnd = value;
	}
	
	public java.util.Date getStartTimeEnd() {
		return this.startTimeEnd;
	}
	public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}
	
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	public void setEndTimeBegin(java.util.Date value) {
		this.endTimeBegin = value;
	}
	
	public java.util.Date getEndTimeBegin() {
		return this.endTimeBegin;
	}
	public void setEndTimeEnd(java.util.Date value) {
		this.endTimeEnd = value;
	}
	
	public java.util.Date getEndTimeEnd() {
		return this.endTimeEnd;
	}
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	
	public java.util.Date getEndTime() {
		return this.endTime;
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
	public void setExpand3(java.lang.String value) {
		this.expand3 = value;
	}
	
	public java.lang.String getExpand3() {
		return this.expand3;
	}

}