package com.hd.sys.core.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hd.sfw.log.trace.LogUtils;
import com.hd.sfw.log.trace.model.SysLogOper;
import com.hd.sys.core.context.FrameworkContext;
import com.hd.sys.core.online.OnlineSession;
import com.hd.sys.core.online.OnlineUserManager;
import com.hd.sys.core.utils.DontLog;
import com.hd.sys.core.utils.ExcludeAuthority;
import com.hd.sys.entity.SysUser;

/**
 * springmvc拦截器，用于权限验证、请求日志记录等
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-19 下午2:48:41
 */
public class DefaultHandlerInterceptor implements HandlerInterceptor {
	
	private OnlineUserManager onlineUserManager;
	
	public void setOnlineUserManager(OnlineUserManager onlineUserManager) {
		this.onlineUserManager = onlineUserManager;
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LogUtils.clearLogId();
		
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView!=null){
			//把项目路径设置到request中
			request.setAttribute("BASE_PATH", request.getContextPath());
			request.setAttribute("PROJECT_NAME", FrameworkContext.PROJECT_NAME);
		}
		
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if( !(handler instanceof HandlerMethod) ){
			return true;
		}
		
		HandlerMethod hdl=(HandlerMethod) handler;
		
		//如果方法标记不需验证权限则直接进入否则验证是否具备相应权限
		if(hdl.getMethodAnnotation(ExcludeAuthority.class)==null){
			//否则验证是否登录以及是否具备相应权限
			OnlineSession onlineSession = onlineUserManager.getCurrentOnlineSession();
			if(onlineSession==null){
				onlineSession = rememberLogin(request, response);
				if(onlineSession!=null){
					//throw new UnauthorizedException("您还未登陆，请先登陆系统.");
					onlineSession.update();
				}
			}
			
			if(hdl.getMethodAnnotation(DontLog.class)==null){
				//记录操作日志
				LogUtils.info(new SysLogOper("访问:"+request.getServletPath(), request.getServletPath(),"action"));
			}
			
		}
		
		return true;
	}

	/**
	 * 记住登录
	 * @param request
	 * @param response
	 * @return 如果从cookie中登录成功则返回OnlineSession对象，否则返回null
	 */
	private OnlineSession rememberLogin(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession(true);
		@SuppressWarnings("unchecked")
		Map<String, Object> map=(Map<String, Object>) session.getAttribute("sessionMap");
		if (map!=null&&map.size()>0) {
			SysUser user =(SysUser) map.get("userInfo");
			if (user!=null) {
				OnlineSession onlineSession = onlineUserManager.login(user, request.getRemoteAddr());
				onlineSession.setSession(session);
				//保存自定义session id到HttpSession中
				session.setAttribute(FrameworkContext.SYS_USER_FLAG, onlineSession.getId());
				return onlineSession;
			}
		}else{
			return null;
		}
		return null;
	}
	
	
}
