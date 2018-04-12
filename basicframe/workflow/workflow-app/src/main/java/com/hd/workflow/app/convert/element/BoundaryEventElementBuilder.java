/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.convert.element;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import com.hd.workflow.app.convert.JsonConvertXml;
import com.hd.workflow.app.convert.Shape;

/**
 * 创建边界事件
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 下午1:38:44
 */
public class BoundaryEventElementBuilder extends DefaultElementBuilder {
	
	private final static Map<String, String> mapping = new HashMap<String, String>();
	
	static{
		mapping.put("boundaryErrorEvent", "errorEventDefinition");
	}
	
	@Override
	public Element create(Shape shape, JsonConvertXml jsonConvertXml) {
		
		Element el = super.create(shape, jsonConvertXml);
		String old = el.getName();
		el.setName("boundaryEvent");
		
		if(mapping.containsKey(old)){
			el.addElement(mapping.get(old));
		}
		
		
		return el;
	}
}
