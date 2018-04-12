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
public class LearnApplyHistory extends Pagination<LearnApplyHistory>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String stuName;
	private java.lang.String stuUser;
	private java.lang.String coach;
	private java.lang.String driveMode;
	private java.lang.String applySorce;
	private java.lang.String subjectCode;
	private java.lang.String subjectModel;
	private java.lang.Integer orderTimes;
	private java.lang.Integer stuTime;
	private java.util.Date startTime;
	private java.lang.String vehicleLicense;
	private java.util.Date endTime;
	private java.util.Date realStartTime;
	private java.util.Date realEndTime;
	private java.lang.Integer realStuTime;
	private java.lang.Integer hasDrive;
	private java.lang.Integer state;
	private java.lang.Integer evaluate;
	private java.lang.String cancelReason;
	private java.lang.String failReason;
	private java.lang.String createUser;
	private java.util.Date createDate;
	private java.lang.Integer deleted;
	private java.lang.String remarks;
	private java.lang.String menuCode;
	private java.lang.String expand1;
	private java.lang.String expand2;
	private java.lang.String expand3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date startTimeBegin;
	private java.util.Date startTimeEnd;
	private java.util.Date endTimeBegin;
	private java.util.Date endTimeEnd;
	private java.util.Date realStartTimeBegin;
	private java.util.Date realStartTimeEnd;
	private java.util.Date realEndTimeBegin;
	private java.util.Date realEndTimeEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	
	private java.lang.String coachName;
	private java.lang.String driveModeName;
	private java.lang.String applySorceName;
	private java.lang.String subjectCodeName;
	private java.lang.String subjectModelName;
	
	public java.util.Date getRealStartTime() {
		return realStartTime;
	}

	public void setRealStartTime(java.util.Date realStartTime) {
		this.realStartTime = realStartTime;
	}

	public java.util.Date getRealEndTime() {
		return realEndTime;
	}

	public void setRealEndTime(java.util.Date realEndTime) {
		this.realEndTime = realEndTime;
	}

	public java.lang.Integer getRealStuTime() {
		return realStuTime;
	}

	public void setRealStuTime(java.lang.Integer realStuTime) {
		this.realStuTime = realStuTime;
	}

	public java.lang.String getCoachName() {
		return coachName;
	}

	public void setCoachName(java.lang.String coachName) {
		this.coachName = coachName;
	}

	public java.lang.String getDriveModeName() {
		return driveModeName;
	}

	public void setDriveModeName(java.lang.String driveModeName) {
		this.driveModeName = driveModeName;
	}

	public java.lang.String getApplySorceName() {
		return applySorceName;
	}

	public void setApplySorceName(java.lang.String applySorceName) {
		this.applySorceName = applySorceName;
	}

	public java.lang.String getSubjectCodeName() {
		return subjectCodeName;
	}

	public void setSubjectCodeName(java.lang.String subjectCodeName) {
		this.subjectCodeName = subjectCodeName;
	}

	public java.lang.String getSubjectModelName() {
		return subjectModelName;
	}

	public void setSubjectModelName(java.lang.String subjectModelName) {
		this.subjectModelName = subjectModelName;
	}

	public LearnApplyHistory(){
	}

	public LearnApplyHistory(
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
	public void setStuName(java.lang.String value) {
		this.stuName = value;
	}
	
	public java.lang.String getStuName() {
		return this.stuName;
	}
	public void setStuUser(java.lang.String value) {
		this.stuUser = value;
	}
	
	public java.lang.String getStuUser() {
		return this.stuUser;
	}
	public void setCoach(java.lang.String value) {
		this.coach = value;
	}
	
	public java.lang.String getCoach() {
		return this.coach;
	}
	public void setDriveMode(java.lang.String value) {
		this.driveMode = value;
	}
	
	public java.lang.String getDriveMode() {
		return this.driveMode;
	}
	public void setApplySorce(java.lang.String value) {
		this.applySorce = value;
	}
	
	public java.lang.String getApplySorce() {
		return this.applySorce;
	}
	public void setSubjectCode(java.lang.String value) {
		this.subjectCode = value;
	}
	
	public java.lang.String getSubjectCode() {
		return this.subjectCode;
	}
	public void setSubjectModel(java.lang.String value) {
		this.subjectModel = value;
	}
	
	public java.lang.String getSubjectModel() {
		return this.subjectModel;
	}
	public void setOrderTimes(java.lang.Integer value) {
		this.orderTimes = value;
	}
	
	public java.lang.Integer getOrderTimes() {
		return this.orderTimes;
	}
	public void setStuTime(java.lang.Integer value) {
		this.stuTime = value;
	}
	
	public java.lang.Integer getStuTime() {
		return this.stuTime;
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
	public void setVehicleLicense(java.lang.String value) {
		this.vehicleLicense = value;
	}
	
	public java.lang.String getVehicleLicense() {
		return this.vehicleLicense;
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
	public void setHasDrive(java.lang.Integer value) {
		this.hasDrive = value;
	}
	
	public java.lang.Integer getHasDrive() {
		return this.hasDrive;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setEvaluate(java.lang.Integer value) {
		this.evaluate = value;
	}
	
	public java.lang.Integer getEvaluate() {
		return this.evaluate;
	}
	public void setCancelReason(java.lang.String value) {
		this.cancelReason = value;
	}
	
	public java.lang.String getCancelReason() {
		return this.cancelReason;
	}
	public void setFailReason(java.lang.String value) {
		this.failReason = value;
	}
	
	public java.lang.String getFailReason() {
		return this.failReason;
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
	public void setDeleted(java.lang.Integer value) {
		this.deleted = value;
	}
	
	public java.lang.Integer getDeleted() {
		return this.deleted;
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
	public void setExpand3(java.lang.String value) {
		this.expand3 = value;
	}
	
	public java.lang.String getExpand3() {
		return this.expand3;
	}

	public java.util.Date getRealStartTimeBegin() {
		return realStartTimeBegin;
	}

	public void setRealStartTimeBegin(java.util.Date realStartTimeBegin) {
		this.realStartTimeBegin = realStartTimeBegin;
	}

	public java.util.Date getRealStartTimeEnd() {
		return realStartTimeEnd;
	}

	public void setRealStartTimeEnd(java.util.Date realStartTimeEnd) {
		this.realStartTimeEnd = realStartTimeEnd;
	}

	public java.util.Date getRealEndTimeBegin() {
		return realEndTimeBegin;
	}

	public void setRealEndTimeBegin(java.util.Date realEndTimeBegin) {
		this.realEndTimeBegin = realEndTimeBegin;
	}

	public java.util.Date getRealEndTimeEnd() {
		return realEndTimeEnd;
	}

	public void setRealEndTimeEnd(java.util.Date realEndTimeEnd) {
		this.realEndTimeEnd = realEndTimeEnd;
	}

	public java.lang.String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(java.lang.String menuCode) {
		this.menuCode = menuCode;
	}

}