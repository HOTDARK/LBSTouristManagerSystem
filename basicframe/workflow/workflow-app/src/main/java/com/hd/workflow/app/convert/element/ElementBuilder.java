/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.convert.element;

import org.dom4j.Element;

import com.hd.workflow.app.convert.JsonConvertXml;
import com.hd.workflow.app.convert.Shape;

/**
 * 节点创建器
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 下午12:00:21
 */
public interface ElementBuilder {
	
	/**
	 * 创建节点
	 * @param shape
	 * @param jsonConvertXml
	 * @return
	 */
	public Element create(Shape shape,JsonConvertXml jsonConvertXml);

	/**
	 * 填充数据、关联关系
	 * @param element
	 * @param jsonConvertXml
	 * @return
	 */
	public Element render(Element element,JsonConvertXml jsonConvertXml);
}
