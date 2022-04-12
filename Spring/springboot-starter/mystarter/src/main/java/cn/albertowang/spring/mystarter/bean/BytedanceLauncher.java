package cn.albertowang.spring.mystarter.bean;

import cn.albertowang.spring.mystarter.config.MyProperties;

/**
 * @author AlbertoWang@FoxMail.com
 * @date 2022/4/7 20:00
 * @description
 **/

public class BytedanceLauncher implements Launcher {

    // 配置信息
    private MyProperties.UserInfo bytedanceUserInfo;

    // 构造函数依赖注入
    public BytedanceLauncher(MyProperties.UserInfo userInfo) {
        this.bytedanceUserInfo = userInfo;
    }

    @Override
    public void login() {
        System.out.println(bytedanceUserInfo.toString());
    }
}
