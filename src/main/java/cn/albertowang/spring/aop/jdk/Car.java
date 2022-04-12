package cn.albertowang.spring.aop.jdk;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:21
 * @description 需要被注册到代理类的接口（目标类Target）
 **/

public interface Car {
    /**
     * 车辆出租待实现方法
     */
    void rent();

    /**
     * 车辆行驶待实现方法
     */
    void drive();
}
