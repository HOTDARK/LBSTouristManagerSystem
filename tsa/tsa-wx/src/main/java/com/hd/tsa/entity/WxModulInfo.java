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
 * @date	2017-11-28
 */
public class WxModulInfo extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String modulName;
	private java.lang.String modulPicture;
	private java.lang.String modulCss;
	private java.lang.String modulUrl;
	private java.lang.Integer releaseFlag;
	private java.lang.Integer deleteFlag;
	private java.lang.String createUser;
	private java.util.Date createDate;
	private java.lang.String modifyUser;
	private java.util.Date modifyDate;
	private java.lang.String extend1;
	private java.lang.String extend2;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	private java.util.Date modifyDateBegin;
	private java.util.Date modifyDateEnd;

	public WxModulInfo(){
	}

	public WxModulInfo(
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
	public void setModulName(java.lang.String value) {
		this.modulName = value;
	}
	
	public java.lang.String getModulName() {
		return this.modulName;
	}
	public void setModulPicture(java.lang.String value) {
		this.modulPicture = value;
	}
	
	public java.lang.String getModulPicture() {
		return this.modulPicture;
	}
	public void setModulCss(java.lang.String value) {
		this.modulCss = value;
	}
	
	public java.lang.String getModulCss() {
		return this.modulCss;
	}
	public void setModulUrl(java.lang.String value) {
		this.modulUrl = value;
	}
	
	public java.lang.String getModulUrl() {
		return this.modulUrl;
	}
	public void setReleaseFlag(java.lang.Integer value) {
		this.releaseFlag = value;
	}
	
	public java.lang.Integer getReleaseFlag() {
		return this.releaseFlag;
	}
	public void setDeleteFlag(java.lang.Integer value) {
		this.deleteFlag = value;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return this.deleteFlag;
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
	public void setModifyUser(java.lang.String value) {
		this.modifyUser = value;
	}
	
	public java.lang.String getModifyUser() {
		return this.modifyUser;
	}
	public void setModifyDateBegin(java.util.Date value) {
		this.modifyDateBegin = value;
	}
	
	public java.util.Date getModifyDateBegin() {
		return this.modifyDateBegin;
	}
	public void setModifyDateEnd(java.util.Date value) {
		this.modifyDateEnd = value;
	}
	
	public java.util.Date getModifyDateEnd() {
		return this.modifyDateEnd;
	}
	public void setModifyDate(java.util.Date value) {
		this.modifyDate = value;
	}
	
	public java.util.Date getModifyDate() {
		return this.modifyDate;
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

}