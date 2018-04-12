/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.parse.handler;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.hd.workflow.engine.parse.ParseHandler;
import com.hd.workflow.engine.parse.ParseUtil;
import com.hd.workflow.model.Conclusion;
import com.hd.workflow.model.ConditionParam;
import com.hd.workflow.model.EndEvent;
import com.hd.workflow.model.FlowElement;

/**
 * 结束节点解析器
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 上午11:49:30
 */
public class EndEventParseHandler implements ParseHandler {

	@Override
	public FlowElement parse(Element element) {
		EndEvent endEvent = new EndEvent();
		ParseUtil.parseCommon(element, endEvent);
			
		endEvent.setConclusion(createConclusion(element.element("conclusion")));
		endEvent.setOutParams(createOutParams(element.element("out")));
		
		return endEvent;
	}
	
	@SuppressWarnings("unchecked")
	private List<ConditionParam> createOutParams(Element el){
		if(el==null){
			return null;
		}
		
		List<ConditionParam> params = new ArrayList<ConditionParam>();
		
		List<Element> list = el.elements();
		for(Element param : list){
			params.add(new ConditionParam(param.attributeValue("key"), param.attributeValue("value")));
		}
		
		return params;
	}
	
	private Conclusion createConclusion(Element el){
		if(el==null){
			return null;
		}
		
		Conclusion conclusion = new Conclusion();
		conclusion.setCode(getElementValue(el,"code"));
		conclusion.setState(getElementValue(el,"state"));
		conclusion.setResult(getElementValue(el,"result"));
		conclusion.setTips(getElementValue(el,"tips"));
		
		return conclusion;
	}
	
	private String getElementValue(Element el,String name){
		Element element = el.element(name);
		if(element!=null){
			return element.attributeValue("value");
		}else{
			return null;
		}
	}

}
