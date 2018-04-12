/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine;

import com.hd.workflow.engine.event.WorkflowEventDispatcher;
import com.hd.workflow.engine.event.WorkflowEventDispatcherImpl;
import com.hd.workflow.engine.pvm.factory.SimpleTaskDelegateFactory;
import com.hd.workflow.engine.pvm.factory.TaskDelegateFactory;
import com.hd.workflow.engine.pvm.impl.el.ExpressionManager;


/**
 * 流程引擎配置
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-2 上午9:45:40
 */
public class WorkflowEngineConfiguration {
	
	//流程引擎线程池最大线程大小
	private int processMaxThreads = 10;
	
	//空闲线程回收时间：秒
	private long processKeepAliveTime = 3000L;
	
	//子任务线程池最大线程大小
	private int taskMaxThreads = 10;
	
	private long taskKeepAliveTime = 3000L;
	
	//表达式管理器
	private ExpressionManager expressionManager = new ExpressionManager();
	
	//自定义任务实例化工厂
	private TaskDelegateFactory taskDelegateFactory = new SimpleTaskDelegateFactory();
	
	//事件支持
	private WorkflowEventDispatcher workflowEventDispatcher = new WorkflowEventDispatcherImpl();
	
	public int getProcessMaxThreads() {
		return processMaxThreads;
	}

	public void setProcessMaxThreads(int processMaxThreads) {
		this.processMaxThreads = processMaxThreads;
	}

	public long getProcessKeepAliveTime() {
		return processKeepAliveTime;
	}

	public void setProcessKeepAliveTime(long processKeepAliveTime) {
		this.processKeepAliveTime = processKeepAliveTime;
	}

	public int getTaskMaxThreads() {
		return taskMaxThreads;
	}

	public void setTaskMaxThreads(int taskMaxThreads) {
		this.taskMaxThreads = taskMaxThreads;
	}

	public long getTaskKeepAliveTime() {
		return taskKeepAliveTime;
	}

	public void setTaskKeepAliveTime(long taskKeepAliveTime) {
		this.taskKeepAliveTime = taskKeepAliveTime;
	}

	/**
	 * @return the taskDelegateFactory
	 */
	public TaskDelegateFactory getTaskDelegateFactory() {
		return taskDelegateFactory;
	}

	/**
	 * @param taskDelegateFactory the taskDelegateFactory to set
	 */
	public void setTaskDelegateFactory(TaskDelegateFactory taskDelegateFactory) {
		this.taskDelegateFactory = taskDelegateFactory;
	}

	/**
	 * @return the expressionManager
	 */
	public ExpressionManager getExpressionManager() {
		return expressionManager;
	}

	/**
	 * @param expressionManager the expressionManager to set
	 */
	public void setExpressionManager(ExpressionManager expressionManager) {
		this.expressionManager = expressionManager;
	}

	/**
	 * @return the workflowEventDispatcher
	 */
	public WorkflowEventDispatcher getWorkflowEventDispatcher() {
		return workflowEventDispatcher;
	}

	/**
	 * @param workflowEventDispatcher the workflowEventDispatcher to set
	 */
	public void setWorkflowEventDispatcher(
			WorkflowEventDispatcher workflowEventDispatcher) {
		this.workflowEventDispatcher = workflowEventDispatcher;
	}
	
}
