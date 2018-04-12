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
public class Message extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private String messageId;
	private Integer fromUserId;
	private Integer toUserId;
	private String remark;
	private String multimedia;
	private java.util.Date createdTime;
	private java.util.Date receivedTime;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createdTimeBegin;
	private java.util.Date createdTimeEnd;
	private java.util.Date receivedTimeBegin;
	private java.util.Date receivedTimeEnd;

	public Message(){
	}

	public Message(
		String messageId
	){
		this.messageId = messageId;
	}

	public void setMessageId(String value) {
		this.messageId = value;
	}
	
	public String getMessageId() {
		return this.messageId;
	}
	public void setFromUserId(Integer value) {
		this.fromUserId = value;
	}
	
	public Integer getFromUserId() {
		return this.fromUserId;
	}
	public void setToUserId(Integer value) {
		this.toUserId = value;
	}
	
	public Integer getToUserId() {
		return this.toUserId;
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
	public void setReceivedTimeBegin(java.util.Date value) {
		this.receivedTimeBegin = value;
	}
	
	public java.util.Date getReceivedTimeBegin() {
		return this.receivedTimeBegin;
	}
	public void setReceivedTimeEnd(java.util.Date value) {
		this.receivedTimeEnd = value;
	}
	
	public java.util.Date getReceivedTimeEnd() {
		return this.receivedTimeEnd;
	}
	public void setReceivedTime(java.util.Date value) {
		this.receivedTime = value;
	}
	
	public java.util.Date getReceivedTime() {
		return this.receivedTime;
	}

}