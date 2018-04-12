/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.parse.handler;

import org.dom4j.Element;

import com.hd.workflow.engine.parse.ParseHandler;
import com.hd.workflow.engine.parse.ParseUtil;
import com.hd.workflow.model.FlowElement;
import com.hd.workflow.model.TaskActivity;

/**
 * 任务解析器
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 下午1:41:14
 */
public class TaskParseHandler implements ParseHandler{

	@Override
	public FlowElement parse(Element element) {
		TaskActivity task = new TaskActivity();
		task.setClazz(element.attributeValue("class"));
		ParseUtil.parseCommon(element, task);
		
		return task;
	}

}
