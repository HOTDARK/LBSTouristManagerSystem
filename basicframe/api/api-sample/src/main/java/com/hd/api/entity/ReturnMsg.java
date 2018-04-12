/**
 * ReturnMsg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.hd.api.entity;

@SuppressWarnings("serial")
public class ReturnMsg  implements java.io.Serializable {
    private java.lang.String errCode;

    private java.lang.String errMsg;

    public ReturnMsg() {
    }

    public ReturnMsg(
           java.lang.String errCode,
           java.lang.String errMsg) {
           this.errCode = errCode;
           this.errMsg = errMsg;
    }


    /**
     * Gets the errCode value for this ReturnMsg.
     * 
     * @return errCode
     */
    public java.lang.String getErrCode() {
        return errCode;
    }


    /**
     * Sets the errCode value for this ReturnMsg.
     * 
     * @param errCode
     */
    public void setErrCode(java.lang.String errCode) {
        this.errCode = errCode;
    }


    /**
     * Gets the errMsg value for this ReturnMsg.
     * 
     * @return errMsg
     */
    public java.lang.String getErrMsg() {
        return errMsg;
    }


    /**
     * Sets the errMsg value for this ReturnMsg.
     * 
     * @param errMsg
     */
    public void setErrMsg(java.lang.String errMsg) {
        this.errMsg = errMsg;
    }

	@Override
	public String toString() {
		return "ReturnMsg [errCode=" + errCode + ", errMsg=" + errMsg + "]";
	}

    

}
