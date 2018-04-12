package com.hd.tsa.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.WxActivityDetail;
import com.hd.tsa.entity.WxAdInfo;
import com.hd.tsa.entity.WxModulInfo;
import com.hd.tsa.service.WxActivityDetailService;
import com.hd.tsa.service.WxAdInfoService;
import com.hd.tsa.service.WxModulInfoService;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

/**
 * 微信广告控制类
 * @author LG
 *
 */
@Controller
@RequestMapping("/index")
public class IndexAction {

	private static Logger logger = Logger.getLogger(IndexAction.class);
	
	@Autowired
	private WxAdInfoService wxAdInfoService;
	@Autowired
	private WxModulInfoService wxModulInfoService;
	@Autowired
	private WxActivityDetailService wxActivityDetailService;
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取首页顶部广告", parentDesc = "微信管理")
	@RequestMapping("/getBaseData")
	@ResponseBody
	public Map<String,Object> getTopAd() {
		Map<String,Object> map=new HashMap<>();
		try {
			WxAdInfo entity=new WxAdInfo();
			entity.setAdPosition("004001");
			entity.setSortColumns("id desc limit 0,5");
			entity.setDeliveryFlag(1);
			entity.setDeleteFlag(0);
			List<WxAdInfo> topList=wxAdInfoService.findByCondition(entity);
			map.put("topAds", topList);
			entity.setAdPosition("004002");
			entity.setSortColumns("id desc limit 0,1");
			List<WxAdInfo> bottomList=wxAdInfoService.findByCondition(entity);
			if(bottomList!=null && bottomList.size()>0){
				map.put("bottomAd", bottomList.get(0));
			} else {
				map.put("bottomAd", null);
			}
			
			WxModulInfo wxModulInfo=new WxModulInfo();
			wxModulInfo.setReleaseFlag(1);
			wxModulInfo.setDeleteFlag(0);
			wxModulInfo.setSortColumns("id");
			List<WxModulInfo> modulList=wxModulInfoService.findByCondition(wxModulInfo);
			map.put("modulList", modulList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/goTelephone")
	public ModelAndView goTelephone(String openid){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("openid", openid);
		modelAndView.setViewName("telephone.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/goOfdrsp")
	public ModelAndView goOfdrsp(String openid){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("openid", openid);
		modelAndView.setViewName("ofdrsp.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/getActivity")
	@ResponseBody
	public Map<String,Object> getActivity() {
		Map<String,Object> map=new HashMap<>();
		try {
			List<WxActivityDetail> list = wxActivityDetailService.findLayout();
			List<List<WxActivityDetail>> resultList=new ArrayList<>();
			List<WxActivityDetail> lineList=null;
			int layId=0;
			int lineNum=1;
			for(int i=0;i<list.size();i++){
				if(layId!=list.get(i).getLayoutId()){
					layId=list.get(i).getLayoutId();
					if(lineList!=null){
						resultList.add(lineList);
						lineList=new ArrayList<>();
						lineNum++;
					}
				}
				if(lineList==null){
					lineList=new ArrayList<>();
				}
				lineList.add(list.get(i));
				
			}
			if(lineList!=null){
				resultList.add(lineList);
			}
			
			map.put("lineNum", lineNum);
			map.put("list", resultList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
}
