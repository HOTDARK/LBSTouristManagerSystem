package com.hd.sfw.webservice;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.hd.sfw.log.trace.LogUtils;
import com.hd.sfw.webservice.exception.IwdWebServiceException;
import com.hd.sfw.webservice.model.WebServiceConfigure;
import com.hd.sfw.webservice.model.WebServiceLog;
import com.hd.sfw.webservice.model.enums.WebServiceType;

/**
 * webservice 管理器实现
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-10 下午2:42:38
 */
public class WebServiceManagerImpl implements WebServiceManager {
	private static final Logger logger = Logger.getLogger(WebServiceManagerImpl.class);
	
	//客户端实现
	private Map<WebServiceType, WebServiceClient> clients;
	
	//系统初始化接口基本信息
	private static Map<String, WebServiceConfigure> configs;
	
	private WebServiceConfigureLoader configureLoader;
	
	@Override
	public <T> T call(Map<String, Object> parmas, String wid, Object[] args, Class<T> responseType) throws IwdWebServiceException{
		if(StringUtils.isEmpty(wid)){
			throw new IllegalArgumentException("webservice标识wid不能为空");
		}
		
		WebServiceConfigure wsci = configs.get(wid);
		if(wsci==null){
			logger.fatal("未查询到接口配置信息：wid="+wid);
			throw new NullPointerException();
		}
		
		if (parmas!=null&&parmas.size()>0) {
			if (parmas.get(WebServiceConfigure.SOCKETTYPEKEY)!=null) {
				wsci.setSocketType(parmas.get(WebServiceConfigure.SOCKETTYPEKEY).toString());
			}
		}
		
		WebServiceClient client = clients.get(wsci.getWebServiceType());
		if(client==null){
			throw new IwdWebServiceException("指定的webservice访问类型不存在：name="+wsci.getName()+",id="+wid+",type="+wsci.getWebServiceType());
		}
		
		wsci.increment();
		
		//接口输入参数
		String input = getInputStr(args);
		
		//接口返回参数
		String output = "";
		
		logger.debug("接口名称:"+wsci.getName());
		logger.debug("接口输入参数:"+input);
		System.out.println("接口输入参数:"+input);
		
		Date startTime = new Date();
	    int wState = WebServiceLog.STATE_NORMAL;
	    
	    
		try{
			T obj = (T)client.call(wsci, args,responseType);
			
			if(null!=obj){
	    		output = obj.toString();
	    		System.out.println("接口输出:"+output);
	    		logger.debug("接口输出:"+output);
	    	}else{
	    		logger.debug("接口输出:null");
	    	}
	    	
	    	return obj;
		}catch (Exception e) {
			output = ExceptionUtils.getStackTrace(e);
	    	wState = WebServiceLog.STATE_ERROR;
	    	logger.error(e.getMessage(),e);
	    	throw new IwdWebServiceException("接口调用错误：name="+wsci.getName()+",id="+wid,e);
		}finally{
	    	WebServiceLog wsl = new WebServiceLog();
	    	wsl.setWid(wsci.getId());
	    	wsl.setWname(wsci.getName());
	    	wsl.setInput(input);
	    	wsl.setOutput(output);
	    	wsl.setStartTime(startTime);
	    	wsl.setEndTime(new Date());
	    	wsl.setState(wState);
	    	if (parmas != null && parmas.size() >= 1) {
	    		for (Map.Entry<String, Object> entry : parmas.entrySet()) {
		    		try {
		    			String fieldName = wsl.getClass().getDeclaredField(entry.getKey()).getName();
		    			Method m = wsl.getClass().getMethod("set".concat(fieldName.substring(0,1).toUpperCase().concat(fieldName.substring(1))), String.class);
		    			m.invoke(wsl, entry.getValue());
		    		} catch (Exception e) {
		    			logger.debug("接口日志自定义属性映射错误，可忽略：".concat(e.getMessage()), e);
		    		}
		        }
	    	}
	    	LogUtils.info(wsl);
	    }
	}
	
	private String getInputStr(Object[] args){
		if(args==null){
			return "";
		}
		return StringUtils.join(args,",");
	}
	
	@Override
	public void load() {
		Map<String, WebServiceConfigure> map = new HashMap<String, WebServiceConfigure>();
		List<WebServiceConfigure> list = configureLoader.getConfigs();
		for (WebServiceConfigure info : list) {
			map.put(info.getId(), info);
		}
		configs = map;
		
		logger.info("webservice配置信息加载完毕,（总共" + map.size() + "个）");
	}

	/**
	 * 设置webservice配置加载器
	 * @param configureLoader
	 */
	public void setConfigureLoader(WebServiceConfigureLoader configureLoader) {
		this.configureLoader = configureLoader;
	}
	
	/**
	 * 设置webservice客户端实现
	 * @param clients
	 */
	public void setClients(Map<WebServiceType, WebServiceClient> clients) {
		this.clients = clients;
	}
	
	@Override
	public WebServiceConfigure getConfigure(String wid) {
		return configs.get(wid);
	}

}