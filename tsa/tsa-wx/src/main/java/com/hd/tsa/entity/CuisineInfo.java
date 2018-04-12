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
 * @date	2017-12-11
 */
public class CuisineInfo extends Pagination<CuisineInfo>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String cuisineName;
	private java.lang.Long parentId;
	private java.lang.Integer leafNode;
	private java.lang.Integer state;
	private java.lang.Integer seqNum;
	private java.lang.Integer deleted;
	private java.lang.String remark;
	private java.lang.String extends1;
	private java.lang.String extends2;
	private java.lang.String extends3;
	//columns END
	
	//用于查询的时间列

	public CuisineInfo(){
	}

	public CuisineInfo(
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
	public void setCuisineName(java.lang.String value) {
		this.cuisineName = value;
	}
	
	public java.lang.String getCuisineName() {
		return this.cuisineName;
	}
	public void setParentId(java.lang.Long value) {
		this.parentId = value;
	}
	
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	public void setLeafNode(java.lang.Integer value) {
		this.leafNode = value;
	}
	
	public java.lang.Integer getLeafNode() {
		return this.leafNode;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setSeqNum(java.lang.Integer value) {
		this.seqNum = value;
	}
	
	public java.lang.Integer getSeqNum() {
		return this.seqNum;
	}
	public void setDeleted(java.lang.Integer value) {
		this.deleted = value;
	}
	
	public java.lang.Integer getDeleted() {
		return this.deleted;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
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

}