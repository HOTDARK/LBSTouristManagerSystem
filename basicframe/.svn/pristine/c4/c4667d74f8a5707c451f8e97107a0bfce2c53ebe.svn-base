/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 分支连线
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-30 上午9:52:42
 */
public class SequenceFlow extends FlowElement {
	
	private String sourceRef;
	
	private String targetRef;
	
	private List<Condition> conditions = new ArrayList<Condition>();

	/**
	 * @return the sourceRef
	 */
	public String getSourceRef() {
		return sourceRef;
	}

	/**
	 * @param sourceRef the sourceRef to set
	 */
	public void setSourceRef(String sourceRef) {
		this.sourceRef = sourceRef;
	}

	/**
	 * @return the targetRef
	 */
	public String getTargetRef() {
		return targetRef;
	}

	/**
	 * @param targetRef the targetRef to set
	 */
	public void setTargetRef(String targetRef) {
		this.targetRef = targetRef;
	}

	/**
	 * @return the conditions
	 */
	public List<Condition> getConditions() {
		return conditions;
	}

	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
	
	public void addCondition(Condition condition){
		conditions.add(condition);
	}
}
