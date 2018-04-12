/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.task;

import com.hd.workflow.engine.exception.BizError;
import com.hd.workflow.engine.pvm.VariableScope;
import com.hd.workflow.engine.pvm.delegate.TaskDelegate;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-13 上午10:06:31
 */
public class ExceptionTask implements TaskDelegate {

	@Override
	public void execute(VariableScope scope) {
		throw new BizError("异常测试");
	}

}
