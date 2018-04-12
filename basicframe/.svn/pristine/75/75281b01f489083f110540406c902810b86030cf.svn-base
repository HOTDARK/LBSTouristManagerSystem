/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.ProcessInstance;


/**
 * 流程引擎管理器<br>
 * 发布，删除，运行 流程等操作
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-27 上午11:43:25
 */
public interface WorkflowEngine {
	
	/**
	 * 启动流程 
	 * @param processDefinitionId
	 * @param variables
	 * @return
	 */
	public Future<ProcessInstance> startProcessInstance(String processDefinitionId, Map<String, Object> variables);
	
	/**
	 * 运行并发子任务
	 * @param runnable
	 * @return
	 */
	public Future<?> submitForkJoin(Runnable runnable);
	
	/**
	 * 发布流程
	 * @param processDefinitionId 如果流程id已存在则覆盖
	 * @param processXml
	 * @param version
	 * @return 
	 */
	public ProcessDefinition deploy(String processDefinitionId,String processXml,int version);
	
	/**
	 * 移除指定id的流程
	 * @param processDefinitionId
	 */
	public void removeProcessDefinition(String processDefinitionId);
	
	/**
	 * 获取所有流程引擎中定义的流程
	 * @return
	 */
	public List<ProcessDefinition> getProcessDefinitions();
	
	/**
	 * 根据流程定义id获取流程定义实例
	 * @param processDefinitionId
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String processDefinitionId);
	
	/**
	 * 销毁流程引擎
	 */
	public void destory();
	
	public WorkflowEngineConfiguration getConfiguration();
	
}
