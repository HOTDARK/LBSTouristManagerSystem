/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.behavior;

import java.util.List;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.engine.WorkflowConstants;
import com.hd.workflow.engine.pvm.PvmConditionParam;
import com.hd.workflow.engine.pvm.VariableScope;
import com.hd.workflow.engine.pvm.delegate.ActivityBehavior;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;
import com.hd.workflow.engine.pvm.delegate.Expression;

/**
 * 结束事件
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-6 下午2:01:34
 */
public class EndEventBehavior implements ActivityBehavior {
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(ActivityExecution execution) throws Exception {
		
		List<PvmConditionParam> params = (List<PvmConditionParam>)execution.getActivity().getProperty(WorkflowConstants.PROPERTYNAME_END_OUT_PARAMETERS);
		if(CollectionUtils.isNotEmpty(params)){
			for(PvmConditionParam param : params){
				execution.setVariable(param.getKey(), getValue(param.getValue(),execution));
			}
		}
		
		execution.end();
	}
	
	private Object getValue(Expression exp,VariableScope scope){
		if(exp==null){
			return null;
		}else{
			return exp.getValue(scope);
		}
	}

}
