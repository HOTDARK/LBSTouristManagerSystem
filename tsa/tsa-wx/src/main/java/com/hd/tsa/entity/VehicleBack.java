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
 * @date	2017-10-19
 */
public class VehicleBack extends Pagination<VehicleBack>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.String orderNum;
	private java.lang.String vehicleLicense;
	private java.util.Date startDate;
	private java.util.Date startTime;
	private java.util.Date endDate;
	private java.util.Date endTime;
	private java.lang.String driveRange;
	private java.lang.String useOrg;
	private java.lang.String useOrgName;
	private java.lang.String vehicleDealer;
	private java.lang.String rideUser;
	private java.lang.String driver;
	private java.lang.String backFreeDate;
	private java.util.Date returnDate;
	private java.lang.String oilConsumption;
	private java.lang.String roadToll;
	private java.lang.String parkingRate;
	private java.lang.String mileage;
	private java.lang.String dispatchInfo;
	private java.lang.String drivingInfo;
	private java.lang.String receivingInfo;
	private java.util.Date createDate;
	private java.lang.String createUser;
	private java.lang.String permitCode;
	private java.lang.String menuCode;
	private java.lang.Integer deleted;
	private java.lang.Integer expand1;
	private java.lang.String expand2;
	private java.lang.String expand3;
	//columns END
	
	//用于查询的时间列
	private java.util.Date startDateBegin;
	private java.util.Date startDateEnd;
	private java.util.Date startTimeBegin;
	private java.util.Date startTimeEnd;
	private java.util.Date endDateBegin;
	private java.util.Date endDateEnd;
	private java.util.Date endTimeBegin;
	private java.util.Date endTimeEnd;
	private java.util.Date returnDateBegin;
	private java.util.Date returnDateEnd;
	private java.util.Date createDateBegin;
	private java.util.Date createDateEnd;

	private java.lang.String price;
	private java.lang.String xc;
	
	public VehicleBack(){
	}

	public java.lang.String getXc() {
		return xc;
	}

	public void setXc(java.lang.String xc) {
		this.xc = xc;
	}

	public java.lang.String getPrice() {
		return price;
	}


	public void setPrice(java.lang.String price) {
		this.price = price;
	}


	public java.lang.String getUseOrgName() {
		return useOrgName;
	}

	public void setUseOrgName(java.lang.String useOrgName) {
		this.useOrgName = useOrgName;
	}

	public java.lang.String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(java.lang.String menuCode) {
		this.menuCode = menuCode;
	}

	public VehicleBack(
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
	public void setStartDateBegin(java.util.Date value) {
		this.startDateBegin = value;
	}
	
	public java.util.Date getStartDateBegin() {
		return this.startDateBegin;
	}
	public void setStartDateEnd(java.util.Date value) {
		this.startDateEnd = value;
	}
	
	public java.util.Date getStartDateEnd() {
		return this.startDateEnd;
	}
	public void setStartDate(java.util.Date value) {
		this.startDate = value;
	}
	
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	public void setStartTimeBegin(java.util.Date value) {
		this.startTimeBegin = value;
	}
	
	public java.util.Date getStartTimeBegin() {
		return this.startTimeBegin;
	}
	public void setStartTimeEnd(java.util.Date value) {
		this.startTimeEnd = value;
	}
	
	public java.util.Date getStartTimeEnd() {
		return this.startTimeEnd;
	}
	public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}
	
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	public void setEndDateBegin(java.util.Date value) {
		this.endDateBegin = value;
	}
	
	public java.util.Date getEndDateBegin() {
		return this.endDateBegin;
	}
	public void setEndDateEnd(java.util.Date value) {
		this.endDateEnd = value;
	}
	
	public java.util.Date getEndDateEnd() {
		return this.endDateEnd;
	}
	public void setEndDate(java.util.Date value) {
		this.endDate = value;
	}
	
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	public void setEndTimeBegin(java.util.Date value) {
		this.endTimeBegin = value;
	}
	
	public java.util.Date getEndTimeBegin() {
		return this.endTimeBegin;
	}
	public void setEndTimeEnd(java.util.Date value) {
		this.endTimeEnd = value;
	}
	
	public java.util.Date getEndTimeEnd() {
		return this.endTimeEnd;
	}
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	public void setDriveRange(java.lang.String value) {
		this.driveRange = value;
	}
	
	public java.lang.String getDriveRange() {
		return this.driveRange;
	}
	public void setUseOrg(java.lang.String value) {
		this.useOrg = value;
	}
	
	public java.lang.String getUseOrg() {
		return this.useOrg;
	}
	public void setVehicleDealer(java.lang.String value) {
		this.vehicleDealer = value;
	}
	
	public java.lang.String getVehicleDealer() {
		return this.vehicleDealer;
	}
	public void setRideUser(java.lang.String value) {
		this.rideUser = value;
	}
	
	public java.lang.String getRideUser() {
		return this.rideUser;
	}
	public void setBackFreeDate(java.lang.String value) {
		this.backFreeDate = value;
	}
	
	public java.lang.String getBackFreeDate() {
		return this.backFreeDate;
	}
	public void setReturnDateBegin(java.util.Date value) {
		this.returnDateBegin = value;
	}
	
	public java.util.Date getReturnDateBegin() {
		return this.returnDateBegin;
	}
	public void setReturnDateEnd(java.util.Date value) {
		this.returnDateEnd = value;
	}
	
	public java.util.Date getReturnDateEnd() {
		return this.returnDateEnd;
	}
	public void setReturnDate(java.util.Date value) {
		this.returnDate = value;
	}
	
	public java.util.Date getReturnDate() {
		return this.returnDate;
	}
	public void setOilConsumption(java.lang.String value) {
		this.oilConsumption = value;
	}
	
	public java.lang.String getOilConsumption() {
		return this.oilConsumption;
	}
	public void setRoadToll(java.lang.String value) {
		this.roadToll = value;
	}
	
	public java.lang.String getRoadToll() {
		return this.roadToll;
	}
	public void setParkingRate(java.lang.String value) {
		this.parkingRate = value;
	}
	
	public java.lang.String getParkingRate() {
		return this.parkingRate;
	}
	public void setMileage(java.lang.String value) {
		this.mileage = value;
	}
	
	public java.lang.String getMileage() {
		return this.mileage;
	}
	public void setDispatchInfo(java.lang.String value) {
		this.dispatchInfo = value;
	}
	
	public java.lang.String getDispatchInfo() {
		return this.dispatchInfo;
	}
	public void setDrivingInfo(java.lang.String value) {
		this.drivingInfo = value;
	}
	
	public java.lang.String getDrivingInfo() {
		return this.drivingInfo;
	}
	public void setReceivingInfo(java.lang.String value) {
		this.receivingInfo = value;
	}
	
	public java.lang.String getReceivingInfo() {
		return this.receivingInfo;
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
	public void setPermitCode(java.lang.String value) {
		this.permitCode = value;
	}
	
	public java.lang.String getPermitCode() {
		return this.permitCode;
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

	public java.lang.String getDriver() {
		return driver;
	}

	public void setDriver(java.lang.String driver) {
		this.driver = driver;
	}

}