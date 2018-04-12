package com.hd.sys.action;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.sys.action.base.BaseAction;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysFunc;
import com.hd.sys.service.SysFuncService;

/**
 * 
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/sysFunc")
public class SysFuncAction extends BaseAction {
	
	/** log4j */
	private static Logger logger = Logger.getLogger(SysFuncAction.class);
	
	@Autowired
	private SysFuncService sysFuncService;
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc=FunLogConst.DESC_SYSTEM_FUN, parentDesc=FunLogConst.DESC_SYSTEM)
	@RequestMapping("/forwardFunc")
	public String forwardFunc(HttpServletRequest request){
		return "system/func_list.jsp";
	}
	
    /**
	 * <p>描述：查询功能树 </p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param request
	 * @param sysFunction
	 */
	@ResponseBody
	@RequestMapping("/getPermFunctionTree")
	public List<SysFunc> getPermFunctionTree(HttpServletRequest request, SysFunc func){
		List<SysFunc> sysFunctionList = null;
		try {
			//获取功能权限
			String funcPermIds = getFuncsPermission(request);
			func.setFunctionIds(funcPermIds);
			func.setFunctionType(2L);
			sysFunctionList = sysFuncService.getPermFunctionTree(func);
			return sysFunctionList;
		} catch (Exception e) {
			logger.error("查询功能树出错" + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * <p>描述：功能信息查询 </p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param model 视图对象
	 * @param parentFunctionId 父级功能ID
	 */
	@ResponseBody
	@RequestMapping("/querySysFunctionList")
	public List<SysFunc> querySysFunctionList(HttpServletRequest request,SysFunc sysFunction)throws Throwable{
		List<SysFunc> sysFuncs = null;
		try {
			sysFuncs = sysFuncService.findByCondition(sysFunction);
		} catch (Exception e) {
			logger.error("功能信息查询" + e.getMessage(), e);
		}
		return sysFuncs;
	}
	/**
	 * <p>描述：进入新增页面 </p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param model 视图对象
	 * @param parentFunctionId 父级功能数据ID
	 * @param parentFunctionName 父级功能名字
	 * @return
	 */
	@RequestMapping("/saveSysFunction")
	public ModelAndView saveSysFunction(HttpServletRequest request, Long parentFunctionId, String parentFunctionName){
		List<Map<String, String>> iconList = null;
		try {
			iconList = sysFuncService.getAllIcon();
		} catch (Exception e) {
			logger.error("查询菜单图标出错：" + e.getMessage(), e);
		}		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("icoClass", iconList);
		SysFunc sysFunc = new SysFunc();
		sysFunc.setParentFunctionId(parentFunctionId);
		sysFunc.setParentFunctionName(parentFunctionName);
		sysFunc.setFunctionType(2L);
		modelAndView.addObject("sysFunction", sysFunc);
		modelAndView.setViewName("system/func_edit.jsp");
		return modelAndView;
	}
	
	/**
	 * <p>描述：根据functionId查询sysFunction </p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param model 视图对象
	 * @param functionId 功能数据ID
	 * @return
	 */
	@RequestMapping("/querySysFunction")
	public ModelAndView querySysFunction(HttpServletRequest request,Long functionId){
		ModelAndView modelAndView = new ModelAndView();
		SysFunc sysFunction = null;
		List<Map<String, String>> iconList = null;
		try {
			iconList = sysFuncService.getAllIcon();
			sysFunction = sysFuncService.findById(functionId);
		} catch (Exception e) {
			logger.error("查询菜单图标出错：" + e.getMessage(), e);
		}
        modelAndView.addObject("icoClass",iconList);
		modelAndView.addObject("sysFunction", sysFunction);
		modelAndView.setViewName("system/func_edit.jsp");
		return modelAndView;
	}
	
	/**
	 * <p>描述：功能信息修改 </p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param model 视图对象
	 * @param sysFunction 功能对象
	 * @param response HttpServletResponse
	 * @return msg 提示信息
	 */
	@ResponseBody
	@RequestMapping("/editSysFunction")
	public boolean editSysFunction(HttpServletRequest request, SysFunc sysFunc){
		try{
			if (sysFunc.getFunctionId() == null) {
				//修改父级FunctionLeafNode
				SysFunc parentFunc = new SysFunc();
				parentFunc.setFunctionId(sysFunc.getParentFunctionId());
				parentFunc.setFunctionLeafNode(0l);
				sysFuncService.updateById(parentFunc);
				sysFunc.setFunctionLeafNode(null);
				//功能信息添加 
				sysFuncService.save(sysFunc);
			} else {
				sysFuncService.updateById(sysFunc);
			}
		} catch (Exception e) {
			logger.error("editSysFunction修改出错：" + e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	/**
	 * <p>描述：功能冻结或激活修改 </p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param sysFunction
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editSysFunctionState")
	public boolean editSysFunctionState(String functionIds, String state){
		try {
			String[] codes = functionIds.split(",");
			SysFunc sysFunction = new SysFunc();
			for (int i = 0; i < codes.length; i++) {
				sysFunction.setFunctionId(Long.valueOf(codes[i]));
				sysFuncService.dealSysFunctionState(sysFunction, state);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 验证功能编码唯一性
	 * @param sysFunction 功能实体
	 * @return 返回true表示存在功能编码,返回false表示不存在功能编码
	 */
	@ResponseBody
	@RequestMapping("/validateHasFuncCode")
	public boolean validateHasFuncCode(SysFunc sysFunction){
		Long functionId = null;
		try{
			functionId = sysFuncService.getFuncIdByCode(sysFunction);
		} catch (Exception e) {
			logger.error("验证功能编码唯一出错：" + e.getMessage(), e);
		}
		return functionId!=null?true:false;
	}
}

