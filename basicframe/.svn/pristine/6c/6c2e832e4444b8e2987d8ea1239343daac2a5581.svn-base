package com.hd.sys.core.online;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hd.sys.core.context.FrameworkContext;
import com.hd.sys.entity.SysUser;

/**
 * 
 * @author somnuscy
 *
 */
@Component
public class OnlineUserManagerImpl implements OnlineUserManager{
	
	private final static Logger log = Logger.getLogger(OnlineUserManagerImpl.class);
	//用户在线列表 <用户名,OnlineSysUser>
	private Map<String, OnlineSysUser> onlines = new ConcurrentHashMap<String, OnlineSysUser>();
	//session列表 <uuid,OnlineSession>
	private Map<String, OnlineSession> sessions = new ConcurrentHashMap<String, OnlineSession>();
	
	@Override
	public SysUser getCurrentUser(){
		OnlineSysUser onlineSysUser = this.getCurrentOnlineUser();
		return onlineSysUser==null?null:onlineSysUser.getUser();
	}
	
	@Override
	public OnlineSysUser getCurrentOnlineUser(){
		OnlineSession onlineSession = getCurrentOnlineSession();
		return onlineSession==null?null:onlineSession.getOnlineSysUser();
	}
	
	@Override
	public OnlineSession getCurrentOnlineSession() {
		//从springmvc和flex blazeDS发起的请求
		ServletRequestAttributes sra =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes());
		if(sra!=null){
			HttpSession session = sra.getRequest().getSession();
			String sid = (String)session.getAttribute(FrameworkContext.SYS_USER_FLAG);
			if(sid!=null){
				return sessions.get(sid);
			}
		}else{
			//TODO:如果sra为空说明请求不是从springmvc发起 此时将从其他地方获取当前用户
			//从webservice发起的请求
		}
		
		//排除之上的情况 则是由系统自身发起的线程 返回system
		
		return null;
	}
	
	@Override
	public List<OnlineSysUser> getOnlineUser(){
		return new ArrayList<OnlineSysUser>(onlines.values());
	}

	@Override
	public OnlineSession login(SysUser user,String from){
		OnlineSysUser onlineSysUser = onlines.get(user.getUserAccount());
			if(onlineSysUser==null){
				onlineSysUser = new OnlineSysUser();
				onlines.put(user.getUserAccount(), onlineSysUser);
			}
			//产生session
			String sid = UUID.randomUUID().toString();
			OnlineSession session = new OnlineSession();
			session.setFrom(from);
			session.setId(sid);
			session.setOnlineSysUser(onlineSysUser);
			onlineSysUser.setUser(user);
			onlineSysUser.getSessions().put(sid, session);
			//新登入清除一次以便修改后的权限能即时生效
			onlineSysUser.clearRights();
			//登陆
			sessions.put(sid, session);
			log.info("用户登入:"+user.getUserAccount()+";ID:"+sid);
			return session;
	}

	@Override
	public void logoutByUsername(String username) {
		if(StringUtils.isNotEmpty(username)&&!"system".equals(username)){
			OnlineSysUser user = onlines.remove(username);
			if(user!=null){
				for(String sid :user.getSessions().keySet()){
					OnlineSession onlineSession = sessions.remove(sid);
					if(onlineSession!=null){
						onlineSession.invalidate();
					}
				}
				log.info("用户登出:"+username);
			}
			
		}
	}
	
	@Override
	public synchronized void logout(String sid){
		logout(sid,true);
	}

	@Override
	public void logout(String sid, boolean invalidate) {
		if(StringUtils.isNotEmpty(sid)){
			OnlineSession onlineSession = sessions.remove(sid);
			if(onlineSession!=null){
				if(onlineSession.getOnlineSysUser().getSessions().size()==1){
					onlines.remove(onlineSession.getOnlineSysUser().getUser().getUserAccount());
				}else{
					onlineSession.getOnlineSysUser().getSessions().remove(sid);
				}
				if(invalidate){
					onlineSession.invalidate();
				}
				log.info("用户登出:user="+onlineSession.getOnlineSysUser().getUser().getUserAccount()+",sid="+sid);
			}
		}
	}
	
}
