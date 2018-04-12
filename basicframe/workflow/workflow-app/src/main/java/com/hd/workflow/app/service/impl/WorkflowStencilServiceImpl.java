/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.service.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.app.dao.WorkflowStencilMapper;
import com.hd.workflow.app.model.WorkflowStencil;
import com.hd.workflow.app.service.WorkflowStencilService;

/**
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-28 下午3:31:04
 */
@Service
public class WorkflowStencilServiceImpl implements WorkflowStencilService{
	
	@Autowired
	private WorkflowStencilMapper stencilMapper;
	
	@Override
	public String stencilset() {
		JsonNode json = this.getStencilsetJson();
		List<WorkflowStencil> stencils = stencilMapper.getStencilset(new WorkflowStencil());
		
		if(CollectionUtils.isNotEmpty(stencils)){
			ArrayNode stencilsArr = (ArrayNode)json.findValue("stencils");
			for(WorkflowStencil stencil : stencils){
				stencilsArr.add(buildStencilJson(stencil));
			}
		}
		
		return json.toString();
	}
	
	private ObjectNode buildStencilJson(WorkflowStencil stencil){
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		
		node.put("type", stencil.getType());
		node.put("id", stencil.getStencilId());
		node.put("title", stencil.getTitle());
		node.put("description",stencil.getDescription());
		node.put("view", stencil.getView());
		node.put("icon", stencil.getIcon());
		node.put("groups", str2arr(stencil.getGroups()));
		node.put("propertyPackages", str2arr(stencil.getPropertyPackages()));
		node.put("roles", str2arr(stencil.getRoles()));
		
		return node;
	}
	
	private ArrayNode str2arr(String str){
		ArrayNode arr = JsonNodeFactory.instance.arrayNode();
		
		if(StringUtils.isNotEmpty(str)){
			for(String item: str.split(",")){
				arr.add(item);
			}
		}
		
		return arr;
	}
	
	/**
	 * 获取模板json
	 * @return
	 */
	private JsonNode getStencilsetJson(){
		InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
		try{
			String str =  IOUtils.toString(stencilsetStream,"utf-8");
	    	ObjectMapper objectMapper = new ObjectMapper();
		    JsonNode json = objectMapper.readTree(str);
		    
		    return json;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			IOUtils.closeQuietly(stencilsetStream);
		}
	}

	@Override
	public String getStencilClazz(String stencil) {
		return stencilMapper.getSentcilClazz(stencil);
	}

	@Override
	public void saveOrUpdate(WorkflowStencil stencil) {
		if(stencil.getId()>0){
			stencilMapper.update(stencil);
		}else{
			stencilMapper.insert(stencil);
		}
	}

	@Override
	public void delete(int id) {
		stencilMapper.delete(id);
	}

	@Override
	public List<WorkflowStencil> findAll(WorkflowStencil workflowStencil) {
		return stencilMapper.getStencilset(workflowStencil);
	}

	@Override
	public boolean checkStencilId(int id, String stencilId) {
		WorkflowStencil stencil = stencilMapper.findByStencilId(stencilId);
		return stencil==null||id==stencil.getId();
	}

	@Override
	public List<WorkflowStencil> findSteGroupList() {
		return stencilMapper.findSteGroupList();
	}

	@Override
	public List<WorkflowStencil> findByPage(WorkflowStencil workflowStencil,
			int start, int length) {
		return stencilMapper.findByPage(workflowStencil,start,length);
	}

	@Override
	public int countByCondition(WorkflowStencil workflowStencil) {
		return stencilMapper.countByCondition(workflowStencil);
	}
	
}
