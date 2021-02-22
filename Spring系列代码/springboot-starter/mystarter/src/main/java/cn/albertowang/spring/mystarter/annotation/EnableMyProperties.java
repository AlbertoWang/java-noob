package cn.albertowang.spring.mystarter.annotation;

import cn.albertowang.spring.mystarter.config.AutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/22 15:15
 * @description 主动生效starter（注解）
 **/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AutoConfiguration.class})
public @interface EnableMyProperties {
}
