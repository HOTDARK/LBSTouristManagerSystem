/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import java.util.List;

import com.hd.sfw.core.model.Pagination;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-12-11
 */
public class DietOrder extends Pagination<DietOrder>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.String orderNum;
	private java.lang.Long storeId;
	private java.lang.String orderUser;
	private java.lang.String price;
	private java.lang.String orderPhone;
	private java.lang.String sendUser;
	private java.lang.String sendPhone;
	private java.lang.String receiveName;
	private java.lang.String address;
	private java.lang.Integer payType;
	private java.util.Date orderDate;
	private java.util.Date receiveDate;
	private java.util.Date cancelDate;
	private java.lang.String dispatching;
	private java.util.Date expectReachDate;
	private java.util.Date reachDate;
	private java.lang.Integer evaluate;
	private java.lang.Integer state;
	private java.lang.String remarks;
	private java.lang.String extends1;
	private java.lang.String extends2;
	private java.lang.String extends3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date orderDateBegin;
	private java.util.Date orderDateEnd;
	private java.util.Date receiveDateBegin;
	private java.util.Date receiveDateEnd;
	private java.util.Date cancelDateBegin;
	private java.util.Date cancelDateEnd;
	private java.util.Date expectReachDateBegin;
	private java.util.Date expectReachDateEnd;
	private java.util.Date reachDateBegin;
	private java.util.Date reachDateEnd;

	private String storeName;
	private String logoPath;
	private String foodNames;
	private String openid;
	private Integer dphPrice;
	
	private List<DietOrderFood> foodList;
	
	public DietOrder(){
	}

	public DietOrder(
		java.lang.String orderNum
	){
		this.orderNum = orderNum;
	}

	public void setOrderNum(java.lang.String value) {
		this.orderNum = value;
	}
	
	public java.lang.String getOrderNum() {
		return this.orderNum;
	}
	public void setStoreId(java.lang.Long value) {
		this.storeId = value;
	}
	
	public java.lang.Long getStoreId() {
		return this.storeId;
	}
	public void setOrderUser(java.lang.String value) {
		this.orderUser = value;
	}
	
	public java.lang.String getOrderUser() {
		return this.orderUser;
	}
	public void setPrice(java.lang.String value) {
		this.price = value;
	}
	
	public java.lang.String getPrice() {
		return this.price;
	}
	public void setOrderPhone(java.lang.String value) {
		this.orderPhone = value;
	}
	
	public java.lang.String getOrderPhone() {
		return this.orderPhone;
	}
	public void setSendUser(java.lang.String value) {
		this.sendUser = value;
	}
	
	public java.lang.String getSendUser() {
		return this.sendUser;
	}
	public void setSendPhone(java.lang.String value) {
		this.sendPhone = value;
	}
	
	public java.lang.String getSendPhone() {
		return this.sendPhone;
	}
	public void setReceiveName(java.lang.String value) {
		this.receiveName = value;
	}
	
	public java.lang.String getReceiveName() {
		return this.receiveName;
	}
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setPayType(java.lang.Integer value) {
		this.payType = value;
	}
	
	public java.lang.Integer getPayType() {
		return this.payType;
	}
	public void setOrderDateBegin(java.util.Date value) {
		this.orderDateBegin = value;
	}
	
	public java.util.Date getOrderDateBegin() {
		return this.orderDateBegin;
	}
	public void setOrderDateEnd(java.util.Date value) {
		this.orderDateEnd = value;
	}
	
	public java.util.Date getOrderDateEnd() {
		return this.orderDateEnd;
	}
	public void setOrderDate(java.util.Date value) {
		this.orderDate = value;
	}
	
	public java.util.Date getOrderDate() {
		return this.orderDate;
	}
	public void setReceiveDateBegin(java.util.Date value) {
		this.receiveDateBegin = value;
	}
	
	public java.util.Date getReceiveDateBegin() {
		return this.receiveDateBegin;
	}
	public void setReceiveDateEnd(java.util.Date value) {
		this.receiveDateEnd = value;
	}
	
	public java.util.Date getReceiveDateEnd() {
		return this.receiveDateEnd;
	}
	public void setReceiveDate(java.util.Date value) {
		this.receiveDate = value;
	}
	
	public java.util.Date getReceiveDate() {
		return this.receiveDate;
	}
	public void setCancelDateBegin(java.util.Date value) {
		this.cancelDateBegin = value;
	}
	
	public java.util.Date getCancelDateBegin() {
		return this.cancelDateBegin;
	}
	public void setCancelDateEnd(java.util.Date value) {
		this.cancelDateEnd = value;
	}
	
	public java.util.Date getCancelDateEnd() {
		return this.cancelDateEnd;
	}
	public void setCancelDate(java.util.Date value) {
		this.cancelDate = value;
	}
	
	public java.util.Date getCancelDate() {
		return this.cancelDate;
	}
	public void setDispatching(java.lang.String value) {
		this.dispatching = value;
	}
	
	public java.lang.String getDispatching() {
		return this.dispatching;
	}
	public void setExpectReachDateBegin(java.util.Date value) {
		this.expectReachDateBegin = value;
	}
	
	public java.util.Date getExpectReachDateBegin() {
		return this.expectReachDateBegin;
	}
	public void setExpectReachDateEnd(java.util.Date value) {
		this.expectReachDateEnd = value;
	}
	
	public java.util.Date getExpectReachDateEnd() {
		return this.expectReachDateEnd;
	}
	public void setExpectReachDate(java.util.Date value) {
		this.expectReachDate = value;
	}
	
	public java.util.Date getExpectReachDate() {
		return this.expectReachDate;
	}
	public void setReachDateBegin(java.util.Date value) {
		this.reachDateBegin = value;
	}
	
	public java.util.Date getReachDateBegin() {
		return this.reachDateBegin;
	}
	public void setReachDateEnd(java.util.Date value) {
		this.reachDateEnd = value;
	}
	
	public java.util.Date getReachDateEnd() {
		return this.reachDateEnd;
	}
	public void setReachDate(java.util.Date value) {
		this.reachDate = value;
	}
	
	public java.util.Date getReachDate() {
		return this.reachDate;
	}
	public void setEvaluate(java.lang.Integer value) {
		this.evaluate = value;
	}
	
	public java.lang.Integer getEvaluate() {
		return this.evaluate;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	public void setExtends1(java.lang.String value) {
		this.extends1 = value;
	}
	
	public java.lang.String getExtends1() {
		return this.extends1;
	}
	public void setExtends2(java.lang.String value) {
		this.extends2 = value;
	}
	
	public java.lang.String getExtends2() {
		return this.extends2;
	}
	public void setExtends3(java.lang.String value) {
		this.extends3 = value;
	}
	
	public java.lang.String getExtends3() {
		return this.extends3;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getFoodNames() {
		return foodNames;
	}

	public void setFoodNames(String foodNames) {
		this.foodNames = foodNames;
	}

	public List<DietOrderFood> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<DietOrderFood> foodList) {
		this.foodList = foodList;
	}

	public Integer getDphPrice() {
		return dphPrice;
	}

	public void setDphPrice(Integer dphPrice) {
		this.dphPrice = dphPrice;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}