/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.impl;

import java.util.ArrayList;
import java.util.List;

import com.hd.workflow.engine.exception.WorkflowEngineException;
import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.PvmActivity;
import com.hd.workflow.engine.pvm.PvmTransition;
import com.hd.workflow.engine.pvm.delegate.ActivityBehavior;

/**
 * 执行节点
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-29 下午4:33:02
 */
public class ActivityImpl extends ProcessElementImpl implements PvmActivity{
	
	//执行节点的行为
	private ActivityBehavior activityBehavior;
	
	//连入分支
	private List<PvmTransition> incomingTransitions = new ArrayList<PvmTransition>();
	
	//外连分支
	private List<PvmTransition> outgoingTransitions = new ArrayList<PvmTransition>();
	
	public ActivityImpl(String id, ProcessDefinition processDefinition) {
		super(id, processDefinition);
	}

	public void setProcessDefinition(ProcessDefinition definition){
		this.processDefinition = definition;
	}

	@Override
	public ActivityBehavior getActivityBehavior() {
		return activityBehavior;
	}

	@Override
	public List<PvmTransition> getIncomingTransitions() {
		if(incomingTransitions==null){
			incomingTransitions = new ArrayList<PvmTransition>();
		}
		return incomingTransitions;
	}

	@Override
	public List<PvmTransition> getOutgoingTransitions() {
		if(outgoingTransitions==null){
			outgoingTransitions = new ArrayList<PvmTransition>();
		}
		return outgoingTransitions;
	}

	@Override
	public PvmTransition findOutgoingTransition(String transitionId) {
		if(outgoingTransitions==null){
			return null;
		}
		
		for(PvmTransition transition : outgoingTransitions){
			if(transitionId.equals(transition.getId())){
				return transition;
			}
		}
		
		return null;
	}
	
	public TransitionImpl createOutgoingTransition(String transitionId) {
		if (transitionId != null) {
			for(PvmTransition pvmTransition : outgoingTransitions){
				if (pvmTransition.getId().equals(transitionId)) {
					throw new WorkflowEngineException("执行节点 "+this+" 存在重复的分支ID "+transitionId);
				}
			}
			
		}
		
		TransitionImpl transition = new TransitionImpl(transitionId, processDefinition);
		transition.setSource(this);
		outgoingTransitions.add(transition);
		    
		return transition;
	}

	/**
	 * @param activityBehavior the activityBehavior to set
	 */
	public void setActivityBehavior(ActivityBehavior activityBehavior) {
		this.activityBehavior = activityBehavior;
	}

	/**
	 * @param incomingTransitions the incomingTransitions to set
	 */
	public void setIncomingTransitions(List<PvmTransition> incomingTransitions) {
		this.incomingTransitions = incomingTransitions;
	}

	/**
	 * @param outgoingTransitions the outgoingTransitions to set
	 */
	public void setOutgoingTransitions(List<PvmTransition> outgoingTransitions) {
		this.outgoingTransitions = outgoingTransitions;
	}

	@Override
	public String toString() {
		return name+":"+id;
		
	}
}
