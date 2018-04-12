/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 边界事件
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 下午2:20:02
 */
public class BoundaryEvent extends FlowNode {
	private String attachedToRef;
	
	//事件类型
	private List<String> events = new ArrayList<String>();

	/**
	 * @return the attachedToRef
	 */
	public String getAttachedToRef() {
		return attachedToRef;
	}

	/**
	 * @param attachedToRef the attachedToRef to set
	 */
	public void setAttachedToRef(String attachedToRef) {
		this.attachedToRef = attachedToRef;
	}

	/**
	 * @return the events
	 */
	public List<String> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(List<String> events) {
		this.events = events;
	}
	
	/**
	 * 添加事件
	 * @param event
	 */
	public void addEvent(String event){
		events.add(event);
	}
}
