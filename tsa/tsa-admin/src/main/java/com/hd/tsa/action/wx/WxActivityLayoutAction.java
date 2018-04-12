package com.hd.tsa.action.wx;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.WxActivityLayout;
import com.hd.tsa.service.wx.WxActivityLayoutService;
import com.hd.sfw.core.model.Pagination;
import com.hd.sys.action.base.BaseAction;

/**
 * 微信活动行
 * @author 李良正
 *
 */
@Controller
@RequestMapping("/wxActivityLayout")
public class WxActivityLayoutAction extends BaseAction{

	@Autowired
	private WxActivityLayoutService wxActivityLayoutService;
	
	/**
	 * 分页查询活动列信息
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByPage")
	public Pagination<WxActivityLayout> findByPage(HttpServletRequest request, WxActivityLayout entity){
		Pagination<WxActivityLayout> pagination = null;
		try {
			entity.setSortColumns("lineNum asc");
			pagination = wxActivityLayoutService.findPageByCondition(entity);
		} catch (Exception e) {
			e.printStackTrace();
			pagination = new Pagination<>(0, new ArrayList<>());
		}
		return pagination;
	}
	 /**
	  * 跳转编辑活动列页面
	  * @param id
	  * @return
	  */
	@RequestMapping("/forwardEditLayout")
	public ModelAndView forwardEditLayout(Long id){
		ModelAndView model = new ModelAndView();
		try {
			if(id != null){
				WxActivityLayout entity = wxActivityLayoutService.findById(id);
				if(entity != null){
					model.addObject("entity", entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("wx/activity_layout_edit.jsp");
		return model;
	}
	
	/**
	 * 保存活动列信息
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveLayout")
	public boolean saveLayout(HttpServletRequest request, WxActivityLayout entity){
		try {
			wxActivityLayoutService.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改活动列信息
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateLayout")
	public boolean updateLayout(HttpServletRequest request, WxActivityLayout entity){
		try {
			wxActivityLayoutService.updateById(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除活动列信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteLayout")
	public boolean deleteLayout(Long id){
		try {
			wxActivityLayoutService.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
