package com.hd.tsa.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.tsa.entity.CommentInfo;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;

/**
 * 商家评价管理控制层
 * @author 寂寞先生
 * 2018年1月15日
 */
@Controller
@RequestMapping("/commentManage")
public class CommentManageAction {

	private static Logger logger = Logger.getLogger(CommentManageAction.class);
	
	/**
	 * 根据用户account获取商家订单评价信息
	 * @param account
	 * @param pageNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCommentList")
	@SuppressWarnings("unchecked")
	public ActionResult getCommentList(Long storeId, Integer pageNum){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(CommentManageAction.class, "propurl", "diet").concat("manageCommentInfo/findPage.action");
			String resultStr = HttpUtils.sendPost(getData, "storeId="+storeId+"&pageNum="+pageNum, 0);
			Map<String, Object> map =JsonUtils.json2Object(resultStr, HashMap.class, null);
			if (CollectionUtils.isNotEmpty(map)) {
				result.put("commentList", map.get("list"));
				result.put("totalPages", map.get("pageNums"));
			} else {
				result.put("totalPages", 0);
				result.put("commentList", null);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMessage("获取商家评价数据失败");
			logger.error("获取商家评价列表失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 商家回复
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveReplay")
	public ActionResult saveReplay(CommentInfo entity){
		ActionResult result = new ActionResult();
		try {
			entity.setReplayFlag(1);
			Object[] objs = {entity};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(CommentManageAction.class, "propurl", "diet").concat("manageCommentInfo/updateComment.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			Boolean restr =JsonUtils.json2Object(resultStr, Boolean.class, null);
			result.setSuccess(restr);
		} catch (Exception e) {
			logger.error("商家回复失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
}
