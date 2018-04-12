/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm;

import java.util.List;

/**
 * 流程定义
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-27 下午1:58:41
 */
public interface ProcessDefinition extends PvmProcessElement{
	
	/**
	 * 获取当前流程的版本号
	 * @return
	 */
	public int getVersion();
	
	/**
	 * 获取所有执行节点
	 * @return
	 */
	public List<? extends PvmActivity> getActivities();
	
	/**
	 * 根据执行节点id查找
	 * @param activityId
	 * @return
	 */
	public PvmActivity findActivity(String activityId);

	/**
	 * 创建流程实例
	 * @return
	 */
	public ProcessInstance createProcessInstance();
	
}