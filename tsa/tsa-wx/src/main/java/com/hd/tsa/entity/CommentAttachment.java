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
 * @date	2017-12-11
 */
public class CommentAttachment extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long commentId;
	private java.lang.String oldFileName;
	private java.lang.String fileName;
	private java.lang.Long size;
	private java.lang.String filePath;
	private java.lang.String fileSuffix;
	private java.util.Date createDate;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;

	public CommentAttachment(){
	}

	public CommentAttachment(
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
	public void setCommentId(java.lang.Long value) {
		this.commentId = value;
	}
	
	public java.lang.Long getCommentId() {
		return this.commentId;
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
	public void setCreateDateBegin(java.util.Date value) {
		this.createDateBegin = value;
	}
	
	public java.util.Date getCreateDateBegin() {
		return this.createDateBegin;
	}
	public void setCreateDateEnd(java.util.Date value) {
		this.createDateEnd = value;
	}
	
	public java.util.Date getCreateDateEnd() {
		return this.createDateEnd;
	}
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

}