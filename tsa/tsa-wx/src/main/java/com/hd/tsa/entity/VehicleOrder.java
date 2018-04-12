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
 * @date	2017-11-08
 */
public class VehicleOrder extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String orderNum;
	private java.lang.String vehicleLicense;
	private java.lang.String driver;
	private java.lang.String useCompany;
	private java.lang.String useCause;
	private java.util.Date startOrderDate;
	private java.util.Date estimateDate;
	private java.lang.String useDuration;
	private java.lang.String boardLocation;
	private java.lang.Integer rideNum;
	private java.lang.String vehicleGearbox;
	private java.lang.String vehicleBrand;
	private java.lang.String vehicleColour;
	private java.lang.String departPlace;
	private java.lang.String destination;
	private java.lang.String state;
	private java.lang.String vehicleState;
	private java.lang.String orderUser;
	private java.lang.String orderUserName;
	private java.lang.String orderTelephone;
	private java.lang.String orderPhone;
	private java.lang.String orderSource;
	private java.util.Date createDate;
	private java.lang.String createUser;
	private java.util.Date updateDate;
	private java.lang.String updateUser;
	private java.lang.String remarks;
	private java.lang.Integer deleted;
	private java.lang.Integer evaluate;
	private java.lang.Integer revoked;
	private java.lang.String permitCode;
	private java.lang.Integer expand1;
	private java.lang.String expand2;
	private java.lang.String expand3;
	private java.lang.String expand4;
	private String busRider;
	private String riderPhone;
	public String getBusRider() {
		return busRider;
	}

	public void setBusRider(String busRider) {
		this.busRider = busRider;
	}

	public String getRiderPhone() {
		return riderPhone;
	}

	public void setRiderPhone(String riderPhone) {
		this.riderPhone = riderPhone;
	}

	private String stateStr;
	private String picPath;
	private String menuCode;
	//columns END
	
	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
	private String vehicleCompanyStr;
	private String gearboxStr;
	private String colorStr;
	private String orderSourceStr;
	private String reason;
	public String getVehicleCompanyStr() {
		return vehicleCompanyStr;
	}

	public void setVehicleCompanyStr(String vehicleCompanyStr) {
		this.vehicleCompanyStr = vehicleCompanyStr;
	}

	public String getGearboxStr() {
		return gearboxStr;
	}

	public void setGearboxStr(String gearboxStr) {
		this.gearboxStr = gearboxStr;
	}

	public String getColorStr() {
		return colorStr;
	}

	public void setColorStr(String colorStr) {
		this.colorStr = colorStr;
	}

	public String getOrderSourceStr() {
		return orderSourceStr;
	}

	public void setOrderSourceStr(String orderSourceStr) {
		this.orderSourceStr = orderSourceStr;
	}

	//用于查询的时间列
	private java.util.Date startOrderDateBegin;
	private java.util.Date startOrderDateEnd;
	private java.util.Date estimateDateBegin;
	private java.util.Date estimateDateEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;
	private java.util.Date updateDateBegin;
	private java.util.Date updateDateEnd;
	
	private java.lang.String stateName;
	private java.lang.String driverName;
	private java.lang.String brandName;

	public java.lang.String getDriverName() {
		return driverName;
	}

	public void setDriverName(java.lang.String driverName) {
		this.driverName = driverName;
	}

	

	public VehicleOrder(){
	}

	public VehicleOrder(
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
	public void setOrderNum(java.lang.String value) {
		this.orderNum = value;
	}
	
	public java.lang.String getOrderNum() {
		return this.orderNum;
	}
	public void setVehicleLicense(java.lang.String value) {
		this.vehicleLicense = value;
	}
	
	public java.lang.String getVehicleLicense() {
		return this.vehicleLicense;
	}
	public void setDriver(java.lang.String value) {
		this.driver = value;
	}
	
	public java.lang.String getDriver() {
		return this.driver;
	}
	public void setUseCompany(java.lang.String value) {
		this.useCompany = value;
	}
	
	public java.lang.String getUseCompany() {
		return this.useCompany;
	}
	public void setUseCause(java.lang.String value) {
		this.useCause = value;
	}
	
	public java.lang.String getUseCause() {
		return this.useCause;
	}
	public void setStartOrderDateBegin(java.util.Date value) {
		this.startOrderDateBegin = value;
	}
	
	public java.util.Date getStartOrderDateBegin() {
		return this.startOrderDateBegin;
	}
	public void setStartOrderDateEnd(java.util.Date value) {
		this.startOrderDateEnd = value;
	}
	
	public java.util.Date getStartOrderDateEnd() {
		return this.startOrderDateEnd;
	}
	public void setStartOrderDate(java.util.Date value) {
		this.startOrderDate = value;
	}
	
	public java.util.Date getStartOrderDate() {
		return this.startOrderDate;
	}
	public void setEstimateDateBegin(java.util.Date value) {
		this.estimateDateBegin = value;
	}
	
	public java.util.Date getEstimateDateBegin() {
		return this.estimateDateBegin;
	}
	public void setEstimateDateEnd(java.util.Date value) {
		this.estimateDateEnd = value;
	}
	
	public java.util.Date getEstimateDateEnd() {
		return this.estimateDateEnd;
	}
	public void setEstimateDate(java.util.Date value) {
		this.estimateDate = value;
	}
	
	public java.util.Date getEstimateDate() {
		return this.estimateDate;
	}
	public void setUseDuration(java.lang.String value) {
		this.useDuration = value;
	}
	
	public java.lang.String getUseDuration() {
		return this.useDuration;
	}
	public void setBoardLocation(java.lang.String value) {
		this.boardLocation = value;
	}
	
	public java.lang.String getBoardLocation() {
		return this.boardLocation;
	}
	public void setRideNum(java.lang.Integer value) {
		this.rideNum = value;
	}
	
	public java.lang.Integer getRideNum() {
		return this.rideNum;
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
	public void setDepartPlace(java.lang.String value) {
		this.departPlace = value;
	}
	
	public java.lang.String getDepartPlace() {
		return this.departPlace;
	}
	public void setDestination(java.lang.String value) {
		this.destination = value;
	}
	
	public java.lang.String getDestination() {
		return this.destination;
	}
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	public void setVehicleState(java.lang.String value) {
		this.vehicleState = value;
	}
	
	public java.lang.String getVehicleState() {
		return this.vehicleState;
	}
	public void setOrderUser(java.lang.String value) {
		this.orderUser = value;
	}
	
	public java.lang.String getOrderUser() {
		return this.orderUser;
	}
	public void setOrderUserName(java.lang.String value) {
		this.orderUserName = value;
	}
	
	public java.lang.String getOrderUserName() {
		return this.orderUserName;
	}
	public void setOrderTelephone(java.lang.String value) {
		this.orderTelephone = value;
	}
	
	public java.lang.String getOrderTelephone() {
		return this.orderTelephone;
	}
	public void setOrderPhone(java.lang.String value) {
		this.orderPhone = value;
	}
	
	public java.lang.String getOrderPhone() {
		return this.orderPhone;
	}
	public void setOrderSource(java.lang.String value) {
		this.orderSource = value;
	}
	
	public java.lang.String getOrderSource() {
		return this.orderSource;
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
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	public void setDeleted(java.lang.Integer value) {
		this.deleted = value;
	}
	
	public java.lang.Integer getDeleted() {
		return this.deleted;
	}
	public void setRevoked(java.lang.Integer value) {
		this.revoked = value;
	}
	
	public java.lang.Integer getRevoked() {
		return this.revoked;
	}
	public void setPermitCode(java.lang.String value) {
		this.permitCode = value;
	}
	
	public java.lang.String getPermitCode() {
		return this.permitCode;
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

	public java.lang.String getStateName() {
		return stateName;
	}

	public void setStateName(java.lang.String stateName) {
		this.stateName = stateName;
	}

	public java.lang.Integer getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(java.lang.Integer evaluate) {
		this.evaluate = evaluate;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public java.lang.String getBrandName() {
		return brandName;
	}

	public void setBrandName(java.lang.String brandName) {
		this.brandName = brandName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


}