/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.convert.element;

import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.hd.workflow.app.convert.JsonConvertXml;
import com.hd.workflow.app.convert.Shape;

/**
 * 结束节点
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-12 下午3:49:51
 */
public class EndEventElementBuilder extends DefaultElementBuilder {
	
	@SuppressWarnings("unchecked")
	@Override
	public Element create(Shape shape, JsonConvertXml jsonConvertXml) {
		Element el =  super.create(shape, jsonConvertXml);
		
		Object obj = shape.getProperties().get("conclusion");
		if(obj instanceof Map){
			
			Map<String, String> conclusion = (Map<String, String>) obj;
			Element conclusionElement = el.addElement("conclusion");
			for(Map.Entry<String, String> entry : conclusion.entrySet()){
				conclusionElement.add(createEl(entry.getKey(),entry.getValue()));
			}
			
		}
		
		Object params = shape.getProperties().get("outparameters");
		if(params instanceof List){
			el.add(listToElement("out", "param", (List<Map<String,String>>)params));
		}
		
		return el;
	}
	
	private Element createEl(String name,String val){
		Element el = DocumentHelper.createElement(name);
		el.addAttribute("value", val);
		return el;
	}
}
