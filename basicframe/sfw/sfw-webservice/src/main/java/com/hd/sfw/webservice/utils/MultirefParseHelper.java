/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.utils;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.classloader.BeanInfoCache;
import org.apache.axis2.databinding.typemapping.SimpleTypeMapper;
import org.apache.axis2.databinding.utils.MultirefHelper;
import org.apache.axis2.engine.DefaultObjectSupplier;

/**
 * 针对存在multiref的反序列化类
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-6 下午12:52:02
 */
public class MultirefParseHelper {
	
	private OMElement om;
	
	private HashMap<String,OMElement> elementMap = new HashMap<String,OMElement>();
	
	private static DefaultObjectSupplier defaultObjectSupplier = new DefaultObjectSupplier();
	
	public MultirefParseHelper(OMElement om) {
		this.om = om;
		readallChildElements();
	}
	
    private void readallChildElements() {
        Iterator<?> childs = ((OMElement)om.getParent()).getChildElements();
        while (childs.hasNext()) {
            OMElement omElement = (OMElement)childs.next();
            OMAttribute id = omElement.getAttribute(new QName("id"));
            if (id != null) {
                elementMap.put(id.getAttributeValue(), omElement);
            }
        }
        
    }
    
    public <T> T deserialize(Class<?> beanClass) throws Exception{
    	return deserialize(om,beanClass);
    }
    
    /**
     * 获取parts上可能存在的引用
     * @param parts
     * @return 如果找到引用返回,如果不存在href属性返回null
     * @throws AxisFault 如果存在引用ID，但查找不存在则抛出此异常
     */
    private OMElement getMultirefOMElement(OMElement parts) throws AxisFault{
    	OMAttribute attr = MultirefHelper.processRefAtt(parts);
    	if(attr==null){
    		return null;
    	}
    	
    	String refId = MultirefHelper.getAttvalue(attr);
    	OMElement refElement = elementMap.get(refId);
    	if(refElement==null){
    		throw new AxisFault("Invalid reference :" + refId);
    	}else{
    		return refElement;
    	}
    }
    
    @SuppressWarnings("unchecked")
	private <T> T deserialize(OMElement om,Class<?> beanClass) throws Exception{
    	OMElement ref = getMultirefOMElement(om);
    	if(ref!=null){
    		return  deserialize(ref,beanClass);
    	}
    	
        HashMap<String, PropertyDescriptor> properties = new HashMap<String, PropertyDescriptor>();
        BeanInfo beanInfo = BeanInfoCache.getCachedBeanInfo(beanClass,null);
        PropertyDescriptor[] propDescs = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor proprty : propDescs) {
            properties.put(proprty.getName(), proprty);
        }
        
        Object beanObj = defaultObjectSupplier.getObject(beanClass);
        
        Iterator<?> elements = om.getChildren();
        while (elements.hasNext()) {
        	Object child = elements.next();
            OMElement parts;
            if (child instanceof OMElement) {
                parts = (OMElement) child;
            } else {
                continue;
            }
            
            PropertyDescriptor propertyDesc = properties.get(parts.getLocalName());
            //如果class中不存在指定的属性跳过
            if(propertyDesc==null){
            	continue;
            }
            
            OMElement refElement = getMultirefOMElement(parts);
            if(refElement!=null){
            	parts = refElement;
            }
            
            //解析
            Class<?> javatype = propertyDesc.getPropertyType();
            Object partObj = null;
            
        	if (SimpleTypeMapper.isSimpleType(javatype)) {
        		partObj = SimpleTypeMapper.getSimpleTypeObject(javatype, parts);
            } else if (SimpleTypeMapper.isCollection(javatype)) {
            	MultirefCollectionAnnotation info = beanClass.getDeclaredField(propertyDesc.getName()).getAnnotation(MultirefCollectionAnnotation.class);
            	partObj = getCollection(parts,info.value());
            } else {
            	partObj = deserialize(parts,javatype);
            }
            
            
            //setter
            Object[] parms = new Object[]{partObj};
            Method writeMethod = propertyDesc.getWriteMethod();
            if (writeMethod != null) {
                writeMethod.setAccessible(true);
                writeMethod.invoke(beanObj, parms);
            }
        }
    	
    	return (T)beanObj;
    }
    
    @SuppressWarnings("unchecked")
	private <T> Collection<T> getCollection(OMElement om,Class<?> clazz) throws Exception{
    	List<T> list = new ArrayList<T>();
    	Iterator<?> iterator = om.getChildren();
    	while(iterator.hasNext()){
    		Object child = iterator.next();
            OMElement parts;
    		if(child instanceof OMElement){
    			parts = (OMElement)child;
    		}else{
    			continue;
    		}
    		
    		list.add((T)deserialize(parts,clazz));
    	}
    	
    	return list;
    }
    
}
