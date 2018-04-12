package com.hd.tsa.action.wx;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.WxActivityDetail;
import com.hd.tsa.entity.WxActivityRel;
import com.hd.tsa.service.wx.WxActivityDetailService;
import com.hd.tsa.service.wx.WxActivityRelService;
import com.hd.sfw.core.model.Pagination;

/**
 * 微信活动列
 * @author 李良正
 *
 */
@Controller
@RequestMapping("/wxActivityRel")
public class WxActivityRelAction {

	@Autowired
	private WxActivityRelService wxActivityRelService;
	
	@Autowired
	private WxActivityDetailService wxActivityDetailService;
	
	/**
	 * 分页查询活动列
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findRelByPage")
	public Pagination<WxActivityRel> findRelByPage(HttpServletRequest request, WxActivityRel entity){
		Pagination<WxActivityRel> pagination = null;
		try {
			pagination = wxActivityRelService.findPageByCondition(entity);
		} catch (Exception e) {
			e.printStackTrace();
			pagination = new Pagination<>(0, new ArrayList<>());
		}
		return pagination;
	}
	
	/**
	 * 根据条件查询活动列
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByCondition")
	public List<WxActivityRel> findByCondition(WxActivityRel entity){
		List<WxActivityRel> list = null;
		try {
			entity.setSortColumns("seqNum asc");
			list = wxActivityRelService.findByCondition(entity);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<>();
		}
		return list;
	}
	
	/**
	 * 跳转编辑活动列页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardEditRel")
	public ModelAndView forwardEditRel(Long id){
		ModelAndView model = new ModelAndView();
		try {
			if(id != null){
				WxActivityRel rel = wxActivityRelService.findById(id);
				if(rel != null){
					model.addObject("rel", rel);
				}
			}
			WxActivityDetail detail = new WxActivityDetail();
			detail.setDeleteFlag(0);
			detail.setUseFlag(1);
			List<WxActivityDetail> list = wxActivityDetailService.findByCondition(detail);
			if(list != null){
				model.addObject("list", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("wx/activity_rel_edit.jsp");
		return model;
	}
	
	/**
	 * 保存活动列
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveActivityRel")
	public boolean saveActivityRel(HttpServletRequest request, WxActivityRel entity){
		try {
			wxActivityRelService.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改活动列
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateActivityRel")
	public boolean updateActivityRel(HttpServletRequest request, WxActivityRel entity){
		try {
			wxActivityRelService.updateById(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
