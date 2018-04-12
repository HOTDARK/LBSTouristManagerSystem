/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.service;

import java.util.List;

import com.hd.workflow.app.model.WorkflowLog;
import com.hd.workflow.app.model.WorkflowStencil;

/**
 * 
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-28 下午3:30:47
 */
public interface WorkflowStencilService {
	
	/**
	 * 返回所有模型
	 * @return
	 */
	public List<WorkflowStencil> findAll(WorkflowStencil workflowStencil);
	
	/**
	 * 获取流程元素模型集合
	 * @return
	 */
	public String stencilset();
	
	/**
	 * 获取模型定义的clazz
	 * @param stencil
	 * @return
	 */
	public String getStencilClazz(String stencil);
	
	/**
	 * 保存或更新 
	 * @param stencil
	 */
	public void saveOrUpdate(WorkflowStencil stencil);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 判断stencilId是否重复
	 * @param id
	 * @param stencilId
	 * @return 不重复返回true,重复返回false
	 */
	public boolean checkStencilId(int id,String stencilId);

	/**
	 * 获取所有分组名称
	 * @return
	 */
	public List<WorkflowStencil> findSteGroupList();

	public List<WorkflowStencil> findByPage(WorkflowStencil workflowStencil,
			int start, int length);

	public int countByCondition(WorkflowStencil workflowStencil);
}
