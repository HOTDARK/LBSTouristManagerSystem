package com.hd.sys.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hd.sys.action.base.BaseAction;

/**
 * 统计功能页面控制类
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/statis")
public class StatisAction extends BaseAction {

	@RequestMapping("/forwardMap")
	public String forwardMap(HttpServletRequest request, HttpServletResponse response){
		return "statis/map_case.jsp";
	}
	
	@RequestMapping("/forwardMapBaidu")
	public String forwardMapBaidu(HttpServletRequest request, HttpServletResponse response){
		return "statis/map_baidu_case.jsp";
	}
	
	@RequestMapping("/forwardGauge")
	public String forwardGauge(HttpServletRequest request, HttpServletResponse response){
		return "statis/gauge_case.jsp";
	}
	
}
