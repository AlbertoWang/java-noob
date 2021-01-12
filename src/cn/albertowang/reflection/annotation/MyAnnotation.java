package cn.albertowang.reflection.annotation;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/12 下午2:37
 * @description 自定义注解，调用了元注解MetaAnnotation作为内部元素
 **/

import java.lang.annotation.*;

// Retention注解决定MyAnnotation注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
// Target注解决定了一个注解可以标注到哪些成分上
@Target({ElementType.TYPE})
// Inherited注解说明了被注解的注解具有可继承性（仅限类继承，接口继承与接口实现均不支持该注解）
@Inherited
public @interface MyAnnotation {
    // 如果只有一个没给出缺省默认值的元素，在使用该注解时可以省略该元素名称
    String value();

    // 给注解添加缺省默认值
    int defaultVal() default -1;

    enum Color {
        Red,
        Green,
        Blue
    }

    // 给注解添加枚举类型元素，仍可以指定缺省默认值
    Color color() default Color.Red;

    // 给注解添加数组类型元素，仍支持缺省默认值
    String[] stringArray() default {"default", "string", "array"};

    // 给注解添加注解类型元素，仍支持缺省默认值
    MetaAnnotation metaAnnotation() default @MetaAnnotation("default");

}
