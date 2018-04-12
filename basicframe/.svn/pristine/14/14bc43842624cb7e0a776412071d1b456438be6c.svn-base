/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.factory;

import com.hd.workflow.engine.pvm.delegate.TaskDelegate;

/**
 * 实例工厂简单实现
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-27 下午4:32:20
 */
public class SimpleTaskDelegateFactory implements TaskDelegateFactory{

	@Override
	public TaskDelegate newInstance(Class<TaskDelegate> clazz)
			throws NewTaskDelegateException {
		
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new NewTaskDelegateException();
		}
		
	}

}
