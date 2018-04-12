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
public class StudentInfo extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String sname;
	private java.lang.String enrollUser;
	private java.lang.String sex;
	private java.util.Date birthday;
	private java.lang.String idCard;
	private java.lang.String idCardPhoto1;
	private java.lang.String idCardPhoto2;
	private java.lang.String resPermitPhoto;
	private java.lang.String resPermit;
	private java.lang.String applyModel;
	private java.lang.String amount;
	private java.lang.String driveMode;
	private java.lang.String telephone;
	private java.lang.String stuStatus;
	private java.lang.String currentSub;
	private java.lang.String enrollSource;
	private java.lang.String remarks;
	private java.util.Date createDate;
	private java.lang.String createUser;
	private java.util.Date updateDate;
	private java.lang.String updteUser;
	private java.lang.Integer deleted;
	private java.util.Date expirationDate;
	private java.lang.String coach;
	private java.lang.Integer evaluate;
	private java.lang.String menuCode;
	private java.lang.String expand1;
	private java.lang.String expand2;
	private java.lang.String expand3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date birthdayBegin;
	private java.util.Date birthdayEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	private java.util.Date updateDateBegin;
	private java.util.Date updateDateEnd;
	private java.util.Date expirationDateBegin;
	private java.util.Date expirationDateEnd;

	private String applyModelStr;
	private String driveModeStr;
	private String coachStr;
	private String currentSubStr;
	private String stuStatusStr;
	
	
	public String getApplyModelStr() {
		return applyModelStr;
	}

	public void setApplyModelStr(String applyModelStr) {
		this.applyModelStr = applyModelStr;
	}

	public String getDriveModeStr() {
		return driveModeStr;
	}

	public void setDriveModeStr(String driveModeStr) {
		this.driveModeStr = driveModeStr;
	}

	public String getCoachStr() {
		return coachStr;
	}

	public void setCoachStr(String coachStr) {
		this.coachStr = coachStr;
	}

	public String getCurrentSubStr() {
		return currentSubStr;
	}

	public void setCurrentSubStr(String currentSubStr) {
		this.currentSubStr = currentSubStr;
	}

	public String getStuStatusStr() {
		return stuStatusStr;
	}

	public void setStuStatusStr(String stuStatusStr) {
		this.stuStatusStr = stuStatusStr;
	}

	public StudentInfo(){
	}

	public StudentInfo(
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
	public void setSname(java.lang.String value) {
		this.sname = value;
	}
	
	public java.lang.String getSname() {
		return this.sname;
	}
	public void setEnrollUser(java.lang.String value) {
		this.enrollUser = value;
	}
	
	public java.lang.String getEnrollUser() {
		return this.enrollUser;
	}
	public void setSex(java.lang.String value) {
		this.sex = value;
	}
	
	public java.lang.String getSex() {
		return this.sex;
	}
	public void setBirthdayBegin(java.util.Date value) {
		this.birthdayBegin = value;
	}
	
	public java.util.Date getBirthdayBegin() {
		return this.birthdayBegin;
	}
	public void setBirthdayEnd(java.util.Date value) {
		this.birthdayEnd = value;
	}
	
	public java.util.Date getBirthdayEnd() {
		return this.birthdayEnd;
	}
	public void setBirthday(java.util.Date value) {
		this.birthday = value;
	}
	
	public java.util.Date getBirthday() {
		return this.birthday;
	}
	public void setIdCard(java.lang.String value) {
		this.idCard = value;
	}
	
	public java.lang.String getIdCard() {
		return this.idCard;
	}
	public void setIdCardPhoto1(java.lang.String value) {
		this.idCardPhoto1 = value;
	}
	
	public java.lang.String getIdCardPhoto1() {
		return this.idCardPhoto1;
	}
	public void setIdCardPhoto2(java.lang.String value) {
		this.idCardPhoto2 = value;
	}
	
	public java.lang.String getIdCardPhoto2() {
		return this.idCardPhoto2;
	}
	public void setResPermitPhoto(java.lang.String value) {
		this.resPermitPhoto = value;
	}
	
	public java.lang.String getResPermitPhoto() {
		return this.resPermitPhoto;
	}
	public void setResPermit(java.lang.String value) {
		this.resPermit = value;
	}
	
	public java.lang.String getResPermit() {
		return this.resPermit;
	}
	public void setApplyModel(java.lang.String value) {
		this.applyModel = value;
	}
	
	public java.lang.String getApplyModel() {
		return this.applyModel;
	}
	public void setAmount(java.lang.String value) {
		this.amount = value;
	}
	
	public java.lang.String getAmount() {
		return this.amount;
	}
	public void setDriveMode(java.lang.String value) {
		this.driveMode = value;
	}
	
	public java.lang.String getDriveMode() {
		return this.driveMode;
	}
	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}
	
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	public void setStuStatus(java.lang.String value) {
		this.stuStatus = value;
	}
	
	public java.lang.String getStuStatus() {
		return this.stuStatus;
	}
	public void setCurrentSub(java.lang.String value) {
		this.currentSub = value;
	}
	
	public java.lang.String getCurrentSub() {
		return this.currentSub;
	}
	public void setEnrollSource(java.lang.String value) {
		this.enrollSource = value;
	}
	
	public java.lang.String getEnrollSource() {
		return this.enrollSource;
	}
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
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
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
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
	public void setUpdteUser(java.lang.String value) {
		this.updteUser = value;
	}
	
	public java.lang.String getUpdteUser() {
		return this.updteUser;
	}
	public void setDeleted(java.lang.Integer value) {
		this.deleted = value;
	}
	
	public java.lang.Integer getDeleted() {
		return this.deleted;
	}
	public void setExpirationDateBegin(java.util.Date value) {
		this.expirationDateBegin = value;
	}
	
	public java.util.Date getExpirationDateBegin() {
		return this.expirationDateBegin;
	}
	public void setExpirationDateEnd(java.util.Date value) {
		this.expirationDateEnd = value;
	}
	
	public java.util.Date getExpirationDateEnd() {
		return this.expirationDateEnd;
	}
	public void setExpirationDate(java.util.Date value) {
		this.expirationDate = value;
	}
	
	public java.util.Date getExpirationDate() {
		return this.expirationDate;
	}
	public void setCoach(java.lang.String value) {
		this.coach = value;
	}
	
	public java.lang.String getCoach() {
		return this.coach;
	}
	public void setEvaluate(java.lang.Integer value) {
		this.evaluate = value;
	}
	
	public java.lang.Integer getEvaluate() {
		return this.evaluate;
	}
	public void setMenuCode(java.lang.String value) {
		this.menuCode = value;
	}
	
	public java.lang.String getMenuCode() {
		return this.menuCode;
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