/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.event;

import com.hd.workflow.engine.pvm.delegate.ActivityExecution;

/**
 * 事件实现
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-10 上午10:01:53
 */
public class WorkflowEventImpl implements WorkflowEvent {
	
	private EventType type;
	
	private ActivityExecution execution;
	
	public WorkflowEventImpl() {
	}

	/**
	 * @param type
	 * @param execution
	 */
	public WorkflowEventImpl(EventType type, ActivityExecution execution) {
		super();
		this.type = type;
		this.execution = execution;
	}

	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}

	/**
	 * @return the execution
	 */
	public ActivityExecution getExecution() {
		return execution;
	}

	/**
	 * @param execution the execution to set
	 */
	public void setExecution(ActivityExecution execution) {
		this.execution = execution;
	}
	
}
