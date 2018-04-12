package com.hd.api.server.intercept;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.api.server.entity.AbsQueryBean;
import com.hd.api.server.entity.WebServiceNorth;
import com.hd.api.server.log.BSASOAPInfo;
import com.hd.api.server.service.WebServiceNorthService;

/**
 * 北线接口日志
 */
@Component
public class QueryBSASOAPLOGInterceptor {
	
	@Autowired
	private WebServiceNorthService webServiceNorthService;
	
	public Object invoke(ProceedingJoinPoint point) throws Throwable{
		WebServiceNorth log = new WebServiceNorth(); 
		log.setStartTime(new Date());
		//获取定义的接口名称
		Method method = ((MethodSignature)point.getSignature()).getMethod();
		Object[] args = point.getArgs();
		if(args!=null&&args.length>0){
			if(args[0] instanceof AbsQueryBean){
				log.setInput(((AbsQueryBean)args[0]).toString());
			}else{
				log.setInput(args[0]+"");
			}
		}else{
			log.setInput("无入参");
		}
		//执行方法获取返回
		Object obj = null;
		try{
			obj = point.proceed();
			log.setOutput(obj==null?"返回参数为null":obj.toString());
		}catch (Exception e) {
			e.printStackTrace();
			log.setOutput(e.getMessage());
		}finally{
			BSASOAPInfo info = method.getAnnotation(BSASOAPInfo.class);
			// 如果方法上没有加BSASOAPInfo注解，则放弃记录日志
			if (info!=null && StringUtils.isNotBlank(info.value())) {
				log.setWname(info.value());
				log.setEndTime(new Date());
				//日志入库
				webServiceNorthService.addWebServiceLog(log);
			}
		}
		return obj;
	}

}
