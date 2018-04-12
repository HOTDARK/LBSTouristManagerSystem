/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.parse.handler;

import org.dom4j.Element;

import com.hd.workflow.engine.parse.ParseHandler;
import com.hd.workflow.engine.parse.ParseUtil;
import com.hd.workflow.model.FlowElement;
import com.hd.workflow.model.StartEvent;

/**
 * 开始节点解析器
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 上午10:39:52
 */
public class StartEventParseHandler implements ParseHandler {

	@Override
	public FlowElement parse(Element element) {
		StartEvent startEvent = new StartEvent();
		
		ParseUtil.parseCommon(element, startEvent);
		
		return startEvent;
	}

}
