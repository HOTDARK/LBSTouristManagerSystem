package com.hd.sys.core.properties.typehandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.hd.sys.core.properties.TypeHandler;

/**
 * 以jaxb方式序列化对象 字符串第一行为类名,以\r\n分割之后为jaxb xml
 * @author sunjian
 * @version V1.0, 2013-5-20 下午12:26:50
 */
public class ObjectTypeHandler implements TypeHandler {

	@Override
	public Object toObj(String str) {
		if(StringUtils.isEmpty(str)){
			return null;
		}
		
		int index = str.indexOf("\r\n");
		
		if(index<0){
			return null;
		}
		
		String className = str.substring(0,index);
		String xml = str.substring(index+2);
		
		try {
			Class<?> clazz = Class.forName(className);
			//创建jaxb上下文
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        dbf.setNamespaceAware(true);
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes());
	        Document doc = db.parse(in);
	        Node fooSubtree = doc.getFirstChild();

	        JAXBElement<?> el = unmarshaller.unmarshal( fooSubtree, clazz);
	        return el.getValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String toStr(Object obj) {
		Class<?> clazz = obj.getClass();
		try{
			JAXBContext context = JAXBContext.newInstance(clazz);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			marshaller.marshal(new JAXBElement(new QName(clazz.getSimpleName()),clazz,obj),out);
			
			return clazz.getName()+"\r\n"+out.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
