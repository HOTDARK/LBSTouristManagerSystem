/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.context;

import org.apache.commons.lang.StringUtils;

import com.hd.workflow.engine.WorkflowEngine;
import com.hd.workflow.engine.WorkflowEngineConfiguration;
import com.hd.workflow.engine.event.EventType;
import com.hd.workflow.engine.event.WorkflowEvent;
import com.hd.workflow.engine.event.WorkflowEventImpl;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;
import com.hd.workflow.engine.pvm.delegate.Expression;

/**
 * 持有流程引擎，简化调用<br>
 * TODO 此种方式无法创建对流程引擎创建多个实例，应再后续版本中改进
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-27 下午2:32:40
 */
public class Context {
	private static WorkflowEngine engine;

	/**
	 * @return the engine
	 */
	public static WorkflowEngine getEngine() {
		return engine;
	}

	/**
	 * @param engine the engine to set
	 */
	public static void setEngine(WorkflowEngine engine) {
		Context.engine = engine;
	}

	/**
	 * 获取流程引擎configuration
	 * @return
	 */
	public static WorkflowEngineConfiguration getConfiguration(){
		return engine.getConfiguration();
	}
	
	/**
	 * 创建表达式
	 * @param exp
	 * @return
	 */
	public static Expression createExpression(String exp){
		if(StringUtils.isNotEmpty(exp)){
			return getConfiguration().getExpressionManager().createExpression(exp);
		}else{
			return null;
		}
		
	}
	
	/**
	 * 触发事件
	 * @param type
	 * @param execution
	 */
	public static void dispatchEvent(EventType type,ActivityExecution execution){
		WorkflowEvent event = new WorkflowEventImpl(type,execution);
		
		getConfiguration().getWorkflowEventDispatcher().dispatchEvent(event);
		
	}
}
