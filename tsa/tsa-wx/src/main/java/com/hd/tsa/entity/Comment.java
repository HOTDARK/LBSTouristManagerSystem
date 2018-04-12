package com.hd.tsa.entity;

import com.hd.sfw.core.model.BaseModel;

/**
 * 评价实体
 * @author Mr.zhang
 *
 */
public class Comment extends BaseModel{

	private static final long serialVersionUID = 7957356827498484137L;

	private java.lang.Long subjectId;  // 项目Id
	private java.lang.String orderNum; // 预约编号
	private java.lang.Integer subjectScore;   // 项目总评分
	private java.lang.Integer personScore;   // 人员评分
	private java.lang.Integer vehicleScore;   // 车辆评分
	private java.lang.String commentInfo;   // 评价信息
	private java.lang.String createUser;   // 评价人
	private java.lang.String evaluateUser;  // 被评人
	private java.lang.String vehicleLicense; // 车牌号
	private java.lang.String createUserName; // 评价人姓名
	private java.lang.String telephone; // 评价人联系方式
	private java.lang.String personSex; // 评价人性别
	
	public java.lang.Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(java.lang.Long subjectId) {
		this.subjectId = subjectId;
	}
	public java.lang.Integer getSubjectScore() {
		return subjectScore;
	}
	public void setSubjectScore(java.lang.Integer subjectScore) {
		this.subjectScore = subjectScore;
	}
	public java.lang.Integer getPersonScore() {
		return personScore;
	}
	public void setPersonScore(java.lang.Integer personScore) {
		this.personScore = personScore;
	}
	public java.lang.Integer getVehicleScore() {
		return vehicleScore;
	}
	public void setVehicleScore(java.lang.Integer vehicleScore) {
		this.vehicleScore = vehicleScore;
	}
	public java.lang.String getCommentInfo() {
		return commentInfo;
	}
	public void setCommentInfo(java.lang.String commentInfo) {
		this.commentInfo = commentInfo;
	}
	public java.lang.String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(java.lang.String createUser) {
		this.createUser = createUser;
	}
	public java.lang.String getEvaluateUser() {
		return evaluateUser;
	}
	public void setEvaluateUser(java.lang.String evaluateUser) {
		this.evaluateUser = evaluateUser;
	}
	public java.lang.String getVehicleLicense() {
		return vehicleLicense;
	}
	public void setVehicleLicense(java.lang.String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}
	public java.lang.String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(java.lang.String createUserName) {
		this.createUserName = createUserName;
	}
	public java.lang.String getTelephone() {
		return telephone;
	}
	public void setTelephone(java.lang.String telephone) {
		this.telephone = telephone;
	}
	public java.lang.String getPersonSex() {
		return personSex;
	}
	public void setPersonSex(java.lang.String personSex) {
		this.personSex = personSex;
	}
	public java.lang.String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(java.lang.String orderNum) {
		this.orderNum = orderNum;
	}
}
