package com.hd.sfw.core.model;

import java.io.Serializable;

/**
 * model基类
 */
@SuppressWarnings("serial")
public class BaseModel implements Serializable{
	
	private String sortColumns;// 排序字段
    private String errCode;// 返回编码
    private String errMsg;// 错误信息

	/**
	 * @return the sortColumns
	 */
	public String getSortColumns() {
		return sortColumns;
	}

	/**
	 * 排序字段,可以有多个，如:id desc或者id desc,name asc
	 * @param sortColumns 
	 */
	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
