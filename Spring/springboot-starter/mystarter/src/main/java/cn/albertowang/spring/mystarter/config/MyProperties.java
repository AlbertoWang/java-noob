package cn.albertowang.spring.mystarter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/22 14:31
 * @description 自定义配置类
 **/

@ConfigurationProperties(prefix = "myconfig")
@Data
public class MyProperties {
    // 已通过外部配置文件配置好的配置
    private UserInfo alibabaLauncher = new UserInfo();
    private UserInfo tencentLauncher = new UserInfo();

    // 配置包含元素
    @Data
    public static class UserInfo {
        private String username;
        private String password;
        private String description;
    }
}
