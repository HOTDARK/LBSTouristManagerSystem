/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.commons;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.workflow.WorkflowEngineInitiator;
import com.hd.workflow.app.dao.WorkflowProcessMapper;
import com.hd.workflow.app.model.WorkflowProcess;
import com.hd.workflow.engine.WorkflowEngine;
import com.hd.workflow.engine.event.EventType;

/**
 * 
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-18 上午10:54:49
 */
@Component
public class WorkflowEngineInitiatorImpl implements  WorkflowEngineInitiator{
	
	private final static Logger log = LoggerFactory.getLogger(WorkflowEngineInitiatorImpl.class);
	
	@Autowired
	private WorkflowProcessMapper workflowProcessMapper;
	
	@Autowired
	private WorkflowLogToDatabaseListener workflowLogToDatabaseListener;

	@Override
	public void init(WorkflowEngine engine, String processType) {
		if (!StringUtils.isEmpty(processType)) {
			processType = "'".concat(processType.replaceAll(",", "','")).concat("'");
		}
		for(WorkflowProcess process : workflowProcessMapper.getAllProcessByType(processType)){
			try{
				engine.deploy(process.getProcessId(),process.getProcessXml(),process.getVersion());
			}catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		
		//添加入库listener
		engine.getConfiguration().getWorkflowEventDispatcher().addEventListener(workflowLogToDatabaseListener,EventType.PROCESS_COMPLETED,EventType.PROCESS_START,EventType.ACTIVITY_PERFORM);
	}

}
