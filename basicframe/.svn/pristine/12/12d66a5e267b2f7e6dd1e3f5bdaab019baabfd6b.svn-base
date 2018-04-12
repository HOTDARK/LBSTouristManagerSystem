/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.convert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.IOUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.workflow.app.convert.element.BoundaryEventElementBuilder;
import com.hd.workflow.app.convert.element.CallActivityElementBuilder;
import com.hd.workflow.app.convert.element.DefaultElementBuilder;
import com.hd.workflow.app.convert.element.ElementBuilder;
import com.hd.workflow.app.convert.element.EndEventElementBuilder;
import com.hd.workflow.app.convert.element.SequenceFlowElementBuilder;

/**
 * 把流程设计器生成的json串转换为流程引擎可识别的xml
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 上午9:52:04
 */
public class JsonConvertXml {
	
	private final static Logger log = LoggerFactory.getLogger(JsonConvertXml.class);
	
	public final static String[] EXCLUDES_PROPERTY_NAME = {"name"};
	
	private Document document;
	
	private Element root;
	
	private Shape process;
	
	private Map<String, Element> elements = new HashMap<String, Element>();
	
	private Map<String, ElementBuilder> builderMap = new HashMap<String, ElementBuilder>();
	
	private ElementBuilder dynamicElementBuilder;
	
	private String processId;
	
	/**
	 * @param str json串
	 * @param dynamicElementBuilder 自定义节点xml创建器
	 */
	public JsonConvertXml(String str,ElementBuilder dynamicElementBuilder) {
		
		builderMap.put("StartEvent", new DefaultElementBuilder());
		builderMap.put("EndEvent", new EndEventElementBuilder());
		builderMap.put("ParallelGateway", new DefaultElementBuilder());
		builderMap.put("CallActivity", new CallActivityElementBuilder());
		builderMap.put("BoundaryErrorEvent", new BoundaryEventElementBuilder());
		builderMap.put("SequenceFlow", new SequenceFlowElementBuilder());
		
		this.dynamicElementBuilder = dynamicElementBuilder;
		
		process = JsonUtils.json2Object(str, Shape.class, null);
		
		processId = process.getProperty("process_id");
		
		document = DocumentHelper.createDocument();
		root = document.addElement("process");
		
	}
	
	public String toXml(){
		convert();
		
		String xml = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLWriter writer = null;
		try{
			writer = new XMLWriter(out);
			writer.write(document);
			xml = out.toString("utf-8");
			
		}catch (Exception e) {
			log.info(e.getMessage(),e);
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException ignore) {
				}
			}
			IOUtils.closeQuietly(out);
		}
		
		return xml;
	}
	
	
	private void convert(){
		root.addAttribute("id", process.getProperty("process_id"));
		root.addAttribute("name", process.getProperty("name"));
		
		convertChildShapes(process.getChildShapes(),null);
		
		for(Element element : elements.values()){
			ElementBuilder builder = getElementBuilder(element.getName());
			Element el = builder.render(element, this);
			if(el!=null){
				root.add(element);
			}
		}
		
	}
	
	private void convertChildShapes(List<Shape> shapes,Shape parentShape){
		if(CollectionUtils.isNotEmpty(shapes)){
			for(Shape shape : shapes){
				Element el = createFlowElement(shape);
				if(parentShape!=null){
					//为子节点添加attachedToRef 关联父节点
					el.addAttribute("attachedToRef", parentShape.getResourceId());
				}
				convertChildShapes(shape.getChildShapes(),shape);
			}
		}
		
	}
	
	public Element createFlowElement(Shape shape){
		ElementBuilder builder = getElementBuilder(shape.getStencil());
		Element el = builder.create(shape, this);
		if(el!=null){
			elements.put(shape.getResourceId(), el);
		}
		
		return el;
	}
	
	private ElementBuilder getElementBuilder(String stencil){
		ElementBuilder builder = builderMap.get(WordUtils.capitalize(stencil));
		return builder==null? dynamicElementBuilder:builder;
	}
	
	/**
	 * 根据id查找Outgoing，返回节点id
	 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
	 * @param id
	 * @return
	 */
	public String getSourceId(String id){
		for(Shape s : process.getChildShapes()){
			if(s.getOutgoing()!=null&&s.getOutgoing().contains(id)){
				return s.getResourceId();
			}
			
			//查找子节点
			if(CollectionUtils.isNotEmpty(s.getChildShapes())){
				for(Shape child : s.getChildShapes()){
					if(child.getOutgoing()!=null&&child.getOutgoing().contains(id)){
						return child.getResourceId();
					}
				}
			}
		}
		
		return null;
	}
	
	public Map<String, Element> getElements(){
		return elements;
	}
	
	/**
	 * 获取流程定义的process_id
	 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
	 * @return
	 */
	public String getProcessId(){
		return processId;
	}
	
}
