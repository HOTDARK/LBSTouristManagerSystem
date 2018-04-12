/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.dao;
import java.util.List;

import com.hd.workflow.app.model.WorkflowLogItem;

/**
 * @version	0.0.1
 * @author	generator
 * @date	2015-03-25
 */
public interface WorkflowLogItemMapper{
	
	
	public void insert(WorkflowLogItem object);
	
	public List<WorkflowLogItem> findByLogId(int id);
	
	public void deleteBylogId(int logId);
}
