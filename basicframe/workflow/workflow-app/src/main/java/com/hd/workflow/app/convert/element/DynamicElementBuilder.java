/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.convert.element;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.workflow.app.convert.JsonConvertXml;
import com.hd.workflow.app.convert.Shape;
import com.hd.workflow.app.service.WorkflowStencilService;

/**
 * 动态自定义的节点元素
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 下午2:27:46
 */
@Component
public class DynamicElementBuilder extends DefaultElementBuilder{
	
	@Autowired
	private WorkflowStencilService workflowStencilService;
	
	@Override
	public Element create(Shape shape, JsonConvertXml jsonConvertXml) {
		Element el = super.create(shape, jsonConvertXml);
		el.setName("task");
		
		String clazz = workflowStencilService.getStencilClazz(shape.getStencil());
		
		el.addAttribute("class", clazz);
		
		return el;
	}
	
}
