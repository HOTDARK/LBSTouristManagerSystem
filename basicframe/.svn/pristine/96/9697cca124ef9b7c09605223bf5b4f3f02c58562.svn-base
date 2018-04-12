/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.webservice.utils;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.hd.sfw.log.trace.LogUtils;
import com.hd.sfw.webservice.WebServiceManager;
import com.hd.sfw.webservice.exception.IwdWebServiceException;
import com.hd.sfw.webservice.model.WebServiceConfigure;
import com.hd.sfw.webservice.model.WebServiceLog;

/**
 * AXIS2 stub 调用模板,应对某些特殊的无法采用通用方法调用的接口，
 * 在使用axis2 adb方式生成代码调用时为达到记录日志信息规范化调用方式，实现的模板方法工具。<br>
 * 使用本工具时应指定WebServiceManager实例。
 * @version	0.0.1 
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-10-17 上午9:53:38
 */
public class StubTemplateUtils{
	private final static Logger log = Logger.getLogger(StubTemplateUtils.class);
	
	private static WebServiceManager webServiceManager;
	
	/**
	 * 模板方法，调用axis2生成的stub接口
	 * @param wid
	 * @param request
	 * @param callback
	 * @return
	 * @throws IwdWebServiceException
	 */
	public static <MyRequset,MyResponse,StubRequest,StubResponse> MyResponse getResponse(String wid,MyRequset request,CallBack<MyRequset,MyResponse,StubRequest,StubResponse> callback) throws IwdWebServiceException{
		
		if(StringUtils.isEmpty(wid)){
			throw new IllegalArgumentException("webservice标识wid不能为空");
		}
		
		WebServiceConfigure wsci = webServiceManager.getConfigure(wid);
		if(wsci==null){
			throw new NullPointerException();
		}
		
		wsci.increment();
		
		//接口输入参数
		String input = request.toString();
		
		//接口返回参数
		String output = "";
		
		log.debug("接口名称:"+wsci.getName());
		log.debug("接口输入参数:"+input);
		
		Date startTime = new Date();
	    int wState = WebServiceLog.STATE_NORMAL;
	    
		try{
			StubRequest stubRequest = callback.request(request);
			StubResponse stubResponse = callback.execute(stubRequest);
			MyResponse myResponse = callback.response(stubResponse);
			
			if(null!=myResponse){
	    		output = myResponse.toString();
	    		log.debug("接口输出:"+output);
	    	}else{
	    		log.debug("接口输出:null");
	    	}
	    	
			return myResponse;
		}catch (Exception e) {
			output = ExceptionUtils.getStackTrace(e);
	    	wState = WebServiceLog.STATE_ERROR;
	    	log.error(e.getMessage(),e);
	    	throw new IwdWebServiceException("接口调用错误",e);
		}finally{
			WebServiceLog wsl = new WebServiceLog();
	    	wsl.setWid(wsci.getId());
	    	wsl.setWname(wsci.getName());
	    	wsl.setInput(input);
	    	wsl.setOutput(output);
	    	wsl.setStartTime(startTime);
	    	wsl.setEndTime(new Date());
	    	wsl.setState(wState);
	    	
	    	LogUtils.info(wsl);
		}
	}
	
	/**
	 * 
	 * 模板接口
	 * @version	0.0.1
	 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
	 * @date	2014-10-17 上午10:34:48
	 * @param <MyRequset> 入参
	 * @param <MyResponse> 出参
	 * @param <StubRequest> stub生成代码中的入参
	 * @param <StubResponse> stub生成代码中的出参
	 */
	public static interface CallBack<MyRequset,MyResponse,StubRequest,StubResponse>{
		
		/**
		 * 把入参转换成stub生成代码的入参
		 * @param request
		 * @return
		 */
		public StubRequest request(MyRequset request);
		
		/**
		 * 把stub出参转换成出参
		 * @param response
		 * @return
		 */
		public MyResponse response(StubResponse response);
		
		/**
		 * 调用stub生成代码中的接口方法
		 * @param request
		 * @return
		 * @throws Exception
		 */
		public StubResponse execute(StubRequest request) throws Exception;
		
	}

	/**
	 * 指定WebServiceManager实例
	 * @param webServiceManager the webServiceManager to set
	 */
	public static void setWebServiceManager(WebServiceManager webServiceManager) {
		StubTemplateUtils.webServiceManager = webServiceManager;
	}

}
