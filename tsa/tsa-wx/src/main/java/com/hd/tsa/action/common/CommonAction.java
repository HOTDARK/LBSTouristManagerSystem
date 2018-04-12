package com.hd.tsa.action.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

@Controller
@RequestMapping("/common")
public class CommonAction {

	/**
	 * 跳转选择人员页面
	 * @return
	 */
	@LogOpt(level=FunLogConst.LEVEL_3, desc="跳转选择人员页面", parentDesc="跳转选择人员页面")
	@RequestMapping("/forwardSelectPerson")
	public String forwardSelectStaff(){
		return "common/select_person.jsp";
	}
	
	/**
	 * 跳转选择单个用户页面
	 * @author wh
	 * @date 2017年7月6日
	 * @return
	 */
	@RequestMapping("forwordSelectOneUser")
	public String forwordSelectPage(){
		return "common/select_oneUser.jsp";
	}
}
