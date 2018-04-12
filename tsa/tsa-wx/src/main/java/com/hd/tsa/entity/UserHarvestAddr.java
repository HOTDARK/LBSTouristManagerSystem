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
 * @date	2017-11-30
 */
public class UserHarvestAddr extends Pagination<UserHarvestAddr> {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String xgh;
	private java.lang.String addrConsignee;
	private java.lang.String addrContact;
	private java.lang.String addrArea;
	private java.lang.String addrDetail;
	private java.lang.String addrLabel;
	private java.lang.Integer addrDefault;
	private java.lang.String remarks;
	private java.lang.String field1;
	private java.lang.Integer field2;
	//columns END
	
	//用于查询的时间列

	public UserHarvestAddr(){
	}

	public UserHarvestAddr(
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
	public void setXgh(java.lang.String value) {
		this.xgh = value;
	}
	
	public java.lang.String getXgh() {
		return this.xgh;
	}
	public void setAddrConsignee(java.lang.String value) {
		this.addrConsignee = value;
	}
	
	public java.lang.String getAddrConsignee() {
		return this.addrConsignee;
	}
	public void setAddrContact(java.lang.String value) {
		this.addrContact = value;
	}
	
	public java.lang.String getAddrContact() {
		return this.addrContact;
	}
	public void setAddrArea(java.lang.String value) {
		this.addrArea = value;
	}
	
	public java.lang.String getAddrArea() {
		return this.addrArea;
	}
	public void setAddrDetail(java.lang.String value) {
		this.addrDetail = value;
	}
	
	public java.lang.String getAddrDetail() {
		return this.addrDetail;
	}
	public void setAddrLabel(java.lang.String value) {
		this.addrLabel = value;
	}
	
	public java.lang.String getAddrLabel() {
		return this.addrLabel;
	}
	public void setAddrDefault(java.lang.Integer value) {
		this.addrDefault = value;
	}
	
	public java.lang.Integer getAddrDefault() {
		return this.addrDefault;
	}
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	public void setField1(java.lang.String value) {
		this.field1 = value;
	}
	
	public java.lang.String getField1() {
		return this.field1;
	}
	public void setField2(java.lang.Integer value) {
		this.field2 = value;
	}
	
	public java.lang.Integer getField2() {
		return this.field2;
	}

}