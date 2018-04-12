package com.hd.tsa.action.wx;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.WxAdInfo;
import com.hd.tsa.service.wx.WxAdInfoService;
import com.hd.sfw.core.model.Pagination;
import com.hd.sys.action.base.BaseAction;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysDict;
import com.hd.sys.service.SysDictService;

/**
 * 微信广告信息
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/wxAdInfo")
public class WxAdInfoAction extends BaseAction {
	
	@Autowired
	private WxAdInfoService wxAdInfoService;
	@Autowired
	private SysDictService sysDictService;

	@LogOpt(level=FunLogConst.LEVEL_2, desc="广告管理", parentDesc="微信管理")
	@RequestMapping("/forwardAdList")
	public ModelAndView forwardAdList(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("wx/ad_list.jsp");
		try {
			List<SysDict> positions = sysDictService.findSysDictByParent("004");
			view.addObject("positions", positions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 根据条件获取微信广告信息列表
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/adInfoList")
	public Pagination<WxAdInfo> adInfoList(HttpServletRequest request, HttpServletResponse response, WxAdInfo entity){
		try {
			return wxAdInfoService.findPageByCondition(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 跳转微信广告编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardEditAdInfo")
	public ModelAndView forwardEditAdInfo(Long id){
		ModelAndView model = new ModelAndView();
		try {
			List<SysDict> positions = sysDictService.findSysDictByParent("004");
			if(positions != null){
				model.addObject("positions", positions);
			}
			if(id != null){
				WxAdInfo entity = wxAdInfoService.findById(id);
				if(entity != null){
					model.addObject("entity", entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("wx/ad_edit.jsp");
		return model;
	}
	
	/**
	 * 保存微信广告
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveWxAdInfo")
	public boolean saveWxAdInfo(HttpServletRequest request, WxAdInfo entity){
		try {
			entity.setCreateDate(new Date());
			entity.setCreateUser(getLoginUser(request).getUserAccount());
			entity.setDeleteFlag(0); //删除标记 0--未删除 1--已删除
			entity.setDeliveryFlag(1); //投放标记 0--未投放 1--已投放
			wxAdInfoService.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改微信广告
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateWxAdInfo")
	public boolean updateWxAdInfo(HttpServletRequest request, WxAdInfo entity){
		try {
			entity.setModifyDate(new Date());
			entity.setModifyUser(getLoginUser(request).getUserAccount());
			wxAdInfoService.updateById(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
