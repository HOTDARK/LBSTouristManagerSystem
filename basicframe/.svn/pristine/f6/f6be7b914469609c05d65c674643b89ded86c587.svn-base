/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.convert;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.hd.workflow.app.convert.deserialize.OutgoingDeserializer;
import com.hd.workflow.app.convert.deserialize.StencilIdDeserializer;

/**
 * 流程设计器中的节点图形
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 上午9:40:11
 */
public class Shape {
	
	private String resourceId;
	
	private Map<String, Object> properties;
	
	@JsonDeserialize(using=StencilIdDeserializer.class)
	private String stencil;
	
	private List<Shape> childShapes;
	
	@JsonDeserialize(using=OutgoingDeserializer.class)
	private List<String> outgoing;

	/**
	 * @return the resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the properties
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	/**
	 * @return the stencil
	 */
	public String getStencil() {
		return stencil;
	}

	/**
	 * @param stencil the stencil to set
	 */
	public void setStencil(String stencil) {
		this.stencil = stencil;
	}

	/**
	 * @return the childShapes
	 */
	public List<Shape> getChildShapes() {
		return childShapes;
	}

	/**
	 * @param childShapes the childShapes to set
	 */
	public void setChildShapes(List<Shape> childShapes) {
		this.childShapes = childShapes;
	}

	/**
	 * @return the outgoing
	 */
	public List<String> getOutgoing() {
		return outgoing;
	}

	/**
	 * @param outgoing the outgoing to set
	 */
	public void setOutgoing(List<String> outgoing) {
		this.outgoing = outgoing;
	}
	
	public String getProperty(String key){
		return (String)properties.get(key);
	}
	
}
