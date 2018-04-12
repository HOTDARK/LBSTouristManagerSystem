/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.client;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.databinding.typemapping.SimpleTypeMapper;
import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.axis2.databinding.utils.ConverterUtil;
import org.apache.axis2.engine.DefaultObjectSupplier;
import org.apache.commons.lang.StringUtils;

import com.hd.sfw.webservice.WebServiceClient;
import com.hd.sfw.webservice.client.annotations.SOAPElement;
import com.hd.sfw.webservice.client.annotations.SOAPParam;
import com.hd.sfw.webservice.client.param.HttpHeaders;
import com.hd.sfw.webservice.model.WebServiceConfigure;

/**
 * 使用axis2 axiom方式调用webservice，入参args[]={请求参数,HTTP头，SOAP头}
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-10-15 上午11:34:23
 */
public class AXIOMClient implements WebServiceClient{
	
	private static Map<Class<?>, List<ParameterField>> requestCache = new ConcurrentHashMap<Class<?>, List<ParameterField>>();
	
	private ServiceClient getServiceClient(WebServiceConfigure configure,Object[] args) throws AxisFault{
		
		ServiceClient client = null;
		try {
			client = new ServiceClient(Axis2ConfigurationContextHolder.context,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
			
		Options options = client.getOptions();
		
		options.setCallTransportCleanup(true);
		
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
	    //设置soapaction
	    options.setAction(configure.getSoapAction());
	    //调用的webservice地址
	    options.setTo(new EndpointReference(configure.getUrl()));
	    //设置超时时间
	    options.setTimeOutInMilliSeconds(configure.getTimeout());
		    
		
		HttpHeaders httpHeaders = null;
//        SoapHeaders soapHeaders = null;
		
		if(args!=null){
			if(args.length>1){
        		httpHeaders = (HttpHeaders)args[1];	
        	}
        	
//        	if(args.length>2){
//        		soapHeaders = (SoapHeaders)args[2];	
//        	}
		}
		
	    //添加自定义http头
	    if(httpHeaders!=null){
			//TODO 因为client是可复用的所以这样设置http头在多线程下可能导致问题，应重新考虑实现方式
	    	client.getOptions().setManageSession(true);
	    	client.getOptions().setProperties(httpHeaders);
	    }
	    
	    //TODO 添加soap头
	    //sender.addHeader(header);
	    
	    return client;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T call(WebServiceConfigure configure, Object[] args,Class<T> responseType) throws Exception {
		
        Object request = null;
        
        if(args!=null){
        	if(args.length>0){
        		request = args[0];	
        	}
        }
        
        ServiceClient sender = getServiceClient(configure,args);
        
        OMElement om = this.getOMElement(null,configure.getMethodName(),request,true,false);
        OMElement result = sender.sendReceive(om);
        
        if(responseType==null){
        	return (T)result;
        }
        
        List<OMElement> oms = new ArrayList<OMElement>();
        Iterator<?> iterator = result.getChildren();
        while(iterator.hasNext()){
        	Object obj = iterator.next();
        	if(obj instanceof OMElement){
        		oms.add((OMElement)obj);
        	}
        }
        
        //重建特定结构使得可以用BeanUtil.deserialize进行反序列化
        if(oms.size()>1){
        	OMFactory factory = OMAbstractFactory.getOMFactory();
        	result = factory.createOMElement(result.getLocalName(),result.getDefaultNamespace());
        	
        	OMElement rs = factory.createOMElement("return",null);
        	
        	for(OMElement oe : oms){
        		rs.addChild(oe);
        	}
        	
        	result.addChild(rs);
        }
        
        return (T)(BeanUtil.deserialize(result, new Object[]{responseType}, new DefaultObjectSupplier())[0]);
	}
	
	private OMElement getOMElement(OMElement parent,String method,Object request,boolean top,boolean include){
		if(request!=null){
			OMFactory factory = OMAbstractFactory.getOMFactory();
			
			//获取和缓存相关定义
        	Class<?> clazz = request.getClass();
        	SOAPElement element = clazz.getAnnotation(SOAPElement.class);
        	OMNamespace ns = null;
        	OMElement om = null;
        	
        	if(element!=null){
        		ns = factory.createOMNamespace(element.namespace(),BeanUtil.getUniquePrefix());
        	}
        	
        	if(parent==null){
        		om = factory.createOMElement(method,ns);	
        	}else{
        		if(include){
        			om = factory.createOMElement(method,parent.getNamespace());
        		}else{
        			om = factory.createOMElement(method,null);
        		}
        		
        		if(ns!=null){
        			om.declareNamespace(ns);	
        		}
        		
        	}
        	
        	List<ParameterField> list = requestCache.get(clazz);
        	if(list==null){
        		list = getFields(clazz);
        		requestCache.put(clazz, list);
        	}
        	
        	//填充
        	for(ParameterField field : list){
        		Object val = field.getValue(request);
        		if(val==null){
        			continue;
        		}
        		
        		if(field.isSimpleType()){
    				OMElement child = factory.createOMElement(field.getName(),field.isInclude()?ns:null);   
        			child.addChild(factory.createOMText(child,ConverterUtil.convertToString(val)));
        			om.addChild(child);
        		}else{
    				OMElement child = getOMElement(om,field.getName(),val,false,field.isInclude());
    				if(field.isInclude()){
    					child.setNamespace(ns);
    				}
    				om.addChild(child);
        		}
        	}
        	
        	return om;
        	
		}
		
		return null;
	}
	
	/**
	 * 反射获取bean中所有field定义
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	private List<ParameterField> getFields(Class<?> clazz){
		List<ParameterField> list = new ArrayList<ParameterField>();
		
		while(clazz!=null){
			Field[] fields = clazz.getDeclaredFields();
			for(Field field : fields){
				Method method = null;
				try {
					method = clazz.getMethod("get"+StringUtils.capitalize(field.getName()), new Class[]{});
				} catch (SecurityException e) {
					//忽略
				} catch (NoSuchMethodException e) {
					//忽略
				}
				
				if(method!=null){
					SOAPParam param = field.getAnnotation(SOAPParam.class);
					ParameterField parameterField = new ParameterField();
					parameterField.setSimpleType(SimpleTypeMapper.isSimpleType(field.getType()));
					parameterField.setMethod(method);
					if(param==null){
						parameterField.setName(field.getName());
						parameterField.setOrder(Integer.MAX_VALUE);
					}else{
						parameterField.setName(StringUtils.isEmpty(param.name())?field.getName():param.name());
						parameterField.setOrder(param.order());
						parameterField.setInclude(param.include());
					}
					
					list.add(parameterField);
				}
			}
			
			clazz = clazz.getSuperclass();
		}
		
		Collections.sort(list);
		
		return list;
	}
	
	/**
	 * 用于存放反射获取的属性和对应的get方法
	 * @version	0.0.1
	 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
	 * @date	2014-10-15 下午2:38:32
	 */
	private static class ParameterField implements Comparable<ParameterField>{
		private String name;
		
		private Method method;
		
		private int order;
		
		private boolean isSimpleType;
		
		private boolean include;
		
		/**
		 * @return the isInclude
		 */
		public boolean isInclude() {
			return include;
		}

		/**
		 * @param isInclude the isInclude to set
		 */
		public void setInclude(boolean include) {
			this.include = include;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}


		/**
		 * @param method the method to set
		 */
		public void setMethod(Method method) {
			this.method = method;
		}

		/**
		 * @return the order
		 */
		public int getOrder() {
			return order;
		}

		/**
		 * @param order the order to set
		 */
		public void setOrder(int order) {
			this.order = order;
		}

		/**
		 * @return the isSimpleType
		 */
		public boolean isSimpleType() {
			return isSimpleType;
		}

		/**
		 * @param isSimpleType the isSimpleType to set
		 */
		public void setSimpleType(boolean isSimpleType) {
			this.isSimpleType = isSimpleType;
		}

		@Override
		public int compareTo(ParameterField o) {
			if(order>o.getOrder()){
				return 1;
			}else if(order==o.getOrder()){
				return 0;
			}else{
				return -1;
			}
		}
		
		public Object getValue(Object instance){
			try {
				return method.invoke(instance, new Object[]{});
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
}
