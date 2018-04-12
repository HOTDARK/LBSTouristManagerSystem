/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.model;

import java.util.Date;

import com.hd.sfw.log.trace.model.SysLogOper;

/**
 * webservice 日志实体类
 * TODO 后期重构webservice日志记录部分代码
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-15 下午3:11:23
 */
public class WebServiceLog extends SysLogOper{
	public final static int STATE_NORMAL = 1;
	
	public final static int STATE_ERROR = 2;
	
	//接口编号
	private String wid;
	
	//接口中文名称
	private String wname;
	
	//接口输入参数
	private String input;
	
	//接口返回参数
	private String output;
	
	//接口调用开始时间
	private Date startTime;
	
	//接口调用结束时间
	private Date endTime;
	
	//接口状态，统计是否有效
	private Integer state;
	
	
	//查询条件
	private String sortColumns;
	private Date startDate;
	private Date endDate;
	
	/**根据不同产品业务，添加。目前只支持添加String类型的属性*/
	/**目前已新保留４个字段，建议之后产品沿用已有字段。不继续添加新属性*/
	public final static String WORKFLOWIDKEY = "wfLogId";
	public final static String WORKFLOWACCOUNT = "account";
	public final static String PROBEID = "probeId";
	public final static String SESSIONKEY = "sessionKey";
	private String wfLogId;// 系统存在流程时，流程日志ID
	private String account;// 系统存在账号时，账号信息
	private String probeId;// 系统存在标识时，标识ID
	private String sessionKey;// 系统存在会话时，会话标识

	/**
	 * @return the wid
	 */
	public String getWid() {
		return wid;
	}

	/**
	 * @param wid the wid to set
	 */
	public void setWid(String wid) {
		this.wid = wid;
	}

	/**
	 * @return the wname
	 */
	public String getWname() {
		return wname;
	}

	/**
	 * @param wname the wname to set
	 */
	public void setWname(String wname) {
		this.wname = wname;
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the sortColumns
	 */
	public String getSortColumns() {
		return sortColumns;
	}

	/**
	 * @param sortColumns the sortColumns to set
	 */
	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getWfLogId() {
		return wfLogId;
	}

	public void setWfLogId(String wfLogId) {
		this.wfLogId = wfLogId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getProbeId() {
		return probeId;
	}

	public void setProbeId(String probeId) {
		this.probeId = probeId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

}
