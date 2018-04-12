package com.hd.sys.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.sfw.core.model.Pagination;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysRole;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysRoleService;
import com.hd.sys.service.SysUserService;

/**
 * <p>类名：SysRoleAction </p>
 * <p>描述：系统角色页面控制类 </p>
 * <p>作者：cb </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleAction {
	
	/** log4j */
	private static Logger logger = Logger.getLogger(SysRoleAction.class);
	
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserService sysUserService;
	
    /**
     * <p>描述：根据主键ID查询 </p>
     * <p>日期：2014-12-13 下午3:10:45 </p>
     * @param roleId 主键ID
     * @return 根据主键ID查询
     */
	@ResponseBody
	@RequestMapping("/findById")
	public SysRole findById(HttpServletRequest request,Long roleId){
		SysRole role = null;
		try {
			role = (SysRole) sysRoleService.findById(roleId);
			if(role != null) {
				return role;
			}
		 } catch (Exception e) {
			logger.error("根据主键ID查询出错：" + e.getMessage(), e);
		}
        return new SysRole();
	}
	
	/**
	 * <p>描述：根据机构ID查询机构列表 </p>
	 * <p>日期：2014-12-10 下午3:43:01 </p>
	 * @param sysRole 
	 * @return 根据机构ID查询机构列表
	 */
	@ResponseBody
	@RequestMapping("/findRoleListByOrgId")
	public Pagination<SysRole> findRoleListByOrgId(HttpServletRequest request,SysRole SysRole){
		Pagination<SysRole> page = null;
		try {
			List<SysRole> roleList = sysRoleService.findRoleListByOrgId(SysRole);
			page = new Pagination<SysRole>(roleList.size(), roleList);
		 } catch (Exception e) {
			logger.error("根据机构ID查询角色列表出错：" + e.getMessage(), e);
		}
        return page;
	}
	
	/**
	 * <p>描述：判断在同级机构下的角色名称是否存在 </p>
	 * <p>日期：2014-12-13 上午9:40:41 </p>
	 * @param role
	 * @return true: 不存在，false: 存在
	 */
	@ResponseBody
	@RequestMapping("/findExistsRoleNameByOrgId")
	public boolean findExistsRoleNameByOrgId(HttpServletRequest request,SysRole role) {
		SysRole sr =null;
		try{
			sr = sysRoleService.findExistsRoleNameByOrgId(role);
			if(sr != null){
				return false;
			}
		}catch(Exception e){
			logger.error("判断该角色是否已存在出错" + e.getMessage(), e);
		}
		return true;
	}
	
	/**
	 * <p>描述：保存角色 </p>
	 * <p>日期：2014-12-13 上午9:45:09 </p>
	 * @param role 要保存的角色对象
	 * @return true: 保存成功，false: 保存失败
	 */
	@ResponseBody
	@RequestMapping("/doSave")
	public boolean doSave(HttpServletRequest request,SysRole role) {
		try{
			sysRoleService.addOrUpdate(role);
		} catch (Exception e) {
			logger.error("保存角色出错：" + e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	/**
	 * <p>描述：保存角色与用户关联 </p>
	 * <p>日期：2014-12-13 上午9:45:09 </p>
	 * @param role 要保存的角色对象
	 * @return true: 保存成功，false: 保存失败
	 */
	@ResponseBody
	@RequestMapping("/saveUserByRoleId")
	public boolean saveUserByRoleId(HttpServletRequest request,SysRole role) {
		try{
			sysRoleService.addUserByRoleId(role);
		} catch (Exception e) {
			logger.error("保存角色出错：" + e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	/**
	 * <p>描述：激活角色 </p>
	 * <p>日期：2014-12-13 上午9:50:11 </p>
	 * @param role 要激活的角色对象
	 * @return true：激活成功，false：激活失败
	 */
	@ResponseBody
	@RequestMapping("/enableRole")
	public boolean enableRole(HttpServletRequest request,SysRole role) {
		try{
			sysRoleService.updateByRoleId(role);
		} catch (Exception e) {
			logger.error("激活角色出错：" + e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	/**
	 * <p>描述：冻结角色 </p>
	 * <p>日期：2014-12-13 上午9:49:07 </p>
	 * @param role 要冻结的角色对象
	 * @return true：冻结成功，false：冻结失败
	 */
	@ResponseBody
	@RequestMapping("/unableRole")
	public boolean unableRole(HttpServletRequest request,SysRole role) {
		try{
			sysRoleService.updateByRoleId(role);
		} catch (Exception e) {
			logger.error("冻结角色出错：" + e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	/**
	 * <p>描述：根据角色IDS激活角色IDS下的用户 </p>
	 * <p>日期：2014-12-13 上午9:50:11 </p>
	 * @param roleIds 角色IDS
	 * @param state 状态【1：激活，0：冻结】
	 * @return true：激活成功，false：激活失败
	 */
	@ResponseBody
	@RequestMapping("/enableRoleUser")
	public boolean enableRoleUser(HttpServletRequest request,String roleIds, Long state) {
		try{
			SysUser user = new SysUser();
			user.setState(state);
			user.setRoleIds(roleIds);
			sysUserService.updateByRoleIds(user);
		} catch (Exception e) {
			logger.error("根据角色IDS激活角色IDS下的用户出错：" + e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	/**
	 * <p>描述：根据角色IDS冻结角色IDS下的用户 </p>
	 * <p>日期：2014-12-13 上午9:49:07 </p>
	 * @param roleIds 角色IDS
	 * @param state 状态【1：激活，0：冻结】
	 * @return true：冻结成功，false：冻结失败
	 */
	@ResponseBody
	@RequestMapping("/unableRoleUser")
	public boolean unableRoleUser(HttpServletRequest request,String roleIds, Long state) {
		try{
			SysUser user = new SysUser();
			user.setState(state);
			user.setRoleIds(roleIds);
			sysUserService.updateByRoleIds(user);
		} catch (Exception e) {
			logger.error("根据角色IDS冻结角色IDS下的用户出错：" + e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	@LogOpt(level=FunLogConst.LEVEL_3, desc="角色变更", parentDesc=FunLogConst.DESC_SYSTEM_ORG)
	@RequestMapping("forwardRoleEdit")
	public String forwardRoleEdit(HttpServletRequest request){
		return "system/org_role_edit.jsp";
	}
	
	@LogOpt(level=FunLogConst.LEVEL_3, desc="角色人员变更", parentDesc=FunLogConst.DESC_SYSTEM_ORG)
	@RequestMapping("forwardRoleUser")
	public String forwardRoleUser(HttpServletRequest request){
		return "system/org_role_user.jsp";
	}
	
	@LogOpt(level=FunLogConst.LEVEL_3, desc="角色权限变更", parentDesc=FunLogConst.DESC_SYSTEM_ORG)
	@RequestMapping("forwardRolepermission")
	public String forwardRolepermission(HttpServletRequest request){
		return "system/org_role_permit.jsp";
	}
	
}