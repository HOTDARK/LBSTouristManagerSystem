/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.convert.element;

import org.dom4j.Element;

import com.hd.workflow.app.convert.JsonConvertXml;
import com.hd.workflow.app.convert.Shape;

/**
 * 创建子流程调用
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 下午1:37:26
 */
public class CallActivityElementBuilder extends DefaultElementBuilder{
	
	private final static String CALLED = "callactivitycalledelement";
	
	@Override
	public Element create(Shape shape, JsonConvertXml jsonConvertXml) {
		Element el = super.create(shape, jsonConvertXml);
		
		el.addAttribute("called", shape.getProperty(CALLED));
		
		return el;
	}
	
	@Override
	protected String[] customExclude() {
		return new String[]{CALLED};
	}
}
