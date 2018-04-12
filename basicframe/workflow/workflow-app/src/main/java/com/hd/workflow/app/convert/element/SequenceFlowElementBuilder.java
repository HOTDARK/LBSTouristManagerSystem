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
 * 创建SequenceFlow
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 下午1:38:08
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SequenceFlowElementBuilder extends DefaultElementBuilder{
	
	private final static String CONDITIONS = "conditionsequenceflow";
	
	
	@Override
	public Element create(Shape shape, JsonConvertXml jsonConvertXml) {
		Element el = super.create(shape, jsonConvertXml);
		String sourceRef = jsonConvertXml.getSourceId(shape.getResourceId());
		String targetRef = null;
		if(shape.getOutgoing()!=null&&shape.getOutgoing().size()==1){
			targetRef = shape.getOutgoing().get(0);
		}
		
		el.addAttribute("sourceRef", sourceRef);
		el.addAttribute("targetRef", targetRef);

		Object obj = shape.getProperties().get(CONDITIONS);
		if(obj!=null&&obj instanceof Map){
			List<Map> conditions = (List<Map>) ((Map)obj).get("conditions");
			if(conditions!=null){
				Element conditionsElement = el.addElement("conditions");
				for(Map item : conditions){
					conditionsElement.add(createConditionElement(item));
				}
			}
			
		}
		
		return el;
	}
	
	/**
	 * 构建condition节点
	 * @param map
	 * @return
	 */
	private Element createConditionElement(Map<String, Object> map){
		Element el = DocumentHelper.createElement("condition");
		for(Map.Entry<String, Object> entry : map.entrySet()){
			String key = entry.getKey();
			Object val = entry.getValue();
			
			if(val instanceof String){
				el.addAttribute(key, (String)val);
			}else if(val instanceof List && "params".equals(key)){
				el.add(listToElement("params","param",(List<Map<String,String>>)val));
			}
		}
		
		return el;
	}
	
}
