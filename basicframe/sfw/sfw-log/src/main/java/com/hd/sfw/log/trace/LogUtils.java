package com.hd.sfw.log.trace;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.sfw.log.trace.model.SysLogOper;

/**
 * 系统日志记录工具类
 * 所有日志实体类都应该是LogInfo的子类
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-18 上午11:33:21
 */
public final class LogUtils {
	
	private final static Logger LOGGER = Logger.getLogger(LogUtils.class);
	
	protected static LogPersisterExecutor logPersisterExecutor;
	
	protected static LogIdApplier logIdApplier;
	
	//日志Id
	private static ThreadLocal<LogId> logIdThreadLocal = new ThreadLocal<LogId>();
	
	public static void info(SysLogOper log){
		if(log==null){
			throw new NullPointerException();
		}
		
		//设置日志级别相关参数
		log.setLevel(SysLogOper.INFO);
		persistence(log);
		
	}
	
	/**
	 * 进行日志记录
	 * @param info
	 */
	private static void persistence(SysLogOper log){
		
		//设置公共参数
		log.setLogDate(new Date());
		log.setLogID(getLogId());
		
		//如果是LogInfo的子类且设置为自动生成message
		if(log.getClass().getSuperclass()==SysLogOper.class&&log.isAutoGenMsg()){
			log.setMessage(getChildMessage(log));
		}
		
		if(logPersisterExecutor!=null){
			logPersisterExecutor.add(log);
		}
		
		
	}
	
	/**
	 * 获取LogInfo子类中所有定义属性的字符串
	 * @param info
	 * @return
	 */
	public static String getChildMessage(SysLogOper info){
		if(info==null){
			throw new NullPointerException();
		}
		
		StringBuilder sb = new StringBuilder();
		Class<?> clazz = info.getClass();
		if(clazz.getSuperclass()==SysLogOper.class){
			
			for(Field field : clazz.getDeclaredFields()){
				
				//如果标记为不记录则跳过
				if((field.getModifiers()&Modifier.STATIC)>0||field.getAnnotation(ExcludeLog.class)!=null){
					continue;
				}
				
				//字段名
				String name = field.getName();
				
				//属性对应字段的getter返回值
				Object obj = null;
				try{
					Method method = clazz.getMethod("get"+StringUtils.capitalize(name), new Class[]{});
					if(method==null){
						continue;
					}
					
					obj = method.invoke(info, new Object[]{});
				}catch (Exception e) {
					LOGGER.warn(e.getMessage(),e);
					continue;
				}
				
				//属性的字符串值
				String value = "";
				
				if(obj instanceof Date){
					//Date类型转换成字符串
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = sdf.format(obj);
				}else if(obj instanceof String){
					//如果是字符串类型可能包含为xml格式为避免歧义加入CData
					value = "<![CDATA["+obj+"]]>";
				}else{
					value = String.valueOf(obj);
				}
				
				sb.append("<").append(name).append(">").append(value).append("</").append(name).append(">\r\n");
			}
			
		}else{
			return "";
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 获取当前线程的日志id
	 * @return 
	 */
	public static LogId getLogId(){
		LogId logId = logIdThreadLocal.get();
		if(logId==null){
			logId = new LogId();
			if(logIdApplier!=null){
				logIdApplier.apply(logId);
			}
			logIdThreadLocal.set(logId);
		}
		return logId;
	}
	
	/**
	 * 在业务逻辑中存在创建线程的情况，
	 * 如果需要在创建的线程中记录日志并且需要知道从属关系则需要设置线程创建者的日志id
	 * @param id
	 * @see 
	 */
	public static void setParentLogId(LogId logId){
		LogId currentLogId = getLogId();
		currentLogId.setUsername(logId.getUsername());
		currentLogId.setPid(logId.getId());
	}
	
	/**
	 * 因为容器为了提高性能处理http的线程不会在请求结束时就被回收<br>
	 * 可能出现一个http处理线程连续处理多个请求的情况 此时使用LocalThread需要注意不同请求的线程可能是同一个。<br>
	 *  本方法的目的为清空logLocalThread 在适当的时机调用此方法以规避上述问题,
	 *  本方法可能需要调用的地方为,SpringMVC，FLEX，webservice服务等http请求开始的地方;本质来讲凡是用到线程池的地方都应该在适当时机调用此方法。
	 */
	public static void clearLogId(){
		logIdThreadLocal.remove();
	}

	
}
