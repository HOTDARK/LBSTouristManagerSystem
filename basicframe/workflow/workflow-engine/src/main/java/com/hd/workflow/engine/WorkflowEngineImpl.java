/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.engine.exception.WorkflowEngineException;
import com.hd.workflow.engine.parse.ProcessParser;
import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.ProcessInstance;
import com.hd.workflow.engine.pvm.context.Context;
import com.hd.workflow.engine.pvm.impl.ProcessDefinitionImpl;

/**
 * 流程引擎实现
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-29 下午3:21:18
 */
public class WorkflowEngineImpl implements WorkflowEngine {
	
	private final static Logger log = LoggerFactory.getLogger(WorkflowEngineImpl.class);
	
	//流程定义map
	private Map<String, ProcessDefinition> definitions = new HashMap<String, ProcessDefinition>();
	
	//流程执行线程池
	private ThreadPoolExecutor processExecutor;
	
	//fork join 并发任务执行线程池
	private ThreadPoolExecutor forkJoinExecutor;
	
	private WorkflowEngineConfiguration workflowEngineConfiguration;
	
	public WorkflowEngineImpl(WorkflowEngineConfiguration configuration) {
		log.info("初始化流程引擎线程池:maxThreads="+configuration.getProcessMaxThreads()+",keepAliveTime="+configuration.getProcessKeepAliveTime());
		processExecutor = new ThreadPoolExecutor(configuration.getProcessMaxThreads(), configuration.getProcessMaxThreads(), configuration.getProcessKeepAliveTime(), TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		processExecutor.allowCoreThreadTimeOut(true);
		
		log.info("初始化子任务线程池:maxThreads="+configuration.getTaskMaxThreads()+",keepAliveTime="+configuration.getTaskKeepAliveTime());
		forkJoinExecutor = new ThreadPoolExecutor(configuration.getTaskMaxThreads(), configuration.getTaskMaxThreads(), configuration.getTaskKeepAliveTime(), TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		forkJoinExecutor.allowCoreThreadTimeOut(true);
		
		this.workflowEngineConfiguration = configuration;
		
		Context.setEngine(this);
		
	}

	@Override
	public Future<ProcessInstance> startProcessInstance(
			String processDefinitionId, Map<String, Object> variables) {
		ProcessDefinition definition = getProcessDefinition(processDefinitionId);
		
		if(definition==null){
			throw new WorkflowEngineException("未找到指定的流程定义:id="+processDefinitionId);
		}
		
		final ProcessInstance instance = definition.createProcessInstance();
		
		//填充变量
		if(CollectionUtils.isNotEmpty(variables)){
			for(Entry<String, Object> entry : variables.entrySet()){
				instance.setVariable(entry.getKey(), entry.getValue());
			}
		}
		return processExecutor.submit(new Callable<ProcessInstance>() {
			@Override
			public ProcessInstance call() throws Exception {
				instance.start();
				return instance;
			}
			
		});
	}
	
	@Override
	public Future<?> submitForkJoin(final Runnable runnable) {
		return forkJoinExecutor.submit(new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				runnable.run();
				return null;
			}
		});
	}

	@Override
	public ProcessDefinition deploy(String id,String processXml,int version) {
		ProcessParser parser = new ProcessParser();
		ProcessDefinition processDefinition = parser.execute(processXml);
		
		((ProcessDefinitionImpl)processDefinition).setVersion(version);
		
		definitions.put(processDefinition.getId(), processDefinition);
		log.info("发布流程成功,{}",processDefinition);
		
		return processDefinition;
	}

	@Override
	public void destory() {
	}

	@Override
	public void removeProcessDefinition(String processDefinitionId) {
		ProcessDefinition definition = definitions.remove(processDefinitionId);
		log.info("移除流程{}",definition);
	}

	@Override
	public List<ProcessDefinition> getProcessDefinitions() {
		return new ArrayList<ProcessDefinition>(definitions.values());
	}

	@Override
	public ProcessDefinition getProcessDefinition(String processDefinitionId) {
		return definitions.get(processDefinitionId);
	}

	@Override
	public WorkflowEngineConfiguration getConfiguration() {
		return workflowEngineConfiguration;
	}

	
}
