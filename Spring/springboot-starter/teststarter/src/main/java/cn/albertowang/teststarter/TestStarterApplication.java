package cn.albertowang.teststarter;

import cn.albertowang.spring.mystarter.annotation.EnableMyProperties;
import cn.albertowang.spring.mystarter.bean.AlibabaLauncher;
import cn.albertowang.spring.mystarter.bean.BytedanceLauncher;
import cn.albertowang.spring.mystarter.bean.TencentLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
// 使用自定义的starter注解
@EnableMyProperties
public class TestStarterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestStarterApplication.class, args);
        // 获取Bean并测试
        AlibabaLauncher alibabaLauncher = context.getBean(AlibabaLauncher.class);
        alibabaLauncher.login();
        TencentLauncher tencentLauncher = (TencentLauncher) context.getBean("tencentLauncher");
        tencentLauncher.login();
        BytedanceLauncher bytedanceLauncher = (BytedanceLauncher) context.getBean("bytedance");
        bytedanceLauncher.login();
        /*
        输出内容（读取了application.yml中的配置信息）：
        MyProperties.UserInfo(username=aliuser, password=123, description=This is alibaba)
        MyProperties.UserInfo(username=tencentuser, password=456, description=This is tencent)
        MyProperties.UserInfo(username=bytedance, password=789, description=This is bytedance)
         */
    }

}
