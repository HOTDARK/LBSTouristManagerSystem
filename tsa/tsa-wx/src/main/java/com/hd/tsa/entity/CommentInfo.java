/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import java.util.List;

import com.hd.sfw.core.model.Pagination;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-12-11
 */
public class CommentInfo extends Pagination<CommentInfo>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long storeId;
	private java.lang.String orderNum;
	private java.lang.Integer storeScore;
	private java.lang.Integer dishesScore;
	private java.lang.Integer dphScore;
	private java.lang.String content;
	private java.lang.String replay;
	private java.lang.Integer pictureFlag;
	private java.lang.String userPhoto;
	private java.lang.String userName;
	private java.lang.String user;
	private java.util.Date date;
	private java.lang.Integer deleteFlag;
	private java.lang.Integer replayFlag;
	private java.lang.String extend1;
	private java.lang.String extend2;
	private java.lang.String extend3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date dateBegin;
	private java.util.Date dateEnd;
	
	private List<CommentAttachment> list;

	public CommentInfo(){
	}

	public CommentInfo(
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
	public void setStoreId(java.lang.Long value) {
		this.storeId = value;
	}
	
	public java.lang.Long getStoreId() {
		return this.storeId;
	}
	public void setOrderNum(java.lang.String value) {
		this.orderNum = value;
	}
	
	public java.lang.String getOrderNum() {
		return this.orderNum;
	}
	public void setStoreScore(java.lang.Integer value) {
		this.storeScore = value;
	}
	
	public java.lang.Integer getStoreScore() {
		return this.storeScore;
	}
	public void setDishesScore(java.lang.Integer value) {
		this.dishesScore = value;
	}
	
	public java.lang.Integer getDishesScore() {
		return this.dishesScore;
	}
	public void setDphScore(java.lang.Integer value) {
		this.dphScore = value;
	}
	
	public java.lang.Integer getDphScore() {
		return this.dphScore;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setReplay(java.lang.String value) {
		this.replay = value;
	}
	
	public java.lang.String getReplay() {
		return this.replay;
	}
	public void setPictureFlag(java.lang.Integer value) {
		this.pictureFlag = value;
	}
	
	public java.lang.Integer getPictureFlag() {
		return this.pictureFlag;
	}
	public void setUserPhoto(java.lang.String value) {
		this.userPhoto = value;
	}
	
	public java.lang.String getUserPhoto() {
		return this.userPhoto;
	}
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	public void setUser(java.lang.String value) {
		this.user = value;
	}
	
	public java.lang.String getUser() {
		return this.user;
	}
	public void setDateBegin(java.util.Date value) {
		this.dateBegin = value;
	}
	
	public java.util.Date getDateBegin() {
		return this.dateBegin;
	}
	public void setDateEnd(java.util.Date value) {
		this.dateEnd = value;
	}
	
	public java.util.Date getDateEnd() {
		return this.dateEnd;
	}
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	public java.util.Date getDate() {
		return this.date;
	}
	public void setDeleteFlag(java.lang.Integer value) {
		this.deleteFlag = value;
	}
	
	public java.lang.Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	public void setReplayFlag(java.lang.Integer value) {
		this.replayFlag = value;
	}
	
	public java.lang.Integer getReplayFlag() {
		return this.replayFlag;
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
	public void setExtend3(java.lang.String value) {
		this.extend3 = value;
	}
	
	public java.lang.String getExtend3() {
		return this.extend3;
	}

	public List<CommentAttachment> getList() {
		return list;
	}

	public void setList(List<CommentAttachment> list) {
		this.list = list;
	}

}