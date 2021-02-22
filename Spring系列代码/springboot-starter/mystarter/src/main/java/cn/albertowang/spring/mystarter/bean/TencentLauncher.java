package cn.albertowang.spring.mystarter.bean;

import cn.albertowang.spring.mystarter.config.MyProperties;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/22 15:07
 * @description Bean接口的一种实现
 **/

public class TencentLauncher implements Launcher {
    // 配置信息
    private MyProperties.UserInfo tencentUserInfo;

    // 构造函数依赖注入
    public TencentLauncher(MyProperties.UserInfo userInfo) {
        this.tencentUserInfo = userInfo;
    }

    @Override
    public void login() {
        System.out.println(tencentUserInfo.toString());
    }
}
