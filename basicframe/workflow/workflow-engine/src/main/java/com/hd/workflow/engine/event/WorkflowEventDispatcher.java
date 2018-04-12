/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.event;

/**
 * 事件发送器
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-29 下午3:30:14
 */
public interface WorkflowEventDispatcher {
	
	/**
	 * 添加一个事件监听器，所有事件都可能被此监听器所接收
	 * @param listener
	 */
	public void addEventListener(WorkflowEventListener listener);
	
	/**
	 * 添加事件监听，只为在指定事件发生时触发
	 * @param listener
	 * @param types 时间类型
	 */
	public void addEventListener(WorkflowEventListener listener, EventType... types);
	
	/**
	 * 移除事件监听器
	 * @param listener
	 */
	 public void removeEventListener(WorkflowEventListener listener);
	 
	/**
	 * 发送事件
	 * @param event
	 */
	 public void dispatchEvent(WorkflowEvent event);
}
