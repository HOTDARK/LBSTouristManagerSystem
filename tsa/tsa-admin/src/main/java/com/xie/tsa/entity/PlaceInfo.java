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
 * @date	2018-03-17
 */
public class PlaceInfo extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private Integer id;
	private String mapName;
	private String imageUrl;
	private String remark;
	private Integer createdBy;
	private java.util.Date createdTime;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createdTimeBegin;
	private java.util.Date createdTimeEnd;

	public PlaceInfo(){
	}

	public PlaceInfo(
		Integer id
	){
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setMapName(String value) {
		this.mapName = value;
	}
	
	public String getMapName() {
		return this.mapName;
	}
	public void setImageUrl(String value) {
		this.imageUrl = value;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
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

}