package com.xie.tsa.action.admin;

import com.alibaba.fastjson.JSON;
import com.hd.sys.core.online.OnlineUserManager;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysUserService;
import com.xie.tsa.entity.HelpInfo;
import com.xie.tsa.entity.Notice;
import com.xie.tsa.entity.Talk;
import com.xie.tsa.service.HelpInfoService;
import com.xie.tsa.service.NoticeService;
import com.xie.tsa.service.TsaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class CivilServantAction {

    @Autowired
    OnlineUserManager onlineUserManager;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    TsaUserService tsaUserService;


    @Autowired
    NoticeService noticeService;

    @Autowired
    HelpInfoService helpInfoService;
    private static Logger logger = LoggerFactory.getLogger(CivilServantAction.class);

    @RequestMapping(value = "/showRegistUsersByPage")
    public String showALLTourist() throws Exception {
        if (isPermit()) {
            return "/admin/userRegist.jsp";
        }
        return "/login.jsp";
    }


    /**
     * 是否拥有管理员权限
     *
     * @return
     * @throws Exception
     */
    public boolean isPermit() throws Exception {
        SysUser currentUser = onlineUserManager.getCurrentUser();
        //判断用户权限如果是普通用户权限或者未登录则失败
        if (currentUser == null) {
            return false;
        }
        SysUser rolesByUserId = sysUserService.getRolesByUserId(currentUser.getUserId()).get(0);
        logger.info("当前用户角色为：" + rolesByUserId.getRoleName());
        if (!(rolesByUserId.getRoleId() == 1)) {
            return false;
        }
        return true;
    }

    /**
     * 根据id批量删除注册信息
     *
     * @return
     */
    @RequestMapping(value = "/deleteUsers", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String deleteByIds(String userIds) throws Exception {
        if (!isPermit()) {
            return JSON.toJSONString(new Talk<String>("200", "fail"));
        }
        String[] split = userIds.split(",");
        if (split.length == 0) {
            return JSON.toJSONString(new Talk<String>("200", "fail"));
        }
        logger.info("ids为" + userIds);
        Boolean flag = tsaUserService.deleteUserByIds(split);
        if (flag) {
            return JSON.toJSONString(new Talk<String>("200", "success"));
        }
        return JSON.toJSONString(new Talk<String>("200", "fail"));
    }

    @RequestMapping("/getNoticeUI")
    public String getNotice(Integer currentPage, Model model) {
        if (currentPage == null) {
            currentPage = 0;
        }
        SysUser sysUser = new SysUser();
        sysUser.setRoleId((long) 1);
        try {
            List<SysUser> adminUsers = sysUserService.findUserListByOrgIdOrRoleId(sysUser);
            logger.info("查出的管理员有+" + adminUsers.size());
            Map<Long, String> admainNames = new HashMap<>();
            for (SysUser user :
                    adminUsers) {
                logger.info("查出的管理员有+" + user.getUserName());
                admainNames.put(user.getUserId(), user.getUserName());
            }
            model.addAttribute("userNames", admainNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Notice> notices = noticeService.getNotices(new Notice(), currentPage * 12, 8);
        Collections.sort(notices);
        model.addAttribute("notices", notices);
        return "admin/notice.jsp";
    }

    @RequestMapping("/addNotice")
    @ResponseBody
    public String addNotice(Notice notice) {
        logger.info(notice.getRemark() + "   " + notice.getType());
        noticeService.insertNotice(notice);
        return JSON.toJSONString(new Talk<String>("200", "success"));
    }

    @RequestMapping("/updateNotice")
    @ResponseBody
    public String updateNotice(Notice notice) {
        logger.info(notice.getRemark() + "   " + notice.getType());
        Notice exitNotice = noticeService.findNoticeByPk(notice.getNoticeId());
        if (exitNotice == null) {
            return JSON.toJSONString(new Talk<String>("200", "fail"));
        }
        exitNotice.setRemark(notice.getRemark());
        noticeService.updateNotice(exitNotice);
        return JSON.toJSONString(new Talk<String>("200", "success"));
    }

    @RequestMapping("/deleteNotice")
    @ResponseBody
    public String deleteNotice(Notice notice) {
        logger.info(notice.getRemark() + "   " + notice.getType());
        Notice exitNotice = noticeService.findNoticeByPk(notice.getNoticeId());
        if (exitNotice == null) {
            return JSON.toJSONString(new Talk<String>("200", "fail"));
        }
        try {
            noticeService.deleteNotice(exitNotice.getNoticeId());
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(new Talk<String>("200", "fail"));
        }
        return JSON.toJSONString(new Talk<String>("200", "success"));
    }

}
