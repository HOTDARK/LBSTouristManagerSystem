/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm;

import java.util.List;

import com.hd.workflow.engine.pvm.delegate.ActivityExecution;

/**
 * 分支
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-27 下午2:23:02
 */
public interface PvmTransition extends PvmProcessElement{
	
	/**
	 * 源节点
	 * @return
	 */
	public PvmActivity getSource();

	/**
	 * 目标节点
	 * @return
	 */
	public PvmActivity getDestination();
	
	/**
	 * 判断此分支是否可以流转
	 * @param execution
	 * @return
	 */
	public boolean evaluate(ActivityExecution execution);

	/**
	 * 获取所有判断条件
	 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
	 * @return
	 */
	public List<PvmCondition> getConditions();
	
	/**
	 * 在分支可流转的情况下，获取分支的判断条件
	 * @param execution
	 * @return
	 */
	public PvmCondition getCondition(ActivityExecution execution);
}
