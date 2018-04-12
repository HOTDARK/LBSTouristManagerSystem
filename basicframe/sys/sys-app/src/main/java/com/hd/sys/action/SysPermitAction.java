package com.hd.sys.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sys.action.base.BaseAction;
import com.hd.sys.dao.SysOrgMapper;
import com.hd.sys.entity.SysOrg;
import com.hd.sys.entity.SysPermit;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysPermitService;

/**
 * <p>类名：SysPermissionAction </p>
 * <p>描述：系统权限管理页面控制类 </p>
 * <p>作者：xmh </p>
 * <p>时间：2014年12月15日 星期一 </p>
 */
@Controller
@RequestMapping("/sysPermit")
public class SysPermitAction extends BaseAction {
	
	/** log4j */
	private static Logger logger = Logger.getLogger(SysPermitAction.class);
	
	@Autowired
	private SysPermitService sysPermitService;
	@Autowired
	private SysOrgMapper sysOrgMapper;
	
	/**
	 * <p>描述：根据角色ID查询功能权限 </p>
	 * <p>日期：2014-12-16 下午3:31:16 </p>
	 * @param roleId 角色ID
	 * @return 根据角色ID查询功能权限
	 */
	@ResponseBody
	@RequestMapping("/findFuncPermissionByRoleId")
	public List<SysPermit> findFuncPermissionByRoleId(HttpServletRequest request,Long roleId) {
		try {
			List<SysPermit> funcPermList = sysPermitService.findFuncPermissionByRoleId(roleId);
			return funcPermList;
		} catch (Exception e) {
			logger.error("根据角色ID查询功能权限出错：" + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * <p>描述：根据角色ID查询机构权限 </p>
	 * <p>日期：2014-12-16 下午3:31:16 </p>
	 * @param roleId 角色ID
	 * @return 根据角色ID查询机构权限
	 */
	@ResponseBody
	@RequestMapping("/findOrgPermissionByRoleId")
	public List<SysPermit> findOrgPermissionByRoleId(HttpServletRequest request,Long roleId) {
		try {
			List<SysPermit> orgPermList = sysPermitService.findOrgPermissionByRoleId(roleId);
			return orgPermList;
		} catch (Exception e) {
			logger.error("根据角色ID查询机构权限出错：" + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * <p>描述：保存组织机构权限配置 </p>
	 * <p>日期：2014年12月15日 星期一 </p>
	 * @param permission 权限
	 * @return true：保存成功，false：保存失败
	 */
	@ResponseBody
	@RequestMapping("/savePermission")
	public boolean savePermission(HttpServletRequest request, SysPermit permission){
		try{
			//机构权限
			List<SysPermit> orgPermList = new ArrayList<SysPermit>();
			String[] orgIds = permission.getOrgIds().split(",");
			if(orgIds != null) {
				for(int i=0; i<orgIds.length; i++) {
					SysPermit orgPerm = new SysPermit();
					orgPerm.setRoleId(permission.getRoleId());
					orgPerm.setOrgId(Long.valueOf(orgIds[i]));
					orgPerm.setCreateTime(new Date());
					orgPerm.setState(1L);
					orgPermList.add(orgPerm);
				}
				sysPermitService.deleteOrgByIdAndRoleId(permission);
				sysPermitService.saveOrgPermissions(orgPermList);
			}
			
			//功能权限
			List<SysPermit> funcPermList = new ArrayList<SysPermit>();
			String[] funcIds = permission.getFuncIds().split(",");
			if(funcIds != null) {
				for(int i=0; i<funcIds.length; i++) {
					SysPermit funcPerm = new SysPermit();
					funcPerm.setRoleId(permission.getRoleId());
					funcPerm.setFunctionId(Long.valueOf(funcIds[i]));
					funcPerm.setCreateTime(new Date());
					funcPerm.setState(1L);
					funcPermList.add(funcPerm);
				}
				sysPermitService.deleteFuncByIdAndRoleId(permission);
				sysPermitService.saveFuncPermissions(funcPermList);
			}
			
			//更新权session权限数据
			SysUser user = getLoginUser(request);
			if(user != null) {
				// 获取顶级机构信息
				SysOrg topOrg = sysOrgMapper.findTopOrg();
				Map<String, String[]> newPermission = sysPermitService.getPermissionByUserId(user.getUserId());
				if (!user.getOrgId().equals(topOrg.getOrgId())) {
					for (String s : newPermission.keySet()) {
						String[] tmp = newPermission.get(s);
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
				}
				request.getSession().setAttribute("roleInfo", newPermission);
			}

			return true;
		} catch (Exception e) {
			logger.error("保存组织机构权限配置出错：" + e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * 
	 * <p>描述：加载部门数据权限 </p>
	 * <p>日期：2014-12-16 下午03:58:29 </p>
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOrgRoleByUser")
	public Map<String, Object> getOrgRoleByUser(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map.put("data", this.getOrgsPermission(request));
			map.put("status", true);
		} catch (Exception e) {
			logger.error("加载部门数据权限出错：" + e.getMessage(), e);
			map.put("status", false);
		}
		return map;
	}
	
	/**
	 * 
	 * <p>描述：加载功能权限 </p>
	 * <p>日期：2014-12-16 下午03:58:29 </p>
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFuncRoleByUser")
	public Map<String, Object> getFuncRoleByUser(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map.put("data", this.getFuncsPermission(request));
			map.put("status", true);
		} catch (Exception e) {
			logger.error("加载功能权限出错：" + e.getMessage(), e);
			map.put("status", false);
		}
		return map;
	}
	
	/**
	 * <p>描述：查询功能树-配置权限 </p>
	 * <p>日期：2014-12-9 上午11:14:52 </p>
	 * @return 组织机构树
	 */
	@ResponseBody
	@RequestMapping("/getFunctionTreeAll")
	public List<SysPermit> getFunctionTreeAll(HttpServletRequest request, SysPermit permission) {
		try {
			@SuppressWarnings("unchecked")
			Map<String, String[]> permissionMap = (Map<String, String[]>)request.getSession().getAttribute("roleInfo");
			SysOrg sysOrg = getLoginOrg(request);
			if (sysOrg != null && permissionMap != null) {
				if (sysOrg.getParentOrgId() == 0L) {
					permission.setPermiss(false);
				} else {
					permission.setPermiss(true);
				}
				// 获取当前角色的功能列表
				List<SysPermit> permits = sysPermitService.getFunctionTreeAll(permission);
				String roleIds = "";
				for (String roleId : permissionMap.keySet()) {
					// 遍历当前登录用户的角色IDS
					roleIds += roleId.concat(",");
				}
				roleIds = roleIds.substring(0, roleIds.length()-1);
				permission.setRoleId(null);
				permission.setRoleIds(roleIds);
				// 获取当前登录用户角色的功能列表
				List<SysPermit> loginPermits = sysPermitService.getFunctionTreeAll(permission);
				if (CollectionUtils.isNotEmpty(loginPermits)) {
					for (SysPermit sysPermit : loginPermits) {
						sysPermit.setChecked(false);
						for (SysPermit permit : permits) {
							if (sysPermit.getFunctionId().equals(permit.getFunctionId()) && permit.isChecked()) {
								sysPermit.setChecked(true);
								break;
							}
						}
					}
					return loginPermits;
				}
			}
		} catch (Exception e) {
			logger.error("查询功能树-配置权限出错：" + e.getMessage(), e);
		}
		return new ArrayList<SysPermit>();
	}
	
	/**
	 * <p>描述：查询组织机构树-配置权限 </p>
	 * <p>日期：2014-12-9 上午11:14:52 </p>
	 * @param sysOrganization 机构对象
	 * @return 组织机构树
	 */
	@ResponseBody
	@RequestMapping("/getOrgTreeAll")
	public List<SysPermit> getOrgTreeAll(HttpServletRequest request,SysPermit permission) {
		try {
			return sysPermitService.getOrgTreeAll(permission);
		} catch (Exception e) {
			logger.error("查询组织机构树-配置权限出错：" + e.getMessage(), e);
		}
		return new ArrayList<SysPermit>();
	}
	
}

