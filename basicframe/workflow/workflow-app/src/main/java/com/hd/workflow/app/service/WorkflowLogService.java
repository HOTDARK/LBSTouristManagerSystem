/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.service;

import java.util.List;

import com.hd.workflow.app.model.WorkflowLog;
import com.hd.workflow.app.model.WorkflowLogItem;

/**
 * 日志service
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-23 上午11:17:11
 */
public interface WorkflowLogService {

	/**
	 * 插入日志
	 * @param info
	 */
	public void add(WorkflowLog info);
	
	/**
	 * 修改日志
	 * @param info
	 */
	public void update(WorkflowLog info);
	
	public void addLogItem(WorkflowLogItem item);
	public void deleteBylogId(int logId);
	
	public WorkflowLog findById(int id);
	
	public List<WorkflowLogItem> findItemByLogId(int id);
	
	/**
	 * 根据条件分页查询 
	 * @param condition
	 * @param start
	 * @param length
	 * @return
	 */
	public List<WorkflowLog> findByPage(WorkflowLog condition,int start,int length);
	
	/**
	 * 统计满足条件的数据条数
	 * @param condition
	 * @return
	 */
	public int countByCondition(WorkflowLog condition);
}
