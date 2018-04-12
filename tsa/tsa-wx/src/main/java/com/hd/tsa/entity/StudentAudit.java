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
public class StudentAudit extends Pagination<StudentAudit>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String sname;
	private java.lang.String enrollUser;
	private java.lang.Integer sex;
	private java.util.Date birthday;
	private java.lang.String idCard;
	private java.lang.String idCardPhoto1;
	private java.lang.String idCardPhoto2;
	private java.lang.String resPermitNum;
	private java.lang.String resPermitPhoto;
	private java.lang.String applyModel;
	private java.lang.String amount;
	private java.lang.String driveMode;
	private java.lang.String telephone;
	private java.lang.String enrollSource;
	private java.lang.String remarks;
	private java.util.Date applyDate;
	private java.util.Date createDate;
	private java.lang.String createUser;
	private java.util.Date updateDate;
	private java.lang.String updateUser;
	private java.lang.Integer evaluate;
	private java.lang.Integer state;
	private java.lang.Integer deleted;
	private java.lang.String expand1;
	private java.lang.String expand2;
	private java.lang.String expand3;
	private int hasPay;
	private String menuCode;
	//columns END
	
	//用于查询的时间列
	private java.util.Date birthdayBegin;
	private java.util.Date birthdayEnd;
	private java.util.Date applyDateBegin;
	private java.util.Date applyDateEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	private java.util.Date updateDateBegin;
	private java.util.Date updateDateEnd;
	
	private java.lang.String applyName;
	private java.lang.String driveName;
	private java.lang.String sourceName;

	public StudentAudit(){
	}

	public StudentAudit(
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
	public void setSex(java.lang.Integer value) {
		this.sex = value;
	}
	
	public java.lang.Integer getSex() {
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
	public void setResPermitNum(java.lang.String value) {
		this.resPermitNum = value;
	}
	
	public java.lang.String getResPermitNum() {
		return this.resPermitNum;
	}
	public void setResPermitPhoto(java.lang.String value) {
		this.resPermitPhoto = value;
	}
	
	public java.lang.String getResPermitPhoto() {
		return this.resPermitPhoto;
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
	public void setApplyDateBegin(java.util.Date value) {
		this.applyDateBegin = value;
	}
	
	public java.util.Date getApplyDateBegin() {
		return this.applyDateBegin;
	}
	public void setApplyDateEnd(java.util.Date value) {
		this.applyDateEnd = value;
	}
	
	public java.util.Date getApplyDateEnd() {
		return this.applyDateEnd;
	}
	public void setApplyDate(java.util.Date value) {
		this.applyDate = value;
	}
	
	public java.util.Date getApplyDate() {
		return this.applyDate;
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
	public void setUpdateUser(java.lang.String value) {
		this.updateUser = value;
	}
	
	public java.lang.String getUpdateUser() {
		return this.updateUser;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setDeleted(java.lang.Integer value) {
		this.deleted = value;
	}
	
	public java.lang.Integer getDeleted() {
		return this.deleted;
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

	public java.lang.Integer getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(java.lang.Integer evaluate) {
		this.evaluate = evaluate;
	}

	public java.lang.String getDriveName() {
		return driveName;
	}

	public void setDriveName(java.lang.String driveName) {
		this.driveName = driveName;
	}

	public java.lang.String getApplyName() {
		return applyName;
	}

	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	public java.lang.String getSourceName() {
		return sourceName;
	}

	public void setSourceName(java.lang.String sourceName) {
		this.sourceName = sourceName;
	}

	public int getHasPay() {
		return hasPay;
	}

	public void setHasPay(int hasPay) {
		this.hasPay = hasPay;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

}