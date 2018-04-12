/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.behavior;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.workflow.engine.WorkflowConstants;
import com.hd.workflow.engine.exception.BizError;
import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.ProcessInstance;
import com.hd.workflow.engine.pvm.PvmActivity;
import com.hd.workflow.engine.pvm.context.Context;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;

/**
 * 调用子流程行为
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-3 上午9:12:13
 */
public class CallActivityBehavior extends AbstractActivityBehavior{
	private final static Logger log = LoggerFactory.getLogger(CallActivityBehavior.class);
	
	@Override
	public void execute(ActivityExecution execution) throws Exception {
		PvmActivity activity = execution.getActivity();
		String processDefinitionId = (String)activity.getProperty(WorkflowConstants.PROPERTYNAME_PROCESS_DEFINITION_ID);
		ProcessDefinition processDefinition = Context.getEngine().getProcessDefinition(processDefinitionId);
		if(processDefinition==null){
			throw new BizError("PROCESS_NOT_FOUND","未找到流程定义,id="+processDefinitionId);
		}
		
		log.debug("进入子流程："+processDefinition);
		
		ProcessInstance instance = processDefinition.createProcessInstance();
		instance.setParent(execution);
		instance.start();
	
		if(instance.getException()!=null){
			log.debug("子流程异常结束:"+processDefinition);
			throw new BizError("SUB_PROCESS_EXCEPTION",instance.getException());
		}else{
			log.debug("子流程结束："+processDefinition);
		}
		
		leave(execution);
	}

}
