/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.service;

import java.util.List;

import com.hd.workflow.app.model.WorkflowProcess;

/**
 * 
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 下午3:24:24
 */
public interface WorkflowProcessService {
	
	/**
	 * 分页查询
	 * @param condition
	 * @param start
	 * @param length
	 * @return
	 */
	public List<WorkflowProcess> findByCondition(WorkflowProcess condition,int start,int length);
	
	/**
	 * 统计满足条件的数据条数
	 * @param condition
	 * @return
	 */
	public int countByCondition(WorkflowProcess condition);
	
	/**
	 * 保存或更新
	 * @param process
	 */
	public void saveOrUpdate(WorkflowProcess process);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 通过流程id查找
	 * @param processId
	 * @return
	 */
	public WorkflowProcess findByProcessId(String processId);
	
	/**
	 * 通过记录id查找 
	 * @param id
	 * @return
	 */
	public WorkflowProcess findById(int id);
	
	/**
	 * 查找指定版本的流程定义信息
	 * @param processId
	 * @param version
	 * @return
	 */
	public WorkflowProcess findByVersion(String processId,int version);
	
	/**
	 * 获取所有的流程信息，但不返回  processXml designerJson designerXml 字段的内容
	 * @return
	 */
	public List<WorkflowProcess> getAllProcessWithoutDetail();
	
	/**
	 * 获取所有版本
	 * @param id
	 * @return
	 */
	public List<WorkflowProcess> getAllVersion(int id);
	
	/**
	 * 检查processId是否存在
	 * @param id 如果id为大于0则表示更新操作时的检查
	 * @param processId
	 * @return
	 */
	public boolean checkProcessId(int id,String processId);
	
}
