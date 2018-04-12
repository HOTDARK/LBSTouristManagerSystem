/*
 * Copyright © 2018, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.xie.tsa.entity;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.model.Pagination;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2018-03-19
 */
public class HelpInfo extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	//columns START
	private String helpInfoId;
	private Integer createdBy;
	private String releasePlace;
	private String remark;
	private String multimedia;
	private java.util.Date releaseTime;
	private String userName;
	private int state; //0表示未解决
	//columns END

	//用于查询的时间列
	private java.util.Date releaseTimeBegin;
	private java.util.Date releaseTimeEnd;

	public HelpInfo(){
	}

	public HelpInfo(
		String helpInfoId
	){
		this.helpInfoId = helpInfoId;
	}

	public void setHelpInfoId(String value) {
		this.helpInfoId = value;
	}
	
	public String getHelpInfoId() {
		return this.helpInfoId;
	}
	public void setCreatedBy(Integer value) {
		this.createdBy = value;
	}
	
	public Integer getCreatedBy() {
		return this.createdBy;
	}
	public void setReleasePlace(String value) {
		this.releasePlace = value;
	}
	
	public String getReleasePlace() {
		return this.releasePlace;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	public void setMultimedia(String value) {
		this.multimedia = value;
	}
	
	public String getMultimedia() {
		return this.multimedia;
	}
	public void setReleaseTimeBegin(java.util.Date value) {
		this.releaseTimeBegin = value;
	}
	
	public java.util.Date getReleaseTimeBegin() {
		return this.releaseTimeBegin;
	}
	public void setReleaseTimeEnd(java.util.Date value) {
		this.releaseTimeEnd = value;
	}
	
	public java.util.Date getReleaseTimeEnd() {
		return this.releaseTimeEnd;
	}
	public void setReleaseTime(java.util.Date value) {
		this.releaseTime = value;
	}
	
	public java.util.Date getReleaseTime() {
		return this.releaseTime;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "HelpInfo{" +
				"helpInfoId='" + helpInfoId + '\'' +
				", createdBy=" + createdBy +
				", releasePlace='" + releasePlace + '\'' +
				", remark='" + remark + '\'' +
				", multimedia='" + multimedia + '\'' +
				", releaseTime=" + releaseTime +
				", userName='" + userName + '\'' +
				", state=" + state +
				", releaseTimeBegin=" + releaseTimeBegin +
				", releaseTimeEnd=" + releaseTimeEnd +
				'}';
	}
}