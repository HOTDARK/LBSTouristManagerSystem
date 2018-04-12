/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.log;

import java.util.ArrayList;
import java.util.List;

import com.hd.workflow.model.ConditionParam;

/**
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-9 下午2:40:49
 */
public class ExecutionInfoItem {
	
	//
	private String id;
	
	private String name;
	
	//结果编码
	private String code;
	
	//状态
	private String state;
	
	//结论
	private String result;
	
	//处理建议
	private String tips;
	
	private List<ConditionParam> params;
	
	private List<ExecutionInfoItem> steps;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the tips
	 */
	public String getTips() {
		return tips;
	}

	/**
	 * @param tips the tips to set
	 */
	public void setTips(String tips) {
		this.tips = tips;
	}

	/**
	 * @return the steps
	 */
	public List<ExecutionInfoItem> getSteps() {
		return steps;
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(List<ExecutionInfoItem> steps) {
		this.steps = steps;
	}
	
	public void addChildItem(ExecutionInfoItem executionStep){
		if(steps==null){
			steps = new ArrayList<ExecutionInfoItem>();
		}
		
		steps.add(executionStep);
	}

	/**
	 * @return the params
	 */
	public List<ConditionParam> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(List<ConditionParam> params) {
		this.params = params;
	}
	
}
