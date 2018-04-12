/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import com.hd.sfw.core.model.Pagination;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-11-29
 */
public class WebNews extends Pagination<WebNews>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String newsType;
	private java.lang.String newsTitle;
	private java.lang.String keyWord;
	private java.lang.String picturePath;
	private java.lang.Integer pictureFlag;
	private java.lang.String newsContent;
	private java.lang.String createUser;
	private java.util.Date createTime;
	private java.lang.String publishUser;
	private java.util.Date publishTime;
	private java.lang.Integer publishFlag;
	private java.lang.Integer deleteFlag;
	private java.lang.String extend1;
	private java.lang.String extend2;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	private java.util.Date publishTimeBegin;
	private java.util.Date publishTimeEnd;
	
	private String createUserName;
	
	private String publishUserName;

	public WebNews(){
	}

	public WebNews(
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
	public void setNewsType(java.lang.String value) {
		this.newsType = value;
	}
	
	public java.lang.String getNewsType() {
		return this.newsType;
	}
	public void setNewsTitle(java.lang.String value) {
		this.newsTitle = value;
	}
	
	public java.lang.String getNewsTitle() {
		return this.newsTitle;
	}
	public void setKeyWord(java.lang.String value) {
		this.keyWord = value;
	}
	
	public java.lang.String getKeyWord() {
		return this.keyWord;
	}
	public void setPicturePath(java.lang.String value) {
		this.picturePath = value;
	}
	
	public java.lang.String getPicturePath() {
		return this.picturePath;
	}
	public void setPictureFlag(java.lang.Integer value) {
		this.pictureFlag = value;
	}
	
	public java.lang.Integer getPictureFlag() {
		return this.pictureFlag;
	}
	public void setNewsContent(java.lang.String value) {
		this.newsContent = value;
	}
	
	public java.lang.String getNewsContent() {
		return this.newsContent;
	}
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
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
	public void setPublishUser(java.lang.String value) {
		this.publishUser = value;
	}
	
	public java.lang.String getPublishUser() {
		return this.publishUser;
	}
	public void setPublishTimeBegin(java.util.Date value) {
		this.publishTimeBegin = value;
	}
	
	public java.util.Date getPublishTimeBegin() {
		return this.publishTimeBegin;
	}
	public void setPublishTimeEnd(java.util.Date value) {
		this.publishTimeEnd = value;
	}
	
	public java.util.Date getPublishTimeEnd() {
		return this.publishTimeEnd;
	}
	public void setPublishTime(java.util.Date value) {
		this.publishTime = value;
	}
	
	public java.util.Date getPublishTime() {
		return this.publishTime;
	}
	public void setPublishFlag(java.lang.Integer value) {
		this.publishFlag = value;
	}
	
	public java.lang.Integer getPublishFlag() {
		return this.publishFlag;
	}
	public void setDeleteFlag(java.lang.Integer value) {
		this.deleteFlag = value;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	public void setExtend1(java.lang.String value) {
		this.extend1 = value;
	}
	
	public java.lang.String getExtend1() {
		return this.extend1;
	}
	public void setExtend2(java.lang.String value) {
		this.extend2 = value;
	}
	
	public java.lang.String getExtend2() {
		return this.extend2;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getPublishUserName() {
		return publishUserName;
	}

	public void setPublishUserName(String publishUserName) {
		this.publishUserName = publishUserName;
	}

}