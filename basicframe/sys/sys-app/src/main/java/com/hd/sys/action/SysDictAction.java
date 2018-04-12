package com.hd.sys.action;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.sfw.core.model.Pagination;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysDict;
import com.hd.sys.service.SysDictService;

/**
 * <p>类名：SysDictAction </p>
 * <p>描述：业务类型页面控制类 </p>
 * <p>作者：somnuscy </p>
 */
@Controller
@RequestMapping("/sysDict")
public class SysDictAction {
	
	/** log4j */
	private static Logger logger = Logger.getLogger(SysDictAction.class);
	
	@Autowired
	private SysDictService sysDictService;
	
	/** 页面名称 */
	private final String VIEW_MODEL = "sysTypeDict";
	
	/** 页面路径 */
	private final String PAGES_URL="/../";
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc=FunLogConst.DESC_SYSTEM_DICT, parentDesc=FunLogConst.DESC_SYSTEM)
	@RequestMapping("/forwardDict")
	public String forwardDict(HttpServletRequest request){
		return "system/dict_list.jsp";
	}
	
	/**
	 * <p>描述：激活 </p>
	 * <p>日期：2014-12-17 下午03:50:51 </p>
	 * @param sysTypeDict
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activateTypeDict")
	public boolean activateTypeDict(HttpServletRequest request,SysDict sysTypeDict) {
		try {
			String[] codes = sysTypeDict.getTypeDictCode().split(",");
			SysDict typeDict = new SysDict();
			for (int i = 0; i < codes.length; i++) {
				typeDict.setTypeDictCode(codes[i]);
				sysDictService.updateDictActivate(typeDict);
			}
			return true;
		} catch (Exception e) {
			logger.error("冻结类型出错：" + e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * <p>描述：冻结 </p>
	 * <p>日期：2014-12-17 上午10:29:36 </p>
	 * @param sysTypeDict
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/freezeTypeDict")
	public boolean freezeTypeDict(HttpServletRequest request,SysDict sysTypeDict) {
		try {
			String[] codes = sysTypeDict.getTypeDictCode().split(",");
			SysDict typeDict = new SysDict();
			for (int i = 0; i < codes.length; i++) {
				typeDict.setTypeDictCode(codes[i]);
				sysDictService.updateDictFreeze(typeDict);
			}
			return true;
		} catch (Exception e) {
			logger.error("冻结类型出错：" + e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * <p>描述： 修改类型</p>
	 * <p>日期：2014-12-16 下午05:22:52 </p>
	 * @param sysTypeDict
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editTypeDict")
	public boolean editTypeDict(HttpServletRequest request, SysDict sysTypeDict) {
		try {
			if (StringUtils.isBlank(sysTypeDict.getOldTypeDictCode())) {
				sysDictService.save(sysTypeDict);
			} else {
				sysDictService.updateById(sysTypeDict);
			}
			return true;
		} catch (Exception e) {
			logger.error("编辑类型出错：" + e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * <p>描述： 修改类型页面</p>
	 * <p>日期：2014-12-16 下午04:08:55 </p>
	 * @param code
	 * @return
	 */
	@RequestMapping("/editTypeDictView")
	public ModelAndView editTypeDictView(HttpServletRequest request, String code){
		ModelAndView modelAndView=new ModelAndView();
		try {
			SysDict typeDict = sysDictService.findById(code);
			if (!typeDict.getParentTypeDictCode().equals("0")&&StringUtils.isNotBlank(typeDict.getParentTypeDictCode())) {
				SysDict pa=sysDictService.findById(typeDict.getParentTypeDictCode());
				if (pa!=null) {
					typeDict.setParentTypeDictName(pa.getTypeDictName());
				}
			}
			modelAndView.addObject("typeDict", typeDict);
			modelAndView.setViewName("system/dict_edit.jsp");
		 } catch (Exception e) {
			logger.error("查询列表出错：" + e.getMessage(), e);
		}
        return modelAndView;
	}
	
	/**
	 * <p>描述： 验证类型编码是否存在</p>
	 * <p>日期：2014-12-16 下午03:26:33 </p>
	 * @param typeDictCode 类型编码
	 * @return 如果存在返回true,否则返回false
	 */
	@ResponseBody
	@RequestMapping("/validateTypeDictCode")
	public boolean validateTypeDictCode(HttpServletRequest request,String typeDictCode) {
		SysDict typeDict = null;
		try {
			typeDict = sysDictService.findById(typeDictCode);
		} catch (Exception e) {
			logger.error("新增类型：" + e.getMessage(), e);
		}
		return typeDict!=null?true:false;
	}
	
	/**
	 * <p>描述：新增类型页面 </p>
	 * <p>日期：2014-12-16 上午10:50:46 </p>
	 * @param pid
	 * @param pName
	 * @return
	 */
	@RequestMapping("/addTypeDictView")
	public ModelAndView addTypeDictView(HttpServletRequest request, String pid, String pName){
		ModelAndView modelAndView=new ModelAndView();
		try {
			SysDict sysDict = new SysDict();
			sysDict.setParentTypeDictCode(pid);
			sysDict.setParentTypeDictName(pName);
			modelAndView.addObject("typeDict", sysDict);
			modelAndView.setViewName("system/dict_edit.jsp");
		 } catch (Exception e) {
			logger.error("新增类型页面出错：" + e.getMessage(), e);
		}
        return modelAndView;
	}
	
    /**
	 * <p>描述：页面跳转方法 </p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param request
	 * @param sysTypeDict
	 */
	@RequestMapping("/browse")
	public ModelAndView browse(HttpServletRequest request,SysDict sysTypeDict){
		ModelAndView modelAndView=new ModelAndView();
		try {
    		modelAndView.setViewName(PAGES_URL+"list_"+VIEW_MODEL);
		 } catch (Exception e) {
			logger.error("查询列表出错：" + e.getMessage(), e);
		}
        return modelAndView;
	}
	
	/**
	 * <p>描述： 获取业务类型表格树</p>
	 * <p>日期：2014-12-9 下午05:15:48 </p>
	 * @return List<SysTypeDict>
	 */
	@ResponseBody
	@RequestMapping("/getTypeDictTableTree")
	public Pagination<SysDict> getTypeDictTableTree(HttpServletRequest request,SysDict typeDict) {
		try {
			if (StringUtils.isBlank(typeDict.getParentTypeDictCode())) {
				typeDict.setState(1L);
				typeDict.setParentTypeDictCode("0");
			}
			return sysDictService.findPageByCondition(typeDict);
		} catch (Exception e) {
			logger.error("获取业务类型表格树出错：" + e.getMessage(), e);
		}
		return null;
	}
	
}