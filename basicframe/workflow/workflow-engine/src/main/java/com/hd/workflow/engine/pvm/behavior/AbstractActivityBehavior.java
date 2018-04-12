/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.behavior;

import java.util.List;

import com.hd.workflow.engine.exception.WorkflowEngineException;
import com.hd.workflow.engine.pvm.PvmActivity;
import com.hd.workflow.engine.pvm.PvmTransition;
import com.hd.workflow.engine.pvm.delegate.ActivityBehavior;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;

/**
 * 提供一些通用方法
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-27 上午9:45:50
 */
public abstract class AbstractActivityBehavior implements ActivityBehavior {
	
	protected void leave(ActivityExecution execution){
		PvmActivity activity = execution.getActivity();
		if(!execution.isEnded()){
			List<PvmTransition> transitionToTake = execution.getTransition(activity);
			
			if(transitionToTake.size()==1){
				execution.take(transitionToTake.get(0));
			}else if(transitionToTake.size()>1){
				throw new WorkflowEngineException("出现多分支，暂不支持此特性。");
			}else{
				throw new WorkflowEngineException("没有可供流转的分支，流程终止。activityId="+activity.getId());
			}
		}
	}
}
