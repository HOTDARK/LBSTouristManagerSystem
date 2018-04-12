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
import com.hd.workflow.model.Condition;
import com.hd.workflow.model.ConditionParam;
import com.hd.workflow.model.FlowElement;
import com.hd.workflow.model.SequenceFlow;

/**
 * 连线解析器
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 上午11:54:12
 */
public class SequenceFlowParseHandler implements ParseHandler{

	@SuppressWarnings("unchecked")
	@Override
	public FlowElement parse(Element element) {
		SequenceFlow flow = new SequenceFlow();
		
		flow.setSourceRef(element.attributeValue("sourceRef"));
		flow.setTargetRef(element.attributeValue("targetRef"));
		
		ParseUtil.parseCommon(element, flow);
		
		Element conditionElement = element.element("condition");
		Element conditionsElement = element.element("conditions");
		
		List<Element> list = new ArrayList<Element>();
		
		if(conditionElement!=null){
			list.add(conditionElement);
		}
		
		if(conditionsElement!=null){
			list.addAll(conditionsElement.elements());
		}
		
		for(Element el : list){
			Condition condition = new Condition();
			condition.setCode(el.attributeValue("code"));
			condition.setResult(el.attributeValue("result"));
			condition.setExpression(el.attributeValue("expression"));
			condition.setTips(el.attributeValue("tips"));
			condition.setState(el.attributeValue("state"));
			
			condition.setParams(getParams(el));
			
			flow.addCondition(condition);
		}
		
		return flow;
	}

	@SuppressWarnings({"unchecked" })
	private List<ConditionParam> getParams(Element el){
		Element paramsEl = el.element("params");
		if(paramsEl==null||paramsEl.elements().size()==0){
			return null;
		}
		
		List<ConditionParam> list = new ArrayList<ConditionParam>();
		for(Element paramEl : (List<Element>)paramsEl.elements()){
			list.add(new ConditionParam(paramEl.attributeValue("key"), paramEl.attributeValue("value"), paramEl.attributeValue("script")));
		}
		
		return list;
	}
}
