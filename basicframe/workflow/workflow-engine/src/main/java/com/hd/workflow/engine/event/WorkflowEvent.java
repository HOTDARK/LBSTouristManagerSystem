/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.event;

import com.hd.workflow.engine.pvm.delegate.ActivityExecution;


/**
 * 工作流事件
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-29 下午3:33:26
 */
public interface WorkflowEvent {

	/**
	 * 事件类型
	 * @return
	 */
	public EventType getType();
	
	
	public ActivityExecution getExecution();
	

}