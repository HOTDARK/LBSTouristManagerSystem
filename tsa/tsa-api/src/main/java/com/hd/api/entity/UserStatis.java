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
public class UserStatis extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.util.Date statisDate;
	private java.lang.Integer syfs;
	private java.lang.Integer drsjzcs;
	private java.lang.Integer drhys;
	private java.lang.Integer dryhys;
	private java.lang.Integer drljzcs;
	private java.util.Date createTime;
	private java.lang.Integer weekRegFirst;
	private java.lang.Integer weekKeep;
	private java.lang.Integer monthRegFirst;
	private java.lang.Integer monthKeep;
	private java.lang.Integer month3Reg;
	private java.lang.Integer month3Silent;
	private java.lang.Integer month6Reg;
	private java.lang.Integer month6Silent;
	private java.lang.Integer month3SilentYester;
	private java.lang.Integer month3YesterBack;
	private java.lang.Integer month6SilentYester;
	private java.lang.Integer month6YesterBack;
	//columns END
	
	//用于查询的时间列
	private java.util.Date statisDateBegin;
	private java.util.Date statisDateEnd;
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;

	public UserStatis(){
	}

	public UserStatis(
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
	public void setStatisDateBegin(java.util.Date value) {
		this.statisDateBegin = value;
	}
	
	public java.util.Date getStatisDateBegin() {
		return this.statisDateBegin;
	}
	public void setStatisDateEnd(java.util.Date value) {
		this.statisDateEnd = value;
	}
	
	public java.util.Date getStatisDateEnd() {
		return this.statisDateEnd;
	}
	public void setStatisDate(java.util.Date value) {
		this.statisDate = value;
	}
	
	public java.util.Date getStatisDate() {
		return this.statisDate;
	}
	public void setSyfs(java.lang.Integer value) {
		this.syfs = value;
	}
	
	public java.lang.Integer getSyfs() {
		return this.syfs;
	}
	public void setDrsjzcs(java.lang.Integer value) {
		this.drsjzcs = value;
	}
	
	public java.lang.Integer getDrsjzcs() {
		return this.drsjzcs;
	}
	public void setDrhys(java.lang.Integer value) {
		this.drhys = value;
	}
	
	public java.lang.Integer getDrhys() {
		return this.drhys;
	}
	public void setDryhys(java.lang.Integer value) {
		this.dryhys = value;
	}
	
	public java.lang.Integer getDryhys() {
		return this.dryhys;
	}
	public void setDrljzcs(java.lang.Integer value) {
		this.drljzcs = value;
	}
	
	public java.lang.Integer getDrljzcs() {
		return this.drljzcs;
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
	public void setWeekRegFirst(java.lang.Integer value) {
		this.weekRegFirst = value;
	}
	
	public java.lang.Integer getWeekRegFirst() {
		return this.weekRegFirst;
	}
	public void setWeekKeep(java.lang.Integer value) {
		this.weekKeep = value;
	}
	
	public java.lang.Integer getWeekKeep() {
		return this.weekKeep;
	}
	public void setMonthRegFirst(java.lang.Integer value) {
		this.monthRegFirst = value;
	}
	
	public java.lang.Integer getMonthRegFirst() {
		return this.monthRegFirst;
	}
	public void setMonthKeep(java.lang.Integer value) {
		this.monthKeep = value;
	}
	
	public java.lang.Integer getMonthKeep() {
		return this.monthKeep;
	}
	public void setMonth3Reg(java.lang.Integer value) {
		this.month3Reg = value;
	}
	
	public java.lang.Integer getMonth3Reg() {
		return this.month3Reg;
	}
	public void setMonth3Silent(java.lang.Integer value) {
		this.month3Silent = value;
	}
	
	public java.lang.Integer getMonth3Silent() {
		return this.month3Silent;
	}
	public void setMonth6Reg(java.lang.Integer value) {
		this.month6Reg = value;
	}
	
	public java.lang.Integer getMonth6Reg() {
		return this.month6Reg;
	}
	public void setMonth6Silent(java.lang.Integer value) {
		this.month6Silent = value;
	}
	
	public java.lang.Integer getMonth6Silent() {
		return this.month6Silent;
	}
	public void setMonth3SilentYester(java.lang.Integer value) {
		this.month3SilentYester = value;
	}
	
	public java.lang.Integer getMonth3SilentYester() {
		return this.month3SilentYester;
	}
	public void setMonth3YesterBack(java.lang.Integer value) {
		this.month3YesterBack = value;
	}
	
	public java.lang.Integer getMonth3YesterBack() {
		return this.month3YesterBack;
	}
	public void setMonth6SilentYester(java.lang.Integer value) {
		this.month6SilentYester = value;
	}
	
	public java.lang.Integer getMonth6SilentYester() {
		return this.month6SilentYester;
	}
	public void setMonth6YesterBack(java.lang.Integer value) {
		this.month6YesterBack = value;
	}
	
	public java.lang.Integer getMonth6YesterBack() {
		return this.month6YesterBack;
	}

}