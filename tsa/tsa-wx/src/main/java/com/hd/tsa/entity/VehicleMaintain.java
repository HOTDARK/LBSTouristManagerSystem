/*
 * Copyright © 2018, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import java.util.List;

import com.hd.sfw.core.model.Pagination;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2018-01-17
 */
public class VehicleMaintain extends Pagination<VehicleMaintain>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String vehicleLicense;
	private java.util.Date repairDate;
	private java.lang.String repairFactory;
	private java.lang.String repairCause;
	private java.lang.String budgetCost;
	private java.lang.String applicant;
	private java.lang.String deptOpinion;
	private java.lang.String leaderOpinion;
	private java.lang.String totalCost;
	private java.lang.String materialCost;
	private java.lang.String auditor;
	private java.lang.String auditOpinion;
	private java.lang.Integer state;
	private java.lang.String createUser;
	private java.util.Date createDate;
	private java.lang.Integer deleted;
	private java.lang.String permitCode;
	private java.lang.String expand1;
	private java.lang.String expand2;
	private java.lang.String expand3;
	private java.lang.String menuCode;
	private java.lang.String invoiceNum;
	private java.util.Date invoiceDate;
	private java.util.Date deptAuditDate;
	private java.lang.String deptAuditUser;
	private java.util.Date safeAuditDate;
	private java.lang.String leaderAuditUser;
	private java.util.Date leaderAuditDate;
	//columns END
	
	//用于查询的时间列
	private java.util.Date repairDateBegin;
	private java.util.Date repairDateEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	private java.util.Date invoiceDateBegin;
	private java.util.Date invoiceDateEnd;
	private java.util.Date deptAuditDateBegin;
	private java.util.Date deptAuditDateEnd;
	private java.util.Date safeAuditDateBegin;
	private java.util.Date safeAuditDateEnd;
	private java.util.Date leaderAuditDateBegin;
	private java.util.Date leaderAuditDateEnd;

	private List<ByoMaterial> byoList;
	private java.lang.String repairFactoryName;
	private java.lang.String applicantName;
	
	public VehicleMaintain(){
	}

	public VehicleMaintain(
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
	public void setVehicleLicense(java.lang.String value) {
		this.vehicleLicense = value;
	}
	
	public java.lang.String getVehicleLicense() {
		return this.vehicleLicense;
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
	public void setRepairFactory(java.lang.String value) {
		this.repairFactory = value;
	}
	
	public java.lang.String getRepairFactory() {
		return this.repairFactory;
	}
	public void setRepairCause(java.lang.String value) {
		this.repairCause = value;
	}
	
	public java.lang.String getRepairCause() {
		return this.repairCause;
	}
	public void setBudgetCost(java.lang.String value) {
		this.budgetCost = value;
	}
	
	public java.lang.String getBudgetCost() {
		return this.budgetCost;
	}
	public void setApplicant(java.lang.String value) {
		this.applicant = value;
	}
	
	public java.lang.String getApplicant() {
		return this.applicant;
	}
	public void setDeptOpinion(java.lang.String value) {
		this.deptOpinion = value;
	}
	
	public java.lang.String getDeptOpinion() {
		return this.deptOpinion;
	}
	public void setLeaderOpinion(java.lang.String value) {
		this.leaderOpinion = value;
	}
	
	public java.lang.String getLeaderOpinion() {
		return this.leaderOpinion;
	}
	public void setTotalCost(java.lang.String value) {
		this.totalCost = value;
	}
	
	public java.lang.String getTotalCost() {
		return this.totalCost;
	}
	public void setMaterialCost(java.lang.String value) {
		this.materialCost = value;
	}
	
	public java.lang.String getMaterialCost() {
		return this.materialCost;
	}
	public void setAuditor(java.lang.String value) {
		this.auditor = value;
	}
	
	public java.lang.String getAuditor() {
		return this.auditor;
	}
	public void setAuditOpinion(java.lang.String value) {
		this.auditOpinion = value;
	}
	
	public java.lang.String getAuditOpinion() {
		return this.auditOpinion;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	public java.lang.Integer getState() {
		return this.state;
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
	public void setPermitCode(java.lang.String value) {
		this.permitCode = value;
	}
	
	public java.lang.String getPermitCode() {
		return this.permitCode;
	}
	public void setExpand1(java.lang.String value) {
		this.expand1 = value;
	}
	
	public java.lang.String getExpand1() {
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
	public void setMenuCode(java.lang.String value) {
		this.menuCode = value;
	}
	
	public java.lang.String getMenuCode() {
		return this.menuCode;
	}
	public void setInvoiceNum(java.lang.String value) {
		this.invoiceNum = value;
	}
	
	public java.lang.String getInvoiceNum() {
		return this.invoiceNum;
	}
	public void setInvoiceDateBegin(java.util.Date value) {
		this.invoiceDateBegin = value;
	}
	
	public java.util.Date getInvoiceDateBegin() {
		return this.invoiceDateBegin;
	}
	public void setInvoiceDateEnd(java.util.Date value) {
		this.invoiceDateEnd = value;
	}
	
	public java.util.Date getInvoiceDateEnd() {
		return this.invoiceDateEnd;
	}
	public void setInvoiceDate(java.util.Date value) {
		this.invoiceDate = value;
	}
	
	public java.util.Date getInvoiceDate() {
		return this.invoiceDate;
	}
	public void setDeptAuditDateBegin(java.util.Date value) {
		this.deptAuditDateBegin = value;
	}
	
	public java.util.Date getDeptAuditDateBegin() {
		return this.deptAuditDateBegin;
	}
	public void setDeptAuditDateEnd(java.util.Date value) {
		this.deptAuditDateEnd = value;
	}
	
	public java.util.Date getDeptAuditDateEnd() {
		return this.deptAuditDateEnd;
	}
	public void setDeptAuditDate(java.util.Date value) {
		this.deptAuditDate = value;
	}
	
	public java.util.Date getDeptAuditDate() {
		return this.deptAuditDate;
	}
	public void setDeptAuditUser(java.lang.String value) {
		this.deptAuditUser = value;
	}
	
	public java.lang.String getDeptAuditUser() {
		return this.deptAuditUser;
	}
	public void setSafeAuditDateBegin(java.util.Date value) {
		this.safeAuditDateBegin = value;
	}
	
	public java.util.Date getSafeAuditDateBegin() {
		return this.safeAuditDateBegin;
	}
	public void setSafeAuditDateEnd(java.util.Date value) {
		this.safeAuditDateEnd = value;
	}
	
	public java.util.Date getSafeAuditDateEnd() {
		return this.safeAuditDateEnd;
	}
	public void setSafeAuditDate(java.util.Date value) {
		this.safeAuditDate = value;
	}
	
	public java.util.Date getSafeAuditDate() {
		return this.safeAuditDate;
	}
	public void setLeaderAuditUser(java.lang.String value) {
		this.leaderAuditUser = value;
	}
	
	public java.lang.String getLeaderAuditUser() {
		return this.leaderAuditUser;
	}
	public void setLeaderAuditDateBegin(java.util.Date value) {
		this.leaderAuditDateBegin = value;
	}
	
	public java.util.Date getLeaderAuditDateBegin() {
		return this.leaderAuditDateBegin;
	}
	public void setLeaderAuditDateEnd(java.util.Date value) {
		this.leaderAuditDateEnd = value;
	}
	
	public java.util.Date getLeaderAuditDateEnd() {
		return this.leaderAuditDateEnd;
	}
	public void setLeaderAuditDate(java.util.Date value) {
		this.leaderAuditDate = value;
	}
	
	public java.util.Date getLeaderAuditDate() {
		return this.leaderAuditDate;
	}

	public List<ByoMaterial> getByoList() {
		return byoList;
	}

	public void setByoList(List<ByoMaterial> byoList) {
		this.byoList = byoList;
	}

	public java.lang.String getRepairFactoryName() {
		return repairFactoryName;
	}

	public void setRepairFactoryName(java.lang.String repairFactoryName) {
		this.repairFactoryName = repairFactoryName;
	}

	public java.lang.String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(java.lang.String applicantName) {
		this.applicantName = applicantName;
	}

}