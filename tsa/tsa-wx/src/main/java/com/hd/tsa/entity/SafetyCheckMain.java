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
 * @date	2018-01-23
 */
public class SafetyCheckMain extends Pagination<SafetyCheckMain>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String vehicleLicense;
	private java.util.Date checkDate;
	private java.lang.String driver;
	private java.lang.String inspector;
	private java.lang.String serviceRecord;
	private java.lang.Integer checkResult;
	private java.lang.String inconformtyReason;
	private java.util.Date createTime;
	private java.lang.Integer deleteFlag;
	private java.lang.String extend1;
	private java.lang.String extend2;
	private java.lang.String extend3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date checkDateBegin;
	private java.util.Date checkDateEnd;
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	
	private String driverName;
	private String inspectorName;
	private List<SafetyCheckAssistant> assistantList;

	public SafetyCheckMain(){
	}

	public SafetyCheckMain(
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
	public void setCheckDateBegin(java.util.Date value) {
		this.checkDateBegin = value;
	}
	
	public java.util.Date getCheckDateBegin() {
		return this.checkDateBegin;
	}
	public void setCheckDateEnd(java.util.Date value) {
		this.checkDateEnd = value;
	}
	
	public java.util.Date getCheckDateEnd() {
		return this.checkDateEnd;
	}
	public void setCheckDate(java.util.Date value) {
		this.checkDate = value;
	}
	
	public java.util.Date getCheckDate() {
		return this.checkDate;
	}
	public void setDriver(java.lang.String value) {
		this.driver = value;
	}
	
	public java.lang.String getDriver() {
		return this.driver;
	}
	public void setInspector(java.lang.String value) {
		this.inspector = value;
	}
	
	public java.lang.String getInspector() {
		return this.inspector;
	}
	public void setServiceRecord(java.lang.String value) {
		this.serviceRecord = value;
	}
	
	public java.lang.String getServiceRecord() {
		return this.serviceRecord;
	}
	public void setCheckResult(java.lang.Integer value) {
		this.checkResult = value;
	}
	
	public java.lang.Integer getCheckResult() {
		return this.checkResult;
	}
	public void setInconformtyReason(java.lang.String value) {
		this.inconformtyReason = value;
	}
	
	public java.lang.String getInconformtyReason() {
		return this.inconformtyReason;
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
	public void setExtend3(java.lang.String value) {
		this.extend3 = value;
	}
	
	public java.lang.String getExtend3() {
		return this.extend3;
	}

	public List<SafetyCheckAssistant> getAssistantList() {
		return assistantList;
	}

	public void setAssistantList(List<SafetyCheckAssistant> assistantList) {
		this.assistantList = assistantList;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getInspectorName() {
		return inspectorName;
	}

	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}

}