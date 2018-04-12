package com.xie.tsa.action.map;

import com.hd.sys.core.online.OnlineUserManager;
import com.hd.sys.entity.SysUser;
import com.xie.tsa.entity.PlaceInfo;
import com.xie.tsa.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hd.sys.action.base.BaseAction;

import java.util.List;

@Controller
@RequestMapping("/baiduMap")
public class MapAction extends BaseAction {

    @Autowired
    MapService mapService;

    @Autowired
    OnlineUserManager onlineUserManager;

    @RequestMapping("/showMapOption")
    public String forwardBaiduMap(Model model) {
        SysUser currentUser = onlineUserManager.getCurrentUser();

        if (currentUser != null) {
            List<PlaceInfo> allPlace = mapService.findAllPlace();
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("places", allPlace);
        }
        return "baiduMap/baiduMapOption.jsp";
    }

    @RequestMapping("/showMap")
    public String showMap(){
        return "baiduMap/showBaiduMap.jsp";
    }
}
