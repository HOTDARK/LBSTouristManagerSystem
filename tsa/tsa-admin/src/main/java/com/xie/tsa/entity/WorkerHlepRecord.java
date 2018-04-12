/*
 * Copyright © 2018, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.xie.tsa.entity;
import com.hd.sfw.core.model.BaseModel;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2018-03-19
 */
public class WorkerHlepRecord extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private String recordId;
	private String helpInfoId;
	private Integer touristId;
	private Integer workerId;
	private String touristRemark;
	private Integer level;
	private String totalTime;
	private java.util.Date releaseTime;
	private java.util.Date finishedTime;
	//columns END
	
	//用于查询的时间列
	private java.util.Date releaseTimeBegin;
	private java.util.Date releaseTimeEnd;
	private java.util.Date finishedTimeBegin;
	private java.util.Date finishedTimeEnd;

	public WorkerHlepRecord(){
	}

	public WorkerHlepRecord(
		String recordId
	){
		this.recordId = recordId;
	}

	public void setRecordId(String value) {
		this.recordId = value;
	}
	
	public String getRecordId() {
		return this.recordId;
	}
	public void setHelpInfoId(String value) {
		this.helpInfoId = value;
	}
	
	public String getHelpInfoId() {
		return this.helpInfoId;
	}
	public void setTouristId(Integer value) {
		this.touristId = value;
	}
	
	public Integer getTouristId() {
		return this.touristId;
	}
	public void setWorkerId(Integer value) {
		this.workerId = value;
	}
	
	public Integer getWorkerId() {
		return this.workerId;
	}
	public void setTouristRemark(String value) {
		this.touristRemark = value;
	}
	
	public String getTouristRemark() {
		return this.touristRemark;
	}
	public void setLevel(Integer value) {
		this.level = value;
	}
	
	public Integer getLevel() {
		return this.level;
	}
	public void setTotalTime(String value) {
		this.totalTime = value;
	}
	
	public String getTotalTime() {
		return this.totalTime;
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
	public void setFinishedTimeBegin(java.util.Date value) {
		this.finishedTimeBegin = value;
	}
	
	public java.util.Date getFinishedTimeBegin() {
		return this.finishedTimeBegin;
	}
	public void setFinishedTimeEnd(java.util.Date value) {
		this.finishedTimeEnd = value;
	}
	
	public java.util.Date getFinishedTimeEnd() {
		return this.finishedTimeEnd;
	}
	public void setFinishedTime(java.util.Date value) {
		this.finishedTime = value;
	}
	
	public java.util.Date getFinishedTime() {
		return this.finishedTime;
	}

}