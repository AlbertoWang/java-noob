package cn.albertowang.spring.aop.cglib;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:21
 * @description 需要被注册到中介（代理类）的接口
 **/

public interface Car {
    public void rent();

    public void drive();
}
