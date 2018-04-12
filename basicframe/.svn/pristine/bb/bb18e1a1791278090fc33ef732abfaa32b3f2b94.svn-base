/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.parse;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.engine.WorkflowConstants;
import com.hd.workflow.engine.exception.ProcessParserException;
import com.hd.workflow.engine.exception.WorkflowEngineException;
import com.hd.workflow.engine.parse.handler.BoundaryEventParseHandler;
import com.hd.workflow.engine.parse.handler.CallActivityParseHandler;
import com.hd.workflow.engine.parse.handler.EndEventParseHandler;
import com.hd.workflow.engine.parse.handler.ParallelGatewayParseHandler;
import com.hd.workflow.engine.parse.handler.SequenceFlowParseHandler;
import com.hd.workflow.engine.parse.handler.StartEventParseHandler;
import com.hd.workflow.engine.parse.handler.TaskParseHandler;
import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.PvmActivity;
import com.hd.workflow.engine.pvm.PvmConclusion;
import com.hd.workflow.engine.pvm.PvmCondition;
import com.hd.workflow.engine.pvm.PvmConditionParam;
import com.hd.workflow.engine.pvm.behavior.BoundaryEventActivityBehavior;
import com.hd.workflow.engine.pvm.behavior.CallActivityBehavior;
import com.hd.workflow.engine.pvm.behavior.EndEventBehavior;
import com.hd.workflow.engine.pvm.behavior.ParallelGatewayBehavior;
import com.hd.workflow.engine.pvm.behavior.StartEventBehavior;
import com.hd.workflow.engine.pvm.behavior.TaskActivityBehavior;
import com.hd.workflow.engine.pvm.context.Context;
import com.hd.workflow.engine.pvm.delegate.ActivityBehavior;
import com.hd.workflow.engine.pvm.delegate.TaskDelegate;
import com.hd.workflow.engine.pvm.impl.ActivityImpl;
import com.hd.workflow.engine.pvm.impl.ProcessDefinitionImpl;
import com.hd.workflow.engine.pvm.impl.TransitionImpl;
import com.hd.workflow.model.BoundaryEvent;
import com.hd.workflow.model.CallActivity;
import com.hd.workflow.model.Conclusion;
import com.hd.workflow.model.Condition;
import com.hd.workflow.model.ConditionParam;
import com.hd.workflow.model.EndEvent;
import com.hd.workflow.model.FlowElement;
import com.hd.workflow.model.FlowNode;
import com.hd.workflow.model.ParallelGateway;
import com.hd.workflow.model.Process;
import com.hd.workflow.model.SequenceFlow;
import com.hd.workflow.model.StartEvent;
import com.hd.workflow.model.TaskActivity;

/**
 * 
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-29 下午3:22:37
 */
public class ProcessParser {
	private static final Logger log = LoggerFactory.getLogger(ProcessParser.class);
	
	private static Map<String, ParseHandler> handlers = new HashMap<String, ParseHandler>();
	private static Map<Class<? extends FlowNode>, ActivityBehavior> behaviors = new HashMap<Class<? extends FlowNode>, ActivityBehavior>();
	static{
		handlers.put("startEvent", new StartEventParseHandler());
		handlers.put("endEvent", new EndEventParseHandler());
		handlers.put("task", new TaskParseHandler());
		handlers.put("callActivity", new CallActivityParseHandler());
		handlers.put("parallelGateway", new ParallelGatewayParseHandler());
		handlers.put("boundaryEvent", new BoundaryEventParseHandler());
		handlers.put("sequenceFlow", new SequenceFlowParseHandler());
		
		//behavior
		behaviors.put(StartEvent.class, new StartEventBehavior());
		behaviors.put(EndEvent.class, new EndEventBehavior());
		behaviors.put(CallActivity.class, new CallActivityBehavior());
		behaviors.put(BoundaryEvent.class, new BoundaryEventActivityBehavior());
		behaviors.put(ParallelGateway.class, new ParallelGatewayBehavior());
		behaviors.put(TaskActivity.class, new TaskActivityBehavior());
		
	}
	
	public ProcessDefinition execute(String schema){
		if(StringUtils.isEmpty(schema)){
			throw new WorkflowEngineException("流程为空");
		}
		
		Process process = null;
		try {
			process = parse(schema);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ProcessParserException("解析流程发生错误",e);
		}
		
		ProcessDefinitionImpl processDefinition = new ProcessDefinitionImpl(process.getId());
		processDefinition.setName(process.getName());
		
		//遍历节点构建执行节点
		for(FlowNode node : process.getNodes().values()){
			ActivityImpl activityImpl = processDefinition.createActivity(node.getId());
			activityImpl.setName(node.getName());
			activityImpl.getProperties().putAll(node.getProperties());
			
			activityImpl.setActivityBehavior(behaviors.get(node.getClass()));
			
			//解析任务class
			if(node instanceof TaskActivity){
				String className = ((TaskActivity) node).getClazz();
				Class<?> clazz = null;
				
				try{
					clazz = ClassUtils.getClass(className);
				}catch (Exception e) {
					throw new ProcessParserException("无法找到实现类："+className);
				}
				
				if(!ClassUtils.isAssignable(clazz, TaskDelegate.class)){
					throw new ProcessParserException(className+"不是正确的实现");
				}
				
				activityImpl.setProperty(WorkflowConstants.PROPERTYNAME_TASK_CLASS, clazz);
			}else if(node instanceof CallActivity){
				String processDefinitionId = ((CallActivity) node).getCalled();
				if(StringUtils.isNotEmpty(processDefinitionId)){
					activityImpl.setProperty(WorkflowConstants.PROPERTYNAME_PROCESS_DEFINITION_ID,((CallActivity) node).getCalled());
				}else{
					throw new ProcessParserException("子流程调用未设置流程id,"+activityImpl);
				}
				
			}else if(node instanceof StartEvent){
				if(processDefinition.getStartingActivity()==null){
					processDefinition.setStartingActivity(activityImpl);
				}else{
					throw new ProcessParserException("出现多个开始节点，"+processDefinition);
				}
				
			}else if(node instanceof EndEvent){
				Conclusion conclusion = ((EndEvent) node).getConclusion();
				if(conclusion!=null){
					PvmConclusion pvmConclusion = new PvmConclusion();
					pvmConclusion.setCode(conclusion.getCode());
					pvmConclusion.setResult(Context.createExpression(conclusion.getResult()));
					pvmConclusion.setState(conclusion.getState());
					pvmConclusion.setTips(Context.createExpression(conclusion.getTips()));
					
					activityImpl.setProperty(WorkflowConstants.PROPERTYNAME_CONCLUSION, pvmConclusion);
				}
				
				List<PvmConditionParam> params = toPvmParam(((EndEvent) node).getOutParams());
				if(CollectionUtils.isNotEmpty(params)){
					activityImpl.setProperty(WorkflowConstants.PROPERTYNAME_END_OUT_PARAMETERS, params);
				}
				
			}
		}
		
		//遍历分支设置关联关系
		for(SequenceFlow flow : process.getSequences().values()){
			ActivityImpl source = (ActivityImpl)processDefinition.findActivity(flow.getSourceRef());
			ActivityImpl target = (ActivityImpl)processDefinition.findActivity(flow.getTargetRef());
			if(source!=null){
				TransitionImpl transitionImpl = source.createOutgoingTransition(flow.getId());
				transitionImpl.setName(flow.getName());
				transitionImpl.setDestination(target);
				if(CollectionUtils.isNotEmpty(flow.getConditions())){
					for(Condition condition : flow.getConditions()){
						PvmCondition pvmCondition = transitionImpl.createPvmCondition();
						pvmCondition.setCode(condition.getCode());
						pvmCondition.setExpression(Context.createExpression(condition.getExpression()));
						pvmCondition.setResult(Context.createExpression(condition.getResult()));
						pvmCondition.setState(condition.getState());
						pvmCondition.setTips(Context.createExpression(condition.getTips()));
						
						pvmCondition.setParams(toPvmParam(condition.getParams()));
					}
				}
				
				flow.getConditions();
			}
		}
		
		//处理边缘事件所属关系
		for(FlowNode node : process.getNodes().values()){
			if(node instanceof BoundaryEvent){
				BoundaryEvent boundaryEvent = (BoundaryEvent) node;
				PvmActivity activity = processDefinition.findActivity(boundaryEvent.getAttachedToRef());
				if(activity==null){
					throw new ProcessParserException("解析流程xml失败，未找到boundaryEvent所属节点，"+processDefinition);
				}
				
				//附加事件到执行节点上
				if(CollectionUtils.isNotEmpty(boundaryEvent.getEvents())){
					for(String event : boundaryEvent.getEvents()){
						((ActivityImpl)activity).setProperty(event, processDefinition.findActivity(node.getId()));
					}
				}
				
			}
		}
		
		return processDefinition;
	}
	
	private List<PvmConditionParam> toPvmParam(List<ConditionParam> params){
		
		if(CollectionUtils.isEmpty(params)){
			return null;
		}
		
		List<PvmConditionParam> list = new ArrayList<PvmConditionParam>();
		for(ConditionParam param : params){
			list.add(new PvmConditionParam(param.getKey(),Context.createExpression(param.getValue()),param.getScript()));
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private Process parse(String schema) throws Exception{
		SAXReader reader = new SAXReader(); 
		
		Document document = reader.read(new ByteArrayInputStream(schema.getBytes("utf-8")));
		
		Element root = document.getRootElement();
		
        Process process = new Process();
        process.setId(root.attributeValue("id"));
        process.setName(root.attributeValue("name"));
        
        List<Element> elements = root.elements();
        for(Element element : elements){
        	String name = element.getName();
        	
    		ParseHandler handler = handlers.get(name);
    		if(handler==null){
    			throw new ProcessParserException("解析处理器未找到：name="+name);
    		}
    		
    		
    		FlowElement fe = handler.parse(element);
    		
    		if(fe!=null){
    			if(StringUtils.isEmpty(fe.getId())){
    				throw new WorkflowEngineException("流程元素"+fe.getClass().getSimpleName()+"id为空");
    			}
    			
    			if(fe instanceof SequenceFlow ){
    				if(process.getSequences().containsKey(fe.getId())){
    					throw new ProcessParserException("连线 id="+fe.getId()+" 已存在");
    				}
    				
    				process.addSequenceFlow((SequenceFlow)fe);
    			}else if(fe instanceof FlowNode){
    				if(process.getNodes().containsKey(fe.getId())){
    					throw new ProcessParserException("节点 id="+fe.getId()+" 已存在");
    				}
    				process.addNode((FlowNode)fe);
    			}else{
    				//忽略
    				System.out.println("fuck");
    			}
    		}else{
    			System.out.println(element);
    		}
        	
        }
        
        return process;
	}

}
