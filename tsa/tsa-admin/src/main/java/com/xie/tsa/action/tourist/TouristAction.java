package com.xie.tsa.action.tourist;

import com.alibaba.fastjson.JSON;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysUserService;
import com.xie.tsa.action.map.LocationAction;
import com.xie.tsa.entity.*;
import com.xie.tsa.service.HelpInfoService;
import com.xie.tsa.service.MapService;
import com.xie.tsa.service.TsaUserService;
import com.xie.tsa.utils.HelpInfoManager;
import com.xie.tsa.utils.LocationManager;
import com.xie.tsa.utils.TokenManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/api/tourist/")
@Controller
public class TouristAction {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    HelpInfoService helpInfoService;

    @Autowired
    TsaUserService tsaUserService;

    @Autowired
    MapService mapService;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(LocationAction.class);

    @RequestMapping("/login")
    @ResponseBody
    public String login(String telphone) {
        logger.info("用户电话号码" + telphone);
        String num = "1234567890";
        for (int i = 0; i < telphone.length(); i++) {
            String c = telphone.substring(i, i);
            if (!num.contains(c)) {
                Talk<String> stringTalk = new Talk<>("200", "fail");
                stringTalk.setData("format error");
                return JSON.toJSONString(stringTalk);
            }
        }
        if (telphone == null & telphone.length() != 11) {
            Talk<String> stringTalk = new Talk<>("200", "fail");
            stringTalk.setData("need telphoneNum");
            return JSON.toJSONString(stringTalk);
        }
        SysUser sysUser = new SysUser();
        sysUser.setOrgId((long) 191);
        sysUser.setUserName(telphone);
        sysUser.setUserAccount(telphone);
        sysUser.setState((long) 0);
        sysUser.setUserPwd("123456");
        sysUser.setPwdState((long) 1);
        sysUser.setTelephone(telphone);
        try {
            String token = tsaUserService.login(sysUser);
            Talk<String> stringTalk = new Talk<>("200", "success");
            stringTalk.setData(token);
            TokenManager.getInstance().addToken(telphone, token);
            logger.info(telphone + token);
            return JSON.toJSONString(stringTalk);

        } catch (Exception e) {
            e.printStackTrace();
            Talk<String> stringTalk = new Talk<>("200", "fail");
            stringTalk.setData("insert error");
            return JSON.toJSONString(stringTalk);
        }
    }

    @RequestMapping("updateLocation")
    @ResponseBody
    public String updateLocation(String token, String userLongitude, String userLatitude) {
        /**
         * userLongitude;//用户经度
         private String userLatitude;/
         */

        String telphone = TokenManager.getInstance().exitToken(token);
        logger.info("用户更新地理位置" + telphone);
        if (telphone == null)
            return JSON.toJSONString(new Talk<String>("200", "fail", "not login"));
        if (userLongitude != null & !"".equals(userLongitude) & userLatitude != null & !"".equals(userLatitude)) {
            UserLocationRecord record = new UserLocationRecord(telphone, userLongitude, userLatitude);
            LocationManager.getInstance().addUserLocationRecord(record);
            return JSON.toJSONString(new Talk<String>("200", "success"));
        }
        return JSON.toJSONString(new Talk<String>("200", "fail", "update fail"));
    }


    @RequestMapping(value = "addHelpInfo", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addHelpInfo(HelpInfo helpInfo, String token) {

        String telphone = TokenManager.getInstance().exitToken(token);

        logger.info(telphone + "添加求助信息" + helpInfo.toString());
        logger.info(token);
        if (telphone == null) {
            return JSON.toJSONString(new Talk<String>("200", "fail"));
        }
        //这部分应该放在service中的另一部分一起
        SysUser sysUser = sysUserService.getSysUser(telphone);
        Integer id = Integer.parseInt(sysUser.getUserId() + "");
        helpInfo.setCreatedBy(id);
        helpInfo.setUserName(telphone);

        if ((helpInfo = helpInfoService.addHelpInfo(helpInfo)) != null) {
            Talk<HelpInfo> success = new Talk<>("200", "success");
            success.setData(helpInfo);
            return JSON.toJSONString(helpInfo);
        }
        return JSON.toJSONString(new Talk<String>("200", "fail"));
    }

    @RequestMapping("/finishHelpInfo")
    @ResponseBody
    public String finishHelpInfo(String helpInfoId, String token) {
        String telphone = TokenManager.getInstance().exitToken(token);

        if (telphone == null) {
            return JSON.toJSONString(new Talk<String>("200", "fail"));
        }
        helpInfoService.touristFinishHelpInfo(helpInfoId);
        return JSON.toJSONString(new Talk<String>("200", "success"));
    }

    @RequestMapping(value = "/getSelfHelpInfo", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getSelfHelpInfo(String token) {
        String telphone = TokenManager.getInstance().exitToken(token);

        if (telphone == null) {
            return JSON.toJSONString(new Talk<String>("200", "fail"));
        }
        Talk<List<HelpInfo>> success = new Talk<>("200", "success");
        success.setData(helpInfoService.getSelfHelpInfo(telphone));
        return JSON.toJSONString(success);
    }

    @RequestMapping(value = "/getWorkerLocation", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getWorkerLocation(String token) {
        String telphone = TokenManager.getInstance().exitToken(token);
        String workerTelphone = HelpInfoManager.getInstance().getWorkerTelphone(telphone);
        UserLocationRecord workerLocationRecord = LocationManager.getInstance()
                .getWorkerLocationRecord(workerTelphone);
        if (workerLocationRecord == null) {
            return JSON.toJSONString(new Talk<String>("200", "fail"));
        }

        Talk<UserLocationRecord> success = new Talk<>("200", "success");
        success.setData(workerLocationRecord);
        return JSON.toJSONString(success);
    }

    @RequestMapping(value = "/getPlace", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAllPlace(String token) {
        String telphone = TokenManager.getInstance().exitToken(token);
        //判断权限是否登陆了
        if(telphone == null){
            //查询失败data 为null
            Talk<List<PlaceLocation>> talk = new Talk<>("200", " data null");
            talk.setData(null);
            return JSON.toJSONString(talk);
        }
        List<PlaceLocation> placeLocationList = mapService.getAllPlace();
        if (placeLocationList != null) {
            //查询成功data为placelist
            Talk<List<PlaceLocation>> talk = new Talk<>("200", "success");
            talk.setData(placeLocationList);
            return JSON.toJSONString(talk);
        } else {
            //查询失败data 为null
            Talk<List<PlaceLocation>> talk = new Talk<>("200", " data null");
            talk.setData(null);
            return JSON.toJSONString(talk);
        }
    }
}
