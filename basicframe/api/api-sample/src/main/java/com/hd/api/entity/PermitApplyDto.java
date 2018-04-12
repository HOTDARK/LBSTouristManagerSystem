/**
 * PermitApplyDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.hd.api.entity;

import java.util.Arrays;

@SuppressWarnings("serial")
public class PermitApplyDto  extends ReturnMsg  implements java.io.Serializable {
    private java.lang.String applyDate;

    private java.lang.String applyUser;

    private java.lang.String checkDate;

    private java.lang.String description;

    private java.lang.String docName;

    private java.lang.Integer id;

    private java.lang.Integer[] ids;

    private java.lang.String permit;

    private java.lang.String[] propName;

    private java.lang.String[] propValue;

    private java.lang.String reviewer;

    private java.lang.Integer status;

    public PermitApplyDto() {
    }

    public PermitApplyDto(
           java.lang.String errCode,
           java.lang.String errMsg,
           java.lang.String applyDate,
           java.lang.String applyUser,
           java.lang.String checkDate,
           java.lang.String description,
           java.lang.String docName,
           java.lang.Integer id,
           java.lang.Integer[] ids,
           java.lang.String permit,
           java.lang.String[] propName,
           java.lang.String[] propValue,
           java.lang.String reviewer,
           java.lang.Integer status) {
        super(
            errCode,
            errMsg);
        this.applyDate = applyDate;
        this.applyUser = applyUser;
        this.checkDate = checkDate;
        this.description = description;
        this.docName = docName;
        this.id = id;
        this.ids = ids;
        this.permit = permit;
        this.propName = propName;
        this.propValue = propValue;
        this.reviewer = reviewer;
        this.status = status;
    }


    /**
     * Gets the applyDate value for this PermitApplyDto.
     * 
     * @return applyDate
     */
    public java.lang.String getApplyDate() {
        return applyDate;
    }


    /**
     * Sets the applyDate value for this PermitApplyDto.
     * 
     * @param applyDate
     */
    public void setApplyDate(java.lang.String applyDate) {
        this.applyDate = applyDate;
    }


    /**
     * Gets the applyUser value for this PermitApplyDto.
     * 
     * @return applyUser
     */
    public java.lang.String getApplyUser() {
        return applyUser;
    }


    /**
     * Sets the applyUser value for this PermitApplyDto.
     * 
     * @param applyUser
     */
    public void setApplyUser(java.lang.String applyUser) {
        this.applyUser = applyUser;
    }


    /**
     * Gets the checkDate value for this PermitApplyDto.
     * 
     * @return checkDate
     */
    public java.lang.String getCheckDate() {
        return checkDate;
    }


    /**
     * Sets the checkDate value for this PermitApplyDto.
     * 
     * @param checkDate
     */
    public void setCheckDate(java.lang.String checkDate) {
        this.checkDate = checkDate;
    }


    /**
     * Gets the description value for this PermitApplyDto.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this PermitApplyDto.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the docName value for this PermitApplyDto.
     * 
     * @return docName
     */
    public java.lang.String getDocName() {
        return docName;
    }


    /**
     * Sets the docName value for this PermitApplyDto.
     * 
     * @param docName
     */
    public void setDocName(java.lang.String docName) {
        this.docName = docName;
    }


    /**
     * Gets the id value for this PermitApplyDto.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this PermitApplyDto.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the ids value for this PermitApplyDto.
     * 
     * @return ids
     */
    public java.lang.Integer[] getIds() {
        return ids;
    }


    /**
     * Sets the ids value for this PermitApplyDto.
     * 
     * @param ids
     */
    public void setIds(java.lang.Integer[] ids) {
        this.ids = ids;
    }

    public java.lang.Integer getIds(int i) {
        return this.ids[i];
    }

    public void setIds(int i, java.lang.Integer _value) {
        this.ids[i] = _value;
    }


    /**
     * Gets the permit value for this PermitApplyDto.
     * 
     * @return permit
     */
    public java.lang.String getPermit() {
        return permit;
    }


    /**
     * Sets the permit value for this PermitApplyDto.
     * 
     * @param permit
     */
    public void setPermit(java.lang.String permit) {
        this.permit = permit;
    }


    /**
     * Gets the propName value for this PermitApplyDto.
     * 
     * @return propName
     */
    public java.lang.String[] getPropName() {
        return propName;
    }


    /**
     * Sets the propName value for this PermitApplyDto.
     * 
     * @param propName
     */
    public void setPropName(java.lang.String[] propName) {
        this.propName = propName;
    }

    public java.lang.String getPropName(int i) {
        return this.propName[i];
    }

    public void setPropName(int i, java.lang.String _value) {
        this.propName[i] = _value;
    }


    /**
     * Gets the propValue value for this PermitApplyDto.
     * 
     * @return propValue
     */
    public java.lang.String[] getPropValue() {
        return propValue;
    }


    /**
     * Sets the propValue value for this PermitApplyDto.
     * 
     * @param propValue
     */
    public void setPropValue(java.lang.String[] propValue) {
        this.propValue = propValue;
    }

    public java.lang.String getPropValue(int i) {
        return this.propValue[i];
    }

    public void setPropValue(int i, java.lang.String _value) {
        this.propValue[i] = _value;
    }


    /**
     * Gets the reviewer value for this PermitApplyDto.
     * 
     * @return reviewer
     */
    public java.lang.String getReviewer() {
        return reviewer;
    }


    /**
     * Sets the reviewer value for this PermitApplyDto.
     * 
     * @param reviewer
     */
    public void setReviewer(java.lang.String reviewer) {
        this.reviewer = reviewer;
    }


    /**
     * Gets the status value for this PermitApplyDto.
     * 
     * @return status
     */
    public java.lang.Integer getStatus() {
        return status;
    }


    /**
     * Sets the status value for this PermitApplyDto.
     * 
     * @param status
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "PermitApplyDto [applyDate=" + applyDate + ", applyUser=" + applyUser + ", checkDate=" + checkDate
				+ ", description=" + description + ", docName=" + docName + ", id=" + id + ", ids="
				+ Arrays.toString(ids) + ", permit=" + permit + ", propName=" + Arrays.toString(propName)
				+ ", propValue=" + Arrays.toString(propValue) + ", reviewer=" + reviewer + ", status=" + status
				+ ", toString()=" + super.toString() + "]";
	}

	

   

}
