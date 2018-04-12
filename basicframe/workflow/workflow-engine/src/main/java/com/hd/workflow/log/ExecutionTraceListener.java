/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.log;

import java.util.ArrayList;

import com.hd.workflow.engine.event.WorkflowEvent;
import com.hd.workflow.engine.event.WorkflowEventListener;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;
import com.hd.workflow.engine.pvm.impl.ExecutionImpl;

/**
 * 记录执行轨迹日志
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-17 下午3:45:54
 */
public class ExecutionTraceListener implements WorkflowEventListener{
	
	public final static String WORKFLOW_TRACE_KEY = "workflow_trace_key";

	@Override
	public void onEvent(WorkflowEvent event) {
		ExecutionImpl execution = (ExecutionImpl)event.getExecution();
		
		switch(event.getType()){
			case PROCESS_START:
				createTrace(execution);
				break;
			case ACTIVITY_PERFORM:
				log(execution.getActivity().getId(),execution);
				break;
			case ACTIVITY_TAKE:
				log(execution.getTransition().getId(),execution);
				break;
			case PROCESS_COMPLETED:
				merge(execution);
				break;
		}
	}
	
	/**
	 * 创建记录器
	 * @param execution
	 * @return
	 */
	private ExecutionTrace createTrace(ActivityExecution execution){
		ExecutionTrace trace = new ExecutionTrace();
		trace.setName(execution.getProcessDefinition().getName());
		trace.setProcessId(execution.getProcessDefinition().getId());
		trace.setVersion(execution.getProcessDefinition().getVersion());
		trace.setChilds(new ArrayList<ExecutionTraceItem>());
		
		execution.setLocalVariable(WORKFLOW_TRACE_KEY, trace);
		
		return trace;
	}
	
	/**
	 * 获取已存在的记录器
	 * @param execution
	 * @return
	 */
	private ExecutionTrace getTrace(ActivityExecution execution){
		if(execution==null){
			return null;
		}
		
		ExecutionTrace trace = (ExecutionTrace)execution.getLocalVariable(WORKFLOW_TRACE_KEY);
		if(trace==null){
			return createTrace(execution);
		}else{
			return trace;
		}
	}
	
	
	private void log(String elementId,ExecutionImpl execution){
		ExecutionTraceItem traceItem = new ExecutionTraceItem(elementId);
		getTrace(execution).getChilds().add(traceItem);
	}
	
	/**
	 * 合并执行轨迹
	 * @param execution
	 */
	private void merge(ExecutionImpl execution){
		ExecutionTrace curTrace = getTrace(execution);
		ExecutionTrace parentTrace = getTrace(execution.getParent());
		
		if(execution.isForkJoin()&&execution.isMainThread()==false){//并发
			parentTrace.addTraceItem(curTrace.getChilds());
		}else if(execution.getParent()!=null&&execution.isMainThread()){//子流程
			String parentId = execution.getParent().getActivity().getId();
			
			for(ExecutionTraceItem trace : parentTrace.getChilds()){
				if(trace.getElementId().equals(parentId)){
					trace.setSubProcess(curTrace);
					break;
				}
			}
		}
		
	}
	
	@Override
	public boolean isFailOnException() {
		return false;
	}

}
