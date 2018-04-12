/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程实体
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 上午10:28:54
 */
public class Process {
	private String id;
	private String name;
	
	private Map<String, FlowNode> nodes = new HashMap<String, FlowNode>();
	
	//source-->flow
	private Map<String, SequenceFlow> sequences = new HashMap<String, SequenceFlow>();

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the nodes
	 */
	public Map<String, FlowNode> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(Map<String, FlowNode> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the sequences
	 */
	public Map<String, SequenceFlow> getSequences() {
		return sequences;
	}

	/**
	 * @param sequences the sequences to set
	 */
	public void setSequences(Map<String, SequenceFlow> sequences) {
		this.sequences = sequences;
	}

	public void addNode(FlowNode node){
		nodes.put(node.getId(), node);
	}
	
	public void addSequenceFlow(SequenceFlow flow){
		sequences.put(flow.getId(), flow);
	}
	
}
