package com.xie.tsa.action.worker;

import com.alibaba.fastjson.JSON;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysUserService;
import com.xie.tsa.entity.HelpInfo;
import com.xie.tsa.entity.SysUserToken;
import com.xie.tsa.entity.Talk;
import com.xie.tsa.entity.UserLocationRecord;
import com.xie.tsa.service.HelpInfoService;
import com.xie.tsa.service.TokenService;
import com.xie.tsa.utils.HelpInfoManager;
import com.xie.tsa.utils.LocationManager;
import com.xie.tsa.utils.TokenManager;
import com.xie.tsa.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.Location;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/worker/")
public class WorkAction {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    TokenService tokenService;

    @Autowired
    HelpInfoService helpInfoService;

    private static Logger logger = LoggerFactory.getLogger(WorkAction.class);
    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String login(String workerAccount, String workerPassword) {
        SysUser sysUser = sysUserService.getSysUser(workerAccount);
        //检查传入的账号是否正确
        if (sysUser == null) {
            Talk<String> stringTalk = new Talk<>("200", "fail");
            stringTalk.setData("loginaccount error");
            return JSON.toJSONString(stringTalk);
        }

        //检查密码是够正确
        if (!sysUser.getUserPwd().equals(workerPassword)) {
            Talk<String> stringTalk = new Talk<>("200", "fail");
            stringTalk.setData("password error");
            return JSON.toJSONString(stringTalk);
        }

        //都正确的情况下，返回用户信息和token
        Talk<Map> stringTalk = new Talk<>("200", "success");
        Map<String, Object> map = new HashMap<>();
        map.put("worker", sysUser);

        SysUserToken byTelphone = tokenService.findByTelphone(sysUser.getTelephone());
        //为空说明是第一次登陆，需要生成token
        if (byTelphone == null) {
            SysUserToken token = new SysUserToken(sysUser.getTelephone(), TokenUtils.getToken(sysUser));
            TokenManager.getInstance().addToken(sysUser.getTelephone(), token.getToken());
            tokenService.insert(token);
            map.put("token", token);
        }else{
            map.put("token", byTelphone);
        }
        stringTalk.setData(map);
        return JSON.toJSONString(stringTalk);
    }

    @RequestMapping(value = "/updateLocation", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateLocation(String token, String userLongitude, String userLatitude) {
        String telphone = TokenUtils.getTelphone(token);
        logger.info("更新位置"+userLongitude+",",userLatitude);
        UserLocationRecord record = new UserLocationRecord(telphone, userLongitude, userLatitude);
        //查询已经存在的位置，和状态
        UserLocationRecord workerLocationRecord = LocationManager.getInstance().getWorkerLocationRecord(telphone);
        if (workerLocationRecord != null) {
            record.setState(workerLocationRecord.getState());
        } else {
            //第一次更新位置消息，他的状态一定是空闲的
            record.setState(0);
        }
        LocationManager.getInstance().addWorkLocationRecord(record);

        Talk<String> stringTalk = new Talk<>("200", "success");
        stringTalk.setData("updateLocation success");
        return JSON.toJSONString(stringTalk);
    }

    @RequestMapping(value = "/finishHelpInfo", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String finishHelpInfo(String token, String helpInfoId) {
        //将位置信息中的状态改变
        String telphone = TokenUtils.getTelphone(token);
        helpInfoService.finishHelpInfo(helpInfoId, telphone);

        Talk<String> stringTalk = new Talk<>("200", "success");
        stringTalk.setData("finishHelpInfo success");
        return JSON.toJSONString(stringTalk);
    }

    @RequestMapping(value = "/getHelpInfo", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getHelpInfo(String token) {
        String telphone = TokenUtils.getTelphone(token);
        logger.info("工作人员电话号码为"+telphone);
        HelpInfo helpInfo = helpInfoService.assignedTask(telphone);

        if (helpInfo != null) {
            Talk<HelpInfo> stringTalk = new Talk<>("200", "success");
            stringTalk.setData(helpInfo);
            return JSON.toJSONString(stringTalk);
        }
        Talk<String> stringTalk = new Talk<>("200", "fail");
        return JSON.toJSONString(stringTalk);
    }

    @RequestMapping(value = "/getTouristLocation", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getLocation(String token) {
        String telphone = TokenUtils.getTelphone(token);
        String touriseTelphone = HelpInfoManager.getInstance().getRelatedTelphone(telphone);
        UserLocationRecord touristLocationRecord = LocationManager
                .getInstance().getTouristLocationRecord(touriseTelphone);
        if(touristLocationRecord == null){
            Talk<String> talk = new Talk<>("200", "fail");
            talk.setData("未查找到");
            return JSON.toJSONString(talk);
        }
        Talk<UserLocationRecord> talk = new Talk<>("200", "success");
        talk.setData(touristLocationRecord);
        return JSON.toJSONString(talk);
    }

}
