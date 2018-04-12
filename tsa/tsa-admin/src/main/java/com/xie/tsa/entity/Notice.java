/*
 * Copyright © 2018, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.xie.tsa.entity;
import com.hd.sfw.core.model.BaseModel;

import java.util.Comparator;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2018-03-19
 */
public class Notice extends BaseModel implements Comparable<Notice>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private Integer noticeId;
	private Integer createdBy;
	private String remark;
	private Integer type;
	private java.util.Date createdTime;
	private String strTime;
	private String userName;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createdTimeBegin;
	private java.util.Date createdTimeEnd;

	public Notice(){
	}

	public Notice(
		Integer noticeId
	){
		this.noticeId = noticeId;
	}

	public void setNoticeId(Integer value) {
		this.noticeId = value;
	}
	
	public Integer getNoticeId() {
		return this.noticeId;
	}
	public void setCreatedBy(Integer value) {
		this.createdBy = value;
	}
	
	public Integer getCreatedBy() {
		return this.createdBy;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
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

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	@Override
	public int compareTo(Notice o) {

		return o.noticeId - this.noticeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}