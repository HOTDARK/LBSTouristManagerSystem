/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.log;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hd.sfw.core.utils.json.JsonUtils;


/**
 * 执行轨迹跟踪
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-16 下午4:58:33
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ExecutionTrace {
	
	private String name;
	
	private String processId;
	
	private int version;
	
	private List<ExecutionTraceItem> childs;
	
	/**
	 * 同步，避免并发加入 
	 * @param items
	 */
	public synchronized void addTraceItem(List<ExecutionTraceItem> items){
		childs.addAll(items);
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

	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the childs
	 */
	public List<ExecutionTraceItem> getChilds() {
		return childs;
	}

	/**
	 * @param childs the childs to set
	 */
	public void setChilds(List<ExecutionTraceItem> childs) {
		this.childs = childs;
	}
	
	public String toJson(){
		return JsonUtils.toJson(this, null, null, null);
	}
	
}
