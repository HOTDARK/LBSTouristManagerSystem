package com.hd.tsa.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.ByoMaterial;
import com.hd.tsa.entity.UserInfo;
import com.hd.tsa.entity.VehicleMaintain;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 维保控制类
 * @author LG
 *
 */
@Controller
@RequestMapping("/maintain")
public class MaintainAction extends UserBaseAction {

	private static Logger logger = Logger.getLogger(MaintainAction.class);
	
	/**
	 * 跳转维修保养页面
	 * @param openid
	 * @param idNum
	 * @param backAccount
	 * @return
	 */
	@RequestMapping("/goMaintain")
	public ModelAndView getMyUseVehicleList(String openid,int idNum,String backAccount) {
		ModelAndView view=new ModelAndView("maintain.jsp");
		try {
			view.addObject("idNum", idNum);
			view.addObject("openid", openid);
			Map<String, Object> map = getUserInfo(null, null, null, openid);
			if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				view.addObject("userInfo", userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	/**
	 * 获取厂家、车辆和材料
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findConfig")
	public Map<String,Object> findConfig(){
		Map<String, Object> map=null;
		try {
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxVehicleMaintain/findConfig.action");
			String result = HttpUtils.sendPost(getDataUrl, "", 0);
			map=JsonUtils.json2Object(result, Map.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增维保
	 * @param entity
	 * @param mcode
	 * @param mcount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveMaintain")
	public boolean saveMaintain(VehicleMaintain entity, String mcode,String mcount){
		boolean iss=false;
		try {
			String listStr="";
			if (mcode != null && !"".equals(mcode) && !"".equals(mcount) && mcount != null) {
				List<ByoMaterial> list=new ArrayList<>();
				ByoMaterial bm=null;
				String[] mcodeArr = mcode.split(",");
				String[] mcountArr = mcount.split(",");
				for(int i=0;i<mcodeArr.length;i++){
					bm=new ByoMaterial();
					bm.setMaterialCode(mcodeArr[i]);
					bm.setCount(Integer.parseInt(mcountArr[i]));
					list.add(bm);
				}
				listStr=JSONArray.fromObject(list).toString();
			}
			Object[] objs={entity};
			String param=HttpUtils.packParam(objs).concat("&listStr="+listStr);
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxVehicleMaintain/sveVehicleMaintain.action");
			String result = HttpUtils.sendPost(getDataUrl, param, 0);
			iss=Boolean.parseBoolean(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return iss;
	}
	
	/**
	 * 获取我的维保列表
	 * @param applicant
	 * @param iDisplayLength
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findMyList")
	public Map<String, Object> findMyList(String applicant,int iDisplayLength, int pageNum){
		Map<String, Object> map=null;
		try {
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxVehicleMaintain/findByPage.action");
			String result = HttpUtils.sendPost(getDataUrl, "applicant="+applicant+"&iDisplayLength="+iDisplayLength+"&pageNum="+pageNum, 0);
			map=JsonUtils.json2Object(result, Map.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 跳转详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/goDetail")
	public ModelAndView goDetail(Long id,String openid){
		ModelAndView view=new ModelAndView("maintainDetail.jsp");
		try {
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxVehicleMaintain/findById.action");
			String result = HttpUtils.sendPost(getDataUrl, "id="+id, 0);
			VehicleMaintain maintain=JsonUtils.json2Object(result, VehicleMaintain.class, null);
			view.addObject("maintain", maintain);
			view.addObject("openid", openid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 获取我的维保列表
	 * @param applicant
	 * @param iDisplayLength
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findMyPermitList")
	public Map<String, Object> findMyPermitList(String permitCode,int pageLength, int pageNum){
		Map<String, Object> map=null;
		try {
			permitCode=permitCode.replace("|", "");
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxVehicleMaintain/findByMenuCode.action");
			String result = HttpUtils.sendPost(getDataUrl, "menuCode="+permitCode+"&pageLength="+pageLength+"&pageNum="+pageNum, 0);
			map=JsonUtils.json2Object(result, Map.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 跳转审核页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/goBack")
	public ModelAndView goBack(Long id,String userAccount,String openid){
		ModelAndView view=new ModelAndView("maintainBack.jsp");
		try {
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxVehicleMaintain/findById.action");
			String result = HttpUtils.sendPost(getDataUrl, "id="+id, 0);
			VehicleMaintain maintain=JsonUtils.json2Object(result, VehicleMaintain.class, null);
			view.addObject("maintain", maintain);
			view.addObject("userAccount", userAccount);
			view.addObject("openid", openid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 审核维保信息
	 * @param applicant
	 * @param iDisplayLength
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/audit")
	public Map<String, Object> audit(Long id, int state, int exmainStat, String opinion, String userAccount, String mcode,String mcount){
		Map<String, Object> map=new HashMap<>();
		try {
			String listStr="";
			if (mcode != null && !"".equals(mcode) && !"".equals(mcount) && mcount != null) {
				List<ByoMaterial> list=new ArrayList<>();
				ByoMaterial bm=null;
				String[] mcodeArr = mcode.split(",");
				String[] mcountArr = mcount.split(",");
				for(int i=0;i<mcodeArr.length;i++){
					bm=new ByoMaterial();
					bm.setMaterialCode(mcodeArr[i]);
					bm.setCount(Integer.parseInt(mcountArr[i]));
					list.add(bm);
				}
				listStr=JSONArray.fromObject(list).toString();
			}
			if(state==2 && exmainStat==0){
				String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxVehicleMaintain/checkCount.action");
				String result = HttpUtils.sendPost(getDataUrl, "listStr="+listStr, 0);
				Map<String, Object> checkMap=JsonUtils.json2Object(result, Map.class, null);
				if(!(boolean)checkMap.get("result")){
					return checkMap;
				}
			}
			
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxVehicleMaintain/updateState.action");
			String result = HttpUtils.sendPost(getDataUrl, "id="+id+"&state="+state+"&exmainStat="+exmainStat+"&opinion="+opinion+"&userAccount="+userAccount+"&listStr="+listStr, 0);
			map.put("result", Boolean.parseBoolean(result));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取详情
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getDetail")
	@ResponseBody
	public Map<String,Object> getDetail(Long id){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			String getDataUrl1 = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxVehicleMaintain/findConfig.action");
			String result1 = HttpUtils.sendPost(getDataUrl1, "", 0);
			map=JsonUtils.json2Object(result1, Map.class, null);
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxVehicleMaintain/findById.action");
			String result = HttpUtils.sendPost(getDataUrl, "id="+id, 0);
			VehicleMaintain maintain=JsonUtils.json2Object(result, VehicleMaintain.class, null);
			map.put("byoList", maintain.getByoList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
