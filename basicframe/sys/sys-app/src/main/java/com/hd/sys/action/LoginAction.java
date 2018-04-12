package com.hd.sys.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.consts.SessionConst;
import com.hd.sys.core.context.FrameworkContext;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.core.online.OnlineSession;
import com.hd.sys.core.online.OnlineUserManager;
import com.hd.sys.core.utils.DontLog;
import com.hd.sys.core.utils.ExcludeAuthority;
import com.hd.sys.dao.SysOrgMapper;
import com.hd.sys.entity.SysOrg;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysOrgService;
import com.hd.sys.service.SysPermitService;
import com.hd.sys.service.SysUserService;

/**
 * 登录功能页面控制类  
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/index")
public class LoginAction {

	private static Logger logger = Logger.getLogger(LoginAction.class);
	
	@Autowired
	private SysPermitService sysPermitService;
	@Autowired
	private SysOrgService sysOrgService;
	@Autowired
	private OnlineUserManager onlineUserManager;
	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private SysUserService sysUserService;
	//默认头像
	private String DEFAULT_USER_PHOTO = "images/profile-pic.jpg";
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/forwardLogin")
	@DontLog
	public String toLogin (HttpServletRequest request, HttpServletResponse response) {
		return "login.jsp";
	}
	
	@RequestMapping("/welcome")
	@DontLog
	public String welcome (HttpServletRequest request, HttpServletResponse response) {
		return "welcome.jsp";
	}
	
	/**
	 * 用户登录验证
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param userAccount
	 * @param userPwd
	 * @param authorizeCode
	 * @return
	 */
	@LogOpt(level=FunLogConst.LEVEL_3, desc="用户登录", parentDesc=FunLogConst.DESC_SYSTEM_LOGIN)
	@RequestMapping("/userLogin")
	@ExcludeAuthority
	public String userLogin(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,
			@RequestParam(value="userAccount",defaultValue="",required=false)String userAccount,
			@RequestParam(value="userPwd",defaultValue="",required=false)String userPwd,
			@RequestParam(value="authorizeCode",defaultValue="",required=false)String authorizeCode) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =null;
		try {
			out = response.getWriter();
			String code = (String)httpSession.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if(!code.equals(authorizeCode)||StringUtils.isBlank(authorizeCode)) {
				 out.print("<script>alert('验证码输入不正确!');history.back(1)</script>");
				 out.flush();
				 out.close();
				 return null;
			}
			if(StringUtils.isBlank(userAccount)||StringUtils.isBlank(userPwd)) {
				out.print("<script>alert('帐号密码不能为空!');history.back(1)</script>");
				out.flush();
				out.close();
				return null;
			}
			SysUser user = sysUserService.getSysUser(userAccount);
			if(user != null && userPwd.equals(user.getUserPwd())){
				if(user.getState() != 1L) {
					out.print("<script>alert('该用户已被冻结!');history.back(1)</script>");
					out.flush();
					out.close();
					return null;
				}
			} else {
				out.print("<script>alert('帐号密码错误!');history.back(1)</script>");
				out.flush();
				out.close();
				return null;
			}
			//在框架中登陆用户
			OnlineSession onlineSession = onlineUserManager.login(user, request.getRemoteAddr());
			if(onlineSession!=null){
				SysUser sysUser = onlineSession.getOnlineSysUser().getUser();
				Map<String, String[]> roleInfo = sysPermitService.getPermissionByUserId(sysUser.getUserId());
				if (roleInfo!=null&&!roleInfo.isEmpty()) {
					// 获取顶级机构信息
					SysOrg topOrg = sysOrgMapper.findTopOrg();
					// 获取当前用户机构
					SysOrg sysOrg = sysOrgMapper.findByPK(sysUser.getOrgId());
					onlineSession.setSession(httpSession);
					httpSession.setAttribute(FrameworkContext.SYS_USER_FLAG, onlineSession.getId());
					Map<String, Object> param=new HashMap<String, Object>();
					param.put(SessionConst.SESSION_MAP_ORG, sysOrg);
					param.put(SessionConst.SESSION_MAP_USER, sysUser);
					// 查询用户关联的所有的角色的机构信息
					List<SysOrg> roleOrgs = sysOrgService.getOrgByUserrole(sysUser.getUserId());
					if (sysOrg.getOrgId().equals(topOrg.getOrgId())) {
						httpSession.setAttribute("roleOrgInfo", roleOrgs);
						httpSession.setAttribute("roleInfo", roleInfo);
					} else {
						// 如果不是顶级组织机构下的
						for (Iterator<SysOrg> iterator = roleOrgs.iterator(); iterator.hasNext();) {
							SysOrg org = iterator.next();
							if (org.getOrgId().equals(topOrg.getOrgId())) {
								iterator.remove();
							}
						}
						httpSession.setAttribute("roleOrgInfo", roleOrgs);
						for (String s : roleInfo.keySet()) {
							String[] tmp = roleInfo.get(s);
							if(StringUtils.isBlank(tmp[1])){
								continue;
							}
							if (tmp[1].contains(topOrg.getOrgId().toString()+",")) {
								tmp[1] = tmp[1].replaceAll(topOrg.getOrgId().toString()+",", "");
							}
							if (tmp[1].contains(topOrg.getOrgId().toString())) {
								tmp[1] = tmp[1].replaceAll(topOrg.getOrgId().toString(), "");
							}
						}
						httpSession.setAttribute("roleInfo", roleInfo);
					}
					httpSession.setAttribute(SessionConst.SESSION_MAP, param);
					return "redirect:main.action";
				}else{
					out.print("<script>alert('无访问权限，请与管理员联系');history.back(1)</script>");
					out.flush();
					out.close();
					return null;
				}
			}else{
				out.print("<script>alert('帐号密码错误');history.back(1)</script>");
				out.flush();
				out.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<script>alert('"+e.getMessage()+"');history.back(1)</script>");
			out.flush();
			out.close();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/main")
	@DontLog
	public ModelAndView toMain (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> hm = (HashMap<String, Object>) request.getSession().getAttribute(SessionConst.SESSION_MAP);
		if(hm != null) {
			SysUser user = (SysUser) hm.get(SessionConst.SESSION_MAP_USER);
			//如果头像为空，则使用默认头像
			String photoDir = PropertiesUtils.getProperty(this.getClass(), "common", "fileUploadDir");
			File photo = new File(photoDir+"/"+user.getUserPhoto());
			if(!photo.exists()) {
				user.setUserPhoto(DEFAULT_USER_PHOTO);
			}
			else{
				user.setUserPhoto("uploads/"+user.getUserPhoto());
			}
			SysOrg org = (SysOrg) hm.get(SessionConst.SESSION_MAP_ORG);
			modelAndView.addObject(SessionConst.SESSION_MAP_USER, user);
			modelAndView.addObject(SessionConst.SESSION_MAP_ORG, org);
			modelAndView.setViewName("index.jsp");
		} else {
			modelAndView.setViewName("login.jsp");
		}
		return modelAndView;
	}
	
	/**
	 * <p>描述：系统登出 </p>
	 * <p>日期：2015-2-2 下午1:56:36 </p>
	 * @param request
	 * @return true: 成功， false：失败
	 */
	@ResponseBody
	@RequestMapping("/userLoginOut")
	@SuppressWarnings("unchecked")
	@DontLog
	public Map<String, String> userLoginOut(HttpServletRequest request,HttpSession session) throws Throwable {
		Map<String, String> result = new HashMap<String, String>();
		try{
			String sid = (String) session.getAttribute(FrameworkContext.SYS_USER_FLAG);
			if(sid!=null){
				((Map<String, Object>)session.getAttribute("roleInfo")).clear();
				((Map<String, Object>)session.getAttribute(SessionConst.SESSION_MAP)).clear();
				onlineUserManager.logout(sid);
			}
			result.put("state", "true");
		}catch(Exception e){
			logger.error("用户登录验证失败：" + e.getMessage(), e);
			result.put("state", "false");
			result.put("error", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 关闭浏览器使session失效
	 * @param request
	 */
	@RequestMapping("/removeSeesion")
	@ExcludeAuthority
	@DontLog
	public void removeSeesion(HttpServletRequest request){
		request.getSession().invalidate();
	}
	
	@LogOpt(level=FunLogConst.LEVEL_3, desc="修改密码", parentDesc=FunLogConst.DESC_SYSTEM_LOGIN)
	@RequestMapping("/userinitpwd")
	public String userinitpwd(HttpServletRequest request){
		return "system/user_init_pwd.jsp";
	}
}
