/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.engine.WorkflowConstants;
import com.hd.workflow.engine.event.EventType;
import com.hd.workflow.engine.exception.BizError;
import com.hd.workflow.engine.exception.WorkflowEngineException;
import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.ProcessInstance;
import com.hd.workflow.engine.pvm.PvmActivity;
import com.hd.workflow.engine.pvm.PvmCondition;
import com.hd.workflow.engine.pvm.PvmTransition;
import com.hd.workflow.engine.pvm.context.Context;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;

/**
 * 执行器本身是单线程串行执行，可由执行节点创建子执行器，并且子执行器可能为并发执行。
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-29 下午4:33:24
 */
public class ExecutionImpl extends VariableScopeImpl implements ProcessInstance {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ExecutionImpl.class);
	
	//当前执行节点
	private PvmActivity activity;
	
	//当前执行的分支
	private PvmTransition transition;
	
	//执行顺序id
	private String execSeqId;
	
	//当前执行器是否结束，此变量可能会被其他线程访问
	private volatile boolean isEnded = false;
	
	//如果在流程执行过程中出现未捕获异常，则保存到此变量中
	private Exception exception;
	
	//当前执行器执行的流程
	private ProcessDefinition processDefinition;
	
	//存放分支上定义的流转条件，只存放在当前执行器中
	private Map<String, PvmCondition> transitionConditionMap = new HashMap<String, PvmCondition>();
	
	//在进行fork join时，标示为主线程
	private boolean isMainThread = true;
	
	//在进行fork join时，标示为并发执行的线程
	private boolean isForkJoin = false;
	
	//在进行fork join时，用于计数并行分支到达数
	private CountDownLatch forkCountDownLatch;
	
	public void start(){
		Context.dispatchEvent(EventType.PROCESS_START, this);
		
		if(activity==null){
			throw new WorkflowEngineException("执行节点为空,流程终止.");
		}
		
		LOGGER.debug("流程启动：{}",getProcessDefinition());
		
		performOperation(activity);
	}
	
	public void performOperation(PvmActivity activity){
		
		execSeqId = UUID.randomUUID().toString();
		this.activity = activity;
		
		//触发activity执行事件
		Context.dispatchEvent(EventType.ACTIVITY_PERFORM, this);
		
		try{
			try {
				activity.getActivityBehavior().execute(this);
			}catch (BizError e) {
				
				PvmActivity errEvent = (PvmActivity)activity.getProperty(WorkflowConstants.PROPERTYNAME_ERROR_EVENT_DEFINITIONS);
				if(errEvent!=null){
					LOGGER.error(e.getMessage(),e);
					LOGGER.debug("捕获异常：{}",errEvent);
					performOperation(errEvent);
				}else{
					throw e;
				}
				
			}
		}catch (Exception e) {
			exception = e;
			LOGGER.error("流程异常终止：{}###{}",e.getMessage(),activity);
			this.end();
		}
		
	}

	@Override
	public PvmActivity getActivity() {
		return activity;
	}
	
	@Override
	public void setActivity(PvmActivity activity) {
		this.activity = activity;
	}

	@Override
	public void take(PvmTransition transition) {
		LOGGER.debug("进入分支：{}",transition);
		
		this.transition = transition;
		Context.dispatchEvent(EventType.ACTIVITY_TAKE, this);
		
		this.performOperation(transition.getDestination());
	}

	@Override
	public void take(List<PvmTransition> transitions) {
		//暂不实现
	}

	@Override
	public void takeAsync(List<PvmTransition> transitions) {
		//暂不实现
	}

	@Override
	public boolean isEnded() {
		return isEnded;
	}

	@Override
	public void end() {
		isEnded = true;
		if(isForkJoin&&!isMainThread){
			ExecutionImpl parent = ((ExecutionImpl)getParent());
			
			if(parent!=null&&exception!=null){
				parent.exception = exception;
				parent.notifyJoin();
			}
			
		}else{
			LOGGER.debug("流程结束：{}",getProcessDefinition());
		}
		
		Context.dispatchEvent(EventType.PROCESS_COMPLETED, this);
	}

	@Override
	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}

	/**
	 * 设置流程定义
	 * @param processDefinition
	 */
	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}

	/**
	 * 获取在流程执行过程中出现的未捕获异常，出现未捕获异常时流程将被终止。
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}
	
	@Override
	public ActivityExecution getParent() {
		return (ActivityExecution)super.getParent();
	}

	@Override
	public void setParent(ActivityExecution execution) {
		super.setParent(execution);
	}

	public void putPvmCondition(String id,PvmCondition condition){
		transitionConditionMap.put(id, condition);
	}
	
	public PvmCondition getPvmCondition(String id){
		return transitionConditionMap.get(id);
	}

	@Override
	public List<PvmTransition> getTransition(PvmActivity activity) {
		List<PvmTransition> outgoing = activity.getOutgoingTransitions();
		List<PvmTransition> transitionToTake = new ArrayList<PvmTransition>();
		if(CollectionUtils.isNotEmpty(outgoing)){
			for(PvmTransition transition : outgoing){
				if(transition.evaluate(this)){
					transitionToTake.add(transition);
				}
			}
		}
		
		return transitionToTake;
	}

	@Override
	public boolean isMainThread() {
		return isMainThread;
	}

	@Override
	public boolean isForkJoin() {
		return isForkJoin;
	}

	@Override
	public void setMainThread(boolean isMainThread) {
		this.isMainThread = isMainThread;
	}

	@Override
	public void setForkJoin(boolean isForkJoin) {
		this.isForkJoin = isForkJoin;
	}

	@Override
	public ActivityExecution createAsyncExecution() {
		ExecutionImpl executionImpl = new ExecutionImpl();
		executionImpl.setParent(this);
		executionImpl.setForkJoin(true);
		executionImpl.setMainThread(false);
		executionImpl.setProcessDefinition(processDefinition);
		
		return executionImpl;
	}

	@Override
	public void setForkNumber(int num) {
		forkCountDownLatch = new CountDownLatch(num);
	}

	@Override
	public void notifyJoin() {
		forkCountDownLatch.countDown();
	}

	@Override
	public void awaitJoin() {
		try {
			forkCountDownLatch.await();
			forkCountDownLatch = null;
			isForkJoin = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PvmTransition getTransition() {
		return transition;
	}

	@Override
	public String getExecSeqId() {
		return execSeqId;
	}
}
