/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hd.workflow.app.model.WorkflowProcess;

/**
 * 流程定义 dao
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 下午2:53:17
 */
public interface WorkflowProcessMapper {
	
	public void insert(WorkflowProcess process);
	
	public void update(WorkflowProcess process);
	
	public void delete(int id);

	/**
	 * 软删除
	 * @param id
	 */
	public void deleteOfSoft(int id);
	
	/**
	 * 根据流程id查找
	 * @param processId
	 * @return
	 */
	public WorkflowProcess findByProcessId(String processId);
	
	public WorkflowProcess findById(int id);
	
	public WorkflowProcess findByVersion(@Param("processId")String processId,@Param("version")int version);
	
	/**
	 * 获取所有的流程信息，但不返回  processXml designerJson designerXml 字段的内容
	 * @return
	 */
	public List<WorkflowProcess> getAllProcessWithoutDetail();
	
	/**
	 * 获取所有流程
	 * @return
	 */
	public List<WorkflowProcess> getAllProcess();
	
	/**
	 * 根据流程类型获取流程
	 * @return
	 */
	public List<WorkflowProcess> getAllProcessByType(@Param("processType")String processType);
	
	
	/**
	 * 获取流程的所有版本
	 * @param id
	 * @return
	 */
	public List<WorkflowProcess> getAllVersion(int id);
	
	/**
	 * 按条件查询实体并分页
	 * @param obj
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<WorkflowProcess> findByPage(@Param("model")WorkflowProcess condition, @Param("start")int start, @Param("limit")int limit);

	/**
	 * 按条件查询总记录数
	 * @param object
	 * @return
	 */
	public int getTotalCount(@Param("model")WorkflowProcess object);
	
}
