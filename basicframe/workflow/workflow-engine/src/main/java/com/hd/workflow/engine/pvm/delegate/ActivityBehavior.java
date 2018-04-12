/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.delegate;


/**
 * 执行节点行为逻辑
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-29 下午4:52:17
 */
public interface ActivityBehavior {
	
	/**
	 * 执行节点对应的行为逻辑
	 * @param execution
	 * @throws Exception
	 */
	public void execute(ActivityExecution execution) throws Exception;
}
