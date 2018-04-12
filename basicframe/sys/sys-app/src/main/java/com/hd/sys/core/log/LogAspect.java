package com.hd.sys.core.log;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.sfw.core.utils.IpUtils;
import com.hd.sys.core.consts.SessionConst;
import com.hd.sys.core.context.ContextHolderUtils;
import com.hd.sys.entity.SysLogFunc;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysLogFuncService;

/**
 * 切面方法入口类
 * applicationContext.xml需要配置通过aspectj方式 管理日志 交由cglib代理
 * @author somnuscy
 *
 */
@Component
@Aspect // 该注解标示该类为切面类
public class LogAspect {
       
	@Autowired
    private SysLogFuncService sysLogService;   
	
    /**
     * 标注该方法体为后置通知，当目标方法执行成功后执行该方法体   
     * @param jp
     * @param rl
     */
	@AfterReturning("within(com.hd..*) && @annotation(rl)")   
    public void addLogSuccess(JoinPoint jp, LogOpt rl){
        SysUser sysUser = getLoginUser();
        if(sysUser != null){
        	String classPath = jp.getSignature().getDeclaringTypeName();// 获取目标类路径
        	String methodName = jp.getSignature().getName();// 获取目标方法签名
        	SysLogFunc sysLog = new SysLogFunc();
        	sysLog.setUserId(sysUser.getUserId());
        	sysLog.setOrgId(sysUser.getOrgId());
        	sysLog.setUserName(sysUser.getUserName());
        	sysLog.setClassFunction(classPath.concat(".").concat(methodName));
        	sysLog.setLogLevel(rl.level());
        	sysLog.setDescription(rl.desc());
        	sysLog.setParentDesc(rl.parentDesc());
        	sysLog.setIp(getIpAddr(jp.getArgs()));// 获取请求IP
        	sysLog.setType(1);
        	sysLog.setCreateTime(new Date());
    		sysLogService.insertLog(sysLog);
        }
    }
  
    /**
     * 标注该方法体为异常通知，当目标方法出现异常时，执行该方法体
     * @param jp
     * @param rl
     * @param ex
     */
	@AfterThrowing(pointcut="within(com.hd..*) && @annotation(rl)", throwing="ex")   
    public void addLogException(JoinPoint jp, LogOpt rl, Exception ex) { 
        SysUser sysUser = getLoginUser();
        if(sysUser != null){
        	String classPath = jp.getSignature().getDeclaringTypeName();// 获取目标类路径
        	String methodName = jp.getSignature().getName();// 获取目标方法签名
        	SysLogFunc sysLog = new SysLogFunc();
        	sysLog.setUserId(sysUser.getUserId());
        	sysLog.setOrgId(sysUser.getOrgId());
        	sysLog.setUserName(sysUser.getUserName());
        	sysLog.setClassFunction(classPath.concat(".").concat(methodName));
        	sysLog.setLogLevel(rl.level());
        	sysLog.setDescription(rl.desc() + ex.toString());
        	sysLog.setParentDesc(rl.parentDesc());
        	sysLog.setIp(getIpAddr(jp.getArgs()));// 获取请求IP
        	sysLog.setType(2);
        	sysLog.setCreateTime(new Date());
    		sysLogService.insertLog(sysLog);
        }
    }
	
	/**
     * 获取请求IP
     * @param args
     * @return
     */
    private String getIpAddr(Object[] args) {
    	HttpServletRequest request = null;
    	for (int i = 0; i < args.length; i++) {
    		try {
    			request = (HttpServletRequest) args[i];
	    		if (request != null) {
	    			return IpUtils.getIpAddr(request);
				}
			} catch (ClassCastException e) {
				continue;
			}
		}
    	return null;
    }
    
    /**
     * 获取登录用户信息
     * @return
     */
    private SysUser getLoginUser(){
    	@SuppressWarnings("unchecked")
    	Map<String, Object> map = (Map<String, Object>) ContextHolderUtils.getSession().getAttribute(SessionConst.SESSION_MAP);// 用户session
    	if (map == null) {
			return null;
		}
        return (SysUser) map.get(SessionConst.SESSION_MAP_USER);
    }
    
}