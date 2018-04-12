package com.hd.tsa.action.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.WebAdInfo;
import com.hd.tsa.service.web.WebAdInfoService;
import com.hd.sfw.core.model.Pagination;
import com.hd.sys.action.base.BaseAction;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysDict;
import com.hd.sys.service.SysDictService;

/**
 * 网页广告信息
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/webAdInfo")
public class WebAdInfoAction extends BaseAction {
	
	@Autowired
	private WebAdInfoService webAdInfoService;
	@Autowired
	private SysDictService sysDictService;
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc="广告管理", parentDesc="网页管理")
	@RequestMapping("/forwardAdList")
	public ModelAndView forwardAdList(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("web/ad_list.jsp");
		try {
			List<SysDict> positions = sysDictService.findSysDictByParent("003");
			view.addObject("positions", positions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 根据条件获取网页广告信息列表
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/adInfoList")
	public Pagination<WebAdInfo> adInfoList(HttpServletRequest request, HttpServletResponse response, WebAdInfo entity){
		try {
			entity.setDeleteFlag(0);
			return webAdInfoService.findPageByCondition(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 跳转网页广告编辑页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardEditAdInfo")
	public ModelAndView forwardEditAdInfo(HttpServletRequest request, Long id){
		ModelAndView model = new ModelAndView();
		try {
			List<SysDict> positions = sysDictService.findSysDictByParent("003");
			if(positions != null){
				model.addObject("positions", positions);
			}
			if(id != null){
					WebAdInfo entity = webAdInfoService.findById(id);
					if(entity != null){
						model.addObject("entity", entity);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("web/ad_edit.jsp");
		return model;
	}
	
	/**
	 * 保存网页广告
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveAdInfo")
	public boolean saveAdInfo(HttpServletRequest request, WebAdInfo entity){
		try {
			entity.setCreateDate(new Date());
			entity.setCreateUser(getLoginUser(request).getUserAccount());
			entity.setDeleteFlag(0); //删除标识 0--未删除 1--已删除
			entity.setDeliveryFlag(1); // 投放标识 0--未投放 1--已投放
			webAdInfoService.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改网页广告
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateAdInfo")
	public boolean updateAdInfo(HttpServletRequest request, WebAdInfo entity){
		try {
			entity.setModifyDate(new Date());
			entity.setModifyUser(getLoginUser(request).getUserAccount());
			webAdInfoService.updateById(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
