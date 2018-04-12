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
 * @date	2018-03-18
 */
public class PlaceLocation extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private Integer placeId;
	private Integer type;
	private String location;
	private Integer createdBy;
	private java.util.Date createdTime;
	private String image;
	private String remark;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createdTimeBegin;
	private java.util.Date createdTimeEnd;

	public PlaceLocation(){
	}

	public PlaceLocation(
		Integer placeId
	){
		this.placeId = placeId;
	}

	public void setPlaceId(Integer value) {
		this.placeId = value;
	}
	
	public Integer getPlaceId() {
		return this.placeId;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setLocation(String value) {
		this.location = value;
	}
	
	public String getLocation() {
		return this.location;
	}
	public void setCreatedBy(Integer value) {
		this.createdBy = value;
	}
	
	public Integer getCreatedBy() {
		return this.createdBy;
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
	public void setImage(String value) {
		this.image = value;
	}
	
	public String getImage() {
		return this.image;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}

}