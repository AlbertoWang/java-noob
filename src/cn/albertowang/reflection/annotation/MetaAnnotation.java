package cn.albertowang.reflection.annotation;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/12 下午2:36
 * @description 元注解，可以被其他注解作为内部元素使用
 **/

public @interface MetaAnnotation {
    String value();
}
