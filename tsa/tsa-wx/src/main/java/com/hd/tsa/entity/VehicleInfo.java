/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import java.io.Serializable;

import com.hd.sfw.core.model.Pagination;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-10-18
 */
public class VehicleInfo extends Pagination<VehicleInfo> implements Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.String vehicleLicense;
	private java.lang.String vehiclePurpose;
	private java.lang.String vehicleType;
	private java.lang.String vehicleGearbox;
	private java.lang.String vehicleBrand;
	private java.lang.String vehicleColour;
	private java.lang.String vehicleState;
	private java.lang.Integer passengerNum;
	private java.util.Date buyingDate;
	private java.util.Date insuranceDate;
	private java.util.Date yearValidate;
	private java.lang.Integer isRetire;
	private java.util.Date retireDate;
	private java.lang.String coverPhoto;
	private java.util.Date createDate;
	private java.lang.String createUser;
	private java.util.Date updateDate;
	private java.lang.String updateUser;
	private java.lang.Integer deleted;
	private java.lang.String remark;
	private java.lang.String vehicleDesc;
	private java.lang.String vehicleDetail;
	private java.util.Date updateEvaluate;
	private java.lang.String totalEvaluate;
	private java.lang.Integer expand1;
	private java.lang.String expand2;
	private java.lang.String expand3;
	private java.lang.String expand4;
	//columns END
	
	//用于查询的时间列
	private java.util.Date buyingDateBegin;
	private java.util.Date buyingDateEnd;
	private java.util.Date insuranceDateBegin;
	private java.util.Date insuranceDateEnd;
	private java.util.Date yearValidateBegin;
	private java.util.Date yearValidateEnd;
	private java.util.Date retireDateBegin;
	private java.util.Date retireDateEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	private java.util.Date updateDateBegin;
	private java.util.Date updateDateEnd;
	private java.util.Date updateEvaluateBegin;
	private java.util.Date updateEvaluateEnd;

	private java.lang.String vehiclePurposeName;
	private java.lang.String vehicleTypeName;
	private java.lang.String vehicleGearboxName;
	private java.lang.String vehicleBrandName;
	private java.lang.String vehicleColourName;
	private java.lang.String vehicleStateName;
	
	private java.lang.String fileIds;
	
	public VehicleInfo(){
	}

	public VehicleInfo(
		java.lang.String vehicleLicense
	){
		this.vehicleLicense = vehicleLicense;
	}

	public void setVehicleLicense(java.lang.String value) {
		this.vehicleLicense = value;
	}
	
	public java.lang.String getVehicleLicense() {
		return this.vehicleLicense;
	}
	public void setVehiclePurpose(java.lang.String value) {
		this.vehiclePurpose = value;
	}
	
	public java.lang.String getVehiclePurpose() {
		return this.vehiclePurpose;
	}
	public void setVehicleType(java.lang.String value) {
		this.vehicleType = value;
	}
	
	public java.lang.String getVehicleType() {
		return this.vehicleType;
	}
	public void setVehicleGearbox(java.lang.String value) {
		this.vehicleGearbox = value;
	}
	
	public java.lang.String getVehicleGearbox() {
		return this.vehicleGearbox;
	}
	public void setVehicleBrand(java.lang.String value) {
		this.vehicleBrand = value;
	}
	
	public java.lang.String getVehicleBrand() {
		return this.vehicleBrand;
	}
	public void setVehicleColour(java.lang.String value) {
		this.vehicleColour = value;
	}
	
	public java.lang.String getVehicleColour() {
		return this.vehicleColour;
	}
	public void setVehicleState(java.lang.String value) {
		this.vehicleState = value;
	}
	
	public java.lang.String getVehicleState() {
		return this.vehicleState;
	}
	public void setPassengerNum(java.lang.Integer value) {
		this.passengerNum = value;
	}
	
	public java.lang.Integer getPassengerNum() {
		return this.passengerNum;
	}
	public void setBuyingDateBegin(java.util.Date value) {
		this.buyingDateBegin = value;
	}
	
	public java.util.Date getBuyingDateBegin() {
		return this.buyingDateBegin;
	}
	public void setBuyingDateEnd(java.util.Date value) {
		this.buyingDateEnd = value;
	}
	
	public java.util.Date getBuyingDateEnd() {
		return this.buyingDateEnd;
	}
	public void setBuyingDate(java.util.Date value) {
		this.buyingDate = value;
	}
	
	public java.util.Date getBuyingDate() {
		return this.buyingDate;
	}
	public void setInsuranceDateBegin(java.util.Date value) {
		this.insuranceDateBegin = value;
	}
	
	public java.util.Date getInsuranceDateBegin() {
		return this.insuranceDateBegin;
	}
	public void setInsuranceDateEnd(java.util.Date value) {
		this.insuranceDateEnd = value;
	}
	
	public java.util.Date getInsuranceDateEnd() {
		return this.insuranceDateEnd;
	}
	public void setInsuranceDate(java.util.Date value) {
		this.insuranceDate = value;
	}
	
	public java.util.Date getInsuranceDate() {
		return this.insuranceDate;
	}
	public void setYearValidateBegin(java.util.Date value) {
		this.yearValidateBegin = value;
	}
	
	public java.util.Date getYearValidateBegin() {
		return this.yearValidateBegin;
	}
	public void setYearValidateEnd(java.util.Date value) {
		this.yearValidateEnd = value;
	}
	
	public java.util.Date getYearValidateEnd() {
		return this.yearValidateEnd;
	}
	public void setYearValidate(java.util.Date value) {
		this.yearValidate = value;
	}
	
	public java.util.Date getYearValidate() {
		return this.yearValidate;
	}
	public void setIsRetire(java.lang.Integer value) {
		this.isRetire = value;
	}
	
	public java.lang.Integer getIsRetire() {
		return this.isRetire;
	}
	public void setRetireDateBegin(java.util.Date value) {
		this.retireDateBegin = value;
	}
	
	public java.util.Date getRetireDateBegin() {
		return this.retireDateBegin;
	}
	public void setRetireDateEnd(java.util.Date value) {
		this.retireDateEnd = value;
	}
	
	public java.util.Date getRetireDateEnd() {
		return this.retireDateEnd;
	}
	public void setRetireDate(java.util.Date value) {
		this.retireDate = value;
	}
	
	public java.util.Date getRetireDate() {
		return this.retireDate;
	}
	public void setCoverPhoto(java.lang.String value) {
		this.coverPhoto = value;
	}
	
	public java.lang.String getCoverPhoto() {
		return this.coverPhoto;
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
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
	}
	public void setUpdateDateBegin(java.util.Date value) {
		this.updateDateBegin = value;
	}
	
	public java.util.Date getUpdateDateBegin() {
		return this.updateDateBegin;
	}
	public void setUpdateDateEnd(java.util.Date value) {
		this.updateDateEnd = value;
	}
	
	public java.util.Date getUpdateDateEnd() {
		return this.updateDateEnd;
	}
	public void setUpdateDate(java.util.Date value) {
		this.updateDate = value;
	}
	
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}
	public void setUpdateUser(java.lang.String value) {
		this.updateUser = value;
	}
	
	public java.lang.String getUpdateUser() {
		return this.updateUser;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
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

	public java.lang.String getVehiclePurposeName() {
		return vehiclePurposeName;
	}

	public void setVehiclePurposeName(java.lang.String vehiclePurposeName) {
		this.vehiclePurposeName = vehiclePurposeName;
	}

	public java.lang.String getVehicleTypeName() {
		return vehicleTypeName;
	}

	public void setVehicleTypeName(java.lang.String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}

	public java.lang.String getVehicleGearboxName() {
		return vehicleGearboxName;
	}

	public void setVehicleGearboxName(java.lang.String vehicleGearboxName) {
		this.vehicleGearboxName = vehicleGearboxName;
	}

	public java.lang.String getVehicleBrandName() {
		return vehicleBrandName;
	}

	public void setVehicleBrandName(java.lang.String vehicleBrandName) {
		this.vehicleBrandName = vehicleBrandName;
	}

	public java.lang.String getVehicleColourName() {
		return vehicleColourName;
	}

	public void setVehicleColourName(java.lang.String vehicleColourName) {
		this.vehicleColourName = vehicleColourName;
	}

	public java.lang.String getVehicleStateName() {
		return vehicleStateName;
	}

	public void setVehicleStateName(java.lang.String vehicleStateName) {
		this.vehicleStateName = vehicleStateName;
	}

	public java.lang.Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(java.lang.Integer deleted) {
		this.deleted = deleted;
	}

	public java.lang.String getFileIds() {
		return fileIds;
	}

	public void setFileIds(java.lang.String fileIds) {
		this.fileIds = fileIds;
	}

	public java.lang.String getVehicleDesc() {
		return vehicleDesc;
	}

	public void setVehicleDesc(java.lang.String vehicleDesc) {
		this.vehicleDesc = vehicleDesc;
	}

	public java.lang.String getVehicleDetail() {
		return vehicleDetail;
	}

	public void setVehicleDetail(java.lang.String vehicleDetail) {
		this.vehicleDetail = vehicleDetail;
	}

	public java.util.Date getUpdateEvaluate() {
		return updateEvaluate;
	}

	public void setUpdateEvaluate(java.util.Date updateEvaluate) {
		this.updateEvaluate = updateEvaluate;
	}

	public java.lang.String getTotalEvaluate() {
		return totalEvaluate;
	}

	public void setTotalEvaluate(java.lang.String totalEvaluate) {
		this.totalEvaluate = totalEvaluate;
	}

	public java.util.Date getUpdateEvaluateBegin() {
		return updateEvaluateBegin;
	}

	public void setUpdateEvaluateBegin(java.util.Date updateEvaluateBegin) {
		this.updateEvaluateBegin = updateEvaluateBegin;
	}

	public java.util.Date getUpdateEvaluateEnd() {
		return updateEvaluateEnd;
	}

	public void setUpdateEvaluateEnd(java.util.Date updateEvaluateEnd) {
		this.updateEvaluateEnd = updateEvaluateEnd;
	}

}