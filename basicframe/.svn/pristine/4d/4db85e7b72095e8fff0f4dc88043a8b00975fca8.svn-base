/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow;

import com.hd.workflow.engine.WorkflowEngine;

/**
 * 提供初始化扩展
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-18 上午10:12:00
 */
public interface WorkflowEngineInitiator{
	
	/**
	 * 实现此方法后放入WorkflowEngineFactoryBean,
	 * 目的是为了在初始化WorkflowEngine实例后处理自定义的一些逻辑。
	 * @param engine
	 */
	public void init(WorkflowEngine engine, String processType);
}
