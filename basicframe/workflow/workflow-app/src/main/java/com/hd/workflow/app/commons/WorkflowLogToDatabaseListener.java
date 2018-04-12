/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.commons;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.workflow.app.UserRelationshipMapping;
import com.hd.workflow.app.model.WorkflowLog;
import com.hd.workflow.app.model.WorkflowLogItem;
import com.hd.workflow.app.service.WorkflowLogService;
import com.hd.workflow.engine.event.EventType;
import com.hd.workflow.engine.event.WorkflowEvent;
import com.hd.workflow.engine.event.WorkflowEventListener;
import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;
import com.hd.workflow.log.ExecutionInfo;
import com.hd.workflow.log.ExecutionInfoItem;
import com.hd.workflow.log.ExecutionInfoListener;
import com.hd.workflow.log.ExecutionTrace;
import com.hd.workflow.log.ExecutionTraceListener;
import com.hd.workflow.model.Conclusion;

/**
 * 记录日志到数据库中
 * @version	0.0.1
 */
@Component
public class WorkflowLogToDatabaseListener implements WorkflowEventListener{
	
	@Autowired
	private WorkflowLogService workflowLogService;

	@Override
	public void onEvent(WorkflowEvent event) {
		
		ActivityExecution execution = event.getExecution();
		
		if(execution.getParent()!=null){
			return;
		}
		
		//流程完全结束
		if(event.getType()==EventType.PROCESS_COMPLETED || event.getType()==EventType.ACTIVITY_PERFORM){
			
			//执行轨迹
			ExecutionTrace trace = (ExecutionTrace)execution.getVariable(ExecutionTraceListener.WORKFLOW_TRACE_KEY);
			
			//执行信息
			ExecutionInfo execInfo = (ExecutionInfo)execution.getVariable(ExecutionInfoListener.EXEC_SEQUENCE_KEY);
			
			int id = (Integer)execution.getVariable(WorkflowLog.WORKFLOWLOG_ID_KEY);
			
			WorkflowLog info = workflowLogService.findById(id);
			info.setTrace(trace.toJson());
			info.setTotalTime(execInfo.getEndTime()-execInfo.getStartTime());
			info.setStartTime(new Date(execInfo.getStartTime()));
			info.setEndTime(new Date(execInfo.getEndTime()));
			
			if(execution.getException()!=null){
				info.setException(ExceptionUtils.getStackTrace(execution.getException()));
			}
			
			Conclusion conclusion = execInfo.getConclusion();
			if(conclusion!=null){
				info.setConclusionCode(conclusion.getCode());
				info.setConclusionResult(conclusion.getResult());
				info.setConclusionState(conclusion.getState());
				info.setConclusionTips(conclusion.getTips());
			}
			workflowLogService.update(info);
			workflowLogService.deleteBylogId(info.getId());
			logItem(execInfo.getSteps(),0,info.getId());
			
		} else if (event.getType()==EventType.PROCESS_START) {
			ProcessDefinition processDefinition = execution.getProcessDefinition();
			WorkflowLog info = new WorkflowLog();
			info.setProcessName(processDefinition.getName());
			info.setProcessId(processDefinition.getId());
			info.setVersion(processDefinition.getVersion());
			Map<String, Object> param = execution.getVariables();
			info.setParams(JsonUtils.toJson(param,null,null,null));
			// 获取用户账户uid
			Object obj = execution.getVariable(UserRelationshipMapping.EXEC_USER_ID);
			if(obj!=null){
				try{
					info.setUid(obj.toString());
				}catch (Exception e) {
					//ignore
				}
			}
			workflowLogService.add(info);
			execution.setVariable(WorkflowLog.WORKFLOWLOG_ID_KEY, info.getId());
		}
	}
	
	private synchronized void logItem(List<ExecutionInfoItem> list,int pid,int logId){
		if(CollectionUtils.isNotEmpty(list)){
			int ord = 1;
			for(ExecutionInfoItem item : Collections.synchronizedList(list)){
				WorkflowLogItem logItem = new WorkflowLogItem();
				logItem.setPid(pid);
				logItem.setLogId(logId);
				logItem.setName(item.getName());
				logItem.setCode(item.getCode());
				logItem.setState(item.getState());
				logItem.setResult(item.getResult());
				logItem.setTips(item.getTips());
				logItem.setParams(item.getParams()==null?"":JsonUtils.toJson(item.getParams(), null, null, null));
				logItem.setOrd(ord);
				
				workflowLogService.addLogItem(logItem);
				
				logItem(item.getSteps(),logItem.getId(),logId);
				
				ord++;
			}
		}
		
	}
	

	@Override
	public boolean isFailOnException() {
		return false;
	}

}
