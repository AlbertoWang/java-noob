package cn.albertowang.spring.aop.staticproxy;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:21
 * @description 需要被注册到中介（代理类）的接口
 **/

public interface Car {
    /**
     * 车辆出租抽象方法
     */
    void rent();

    /**
     * 车辆行驶抽象方法
     */
    void drive();
}
