/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.parse.handler;

import java.util.List;

import org.dom4j.Element;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.engine.parse.ParseHandler;
import com.hd.workflow.engine.parse.ParseUtil;
import com.hd.workflow.model.BoundaryEvent;
import com.hd.workflow.model.FlowElement;

/**
 * 边界事件解析器
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 下午2:19:40
 */
public class BoundaryEventParseHandler implements ParseHandler{

	@SuppressWarnings("unchecked")
	@Override
	public FlowElement parse(Element element) {
		BoundaryEvent boundaryEvent = new BoundaryEvent();
		boundaryEvent.setAttachedToRef(element.attributeValue("attachedToRef"));
		
		ParseUtil.parseCommon(element, boundaryEvent);
		
		List<Element> list = element.elements();
		if(CollectionUtils.isNotEmpty(list)){
			for(Element el : list){
				boundaryEvent.addEvent(el.getName());
			}
		}
		
		return boundaryEvent;
	}

}
