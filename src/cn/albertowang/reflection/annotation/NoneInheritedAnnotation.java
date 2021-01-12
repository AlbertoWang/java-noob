package cn.albertowang.reflection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/12 下午3:20
 * @description 不支持继承的注解
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface NoneInheritedAnnotation {
    String value() default "ThisIsNoneInheritedAnnotation";

    MetaAnnotation metaAnnotation();
}
