/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.PvmActivity;
import com.hd.workflow.engine.pvm.PvmCondition;
import com.hd.workflow.engine.pvm.PvmTransition;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;
import com.hd.workflow.engine.pvm.delegate.Expression;

/**
 * 执行分支实现
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-29 下午4:32:52
 */
public class TransitionImpl extends ProcessElementImpl implements PvmTransition{
	private final static Logger LOGGER = LoggerFactory.getLogger(TransitionImpl.class);
	
	private PvmActivity source;
	
	private PvmActivity destination;
	
	//分支上附属的条件信息
	private List<PvmCondition> conditions;

	public TransitionImpl(String id, ProcessDefinition processDefinition) {
		super(id, processDefinition);
	}

	@Override
	public PvmActivity getSource() {
		return source;
	}

	@Override
	public PvmActivity getDestination() {
		return destination;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(PvmActivity source) {
		this.source = source;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(PvmActivity destination) {
		this.destination = destination;
	    destination.getIncomingTransitions().add(this);
	}

	@Override
	public boolean evaluate(ActivityExecution execution) {
		boolean flag = false;
		if(CollectionUtils.isNotEmpty(conditions)){
			for(PvmCondition condition : conditions){
				Expression expression = condition.getExpression();
				if(expression!=null){
					Object val = expression.getValue(execution);
					if(val instanceof Boolean){
						if((Boolean)val){
							((ExecutionImpl)execution).putPvmCondition(id, condition);
							flag = true;
							break;
						}
					}else{
						LOGGER.warn("流程：{}，分支：{}；表达式结果不是boolean值，无法识别。",processDefinition,this);
					}
				}else if(conditions.size()==1){
					//如果条件为null且只有一个条件时，认为此分支可进入
					((ExecutionImpl)execution).putPvmCondition(id, condition);
					flag = true;
				}
			}
		}else{
			return true;
		}
		
		return flag;
	}

	@Override
	public List<PvmCondition> getConditions() {
		return conditions;
	}

	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(List<PvmCondition> conditions) {
		this.conditions = conditions;
	}
	
	@Override
	public PvmCondition getCondition(ActivityExecution execution) {
		return ((ExecutionImpl)execution).getPvmCondition(id);
	}
	
	public PvmCondition createPvmCondition(){
		PvmCondition condition = new PvmCondition();
		condition.setTransition(this);
		
		if(CollectionUtils.isEmpty(conditions)){
			conditions = new ArrayList<PvmCondition>();
		}
		
		conditions.add(condition);
		
		return condition;
	}
	
	@Override
	public String toString() {
		return source+"-->"+destination;
	}
	
}
