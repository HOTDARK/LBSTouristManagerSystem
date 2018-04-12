package com.xie.tsa.action.timer;

import com.xie.tsa.entity.HelpInfo;
import com.xie.tsa.service.HelpInfoService;
import com.xie.tsa.utils.HelpInfoManager;
import com.xie.tsa.utils.LocationManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class Timer implements InitializingBean {

    @Autowired
    HelpInfoService helpInfoService;

    /**
     * 定时任务，
     * 删除LocationManager中的位置信息
     * 每天1点删除一次
     */

    @Scheduled(cron = "0 0 1 * * ?")
    public void deleteAllTempLocation() {
        LocationManager.getInstance().removeAll();
    }

    /**
     * 清除HelpInfoManager中所有的缓存信息
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void deleteAllTempHelpInfo() {
        HelpInfoManager.getInstance().removeAll();
    }


    /**
     * 在服务器初始化后的时候把数据库中未完成的helpinfo读入内存
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<HelpInfo> helpInfoByActive = helpInfoService.findHelpInfoByActive(0, 1000);

        for (HelpInfo helpInfo :
                helpInfoByActive) {
            HelpInfoManager.getInstance().addHelpInfo(helpInfo);
        }
    }
}
