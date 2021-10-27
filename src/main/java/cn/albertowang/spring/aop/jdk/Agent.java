package cn.albertowang.spring.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:46
 * @description 中介（代理类
 **/

public class Agent implements InvocationHandler {

    /**
     * 委托类的接口，JDK自带的动态代理必须给出一个interface
     */
    private Car car;

    /**
     * 通过反射调用委托类实现的方法，每次调用代理给出的委托类的方法，都会走一遍这个流程
     * 实现InvocationHandler（反射机制）来达到动态代理
     *
     * @param proxy  代理实例
     * @param method 代理实例对应接口中的方法
     * @param args   代理实例带来的参数变量
     * @return 代理实例invoke后的结果
     * @throws Throwable 代理实例invock过程的exception
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 前置准备
        System.out.println("Find suitable customer");
        // 使用反射调用委托类的具体方法
        method.invoke(this.car, args);
        // 后续处理
        System.out.println("Rend the car to customer");
        return null;
    }

    /**
     * 将委托类的实例返回给调用者
     *
     * @param car 接口
     * @return 调用方需要的实例
     */
    public Object getInstance(Car car) {
        // 将委托类替换为具体实现
        this.car = car;
        // 将委托类的类型确定为与参数相同的实现
        Class<?> clazz = car.getClass();
        // 返回委托类实例
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    /**
     * 输出：
     * Find suitable customer
     * Rent a jeep
     * Rend the car to customer
     * Find suitable customer
     * Drive off-road
     * Rend the car to customer
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 此时的car已经是Jeep类（委托类）
        Car car = (Car) new Agent().getInstance(new Jeep());
        car.rent();
        car.drive();

        /*
        以下不提供接口的方式不可用，因此需要CGLib动态代理
        Jeep jeep = (Jeep) new Agent().getInstance(new Jeep());
        jeep.rent();
        jeep.drive();
         */
    }
}
