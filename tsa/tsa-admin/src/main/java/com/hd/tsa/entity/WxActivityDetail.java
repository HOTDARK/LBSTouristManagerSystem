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
 * @date	2017-11-13
 */
public class WxActivityDetail extends Pagination<WebAdInfo> {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String detailName;
	private java.lang.String detailSubName;
	private java.lang.String subCss;
	private java.lang.String detailPicture;
	private java.lang.String detailUrl;
	private java.lang.String detailDesc;
	private java.lang.Integer useFlag;
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

	private String createUserName;
	
	private String modifyUserName;
	
	public WxActivityDetail(){
	}

	public WxActivityDetail(
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
	public void setDetailName(java.lang.String value) {
		this.detailName = value;
	}
	
	public java.lang.String getDetailName() {
		return this.detailName;
	}
	public void setDetailSubName(java.lang.String value) {
		this.detailSubName = value;
	}
	
	public java.lang.String getDetailSubName() {
		return this.detailSubName;
	}
	public void setSubCss(java.lang.String value) {
		this.subCss = value;
	}
	
	public java.lang.String getSubCss() {
		return this.subCss;
	}
	public void setDetailPicture(java.lang.String value) {
		this.detailPicture = value;
	}
	
	public java.lang.String getDetailPicture() {
		return this.detailPicture;
	}
	public void setDetailUrl(java.lang.String value) {
		this.detailUrl = value;
	}
	
	public java.lang.String getDetailUrl() {
		return this.detailUrl;
	}
	public void setDetailDesc(java.lang.String value) {
		this.detailDesc = value;
	}
	
	public java.lang.String getDetailDesc() {
		return this.detailDesc;
	}
	public void setUseFlag(java.lang.Integer value) {
		this.useFlag = value;
	}
	
	public java.lang.Integer getUseFlag() {
		return this.useFlag;
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

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

}