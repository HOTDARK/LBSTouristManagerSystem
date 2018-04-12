package com.hd.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.sfw.core.model.Pagination;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.context.FrameworkContext;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.core.online.OnlineUserInfo;
import com.hd.sys.core.online.OnlineUserManager;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysUserService;

/**
 * <p>类名：SysUserAction </p>  
 * <p>描述：系统角色页面控制类 </p>  
 * <p>作者：cb </p>
 * <p>时间：2014年12月8日 星期一 </p>      
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserAction {
	
	/** log4j */
	private static Logger logger = Logger.getLogger(SysUserAction.class);
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private OnlineUserManager onlineUserManager;
	
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request){
        return "system/test.jsp";
	}
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc=FunLogConst.DESC_SYSTEM_USER, parentDesc=FunLogConst.DESC_SYSTEM)
	@RequestMapping("/forwardUser")
	public String forwardUser(HttpServletRequest request){
		return "system/user_list.jsp";
	}
	
	/**
	 * <p>描述:新增、修改、用户 </p>
	 * <p>日期：2014-12-11 下午3:39:48 </p>
	 * @param request
	 * @param sysUser 用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editUser")
	public Map<String,String> editUser(HttpServletRequest request,SysUser sysUser){
		Map<String,String> map = new HashMap<String,String>();
		try {
			sysUserService.saveOrUpdateUser(request,sysUser);
			map.put("userPhoto", sysUser.getUserPhoto());
			return map;
		 } catch (Exception e) {
			logger.error("新增/修改/用户出错：" + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * <p>描述:重置密码 </p>
	 * <p>日期：2014-12-11 下午3:39:48 </p>
	 * @param request
	 * @param sysUser 用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editUserPwd")
	public boolean editUserPwd(HttpServletRequest request,SysUser sysUser){
		try {
			sysUserService.updateById(sysUser);
			return true;
		 } catch (Exception e) {
			logger.error("重置密码出错：" + e.getMessage(), e);
		}
		return false;
	}
	
	/**
	 * <p>描述：激活or冻结用户 </p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param userIds 多个用户Id组成的字符串
	 * @param userState  1-激活,0-冻结
	 */
	@ResponseBody
	@RequestMapping("/activateAndFrozen")
	public boolean activateAndFrozen(HttpServletRequest request, String userIds, Long userState){
		try {
			SysUser sysUser = new SysUser();
			sysUser.setUserIds(userIds);
			sysUser.setState(userState);
			sysUserService.updateStateById(sysUser);
			return true;
		} catch (Exception e) {
			logger.error("查询列表出错：" + e.getMessage(), e);
		}
		return false;
	}
	
	/**
	 * <p>描述:根据用户id查询用户角色以及角色所属机构信息 </p>
	 * <p>日期：2014-12-9 上午10:40:22 </p>
	 * @param request
	 * @param userId 用户id
	 * @return 返回roleId,roleName,orgName 集合
	 */
	@ResponseBody
	@RequestMapping("/getRolesByUserId")
	public List<SysUser> getRolesByUserId(HttpServletRequest request, Long userId) {
		try {
			if (userId != null) {
				return sysUserService.getRolesByUserId(userId);
			}
		} catch (Exception e) {
			logger.error("查询用户角色出错：" + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * <p>描述:根据机构id查询 该机构所有角色</p>
	 * <p>日期：2014-12-9 上午11:12:18 </p>
	 * @param orgId 机构id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRolesByOrgId")
	public List<SysUser> getRolesByOrgId(HttpServletRequest request, Long orgId) {
		try {
			if (orgId != null) {
				List<SysUser> users = sysUserService.getRolesByOrgId(orgId);
				return users;
			}
		} catch (Exception e) {
			logger.error("查询机构角色出错：" + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * <p>描述:批量增加用户角色 </p>
	 * <p>日期：2014-12-10 上午9:20:23 </p>
	 * @param request
	 * @param userId userId
	 * @param roleIdArr 角色id数组
	 */
	@ResponseBody
	@RequestMapping("/updateUserRoles")
	public boolean updateUserRoles(HttpServletRequest request, SysUser user, String roleIdStr) {
		if (user.getUserId() != null) {
			try {
				sysUserService.updateUserRoles(user,roleIdStr);
				return true;
			} catch (Exception e) {
				logger.error("更新角色出错：" + e.getMessage(), e);
			}
		}
		return false;
	}
	
	/**
	 * <p>描述:批量增加用户角色 </p>
	 * <p>日期：2014-12-10 上午9:20:23 </p>
	 * @param request
	 * @param user 用户对象
	 */
	@ResponseBody
	@RequestMapping("/updateUserOrg")
	public boolean updateUserOrg(HttpServletRequest request, SysUser user) {
		if (user.getUserId() != null) {
			try {
				sysUserService.updateUserOrg(user);
				return true;
			} catch (Exception e) {
				logger.error("更新用户机构出错：" + e.getMessage(), e);
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <p>描述：根据组织机构ID查询用户列表 </p>
	 * <p>日期：2014-12-10 下午04:20:19 </p>
	 * @param request HttpServletRequest对象
	 * @param sysUser 用户实体类
	 * @return 返回用户集合
	 */
	@ResponseBody
	@RequestMapping("/getUserListByOrgId")
	public Pagination<SysUser> getUserListByOrgId(HttpServletRequest request,SysUser sysUser) {
		try {
			Pagination<SysUser> userPage = sysUserService.getSysUserListByOrgId(sysUser);
			return userPage;
		} catch (Exception e) {
			logger.error("根据组织机构查询用户出错：" + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 
	 * <p>描述：根据组织机构ID查询用户列表 </p>
	 * <p>日期：2014-12-10 下午04:20:19 </p>
	 * @param request HttpServletRequest对象
	 * @param sysUser 用户实体类
	 * @return 返回用户集合
	 */
	@ResponseBody
	@RequestMapping("/findUserListByOrgIdOrRoleId")
	public List<SysUser> findUserListByOrgIdOrRoleId(HttpServletRequest request,SysUser sysUser) {
		try {
			List<SysUser> userList = sysUserService.findUserListByOrgIdOrRoleId(sysUser);
			return userList;
		} catch (Exception e) {
			logger.error("根据机构ID或角色ID查询用户出错：" + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 
	 * <p>描述：查询用户信息 </p>
	 * <p>日期：2014-12-10 下午04:20:19 </p>
	 * @param request HttpServletRequest对象
	 * @param userId 
	 * @return 返回用户
	 */
	@LogOpt(level=FunLogConst.LEVEL_3, desc="用户变更", parentDesc=FunLogConst.DESC_SYSTEM_USER)
	@RequestMapping("/toUserEdit")
	public String toUserEdit(HttpServletRequest request,ModelMap model,Long userId){
		try {
			if (userId != null) {
				SysUser user = (SysUser) sysUserService.findById(userId);
				model.addAttribute("user", user);
				model.addAttribute("orgId", user.getOrgId());
				if (StringUtils.isNotBlank(user.getUserPhoto())) {
					//String contenxtPath = request.getContextPath();
					String basePath = request.getScheme() + "://" + request.getServerName()
							+ ":" + request.getServerPort() ;
					model.addAttribute("path", basePath+"/"+user.getUserPhoto());
				}
			} else {
				model.addAttribute("orgId", request.getParameter("orgId"));
			}
		} catch (Exception e) {
			logger.error("查询用户信息出错：" + e.getMessage(), e);
		}
		return "system/user_edit.jsp";
	}
	
	/**
	 * 密码重置
	 * @return
	 */
	@LogOpt(level=FunLogConst.LEVEL_3, desc="密码重置", parentDesc=FunLogConst.DESC_SYSTEM_USER)
	@RequestMapping("/toPwdInit")
	public String toPwdInit(HttpServletRequest request,ModelMap model){
		return "system/user_init_pwd.jsp";
	}
	
	/**
	 * 机构变更
	 */
	@LogOpt(level=FunLogConst.LEVEL_3, desc="机构变更", parentDesc=FunLogConst.DESC_SYSTEM_USER)
	@RequestMapping("toOrgEdit")
	public ModelAndView toOrgEdit(HttpServletRequest request,ModelMap model){
		ModelAndView view = new ModelAndView("system/user_org_edit.jsp");
		view.addObject("orgTitle", request.getParameter("orgTitle"));
		view.addObject("roleTitle", request.getParameter("roleTitle"));
		return view;
	}
	
	/**
	 * 
	 * <p>描述：查询用户详细信息 </p>
	 * <p>日期：2014-12-10 下午04:20:19 </p>
	 * @param request HttpServletRequest对象
	 * @param userId 用户id
	 * @return 返回用户
	 */
	@ResponseBody
	@RequestMapping("/getSysUserInfo")
	public SysUser getSysUserInfo(HttpServletRequest request, Long userId){	
		SysUser user = null;
		try {
			if (userId != null) {
				user = sysUserService.getUserInfoById(userId);
			}
		} catch (Exception e) {
			logger.error("查询用户信息出错：" + e.getMessage(), e);
		}
		return user;
	}
	
	/**
	 * <p>描述:跳转角色修改页面 </p>
	 * <p>日期：2014-12-13 上午9:56:43 </p>
	 * @param model
	 * @param request
	 * @param userId 用户id
	 * @return
	 */
	@LogOpt(level=FunLogConst.LEVEL_3, desc="角色变更", parentDesc=FunLogConst.DESC_SYSTEM_USER)
	@RequestMapping("/toUserRoleEdit")
	public String toUserRoleEdit(HttpServletRequest request,ModelMap model,Long userId){	
		try {
			if (userId != null) {
				SysUser user = sysUserService.getUserInfoById(userId);
				model.addAttribute("user", user);
			}
		} catch (Exception e) {
			logger.error("查询用户信息出错：" + e.getMessage(), e);
		}
		return "system/user_role_edit.jsp";
	}
	
	@ResponseBody
	@RequestMapping("getCountByEntity")
	public boolean getCountByEntity(HttpServletRequest request, SysUser sysUser) {
		Long userId = sysUser.getUserId();
		sysUser.setUserId(null);
		Pagination<SysUser> page;
		try {
			sysUser.setiDisplayStart(0);
			sysUser.setiDisplayLength(10);
			page = (Pagination<SysUser>) sysUserService.findPageByCondition(sysUser);
			if (page.getiTotalRecords() == 0) {//数据库不存在
				return false;
			} else if (page.getiTotalRecords() == 1) {//数据库存在，需要判断是否是本人或者其他人
				if (null != userId && page.getData().get(0).getUserId().equals(userId)) {
					return false;
				} else {
					return true;
				}
			} else {//已存在
				return true;
			}
		} catch (Exception e) {
			logger.error("查询是否重复(userAccount)：" + e.getMessage(), e);
		}
		return true;
	}
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc=FunLogConst.DESC_SYSTEM_LOGIN, parentDesc=FunLogConst.DESC_SYSTEM)
	@RequestMapping("/forwardOnlineUsers")
	public String forwardOnlineUsers(HttpServletRequest request){
		return "system/user_online_list.jsp";
	}
	
	/**
	 * 在线用户
	 */
	@ResponseBody
	@RequestMapping("/onlineUsers")
	public Pagination<OnlineUserInfo> getOnlineUsers(
			HttpServletRequest request, OnlineUserInfo onlineUserInfo) {
		try {
			Pagination<OnlineUserInfo> listPagination = sysUserService .getOnlineUsers(onlineUserInfo);
			return listPagination;
		} catch (Exception e) {
			logger.error("查询数据列表出错：" + e.getMessage(), e);
		}
		return null;
	}
	
	
	//手动注销登陆用户
	@RequestMapping("/logoutByManual")
	@ResponseBody
	public Map<String, Object> logoutByPerson(HttpSession session,@RequestParam("sid")String sid){
		Map<String, Object> map=new HashMap<String, Object>();
		String sidS = (String)session.getAttribute(FrameworkContext.SYS_USER_FLAG);
		if(StringUtils.isNotBlank(sidS)&&StringUtils.isNotBlank(sid)){
			if (sidS.equals(sid)) {
				map.put("refrsh", true);
			} else{
				map.put("refrsh", false);
			}
		}
		map.put("state", true);
		onlineUserManager.logout(sid);
		return map;
	}
}

