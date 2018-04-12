/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.event;

/**
 * 事件监听
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-29 下午3:30:44
 */
public interface WorkflowEventListener {
	
	/**
	 * 触发事件
	 * @param event
	 */
	public void onEvent(WorkflowEvent event);
	
	/**
	 * 出现异常时是否抛出异常
	 * @return
	 */
	public boolean isFailOnException();
	
}
