/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import java.util.List;

import com.hd.tsa.entity.StoreAttachment;
import com.hd.sfw.core.model.Pagination;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-12-11
 */
public class StoreInfo extends Pagination<StoreInfo>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String storeName;
	private java.lang.String userAccount;
	private java.lang.String logo;
	private java.lang.String address;
	private java.lang.String linkman;
	private java.lang.String phone;
	private java.lang.String orderPhone;
	private java.lang.Integer initiatePrice;
	private java.lang.Integer dphPrice;
	private java.lang.String affiche;
	private java.lang.String notice;
	private java.lang.String dispatching;
	private java.lang.String license;
	private java.lang.String permit;
	private java.lang.String dphTime;
	private java.lang.Double storeScore;
	private java.lang.Double dishesScore;
	private java.lang.Double dphScore;
	private java.util.Date evaluateDate;
	private java.lang.Integer status;
	private java.lang.String storeIntro;
	private java.lang.Integer storeStatus;
	private java.lang.String createUser;
	private java.util.Date createDate;
	private java.lang.String updateUser;
	private java.util.Date updateDate;
	private java.lang.Integer deleteFlag;
	private java.lang.String remarks;
	private java.lang.String extend1;
	private java.lang.String extend2;
	private java.lang.String extend3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date evaluateDateBegin;
	private java.util.Date evaluateDateEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	private java.util.Date updateDateBegin;
	private java.util.Date updateDateEnd;

	private String fileIds;
	private java.lang.String cuiIds;
	private int sales; //月销量
	private List<StoreAttachment> list;//餐厅简介图片
	private java.lang.String imgIds;
	private java.lang.String paths;
	
	public StoreInfo(){
	}

	public StoreInfo(
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
	public void setStoreName(java.lang.String value) {
		this.storeName = value;
	}
	
	public java.lang.String getStoreName() {
		return this.storeName;
	}
	public void setUserAccount(java.lang.String value) {
		this.userAccount = value;
	}
	
	public java.lang.String getUserAccount() {
		return this.userAccount;
	}
	public void setLogo(java.lang.String value) {
		this.logo = value;
	}
	
	public java.lang.String getLogo() {
		return this.logo;
	}
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setLinkman(java.lang.String value) {
		this.linkman = value;
	}
	
	public java.lang.String getLinkman() {
		return this.linkman;
	}
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setOrderPhone(java.lang.String value) {
		this.orderPhone = value;
	}
	
	public java.lang.String getOrderPhone() {
		return this.orderPhone;
	}
	public void setInitiatePrice(java.lang.Integer value) {
		this.initiatePrice = value;
	}
	
	public java.lang.Integer getInitiatePrice() {
		return this.initiatePrice;
	}
	public void setDphPrice(java.lang.Integer value) {
		this.dphPrice = value;
	}
	
	public java.lang.Integer getDphPrice() {
		return this.dphPrice;
	}
	public void setAffiche(java.lang.String value) {
		this.affiche = value;
	}
	
	public java.lang.String getAffiche() {
		return this.affiche;
	}
	public void setNotice(java.lang.String value) {
		this.notice = value;
	}
	
	public java.lang.String getNotice() {
		return this.notice;
	}
	public void setDispatching(java.lang.String value) {
		this.dispatching = value;
	}
	
	public java.lang.String getDispatching() {
		return this.dispatching;
	}
	public void setLicense(java.lang.String value) {
		this.license = value;
	}
	
	public java.lang.String getLicense() {
		return this.license;
	}
	public void setPermit(java.lang.String value) {
		this.permit = value;
	}
	
	public java.lang.String getPermit() {
		return this.permit;
	}
	public void setDphTime(java.lang.String value) {
		this.dphTime = value;
	}
	
	public java.lang.String getDphTime() {
		return this.dphTime;
	}
	public void setStoreScore(java.lang.Double value) {
		this.storeScore = value;
	}
	
	public java.lang.Double getStoreScore() {
		return this.storeScore;
	}
	public void setDishesScore(java.lang.Double value) {
		this.dishesScore = value;
	}
	
	public java.lang.Double getDishesScore() {
		return this.dishesScore;
	}
	public void setDphScore(java.lang.Double value) {
		this.dphScore = value;
	}
	
	public java.lang.Double getDphScore() {
		return this.dphScore;
	}
	public void setEvaluateDateBegin(java.util.Date value) {
		this.evaluateDateBegin = value;
	}
	
	public java.util.Date getEvaluateDateBegin() {
		return this.evaluateDateBegin;
	}
	public void setEvaluateDateEnd(java.util.Date value) {
		this.evaluateDateEnd = value;
	}
	
	public java.util.Date getEvaluateDateEnd() {
		return this.evaluateDateEnd;
	}
	public void setEvaluateDate(java.util.Date value) {
		this.evaluateDate = value;
	}
	
	public java.util.Date getEvaluateDate() {
		return this.evaluateDate;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setStoreIntro(java.lang.String value) {
		this.storeIntro = value;
	}
	
	public java.lang.String getStoreIntro() {
		return this.storeIntro;
	}
	public void setStoreStatus(java.lang.Integer value) {
		this.storeStatus = value;
	}
	
	public java.lang.Integer getStoreStatus() {
		return this.storeStatus;
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
	public void setDeleteFlag(java.lang.Integer value) {
		this.deleteFlag = value;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
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

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public List<StoreAttachment> getList() {
		return list;
	}

	public void setList(List<StoreAttachment> list) {
		this.list = list;
	}

	public java.lang.String getCuiIds() {
		return cuiIds;
	}

	public void setCuiIds(java.lang.String cuiIds) {
		this.cuiIds = cuiIds;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public java.lang.String getImgIds() {
		return imgIds;
	}

	public void setImgIds(java.lang.String imgIds) {
		this.imgIds = imgIds;
	}

	public java.lang.String getPaths() {
		return paths;
	}

	public void setPaths(java.lang.String paths) {
		this.paths = paths;
	}

}