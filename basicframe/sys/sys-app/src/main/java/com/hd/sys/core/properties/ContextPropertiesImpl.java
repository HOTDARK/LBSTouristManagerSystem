package com.hd.sys.core.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.sys.entity.SysProp;
import com.hd.sys.service.base.BaseService;

/**
 * @author sunjian
 * @version V1.0, 2013-5-16 下午03:07:12
 */
public class ContextPropertiesImpl implements ContextProperties {
	
	private final static Logger log = Logger.getLogger(ContextPropertiesImpl.class);

	//存放所有持久化的Property
	private List<SysProp> properties = new ArrayList<SysProp>();
	
	//存放系统的property
	private Map<String, Object> contextProperties = new ConcurrentHashMap<String, Object>();
	
	//存放类型处理器
	private Map<String, TypeHandler> handlers = new HashMap<String, TypeHandler>();
	
	private BaseService<SysProp, String> sysPropService;
	
	/**
	 * @param sysPropService the sysPropService to set
	 */
	public void setSysPropService(BaseService<SysProp, String> sysPropService) {
		this.sysPropService = sysPropService;
	}

	public void setHandlers(Map<String, TypeHandler> handlers) {
		log.info("初始化框架上下文属性类型处理器");
		this.handlers = handlers;
	}

	/**
	 * 初始化
	 */
	public void init(){
		log.info("初始化框架上下文属性环境变量");
		try {
			properties = sysPropService.findByCondition(new SysProp());
			for(SysProp p :properties){
				this.put(p.getKey(), this.convert(p.getType(),p.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过key obj生成property对象
	 * @param key
	 * @param obj
	 * @return
	 */
	private SysProp handle(String key,Object obj){
		SysProp property = new SysProp();
		property.setKey(key);
		property.setState(SysProp.STATE_READWRITE);
		
		String type = obj.getClass().getSimpleName();
		
		TypeHandler handler = handlers.get(type);
		if(handler==null){
			type = "Object";
			handler = handlers.get(type);
		}
		
		property.setType(type);
		property.setValue(handler.toStr(obj));
		
		return property;
		
	}
	
	@Override
	public Object convert(String type, String content) {
		TypeHandler handler = handlers.get(type);
		if(handler==null){
			throw new IllegalArgumentException("类型处理器不存在:"+type);
		}
		
		return handler.toObj(content);
	}
	
	
	private Object get(String key){
		//如果系统中包含对应key则直接返回否则在java环境变量中查询
		return contextProperties.containsKey(key)?contextProperties.get(key):System.getProperty(key);
	}
	
	@Override
	public boolean getBoolean(String key){
		return (Boolean)get(key);
	}
	
	@Override
	public Integer getInt(String key){
		return (Integer)get(key);
	}
	
	@Override
	public String getString(String key){
		return (String)get(key);
	}

	@Override
	public Object getObject(String key) {
		return get(key);
	}
	
	@Override
	public long getLong(String key) {
		return (Long)get(key);
	}

	@Override
	public Map<String, Object> getProperties() {
		return contextProperties;
	}
	
	@Override
	public void put(String key, Object obj) {
		contextProperties.put(key, obj);
	}
	
	@Override
	public void save(String key,Object obj,int state) {
		if(StringUtils.isEmpty(key)){
			throw new NullPointerException();
		}
		
		SysProp old = this.findProperty(key);
		
		SysProp property = this.handle(key, obj);
		if(old==null){
			//添加
			try {
				sysPropService.save(property);
				properties.add(property);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			//在读写状态才可以更新
			if(old.getState()!=SysProp.STATE_READONLY){
				SysProp sp = new SysProp();
				sp.setKey(key);
				sp.setValue(property.getValue());
				sp.setType(property.getType());
				try {
					sysPropService.updateById(sp);
					properties.remove(old);
					properties.add(property);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				throw new IllegalArgumentException("Property为ReadOnly不可修改");
			}
		}
		
		//更新内存
		put(key,obj);
	}

	@Override
	public void remove(String key) {
		contextProperties.remove(key);
	}

	@Override
	public void delete(String key) {
		SysProp property = this.findProperty(key);
		
		if(property!=null){
			if(SysProp.STATE_READWRITE==property.getState()){
				//删除数据库中的内容
				try {
					sysPropService.deleteById(key);
					//移除内存中的内容
					contextProperties.remove(key);
					properties.remove(property);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				throw new IllegalArgumentException("Property不可删除");
			}
			
		}else{
			//如property为空则代表需要删除的是一个临时全局变量 直接从map中移除
			contextProperties.remove(key);
		}
		
	}
	
	@Override
	public SysProp findProperty(String key){
		for(SysProp p : properties){
			if(p.getKey().equals(key)){
				return p;
			}
		}
		
		return null;
	}

}
