/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.workflow.app.dao.WorkflowLogItemMapper;
import com.hd.workflow.app.dao.WorkflowLogMapper;
import com.hd.workflow.app.model.WorkflowLog;
import com.hd.workflow.app.model.WorkflowLogItem;
import com.hd.workflow.app.service.WorkflowLogService;

/**
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-23 上午11:19:00
 */
@Service
public class WorkflowLogServiceImpl implements WorkflowLogService{
	
	@Autowired
	private WorkflowLogMapper workflowLogMapper;
	
	@Autowired
	private WorkflowLogItemMapper workflowLogItemMapper;

	@Override
	public List<WorkflowLog> findByPage(WorkflowLog condition, int start,int length) {
		return workflowLogMapper.findByPage(condition, start, length);
	}

	@Override
	public int countByCondition(WorkflowLog condition) {
		return workflowLogMapper.findNumByCondition(condition);
	}

	@Override
	public WorkflowLog findById(int id) {
		return workflowLogMapper.findByPK(id);
	}

	@Override
	public void add(WorkflowLog info) {
		workflowLogMapper.insert(info);
	}

	@Override
	public void addLogItem(WorkflowLogItem item) {
		workflowLogItemMapper.insert(item);
	}
	
	@Override
	public void deleteBylogId(int logId){
		workflowLogItemMapper.deleteBylogId(logId);
	}

	@Override
	public List<WorkflowLogItem> findItemByLogId(int id) {
		return workflowLogItemMapper.findByLogId(id);
	}

	@Override
	public void update(WorkflowLog info) {
		workflowLogMapper.updateByPK(info);
	}
	

}
