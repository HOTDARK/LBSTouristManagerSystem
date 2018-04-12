package com.hd.tsa.action.wx;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.WxModulInfo;
import com.hd.tsa.service.wx.WxModulInfoService;
import com.hd.sfw.core.model.Pagination;
import com.hd.sys.action.base.BaseAction;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

/**
 * 微信模块信息
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/wxModulInfo")
public class WxModulInfoAction extends BaseAction {
	
	@Autowired
	private WxModulInfoService wxModulInfoService;
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc="模块管理", parentDesc="微信管理")
	@RequestMapping("/forwardModulList")
	public String forwardModulList(HttpServletRequest request, HttpServletResponse response){
		return "wx/modul_list.jsp";
	}
	
	/**
	 * 根据条件获取微信模块信息列表
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modulInfoList")
	public Pagination<WxModulInfo> modulInfoList(HttpServletRequest request, HttpServletResponse response, WxModulInfo entity){
		try {
			return wxModulInfoService.findPageByCondition(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 跳转微信模块编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardModulEdit")
	public ModelAndView forwardModulEdit(Long id){
		ModelAndView model = new ModelAndView();
		try {
			if(id != null){
				WxModulInfo entity = wxModulInfoService.findById(id);
				if(entity != null){
					model.addObject("entity", entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("wx/modul_edit.jsp");
		return model;
	}
	
	/**
	 * 保存微信模块信息
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveModulInfo")
	public boolean saveModulInfo(HttpServletRequest request, WxModulInfo entity){
		try {
			entity.setCreateDate(new Date());
			entity.setCreateUser(getLoginUser(request).getUserAccount());
			entity.setDeleteFlag(0); //删除标识 0--未删除 1--已删除
			entity.setReleaseFlag(1); //发布标记 0--未发布 1--已发布
			wxModulInfoService.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改微信模块
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateModulInfo")
	public boolean updateModulInfo(HttpServletRequest request, WxModulInfo entity){
		try {
			entity.setModifyDate(new Date());
			entity.setModifyUser(getLoginUser(request).getUserAccount());
			wxModulInfoService.updateById(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
