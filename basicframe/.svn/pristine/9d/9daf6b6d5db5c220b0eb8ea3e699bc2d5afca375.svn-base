/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.log;

import java.util.ArrayList;
import java.util.List;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.engine.WorkflowConstants;
import com.hd.workflow.engine.event.WorkflowEvent;
import com.hd.workflow.engine.event.WorkflowEventListener;
import com.hd.workflow.engine.pvm.PvmConclusion;
import com.hd.workflow.engine.pvm.PvmCondition;
import com.hd.workflow.engine.pvm.PvmConditionParam;
import com.hd.workflow.engine.pvm.PvmTransition;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;
import com.hd.workflow.engine.pvm.delegate.Expression;
import com.hd.workflow.engine.pvm.impl.ExecutionImpl;
import com.hd.workflow.log.script.ScriptUtil;
import com.hd.workflow.model.Conclusion;
import com.hd.workflow.model.ConditionParam;

/**
 * 记录流程执行信息
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-10 上午10:12:59
 */
public class ExecutionInfoListener implements WorkflowEventListener {
	
	public static String EXEC_SEQUENCE_KEY = "exec_sequence_key"; 

	@Override
	public void onEvent(WorkflowEvent event) {
		ExecutionImpl execution = (ExecutionImpl)event.getExecution();
		switch(event.getType()){
			case ACTIVITY_TAKE:
				logStep(execution);break;
			case PROCESS_COMPLETED:
				logConclusion(execution);break;
			case PROCESS_START:
				logInit(execution);break;
		}
	}
	
	private void logInit(ExecutionImpl execution){
		//只有是主流程才记录
		if(execution.getParent()!=null){
			return;
		}
		ExecutionInfo info = getExecutionSequenceLog(execution);
		info.setStartTime(System.currentTimeMillis());
		info.setEndTime(System.currentTimeMillis());
	}
	
	private void logConclusion(ExecutionImpl execution){
		
		//只有是主流程才记录总结论
		if(execution.getParent()!=null){
			return;
		}
		ExecutionInfo info = getExecutionSequenceLog(execution);
		info.setEndTime(System.currentTimeMillis());
		
		PvmConclusion pvmConclusion = (PvmConclusion)execution.getActivity().getProperty(WorkflowConstants.PROPERTYNAME_CONCLUSION);
		if(pvmConclusion!=null){
			Conclusion conclusion = new Conclusion();
			conclusion.setCode(pvmConclusion.getCode());
			conclusion.setResult(getExpValue(pvmConclusion.getResult(), execution));
			conclusion.setState(pvmConclusion.getState());
			conclusion.setTips(getExpValue(pvmConclusion.getTips(), execution));
			
			info.setConclusion(conclusion);
		}
		
	}
	
	private void logStep(ExecutionImpl execution){
		
		PvmTransition transition = execution.getTransition();
		if(transition==null){
			return;
		}
		
		PvmCondition condition = execution.getPvmCondition(transition.getId());
		if(condition==null){
			return;
		}
		
		ExecutionInfo sequenceLog = getExecutionSequenceLog(execution);
		
		sequenceLog.add(createExecutionStep(execution,condition), execution);
		sequenceLog.setEndTime(System.currentTimeMillis());
	}
	
	private ExecutionInfo getExecutionSequenceLog(ExecutionImpl execution){
		ExecutionInfo sequenceLog = (ExecutionInfo)execution.getVariable(EXEC_SEQUENCE_KEY);
		
		if(sequenceLog==null){
			sequenceLog = new ExecutionInfo();
			execution.setVariable(EXEC_SEQUENCE_KEY, sequenceLog);
		}
		
		return sequenceLog;
	}
	
	private ExecutionInfoItem createExecutionStep(ActivityExecution execution,PvmCondition condition){
		ExecutionInfoItem step = new ExecutionInfoItem();
		step.setName(execution.getActivity().getName());
		step.setId(execution.getExecSeqId());
		step.setCode(condition.getCode());
		step.setState(condition.getState());
		
		step.setResult(getExpValue(condition.getResult(),execution));
		step.setTips(getExpValue(condition.getTips(), execution));
		
		if(CollectionUtils.isNotEmpty(condition.getParams())){
			List<ConditionParam> params = new ArrayList<ConditionParam>();
			for(PvmConditionParam param : condition.getParams()){
				ConditionParam outParam = new ConditionParam(param.getKey(), getExpValue(param.getValue(),execution));
				outParam.setColor(ScriptUtil.execute(param.getScript(), execution));
				params.add(outParam);
				
			}
			
			step.setParams(params);
		}
		
		return step;
		
	}
	
	private String getExpValue(Expression exp,ActivityExecution execution){
		if(exp==null){
			return null;
		}else{
			Object obj = exp.getValue(execution);
			return obj==null?null:obj.toString();
		}
	}
	
	@Override
	public boolean isFailOnException() {
		return false;
	}

}
