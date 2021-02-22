package cn.albertowang.spring.mystarter.bean;

import cn.albertowang.spring.mystarter.config.MyProperties;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/22 15:09
 * @description Bean接口的一种实现
 **/

public class AlibabaLauncher implements Launcher {
    // 配置信息
    private MyProperties.UserInfo alibabaUserInfo;

    // 构造函数依赖注入
    public AlibabaLauncher(MyProperties.UserInfo userInfo) {
        this.alibabaUserInfo = userInfo;
    }

    @Override
    public void login() {
        System.out.println(alibabaUserInfo.toString());
    }
}
