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
 * @date	2017-12-06
 */
public class ComplaintProposal extends Pagination<ComplaintProposal>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.String id;
	private java.lang.String title;
	private java.lang.String content;
	private java.lang.String theType;
	private java.lang.Integer type;
	private java.lang.String feedback;
	private java.lang.String level;
	private java.lang.Integer adopt;
	private java.lang.String adoptEvaluate;
	private java.lang.Integer evaluateNum;
	private java.lang.String evaluateContent;
	private java.lang.String menuCode;
	private java.lang.String jurisdictionCode;
	private java.lang.Integer state;
	private java.lang.Integer track;
	private java.lang.Integer deleted;
	private java.util.Date createTime;
	private java.lang.String createUser;
	private java.lang.String extends1;
	private java.lang.String extends2;
	private java.lang.String extends3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	
	private java.lang.String reason;
	
	private java.util.Date feedbackTime;
	private java.util.Date suerTime;
	private java.lang.Integer timeDifference;
	
	private List<ComplaintProposalAttachment> list;

	public java.lang.String getReason() {
		return reason;
	}

	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}

	public java.util.Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(java.util.Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public java.util.Date getSuerTime() {
		return suerTime;
	}

	public void setSuerTime(java.util.Date suerTime) {
		this.suerTime = suerTime;
	}

	public java.lang.Integer getTimeDifference() {
		return timeDifference;
	}

	public void setTimeDifference(java.lang.Integer timeDifference) {
		this.timeDifference = timeDifference;
	}

	public ComplaintProposal(){
	}

	public ComplaintProposal(
		java.lang.String id
	){
		this.id = id;
	}

	public java.lang.String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(java.lang.String menuCode) {
		this.menuCode = menuCode;
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setTheType(java.lang.String value) {
		this.theType = value;
	}
	
	public java.lang.String getTheType() {
		return this.theType;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setFeedback(java.lang.String value) {
		this.feedback = value;
	}
	
	public java.lang.String getFeedback() {
		return this.feedback;
	}
	public void setLevel(java.lang.String value) {
		this.level = value;
	}
	
	public java.lang.String getLevel() {
		return this.level;
	}
	public void setAdopt(java.lang.Integer value) {
		this.adopt = value;
	}
	
	public java.lang.Integer getAdopt() {
		return this.adopt;
	}
	public void setAdoptEvaluate(java.lang.String value) {
		this.adoptEvaluate = value;
	}
	
	public java.lang.String getAdoptEvaluate() {
		return this.adoptEvaluate;
	}
	public void setEvaluateNum(java.lang.Integer value) {
		this.evaluateNum = value;
	}
	
	public java.lang.Integer getEvaluateNum() {
		return this.evaluateNum;
	}
	public void setEvaluateContent(java.lang.String value) {
		this.evaluateContent = value;
	}
	
	public java.lang.String getEvaluateContent() {
		return this.evaluateContent;
	}
	public void setJurisdictionCode(java.lang.String value) {
		this.jurisdictionCode = value;
	}
	
	public java.lang.String getJurisdictionCode() {
		return this.jurisdictionCode;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setTrack(java.lang.Integer value) {
		this.track = value;
	}
	
	public java.lang.Integer getTrack() {
		return this.track;
	}
	public void setDeleted(java.lang.Integer value) {
		this.deleted = value;
	}
	
	public java.lang.Integer getDeleted() {
		return this.deleted;
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

	public List<ComplaintProposalAttachment> getList() {
		return list;
	}

	public void setList(List<ComplaintProposalAttachment> list) {
		this.list = list;
	}

}