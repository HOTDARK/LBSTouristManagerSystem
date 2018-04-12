/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.entity;
import com.hd.sfw.core.model.BaseModel;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-08-09
 */
public class UserLoginHis extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String xgh;
	private java.lang.Integer dlfs;
	private java.lang.String dlip;
	private java.util.Date dlsj;
	//columns END
	
	//用于查询的时间列
	private java.util.Date dlsjBegin;
	private java.util.Date dlsjEnd;

	public UserLoginHis(){
	}

	public UserLoginHis(
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
	public void setDlfs(java.lang.Integer value) {
		this.dlfs = value;
	}
	
	public java.lang.Integer getDlfs() {
		return this.dlfs;
	}
	public void setDlip(java.lang.String value) {
		this.dlip = value;
	}
	
	public java.lang.String getDlip() {
		return this.dlip;
	}
	public void setDlsjBegin(java.util.Date value) {
		this.dlsjBegin = value;
	}
	
	public java.util.Date getDlsjBegin() {
		return this.dlsjBegin;
	}
	public void setDlsjEnd(java.util.Date value) {
		this.dlsjEnd = value;
	}
	
	public java.util.Date getDlsjEnd() {
		return this.dlsjEnd;
	}
	public void setDlsj(java.util.Date value) {
		this.dlsj = value;
	}
	
	public java.util.Date getDlsj() {
		return this.dlsj;
	}

	@Override
	public String toString() {
		return "UserLoginHis [id=" + id + ", xgh=" + xgh + ", dlfs=" + dlfs + ", dlip=" + dlip + ", dlsj=" + dlsj
				+ ", dlsjBegin=" + dlsjBegin + ", dlsjEnd=" + dlsjEnd + "]";
	}
	
}