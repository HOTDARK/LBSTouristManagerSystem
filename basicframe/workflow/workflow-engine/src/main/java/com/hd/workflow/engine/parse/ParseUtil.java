/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.parse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.hd.workflow.model.FlowElement;

/**
 * 解析工具类
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 上午11:46:33
 */
public class ParseUtil {
	
	/**
	 * 解析通用的元素属性
	 */
	@SuppressWarnings("unchecked")
	public static void parseCommon(Element element,FlowElement flowElement){
		flowElement.setId(element.attributeValue("id"));
		flowElement.setName(element.attributeValue("name"));
		
		Element properties = element.element("properties");
		if(properties!=null){
			Map<String, String> map = new HashMap<String, String>();
			List<Element> list = properties.elements();
			for(Element prop : list){
				map.put(prop.attributeValue("name"), prop.attributeValue("value"));
			}
			flowElement.setProperties(map);
		}
	}
	
}
