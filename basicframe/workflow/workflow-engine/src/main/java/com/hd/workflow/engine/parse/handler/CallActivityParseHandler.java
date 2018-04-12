/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.parse.handler;

import org.dom4j.Element;

import com.hd.workflow.engine.parse.ParseHandler;
import com.hd.workflow.engine.parse.ParseUtil;
import com.hd.workflow.model.CallActivity;
import com.hd.workflow.model.FlowElement;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 上午11:52:18
 */
public class CallActivityParseHandler implements ParseHandler {

	@Override
	public FlowElement parse(Element element) {
		CallActivity callActivity = new CallActivity();
		
		callActivity.setCalled(element.attributeValue("called"));
		ParseUtil.parseCommon(element, callActivity);
		
		return callActivity;
	}

}
