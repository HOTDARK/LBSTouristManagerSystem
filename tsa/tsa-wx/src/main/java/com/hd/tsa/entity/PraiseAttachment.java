/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import com.hd.sfw.core.model.BaseModel;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-12-06
 */
public class PraiseAttachment extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long praiseId;
	private java.lang.String fileType;
	private java.lang.String oldFileName;
	private java.lang.String fileName;
	private java.lang.Long size;
	private java.lang.String filePath;
	private java.lang.String fileSuffix;
	private java.util.Date createTime;
	private java.lang.String createUser;
	private java.lang.String extends1;
	private java.lang.String extends2;
	private java.lang.String extends3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;

	public PraiseAttachment(){
	}

	public PraiseAttachment(
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
	public void setPraiseId(java.lang.Long value) {
		this.praiseId = value;
	}
	
	public java.lang.Long getPraiseId() {
		return this.praiseId;
	}
	public void setFileType(java.lang.String value) {
		this.fileType = value;
	}
	
	public java.lang.String getFileType() {
		return this.fileType;
	}
	public void setOldFileName(java.lang.String value) {
		this.oldFileName = value;
	}
	
	public java.lang.String getOldFileName() {
		return this.oldFileName;
	}
	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}
	
	public java.lang.String getFileName() {
		return this.fileName;
	}
	public void setSize(java.lang.Long value) {
		this.size = value;
	}
	
	public java.lang.Long getSize() {
		return this.size;
	}
	public void setFilePath(java.lang.String value) {
		this.filePath = value;
	}
	
	public java.lang.String getFilePath() {
		return this.filePath;
	}
	public void setFileSuffix(java.lang.String value) {
		this.fileSuffix = value;
	}
	
	public java.lang.String getFileSuffix() {
		return this.fileSuffix;
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
	public void setExtends1(java.lang.String value) {
		this.extends1 = value;
	}
	
	public java.lang.String getExtends1() {
		return this.extends1;
	}
	public void setExtends2(java.lang.String value) {
		this.extends2 = value;
	}
	
	public java.lang.String getExtends2() {
		return this.extends2;
	}
	public void setExtends3(java.lang.String value) {
		this.extends3 = value;
	}
	
	public java.lang.String getExtends3() {
		return this.extends3;
	}

}