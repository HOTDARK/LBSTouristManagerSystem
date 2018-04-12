/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hd.workflow.app.model.WorkflowStencil;


/**
 * dao访问
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-28 下午3:15:21
 */
public interface WorkflowStencilMapper {
	
	public void insert(WorkflowStencil stencil);
	
	public void update(WorkflowStencil stencil);
	
	public void delete(int id);
	
	/**
	 * 获取所有流程元素模型
	 * @return
	 */
	public List<WorkflowStencil> getStencilset(WorkflowStencil stencil);
	
	/**
	 * 获取模型clazz
	 * @param stencilId
	 * @return
	 */
	public String getSentcilClazz(String stencilId);
	
	public WorkflowStencil findByStencilId(String stencilId);
	
	/**
	 * 获取所有分组名称
	 * @return
	 */
	public List<WorkflowStencil> findSteGroupList();

	public List<WorkflowStencil> findByPage(@Param("model")WorkflowStencil workflowStencil,
			@Param("start")int start, @Param("limit")int length);

	public int countByCondition(@Param("model")WorkflowStencil workflowStencil);
	
}
