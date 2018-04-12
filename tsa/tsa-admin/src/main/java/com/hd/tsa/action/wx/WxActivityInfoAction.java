package com.hd.tsa.action.wx;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.WxActivityInfo;
import com.hd.tsa.entity.WxActivityRel;
import com.hd.tsa.service.wx.WxActivityInfoService;
import com.hd.sfw.core.model.Pagination;
import com.hd.sys.action.base.BaseAction;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysDict;
import com.hd.sys.service.SysDictService;

/**
 * 微信活动信息
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/wxActivityInfo")
public class WxActivityInfoAction extends BaseAction {
	
	@Autowired
	private WxActivityInfoService wxActivityInfoService;
	@Autowired
	private SysDictService sysDictService;
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc="活动管理", parentDesc="微信管理")
	@RequestMapping("/forwardActivityList")
	public ModelAndView forwardActivityList(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("wx/activity_list.jsp");
		try {
			List<SysDict> positions = sysDictService.findSysDictByParent("005");
			view.addObject("positions", positions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 根据条件获取微信活动信息列表
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activityList")
	public Pagination<WxActivityInfo> activityList(HttpServletRequest request, HttpServletResponse response, WxActivityInfo entity){
		try {
			return wxActivityInfoService.findPageByCondition(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 跳转微信活动编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardEditWxActivity")
	public ModelAndView forwardEditWxActivity(Long id){
		ModelAndView model = new ModelAndView();
		try {
			if(id != null){
				WxActivityInfo entity = wxActivityInfoService.findById(id);
				if(entity != null){
					model.addObject("entity", entity);
				}
			}
			List<SysDict> positions = sysDictService.findSysDictByParent("005");
			if(positions != null){
				model.addObject("positions", positions);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("wx/activity_edit.jsp");
		return model;
	}
	
	/**
	 * 保存微信活动
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveWxActivity")
	public boolean saveWxActivity(HttpServletRequest request, WxActivityInfo entity){
		try {
			entity.setCreateDate(new Date());
			entity.setCreateUser(getLoginUser(request).getUserAccount());
			entity.setUseFlag(0); //应用标记 0--未应用 1--已应用
			entity.setDeleteFlag(0); //删除标记 0--未删除 1--已删除
			wxActivityInfoService.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改微信活动
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateWxActivity")
	public boolean updateWxActivity(HttpServletRequest request, WxActivityInfo entity){
		try {
			entity.setModifyDate(new Date());
			entity.setModifyUser(getLoginUser(request).getUserAccount());
			wxActivityInfoService.updateById(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 跳转微信活动行列表页面
	 * @return
	 */
	@RequestMapping("/forwardLookWxActivityLayout")
	public String forwardLookWxActivityLayout(){
		return "wx/activity_layout_list.jsp";
	}
	
	/**
	 * 查看微信活动排版详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardLookActivity")
	public ModelAndView forwardLookActivity(Long id){
		ModelAndView model = new ModelAndView();
		try {
			if(id != null){
				Map<Integer, List<WxActivityRel>> map = wxActivityInfoService.queryActivityDetail(id);
				if(map != null){
					model.addObject("map", map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("wx/activity_look.jsp");
		return model;
	}
	
	/**
	 * 验证活动行列信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkWxActivity")
	public boolean checkWxActivity(Long id){
		try {
			return wxActivityInfoService.checkActivityInfo(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
