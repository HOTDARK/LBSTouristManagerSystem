/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.parse.handler;

import org.dom4j.Element;

import com.hd.workflow.engine.parse.ParseHandler;
import com.hd.workflow.engine.parse.ParseUtil;
import com.hd.workflow.model.FlowElement;
import com.hd.workflow.model.ParallelGateway;

/**
 * 并发网关解析器
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 下午1:02:07
 */
public class ParallelGatewayParseHandler implements ParseHandler {

	@Override
	public FlowElement parse(Element element) {
		ParallelGateway parallelGateway = new ParallelGateway();
		
		ParseUtil.parseCommon(element, parallelGateway);
		
		return parallelGateway;
	}

}
