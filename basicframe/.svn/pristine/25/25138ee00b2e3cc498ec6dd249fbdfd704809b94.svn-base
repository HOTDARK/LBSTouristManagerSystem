/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sys.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.sfw.core.cache.CacheManager;
import com.hd.sfw.core.cache.CacheableDefine;
import com.hd.sfw.core.cache.SimpleCacheManager;
import com.hd.sfw.core.model.Pagination;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sys.action.base.ActionResult;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.vo.CacheableDefineVO;

/**
 * cache 管理action
 * @version	0.0.1
 * @author somnuscy
 */
@Controller
@RequestMapping("/sysCache")
public class SysCacheAction {
	
	@Autowired
	private CacheManager cacheManager;
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc=FunLogConst.DESC_SYSTEM_CACHE, parentDesc=FunLogConst.DESC_SYSTEM)
	@RequestMapping("forwardCache")
	public ModelAndView forwardCache(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("system/cache_list.jsp");
		modelAndView.addObject("state", cacheManager.isOpen());
		return modelAndView;
	}
	
	@LogOpt(level=FunLogConst.LEVEL_3, desc="缓存变更", parentDesc=FunLogConst.DESC_SYSTEM_CACHE)
	@RequestMapping("/forwardEdit")
	public String forwardEdit(HttpServletRequest request) {
		return "system/cache_edit.jsp";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Pagination<CacheableDefineVO> list(HttpServletRequest request, CacheableDefineVO defineVO) {
		List<CacheableDefine> list = cacheManager.getCacheableDefines();
		List<CacheableDefineVO> voList = new ArrayList<CacheableDefineVO>();
		//转换
		if(CollectionUtils.isNotEmpty(list)){
			for(CacheableDefine define : list){
				voList.add(new CacheableDefineVO(define));
			}
		}
		return new Pagination<CacheableDefineVO>(voList.size(), 0, voList.size(), voList);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public ActionResult edit(HttpServletRequest request, String id) {
		ActionResult result = new ActionResult();
		result.setSuccess(true);
		result.put("define", new CacheableDefineVO(cacheManager.getCacheableDefineByID(id)));
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ActionResult update(HttpServletRequest request, CacheableDefine define) {
		ActionResult result = new ActionResult();
		CacheableDefine po = cacheManager.getCacheableDefineByID(define.getId());
		if(po==null){
			result.setMessage("设置失败，未找到对应方法!");
			return result;
		}
		//改变相关参数
		po.setState(define.getState());
		po.setTimeToLive(define.getTimeToLive());
		po.setTimeToIdle(define.getTimeToIdle());
		result.setSuccess(true);
		return result;
	}
	
	@LogOpt(level=FunLogConst.LEVEL_3, desc="缓存状态变更", parentDesc=FunLogConst.DESC_SYSTEM_CACHE)
	@RequestMapping("/updateState")
	@ResponseBody
	public ActionResult updateState(CacheableDefine define) {
		ActionResult result = new ActionResult();
		CacheableDefine po = cacheManager.getCacheableDefineByID(define.getId());
		if(po==null){
			result.setMessage("设置失败，未找到对应方法!");
			return result;
		}
		//改变相关参数
		po.setState(define.getState());
		result.setSuccess(true);
		return result;
	}
	
	@LogOpt(level=FunLogConst.LEVEL_3, desc="缓存管理器状态变更", parentDesc=FunLogConst.DESC_SYSTEM_CACHE)
	@RequestMapping("/change")
	@ResponseBody
	public ActionResult change(HttpServletRequest request, @RequestParam(value="state",defaultValue="true",required=false)boolean state){
		ActionResult result = new ActionResult();
		try {
			((SimpleCacheManager)cacheManager).setOpen(state);
			result.setSuccess(true);
			result.put("state", cacheManager.isOpen());
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
}
