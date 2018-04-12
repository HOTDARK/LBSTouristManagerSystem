/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.impl;

import java.util.ArrayList;
import java.util.List;

import com.hd.workflow.engine.exception.WorkflowEngineException;
import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.ProcessInstance;
import com.hd.workflow.engine.pvm.PvmActivity;

/**
 * 流程定义实现
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-28 上午11:43:33
 */
public class ProcessDefinitionImpl extends ProcessElementImpl implements ProcessDefinition {
	
	private List<PvmActivity> activities = new ArrayList<PvmActivity>();
	
	private PvmActivity startingActivity;
	
	//流程版本号
	private int version;
	
	public ProcessDefinitionImpl(String id){
		super(id,null);
		processDefinition = this;
	}
	
	@Override
	public List<? extends PvmActivity> getActivities() {
		return activities;
	}

	@Override
	public PvmActivity findActivity(String activityId) {
		if(activityId==null){
			throw new NullPointerException();
		}
		
		for(PvmActivity activity : activities){
			if(activityId.equals(activity.getId())){
				return activity;
			}
			
		}
		
		return null;
	}

	@Override
	public ProcessInstance createProcessInstance() {
		ExecutionImpl instance = new ExecutionImpl();
		instance.setProcessDefinition(this);
		instance.setActivity(startingActivity);
		
		return instance;
	}
	
	/**
	 * 创建执行节点
	 * @param activityId
	 * @return
	 */
	public ActivityImpl createActivity(String activityId){
		ActivityImpl activity = new ActivityImpl(activityId, processDefinition);
		if (activityId!=null) {
	    	if (processDefinition.findActivity(activityId) != null) {
	    		throw new WorkflowEngineException("执行节点id重复 '" + activityId + "'");
	    	}
	    }
	    
	    activities.add(activity);
	    return activity;
		
	}
	
	@Override
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version){
		this.version = version;
	}
	
	/**
	 * @return the startingActivity
	 */
	public PvmActivity getStartingActivity() {
		return startingActivity;
	}

	/**
	 * @param startingActivity the startingActivity to set
	 */
	public void setStartingActivity(PvmActivity startingActivity) {
		this.startingActivity = startingActivity;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(List<PvmActivity> activities) {
		this.activities = activities;
	}

	@Override
	public String toString() {
		return name+":"+id;
	}


}
