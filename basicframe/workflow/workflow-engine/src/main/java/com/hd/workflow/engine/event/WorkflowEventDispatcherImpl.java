/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.workflow.engine.exception.WorkflowEngineException;

/**
 * 工作流事件分发器
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-9 下午4:50:32
 */
public class WorkflowEventDispatcherImpl implements WorkflowEventDispatcher {
	
	private final static Logger log = LoggerFactory.getLogger(WorkflowEventDispatcherImpl.class);
	
	//按类型分组的监听器
	private Map<EventType, List<WorkflowEventListener>> listenerGroups = new HashMap<EventType, List<WorkflowEventListener>>();
	
	//全局监听器
	private List<WorkflowEventListener> listeners = new ArrayList<WorkflowEventListener>();

	@Override
	public synchronized void addEventListener(WorkflowEventListener listener) {
		if(!listeners.contains(listener)){
			listeners.add(listener);
		}
	}

	@Override
	public synchronized void addEventListener(WorkflowEventListener listener,EventType... types) {
		if(types==null||types.length==0){
			addEventListener(listener);
		}else{
			for(EventType type : types){
				List<WorkflowEventListener> list = listenerGroups.get(type);
				if(list==null){
					list = new ArrayList<WorkflowEventListener>();
					listenerGroups.put(type, list);
				}
				
				if(!list.contains(listener)){
					list.add(listener);
				}
				
			}
		}
	}

	@Override
	public synchronized void removeEventListener(WorkflowEventListener listener) {
		listeners.remove(listener);

		for (List<WorkflowEventListener> listeners : listenerGroups.values()) {
			listeners.remove(listener);
		}
	}

	@Override
	public void dispatchEvent(WorkflowEvent event) {
		if (event == null) {
			throw new IllegalArgumentException("Event cannot be null.");
		}

		if (event.getType() == null) {
			throw new IllegalArgumentException("Event type cannot be null.");
		}

		// 调用全局监听器
		if (!listeners.isEmpty()) {
			for (WorkflowEventListener listener : listeners) {
				dispatchEvent(event, listener);
			}
		}

		//调用特定类型的监听器
		List<WorkflowEventListener> typed = listenerGroups.get(event.getType());
		if (typed != null && !typed.isEmpty()) {
			for (WorkflowEventListener listener : typed) {
				dispatchEvent(event, listener);
			}
		}
		
	}
	
	protected void dispatchEvent(WorkflowEvent event, WorkflowEventListener listener) {
		try {
			listener.onEvent(event);
		} catch (Throwable t) {
			if (listener.isFailOnException()) {
				throw new WorkflowEngineException("执行listener发生异常", t);
			} else {
				log.warn("执行listener发生异常，异常忽略", t);
			}
		}
	}

}
