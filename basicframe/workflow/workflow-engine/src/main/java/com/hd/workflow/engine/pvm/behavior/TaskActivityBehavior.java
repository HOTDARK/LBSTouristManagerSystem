/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.behavior;

import com.hd.workflow.engine.WorkflowConstants;
import com.hd.workflow.engine.pvm.PvmActivity;
import com.hd.workflow.engine.pvm.context.Context;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;
import com.hd.workflow.engine.pvm.delegate.TaskDelegate;

/**
 * 任务行为
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-12 下午3:55:26
 */
public class TaskActivityBehavior extends AbstractActivityBehavior{

	@SuppressWarnings("unchecked")
	@Override
	public void execute(ActivityExecution execution) throws Exception {
		PvmActivity activity = execution.getActivity();
		Class<TaskDelegate> clazz = (Class<TaskDelegate>)activity.getProperty(WorkflowConstants.PROPERTYNAME_TASK_CLASS);
		TaskDelegate delegate = Context.getConfiguration().getTaskDelegateFactory().newInstance(clazz);
		delegate.execute(execution);
		
		leave(execution);
	}

}