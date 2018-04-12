/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.log;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-16 下午4:58:33
 */
public class ExecutionTraceItem {
	
	private String elementId;
	
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private ExecutionTrace subProcess;
	
	public ExecutionTraceItem() {
	}

	public ExecutionTraceItem(String elementId) {
		super();
		this.elementId = elementId;
	}

	/**
	 * @return the elementId
	 */
	public String getElementId() {
		return elementId;
	}

	/**
	 * @param elementId the elementId to set
	 */
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	/**
	 * @return the subProcess
	 */
	public ExecutionTrace getSubProcess() {
		return subProcess;
	}

	/**
	 * @param subProcess the subProcess to set
	 */
	public void setSubProcess(ExecutionTrace subProcess) {
		this.subProcess = subProcess;
	}
	
}
