/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.behavior;

import com.hd.workflow.engine.pvm.delegate.ActivityExecution;

/**
 * 边界事件，用于捕获执行节点异常
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-4 下午4:49:33
 */
public class BoundaryEventActivityBehavior extends AbstractActivityBehavior{

	@Override
	public void execute(ActivityExecution execution) throws Exception {
		leave(execution);
	}

}
