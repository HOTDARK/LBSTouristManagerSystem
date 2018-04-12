/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import com.hd.sfw.core.model.Pagination;

/**
 * 报修单实体类
 * @version	0.0.1
 * @author	LG
 * @date	2017-08-14
 */
public class RepairInfo extends Pagination<RepairInfo>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String repairNum;
	private java.lang.String repairChannel;
	private java.util.Date repairDate;
	private java.lang.String repairType;
	private java.lang.String repairArea;
	private java.lang.String detailLocation;
	private java.lang.String repairProjectOne;
	private java.lang.String repairProjectTwo;
	private java.lang.String repairContent;
	private java.util.Date orderRepairDate;
	private java.lang.String repairUser;
	private java.lang.String repairUserName;
	private java.lang.String userPhone;
	private java.lang.String repairCompany;
	private java.lang.String serviceCompany;
	private java.lang.String handCompany;
	private java.lang.String acceptUser;
	private java.util.Date responseTime;
	private java.lang.String repairState;
	private java.util.Date completeDate;
	private java.lang.String repairPerson;
	private java.lang.String paymentType;
	private java.lang.String budgetAmount;
	private java.lang.Integer isConfirm;
	private java.lang.Integer repairScheme;
	private java.lang.String satisfaction;
	private java.lang.String finalAmount;
	private java.lang.Integer isReceipt;
	private java.lang.Integer revokeState;
	private java.lang.String permitCode;
	private java.lang.String createUser;
	private java.util.Date createDate;
	private java.lang.Integer deleted;
	private java.lang.Integer expand1;
	private java.lang.String expand2;
	private java.lang.String expand3;
	private java.lang.String expand4;
	//columns END
	private String xgh;//学工号
	private String repairAreaStr;
	private Boolean repairFlag=false;
	private String repairCompanyStr;
	public String getRepairAreaStr() {
		return repairAreaStr;
	}
	
	public void setRepairAreaStr(String repairAreaStr) {
		this.repairAreaStr = repairAreaStr;
	}

	public String getRepairProjectOneStr() {
		return repairProjectOneStr;
	}

	public void setRepairProjectOneStr(String repairProjectOneStr) {
		this.repairProjectOneStr = repairProjectOneStr;
	}

	public String getServiceCompanyStr() {
		return serviceCompanyStr;
	}

	public void setServiceCompanyStr(String serviceCompanyStr) {
		this.serviceCompanyStr = serviceCompanyStr;
	}

	public int getUserTime() {
		return userTime;
	}

	public void setUserTime(int userTime) {
		this.userTime = userTime;
	}

	public String getRepairStateStr() {
		return repairStateStr;
	}

	public void setRepairStateStr(String repairStateStr) {
		this.repairStateStr = repairStateStr;
	}

	private String repairProjectOneStr;
	private String serviceCompanyStr;
	private int userTime;
	private String repairStateStr;
	private String queryStr;
	private int currentPage=1;
	private String channelStr;
	private String paymentStr;
	private String picPath;
	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	//用于查询的时间列
	private java.util.Date repairDateBegin;
	private java.util.Date repairDateEnd;
	private java.util.Date orderRepairDateBegin;
	private java.util.Date orderRepairDateEnd;
	private java.util.Date responseTimeBegin;
	private java.util.Date responseTimeEnd;
	private java.util.Date completeDateBegin;
	private java.util.Date completeDateEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;

	public RepairInfo(){
	}

	public RepairInfo(
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
	public void setRepairNum(java.lang.String value) {
		this.repairNum = value;
	}
	
	public java.lang.String getRepairNum() {
		return this.repairNum;
	}
	public void setRepairChannel(java.lang.String value) {
		this.repairChannel = value;
	}
	
	public java.lang.String getRepairChannel() {
		return this.repairChannel;
	}
	public void setRepairDateBegin(java.util.Date value) {
		this.repairDateBegin = value;
	}
	
	public java.util.Date getRepairDateBegin() {
		return this.repairDateBegin;
	}
	public void setRepairDateEnd(java.util.Date value) {
		this.repairDateEnd = value;
	}
	
	public java.util.Date getRepairDateEnd() {
		return this.repairDateEnd;
	}
	public void setRepairDate(java.util.Date value) {
		this.repairDate = value;
	}
	
	public java.util.Date getRepairDate() {
		return this.repairDate;
	}
	public void setRepairType(java.lang.String value) {
		this.repairType = value;
	}
	
	public java.lang.String getRepairType() {
		return this.repairType;
	}
	public void setRepairArea(java.lang.String value) {
		this.repairArea = value;
	}
	
	public java.lang.String getRepairArea() {
		return this.repairArea;
	}
	public void setDetailLocation(java.lang.String value) {
		this.detailLocation = value;
	}
	
	public java.lang.String getDetailLocation() {
		return this.detailLocation;
	}
	public void setRepairProjectOne(java.lang.String value) {
		this.repairProjectOne = value;
	}
	
	public java.lang.String getRepairProjectOne() {
		return this.repairProjectOne;
	}
	public void setRepairProjectTwo(java.lang.String value) {
		this.repairProjectTwo = value;
	}
	
	public java.lang.String getRepairProjectTwo() {
		return this.repairProjectTwo;
	}
	public void setRepairContent(java.lang.String value) {
		this.repairContent = value;
	}
	
	public java.lang.String getRepairContent() {
		return this.repairContent;
	}
	public void setOrderRepairDateBegin(java.util.Date value) {
		this.orderRepairDateBegin = value;
	}
	
	public java.util.Date getOrderRepairDateBegin() {
		return this.orderRepairDateBegin;
	}
	public void setOrderRepairDateEnd(java.util.Date value) {
		this.orderRepairDateEnd = value;
	}
	
	public java.util.Date getOrderRepairDateEnd() {
		return this.orderRepairDateEnd;
	}
	public void setOrderRepairDate(java.util.Date value) {
		this.orderRepairDate = value;
	}
	
	public java.util.Date getOrderRepairDate() {
		return this.orderRepairDate;
	}
	public void setRepairUser(java.lang.String value) {
		this.repairUser = value;
	}
	
	public java.lang.String getRepairUser() {
		return this.repairUser;
	}
	public void setRepairUserName(java.lang.String value) {
		this.repairUserName = value;
	}
	
	public java.lang.String getRepairUserName() {
		return this.repairUserName;
	}
	public void setUserPhone(java.lang.String value) {
		this.userPhone = value;
	}
	
	public java.lang.String getUserPhone() {
		return this.userPhone;
	}
	public void setRepairCompany(java.lang.String value) {
		this.repairCompany = value;
	}
	
	public java.lang.String getRepairCompany() {
		return this.repairCompany;
	}
	public void setServiceCompany(java.lang.String value) {
		this.serviceCompany = value;
	}
	
	public java.lang.String getServiceCompany() {
		return this.serviceCompany;
	}
	public void setHandCompany(java.lang.String value) {
		this.handCompany = value;
	}
	
	public java.lang.String getHandCompany() {
		return this.handCompany;
	}
	public void setAcceptUser(java.lang.String value) {
		this.acceptUser = value;
	}
	
	public java.lang.String getAcceptUser() {
		return this.acceptUser;
	}
	public void setResponseTimeBegin(java.util.Date value) {
		this.responseTimeBegin = value;
	}
	
	public java.util.Date getResponseTimeBegin() {
		return this.responseTimeBegin;
	}
	public void setResponseTimeEnd(java.util.Date value) {
		this.responseTimeEnd = value;
	}
	
	public java.util.Date getResponseTimeEnd() {
		return this.responseTimeEnd;
	}
	public void setResponseTime(java.util.Date value) {
		this.responseTime = value;
	}
	
	public java.util.Date getResponseTime() {
		return this.responseTime;
	}
	public void setRepairState(java.lang.String value) {
		this.repairState = value;
	}
	
	public java.lang.String getRepairState() {
		return this.repairState;
	}
	public void setCompleteDateBegin(java.util.Date value) {
		this.completeDateBegin = value;
	}
	
	public java.util.Date getCompleteDateBegin() {
		return this.completeDateBegin;
	}
	public void setCompleteDateEnd(java.util.Date value) {
		this.completeDateEnd = value;
	}
	
	public java.util.Date getCompleteDateEnd() {
		return this.completeDateEnd;
	}
	public void setCompleteDate(java.util.Date value) {
		this.completeDate = value;
	}
	
	public java.util.Date getCompleteDate() {
		return this.completeDate;
	}
	public void setRepairPerson(java.lang.String value) {
		this.repairPerson = value;
	}
	
	public java.lang.String getRepairPerson() {
		return this.repairPerson;
	}
	public void setPaymentType(java.lang.String value) {
		this.paymentType = value;
	}
	
	public java.lang.String getPaymentType() {
		return this.paymentType;
	}
	public void setBudgetAmount(java.lang.String value) {
		this.budgetAmount = value;
	}
	
	public java.lang.String getBudgetAmount() {
		return this.budgetAmount;
	}
	public void setIsConfirm(java.lang.Integer value) {
		this.isConfirm = value;
	}
	
	public java.lang.Integer getIsConfirm() {
		return this.isConfirm;
	}
	public void setRepairScheme(java.lang.Integer value) {
		this.repairScheme = value;
	}
	
	public java.lang.Integer getRepairScheme() {
		return this.repairScheme;
	}
	public void setSatisfaction(java.lang.String value) {
		this.satisfaction = value;
	}
	
	public java.lang.String getSatisfaction() {
		return this.satisfaction;
	}
	public void setFinalAmount(java.lang.String value) {
		this.finalAmount = value;
	}
	
	public java.lang.String getFinalAmount() {
		return this.finalAmount;
	}
	public void setIsReceipt(java.lang.Integer value) {
		this.isReceipt = value;
	}
	
	public java.lang.Integer getIsReceipt() {
		return this.isReceipt;
	}
	public void setRevokeState(java.lang.Integer value) {
		this.revokeState = value;
	}
	
	public java.lang.Integer getRevokeState() {
		return this.revokeState;
	}
	public void setPermitCode(java.lang.String value) {
		this.permitCode = value;
	}
	
	public java.lang.String getPermitCode() {
		return this.permitCode;
	}
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
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
	public void setDeleted(java.lang.Integer value) {
		this.deleted = value;
	}
	
	public java.lang.Integer getDeleted() {
		return this.deleted;
	}
	public void setExpand1(java.lang.Integer value) {
		this.expand1 = value;
	}
	
	public java.lang.Integer getExpand1() {
		return this.expand1;
	}
	public void setExpand2(java.lang.String value) {
		this.expand2 = value;
	}
	
	public java.lang.String getExpand2() {
		return this.expand2;
	}
	public void setExpand3(java.lang.String value) {
		this.expand3 = value;
	}
	
	public java.lang.String getExpand3() {
		return this.expand3;
	}
	public void setExpand4(java.lang.String value) {
		this.expand4 = value;
	}
	
	public java.lang.String getExpand4() {
		return this.expand4;
	}

	public String getXgh() {
		return xgh;
	}

	public void setXgh(String xgh) {
		this.xgh = xgh;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getChannelStr() {
		return channelStr;
	}

	public void setChannelStr(String channelStr) {
		this.channelStr = channelStr;
	}

	public String getPaymentStr() {
		return paymentStr;
	}

	public void setPaymentStr(String paymentStr) {
		this.paymentStr = paymentStr;
	}

	public Boolean getRepairFlag() {
		return repairFlag;
	}

	public void setRepairFlag(Boolean repairFlag) {
		this.repairFlag = repairFlag;
	}

	public String getRepairCompanyStr() {
		return repairCompanyStr;
	}

	public void setRepairCompanyStr(String repairCompanyStr) {
		this.repairCompanyStr = repairCompanyStr;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

}