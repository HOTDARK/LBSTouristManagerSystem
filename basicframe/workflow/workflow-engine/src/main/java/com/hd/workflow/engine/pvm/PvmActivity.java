/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm;

import java.util.List;

import com.hd.workflow.engine.pvm.delegate.ActivityBehavior;

/**
 * 执行节点
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-27 下午2:22:31
 */
public interface PvmActivity extends PvmProcessElement{
	
	/**
	 * 获取执行节点对应的行为，即实际的逻辑代码
	 * @return
	 */
	public ActivityBehavior getActivityBehavior();

	/**
	 * 连进来的连线
	 * @return
	 */
	public List<PvmTransition> getIncomingTransitions();

	/**
	 * 连出去的连线
	 * @return
	 */
	public List<PvmTransition> getOutgoingTransitions();
	  
	/**
	 * 通过连线id查找连出去的连线
	 * @param transitionId
	 * @return
	 */
	public PvmTransition findOutgoingTransition(String transitionId);
}
