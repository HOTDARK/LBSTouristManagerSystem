/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow;

import java.util.List;

import org.springframework.beans.factory.FactoryBean;

import com.hd.workflow.engine.WorkflowEngine;
import com.hd.workflow.engine.WorkflowEngineConfiguration;
import com.hd.workflow.engine.WorkflowEngineImpl;
import com.hd.workflow.engine.event.WorkflowEventDispatcher;
import com.hd.workflow.engine.event.WorkflowEventListener;
import com.hd.workflow.engine.pvm.factory.TaskDelegateFactory;
import com.hd.workflow.engine.pvm.impl.el.ExpressionManager;

/**
 * 用于spring创建WorkflowEngine实例
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-18 上午9:27:36
 */
public class WorkflowEngineFactoryBean implements FactoryBean<WorkflowEngine>{
	
	private WorkflowEngineConfiguration configuration = new WorkflowEngineConfiguration();
	
	private WorkflowEngine engine;
	
	private List<WorkflowEventListener> listeners;
	
	private WorkflowEngineInitiator engineInitiator;
	
	private String processType;
	
	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public void setProcessMaxThreads(int processMaxThreads) {
		configuration.setProcessMaxThreads(processMaxThreads);
	}

	public void setProcessKeepAliveTime(long processKeepAliveTime) {
		configuration.setProcessKeepAliveTime(processKeepAliveTime);
	}

	public void setTaskMaxThreads(int taskMaxThreads) {
		configuration.setTaskMaxThreads(taskMaxThreads);
	}

	public void setTaskKeepAliveTime(long taskKeepAliveTime) {
		configuration.setTaskKeepAliveTime(taskKeepAliveTime);
	}

	/**
	 * @param taskDelegateFactory the taskDelegateFactory to set
	 */
	public void setTaskDelegateFactory(TaskDelegateFactory taskDelegateFactory) {
		configuration.setTaskDelegateFactory(taskDelegateFactory);
	}

	/**
	 * @param expressionManager the expressionManager to set
	 */
	public void setExpressionManager(ExpressionManager expressionManager) {
		configuration.setExpressionManager(expressionManager);
	}

	/**
	 * @param workflowEventDispatcher the workflowEventDispatcher to set
	 */
	public void setWorkflowEventDispatcher(WorkflowEventDispatcher workflowEventDispatcher) {
		configuration.setWorkflowEventDispatcher(workflowEventDispatcher);
	}
	
	public void setListeners(List<WorkflowEventListener> listeners){
		this.listeners = listeners;
	}
	
	
	/**
	 * @param engineInitiator the engineInitiator to set
	 */
	public void setEngineInitiator(WorkflowEngineInitiator engineInitiator) {
		this.engineInitiator = engineInitiator;
	}

	@Override
	public WorkflowEngine getObject() throws Exception {
		
		if(engine==null){
			engine = new WorkflowEngineImpl(configuration);
			
			for(WorkflowEventListener ls : listeners){
				configuration.getWorkflowEventDispatcher().addEventListener(ls);
			}
			
			//初始化
			engineInitiator.init(engine, processType);
		}
		
		return engine;
	}

	@Override
	public Class<?> getObjectType() {
		return WorkflowEngine.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
