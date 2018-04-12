package com.hd.tsa.action.wx;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.WxActivityDetail;
import com.hd.tsa.service.wx.WxActivityDetailService;
import com.hd.sfw.core.model.Pagination;
import com.hd.sys.action.base.BaseAction;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

/**
 * 微信活动详情
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/wxActivityDetail")
public class WxActivityDetailAction extends BaseAction {
	
	@Autowired
	private WxActivityDetailService wxActivityDetailService;
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc="活动素材", parentDesc="微信管理")
	@RequestMapping("/forwardActivityDetail")
	public String forwardActivityDetail(HttpServletRequest request, HttpServletResponse response){
		return "wx/activity_detail_list.jsp";
	}
	
	/**
	 * 根据条件获取微信活动详情列表
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activityDetailList")
	public Pagination<WxActivityDetail> activityDetailList(HttpServletRequest request, HttpServletResponse response, WxActivityDetail entity){
		try {
			return wxActivityDetailService.findPageByCondition(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 跳转微信活动详情编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardEditDetail")
	public ModelAndView forwardEditDetail(Long id){
		ModelAndView model = new ModelAndView();
		try {
			if(id != null){
				WxActivityDetail entity = wxActivityDetailService.findById(id);
				if(entity != null){
					model.addObject("entity", entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("wx/activity_detail_edit.jsp");
		return model;
	}
	
	/**
	 * 保存微信活动详情
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveDetail")
	public boolean saveDetail(HttpServletRequest request, WxActivityDetail entity){
		try {
			entity.setCreateDate(new Date());
			entity.setCreateUser(getLoginUser(request).getUserAccount());
			entity.setUseFlag(1); //可用标记 0--不可用 1--可用
			entity.setDeleteFlag(0); //删除标记 0--未删除 1--已删除
			wxActivityDetailService.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改微信活动详情
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateDetail")
	public boolean updateDetail(HttpServletRequest request, WxActivityDetail entity){
		try {
			entity.setModifyDate(new Date());
			entity.setModifyUser(getLoginUser(request).getUserAccount());
			wxActivityDetailService.updateById(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
