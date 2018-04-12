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
public class UserTrail extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private String trailId;
	private Integer userId;
	private String userTrail;
	private Integer trailPointCount;
	private java.util.Date createdTime;
	private java.util.Date recordTime;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createdTimeBegin;
	private java.util.Date createdTimeEnd;
	private java.util.Date recordTimeBegin;
	private java.util.Date recordTimeEnd;

	public UserTrail(){
	}

	public UserTrail(
		String trailId
	){
		this.trailId = trailId;
	}

	public void setTrailId(String value) {
		this.trailId = value;
	}
	
	public String getTrailId() {
		return this.trailId;
	}
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserTrail(String value) {
		this.userTrail = value;
	}
	
	public String getUserTrail() {
		return this.userTrail;
	}
	public void setTrailPointCount(Integer value) {
		this.trailPointCount = value;
	}
	
	public Integer getTrailPointCount() {
		return this.trailPointCount;
	}
	public void setCreatedTimeBegin(java.util.Date value) {
		this.createdTimeBegin = value;
	}
	
	public java.util.Date getCreatedTimeBegin() {
		return this.createdTimeBegin;
	}
	public void setCreatedTimeEnd(java.util.Date value) {
		this.createdTimeEnd = value;
	}
	
	public java.util.Date getCreatedTimeEnd() {
		return this.createdTimeEnd;
	}
	public void setCreatedTime(java.util.Date value) {
		this.createdTime = value;
	}
	
	public java.util.Date getCreatedTime() {
		return this.createdTime;
	}
	public void setRecordTimeBegin(java.util.Date value) {
		this.recordTimeBegin = value;
	}
	
	public java.util.Date getRecordTimeBegin() {
		return this.recordTimeBegin;
	}
	public void setRecordTimeEnd(java.util.Date value) {
		this.recordTimeEnd = value;
	}
	
	public java.util.Date getRecordTimeEnd() {
		return this.recordTimeEnd;
	}
	public void setRecordTime(java.util.Date value) {
		this.recordTime = value;
	}
	
	public java.util.Date getRecordTime() {
		return this.recordTime;
	}

}