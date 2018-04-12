/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.impl;

import java.util.HashMap;
import java.util.Map;

import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.PvmProcessElement;

/**
 * 
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-6 下午3:08:54
 */
public abstract class ProcessElementImpl implements PvmProcessElement{
	
	protected String id;
	
	protected String name;
	
	protected ProcessDefinition processDefinition;
	
	protected Map<String, Object> properties = new HashMap<String, Object>();
	
	public ProcessElementImpl(String id, ProcessDefinition processDefinition) {
		this.id = id;
		this.processDefinition = processDefinition;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Object getProperty(String name) {
		if(properties==null){
			return null;
		}
		
		return properties.get(name);
	}
	
	@Override
	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}
	
	public void setProperty(String name, Object value) {
		if (properties==null) {
			properties = new HashMap<String, Object>();
		}
		properties.put(name, value);
	}
		  
	public Map<String, Object> getProperties() {
		return properties;
	}
		  
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
