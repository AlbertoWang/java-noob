package cn.albertowang.spring.mystarter.config;

import cn.albertowang.spring.mystarter.bean.AlibabaLauncher;
import cn.albertowang.spring.mystarter.bean.BytedanceLauncher;
import cn.albertowang.spring.mystarter.bean.TencentLauncher;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/22 14:32
 * @description 自定义配置加载类
 **/

@EnableConfigurationProperties(value = MyProperties.class)
@Configuration
public class AutoConfiguration {

    // 以下过程在Spring主启动类启动后自动加载
    // 注册一种Bean
    @Bean
    public AlibabaLauncher alibabaLauncher(MyProperties properties) {
        return new AlibabaLauncher(properties.getAlibabaLauncher());
    }

    // 注册另一种Bean
    @Bean
    public TencentLauncher tencentLauncher(MyProperties properties) {
        return new TencentLauncher(properties.getTencentLauncher());
    }

    @Bean(value = "bytedance")
    public BytedanceLauncher bytedanceLauncher(MyProperties properties) {
        return new BytedanceLauncher(properties.getBytedanceLauncher());
    }
}
