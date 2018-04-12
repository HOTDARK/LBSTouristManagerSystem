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
public class WxAdInfo extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String adPosition;
	private java.lang.String adPicture;
	private java.lang.String adName;
	private java.lang.Integer adWidth;
	private java.lang.Integer adHeight;
	private java.lang.String adDesc;
	private java.lang.String adUrl;
	private java.util.Date bdate;
	private java.util.Date edate;
	private java.lang.Integer deliveryFlag;
	private java.lang.Integer deleteFlag;
	private java.lang.String createUser;
	private java.util.Date createDate;
	private java.lang.String modifyUser;
	private java.util.Date modifyDate;
	private java.lang.String extend1;
	private java.lang.String extend2;
	//columns END
	
	//用于查询的时间列
	private java.util.Date bdateBegin;
	private java.util.Date bdateEnd;
	private java.util.Date edateBegin;
	private java.util.Date edateEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	private java.util.Date modifyDateBegin;
	private java.util.Date modifyDateEnd;

	public WxAdInfo(){
	}

	public WxAdInfo(
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
	public void setAdPosition(java.lang.String value) {
		this.adPosition = value;
	}
	
	public java.lang.String getAdPosition() {
		return this.adPosition;
	}
	public void setAdPicture(java.lang.String value) {
		this.adPicture = value;
	}
	
	public java.lang.String getAdPicture() {
		return this.adPicture;
	}
	public void setAdName(java.lang.String value) {
		this.adName = value;
	}
	
	public java.lang.String getAdName() {
		return this.adName;
	}
	public void setAdWidth(java.lang.Integer value) {
		this.adWidth = value;
	}
	
	public java.lang.Integer getAdWidth() {
		return this.adWidth;
	}
	public void setAdHeight(java.lang.Integer value) {
		this.adHeight = value;
	}
	
	public java.lang.Integer getAdHeight() {
		return this.adHeight;
	}
	public void setAdDesc(java.lang.String value) {
		this.adDesc = value;
	}
	
	public java.lang.String getAdDesc() {
		return this.adDesc;
	}
	public void setAdUrl(java.lang.String value) {
		this.adUrl = value;
	}
	
	public java.lang.String getAdUrl() {
		return this.adUrl;
	}
	public void setBdateBegin(java.util.Date value) {
		this.bdateBegin = value;
	}
	
	public java.util.Date getBdateBegin() {
		return this.bdateBegin;
	}
	public void setBdateEnd(java.util.Date value) {
		this.bdateEnd = value;
	}
	
	public java.util.Date getBdateEnd() {
		return this.bdateEnd;
	}
	public void setBdate(java.util.Date value) {
		this.bdate = value;
	}
	
	public java.util.Date getBdate() {
		return this.bdate;
	}
	public void setEdateBegin(java.util.Date value) {
		this.edateBegin = value;
	}
	
	public java.util.Date getEdateBegin() {
		return this.edateBegin;
	}
	public void setEdateEnd(java.util.Date value) {
		this.edateEnd = value;
	}
	
	public java.util.Date getEdateEnd() {
		return this.edateEnd;
	}
	public void setEdate(java.util.Date value) {
		this.edate = value;
	}
	
	public java.util.Date getEdate() {
		return this.edate;
	}
	public void setDeliveryFlag(java.lang.Integer value) {
		this.deliveryFlag = value;
	}
	
	public java.lang.Integer getDeliveryFlag() {
		return this.deliveryFlag;
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