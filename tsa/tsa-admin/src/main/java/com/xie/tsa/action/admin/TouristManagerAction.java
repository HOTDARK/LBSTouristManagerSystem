package com.xie.tsa.action.admin;

import com.alibaba.fastjson.JSON;
import com.xie.tsa.entity.HelpInfo;
import com.xie.tsa.entity.Talk;
import com.xie.tsa.service.HelpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class TouristManagerAction {

    @Autowired
    HelpInfoService helpInfoService;

    @RequestMapping("/getHelpInfoUI")
    public String showTouristHelpInfoUI(Integer currentPage,Model model){
        if(currentPage == null){
            currentPage = 0;
        }
        Integer count  =  helpInfoService.helpInfoCount();
        model.addAttribute("count",count);
        List<HelpInfo> helpInfoByActive = helpInfoService.findHelpInfoByActive(currentPage * 20, currentPage * 20 + 20);
        model.addAttribute("helpInfos",helpInfoByActive);
        return "admin/helpInfo.jsp";
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public String changeHelpInfoState(HelpInfo helpInfo ){
        if(helpInfo.getHelpInfoId() != null){
            if( helpInfoService.changeState(helpInfo)){
                return JSON.toJSONString(new Talk<String>("200","success"));
            }
        }
        return JSON.toJSONString(new Talk<String>("200","fail"));
    }


    @RequestMapping("/getTouristInfoUI")
    public String showTouristInfoUI(Integer currentPage,Model model){
        return "admin/touristInfo.jsp";
    }


}
