/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine;

import com.hd.workflow.engine.pvm.delegate.ActivityBehavior;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-6 下午4:21:44
 */
public class MyTestBehavior implements ActivityBehavior {

	@Override
	public void execute(ActivityExecution execution) throws Exception {
		System.out.println("test");
	}

}
