/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;

import com.hd.workflow.engine.pvm.delegate.ActivityExecution;
import com.hd.workflow.model.Conclusion;

/**
 * 执行器日志信息
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-10 下午3:05:16
 */
public class ExecutionInfo {
	private List<ExecutionInfoItem> steps = Collections.synchronizedList(new ArrayList<ExecutionInfoItem>());
	
	private Map<String, ExecutionInfoItem> stepMap = new ConcurrentHashMap<String, ExecutionInfoItem>();
	
	//总结论
	private Conclusion conclusion;
	
	private long startTime;
	
	private long endTime;
	
	private ReentrantLock lock = new ReentrantLock();
	
	public void add(ExecutionInfoItem step,ActivityExecution execution){
		
		//如果此节点未设置名称不记录
		if(StringUtils.isEmpty(step.getName())){
			return;
		}
		
		//子流程调用节点的结果合并
		if(stepMap.containsKey(step.getId())){
			ExecutionInfoItem old = stepMap.get(step.getId());
			old.setCode(step.getCode());
			old.setResult(step.getResult());
			old.setState(step.getState());
			old.setTips(step.getTips());
			old.setParams(step.getParams());
			
			return;
		}
		
		//是否是子节点
		boolean isSubItem = false;
		
		ActivityExecution parentExecution = execution.getParent();		
		if(parentExecution!=null){
			
			//并发分支
			if(execution.isForkJoin()){
				//是主线程的fork
				if(execution.isMainThread()){
					isSubItem = true;
				}else if(parentExecution.getParent()!=null){ 
					//fork的新线程在一个新的execution中运行，其parent为fork的主线程，所以需判断parent是否存在上一级parent
					isSubItem = true;
					parentExecution = parentExecution.getParent();
				}
			}else{
				isSubItem = true;
			}
			
		}
		
		if(isSubItem){
			String parentId = parentExecution.getExecSeqId();
			
			//如果记录并发执行器应加锁
			if(execution.isForkJoin()){
				lock.lock();
			}
			
			ExecutionInfoItem parent = stepMap.get(parentId);
			if(parent==null){
				parent = new ExecutionInfoItem();
				parent.setName(parentExecution.getActivity().getName());
				parent.setId(parentId);
				
				steps.add(parent);
				stepMap.put(parentId, parent);
			}
			parent.addChildItem(step);
			stepMap.put(step.getId(), step);
			
			//如果记录并发执行器应释放前面加的锁
			if(execution.isForkJoin()){
				lock.unlock();
			}
			
		}else{
			stepMap.put(step.getId(), step);
			steps.add(step);
		}
		
	}
	
	/**
	 * @return the conclusion
	 */
	public Conclusion getConclusion() {
		return conclusion;
	}

	/**
	 * @param conclusion the conclusion to set
	 */
	public void setConclusion(Conclusion conclusion) {
		this.conclusion = conclusion;
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

	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
}
