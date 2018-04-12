package com.xie.tsa.action.map;

import com.alibaba.fastjson.JSON;
import com.hd.sys.action.base.BaseAction;
import com.hd.sys.core.online.OnlineUserManager;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysUserService;
import com.xie.tsa.entity.Talk;
import com.xie.tsa.entity.PlaceLocation;
import com.xie.tsa.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationAction extends BaseAction {

    @Autowired
    MapService mapService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    OnlineUserManager onlineUserManager;
    private static Logger logger = LoggerFactory.getLogger(LocationAction.class);

    /**
     * data = {"type":id,"location":location,"createdBy":${currentUser.userId}};
     *插入一条地点
     * @return
     */
    @RequestMapping("/addPlace")
    @ResponseBody
    public String addPlace(int type, String location, int createdBy, String remark) {

        logger.info( type + "    " + location + "   " + remark);
        try {
            if (!isPermit()) {
                Talk<String> talk = new Talk<>("200", "request power");
                return JSON.toJSONString(talk);
            }
            ;
        } catch (Exception e) {
            e.printStackTrace();
            Talk<String> talk = new Talk<>("200", "request power");
            return JSON.toJSONString(talk);
        }

        //判断数据是否完整
        if (type != 0 && createdBy != 0 && location != null && !"".equals(location)) {
            PlaceLocation placeLocation = new PlaceLocation();
            placeLocation.setCreatedBy(createdBy);
            placeLocation.setLocation(location);
            placeLocation.setType(type);
            placeLocation.setRemark(remark);

            //判断是否插入成功
            if (!mapService.insertPlaceLocation(placeLocation)) {
                Talk<String> talk = new Talk<>("200", "insert fail");
                return JSON.toJSONString(talk);
            }

        } else {
            Talk<String> talk = new Talk<>("200", "lost information");
            return JSON.toJSONString(talk);
        }
        //返回信息
        Talk<String> talk = new Talk<>("200", "success");
        return JSON.toJSONString(talk);
    }

    /**
     * 获取所有的地点
     * @return
     */
    @RequestMapping("/getPlace")
    @ResponseBody
    public String getAllPlace() {

        //判断权限
        SysUser currentUser = onlineUserManager.getCurrentUser();
        if (currentUser == null) {
            Talk<String> talk = new Talk<>("200", "request power");
            return JSON.toJSONString(talk);
        } else {
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

    /**
     *修改一个地点描述
     * @param placeId
     * @param remark
     * @return
     */
    @RequestMapping("/updatePlace")
    @ResponseBody
    public String updatePlaceById(Integer placeId, String remark) {
        if (placeId != null) {
            PlaceLocation exit = mapService.findPlaceById(placeId);
            if (exit != null) {
                exit.setRemark(remark);
                mapService.updateByPlace(exit);

            }
        } else {
            return JSON.toJSONString(new Talk<>("200", "输入有误"));
        }
        return JSON.toJSONString(new Talk<>("200", "修改成功"));
    }

    /**
     * 删除一个地点
     * @param placeId
     * @return
     */
    @RequestMapping("/deletePlace")
    @ResponseBody
    public String deletePlaceById(Integer placeId) {
        try {
            if (placeId != null&&isPermit()) {
                mapService.deleteByPlace(placeId);
            } else {
                return JSON.toJSONString(new Talk<>("200", "error input or no power"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(new Talk<>("200", "delete fail"));
        }
        return JSON.toJSONString(new Talk<>("200", "delete success"));
    }

    /**
     * 是否拥有管理员权限
     * @return
     * @throws Exception
     */
    public boolean isPermit() throws Exception {
        SysUser currentUser = onlineUserManager.getCurrentUser();
        //判断用户权限如果是普通用户权限则失败
        if (currentUser == null) {
            return false;
        }
        SysUser rolesByUserId = sysUserService.getRolesByUserId(currentUser.getUserId()).get(0);
        logger.info("roleId为：" + rolesByUserId.getRoleId());
        if (!(rolesByUserId.getRoleId() == 1)) {
            return false;
        }
        return true;
    }
}
