/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.client.entity;

import java.util.Date;

import com.hd.sfw.core.model.Pagination;

/**
 * webservice 日志实体类
 * TODO 后期重构webservice日志记录部分代码
 */
@SuppressWarnings("serial")
public class SysWebServiceLog extends Pagination<SysWebServiceLog>{
	public final static int STATE_NORMAL = 1;
	
	public final static int STATE_ERROR = 2;
	
	public final static String WORKFLOWIDKEY="workFlowIdKey";
	public final static String WORKFLOWACCOUNT="WORKFLOWACCOUNT";
	
	private Long id;
	
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
	
	private String username;
	
	//查询条件
	private String startDate;
	private String endDate;
	
	private String wf_log_id;//诊断流程ID
	
	private String account;//诊断的帐号

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


	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getWf_log_id() {
		return wf_log_id;
	}

	public void setWf_log_id(String wf_log_id) {
		this.wf_log_id = wf_log_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
}
