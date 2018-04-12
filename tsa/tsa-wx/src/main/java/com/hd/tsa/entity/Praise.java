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
 * @date	2017-12-06
 */
public class Praise extends Pagination<Praise>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String title;
	private java.lang.String content;
	private java.lang.String organization;
	private java.lang.String individual;
	private java.lang.Integer state;
	private java.lang.Integer deleted;
	private java.util.Date createTime;
	private java.lang.String createUser;
	private java.lang.String extend1;
	private java.lang.String extend2;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	
	List<PraiseAttachment> list;

	public Praise(){
	}

	public Praise(
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
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setOrganization(java.lang.String value) {
		this.organization = value;
	}
	
	public java.lang.String getOrganization() {
		return this.organization;
	}
	public void setIndividual(java.lang.String value) {
		this.individual = value;
	}
	
	public java.lang.String getIndividual() {
		return this.individual;
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
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	public void setCreateTimeEnd(java.util.Date value) {
		this.createTimeEnd = value;
	}
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
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

	public List<PraiseAttachment> getList() {
		return list;
	}

	public void setList(List<PraiseAttachment> list) {
		this.list = list;
	}

}