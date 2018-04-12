/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.model;

import java.util.Date;

import com.hd.sfw.core.model.BaseModel;

/**
 * 流程日志
 * @version	0.0.1
 */
public class WorkflowLog extends BaseModel implements UserData {
	
	private static final long serialVersionUID = 1L;
	
	public final static String WORKFLOWLOG_ID_KEY = "workflowlog_id_key";

	private int id;
	
	private String uid;
	
	private String username;
	
	private String processName;
	
	private String processId;
	
	private int version;
	
	private Date startTime;
	
	private Date endTime;
	
	private long totalTime;
	
	private String params;
	
	//执行轨迹,json
	private String trace;
	
	/**总结论**/
	private String conclusionCode;
	
	private String conclusionState;
	
	private String conclusionResult;
	
	private String conclusionTips;
	
	private String exception;
	
	//用于查询的时间列
	private java.util.Date startTimeBegin;
	private java.util.Date startTimeEnd;
	private java.util.Date endTimeBegin;
	private java.util.Date endTimeEnd;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the processName
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * @param processName the processName to set
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
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
	 * @return the trace
	 */
	public String getTrace() {
		return trace;
	}

	/**
	 * @param trace the trace to set
	 */
	public void setTrace(String trace) {
		this.trace = trace;
	}

	/**
	 * @return the conclusionCode
	 */
	public String getConclusionCode() {
		return conclusionCode;
	}

	/**
	 * @param conclusionCode the conclusionCode to set
	 */
	public void setConclusionCode(String conclusionCode) {
		this.conclusionCode = conclusionCode;
	}

	/**
	 * @return the conclusionState
	 */
	public String getConclusionState() {
		return conclusionState;
	}

	/**
	 * @param conclusionState the conclusionState to set
	 */
	public void setConclusionState(String conclusionState) {
		this.conclusionState = conclusionState;
	}

	/**
	 * @return the conclusionResult
	 */
	public String getConclusionResult() {
		return conclusionResult;
	}

	/**
	 * @param conclusionResult the conclusionResult to set
	 */
	public void setConclusionResult(String conclusionResult) {
		this.conclusionResult = conclusionResult;
	}

	/**
	 * @return the conclusionTips
	 */
	public String getConclusionTips() {
		return conclusionTips;
	}

	/**
	 * @param conclusionTips the conclusionTips to set
	 */
	public void setConclusionTips(String conclusionTips) {
		this.conclusionTips = conclusionTips;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the totalTime
	 */
	public long getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @return the startTimeBegin
	 */
	public java.util.Date getStartTimeBegin() {
		return startTimeBegin;
	}

	/**
	 * @param startTimeBegin the startTimeBegin to set
	 */
	public void setStartTimeBegin(java.util.Date startTimeBegin) {
		this.startTimeBegin = startTimeBegin;
	}

	/**
	 * @return the startTimeEnd
	 */
	public java.util.Date getStartTimeEnd() {
		return startTimeEnd;
	}

	/**
	 * @param startTimeEnd the startTimeEnd to set
	 */
	public void setStartTimeEnd(java.util.Date startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}

	/**
	 * @return the endTimeBegin
	 */
	public java.util.Date getEndTimeBegin() {
		return endTimeBegin;
	}

	/**
	 * @param endTimeBegin the endTimeBegin to set
	 */
	public void setEndTimeBegin(java.util.Date endTimeBegin) {
		this.endTimeBegin = endTimeBegin;
	}

	/**
	 * @return the endTimeEnd
	 */
	public java.util.Date getEndTimeEnd() {
		return endTimeEnd;
	}

	/**
	 * @param endTimeEnd the endTimeEnd to set
	 */
	public void setEndTimeEnd(java.util.Date endTimeEnd) {
		this.endTimeEnd = endTimeEnd;
	}

	/**
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
