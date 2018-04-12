/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.workflow.app.dao.WorkflowProcessMapper;
import com.hd.workflow.app.model.WorkflowProcess;
import com.hd.workflow.app.service.WorkflowProcessService;
import com.hd.workflow.engine.WorkflowEngine;

/**
 * 
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 下午3:24:39
 */
@Service
public class WorkflowProcessServiceImpl implements WorkflowProcessService {
	
	@Autowired
	private WorkflowProcessMapper workflowProcessMapper;
	
	@Autowired
	private WorkflowEngine workflowEngine;

	@Override
	public WorkflowProcess findByProcessId(String processId) {
		return workflowProcessMapper.findByProcessId(processId);
	}

	@Override
	public WorkflowProcess findById(int id) {
		return workflowProcessMapper.findById(id);
	}

	@Override
	public void saveOrUpdate(WorkflowProcess process) {
		/*
		 * 更新操作未实现同步，在多个客户端频繁更新某个流程时可能导致版本号异常，
		 * 现阶段不考虑完善此功能
		 * 
		 */
		
		if(process.getId()>0){
			WorkflowProcess old = workflowProcessMapper.findById(process.getId());
			if(old==null){
				return;
			}
			
			//判断是否更新的是历史版本
			if(old.getResourceId()!=0){
				old = workflowProcessMapper.findById(old.getResourceId());
			}
			
			if(old!=null&&hasChanged(old, process)){
				//处理版本号，新增加一个版本
				process.setVersion(old.getVersion()+1);
				process.setId(old.getId());
				process.setLastUpdateTime(new Date());
				process.setState(old.getState());
				process.setResourceId(0);
				process.setUid(old.getUid());
				
				//更新老数据的id对应的那条数据
				workflowProcessMapper.update(process);
				
				old.setResourceId(old.getId());
				old.setId(0);
				//保存老数据,作为历史版本
				workflowProcessMapper.insert(old);
			}
		}else{
			process.setLastUpdateTime(new Date());
			process.setState(1);
			process.setResourceId(0);
			process.setVersion(1);
			//新增流程
			workflowProcessMapper.insert(process);
		}
		
		//发布流程
		workflowEngine.deploy(process.getProcessId(), process.getProcessXml(), process.getVersion());
		
	}
	
	/**
	 * 判断流程是否被改变
	 * @param old
	 * @param nw
	 * @return 如果改变返回true
	 */
	private boolean hasChanged(WorkflowProcess old,WorkflowProcess nw){
		if(old!=null&&nw!=null){
			
			boolean flag = StringUtils.equals(old.getDesignerJson(), nw.getDesignerJson())
						&&StringUtils.equals(old.getName(), nw.getName())
						&&StringUtils.equals(old.getDescription(), old.getDescription())
						&&StringUtils.equals(old.getProcessId(), old.getProcessId());
			
			return !flag;
		}else{
			return false;
		}
	}

	@Override
	public List<WorkflowProcess> getAllProcessWithoutDetail() {
		return workflowProcessMapper.getAllProcessWithoutDetail();
	}

	@Override
	public void delete(int id) {
		WorkflowProcess process = workflowProcessMapper.findById(id);
		if(process!=null){
			workflowProcessMapper.deleteOfSoft(id);
			workflowEngine.removeProcessDefinition(process.getProcessId());
		}
		
	}

	@Override
	public WorkflowProcess findByVersion(String processId, int version) {
		return workflowProcessMapper.findByVersion(processId, version);
	}

	@Override
	public List<WorkflowProcess> getAllVersion(int id) {
		return workflowProcessMapper.getAllVersion(id);
	}

	@Override
	public boolean checkProcessId(int id, String processId) {
		WorkflowProcess process = workflowProcessMapper.findByProcessId(processId);
		
		if(process==null){
			return true;
		}
		
		if(id==process.getId()){
			return true;
		}
		
		if(id>0){
			WorkflowProcess that = workflowProcessMapper.findById(id);
			if(that.getResourceId()==process.getId()){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public List<WorkflowProcess> findByCondition(WorkflowProcess condition,int start, int length) {
		return workflowProcessMapper.findByPage(condition, start, length);
	}

	@Override
	public int countByCondition(WorkflowProcess condition) {
		return workflowProcessMapper.getTotalCount(condition);
	}

}
