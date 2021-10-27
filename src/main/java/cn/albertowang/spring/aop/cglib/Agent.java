package cn.albertowang.spring.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:46
 * @description 中介（代理类）
 **/

public class Agent implements MethodInterceptor {

    /**
     * 通过CGLib的拦截器调用委托类的方法，与动态JDK类似，每次调用代理给出的委托类的方法，都会走一遍这个流程
     *
     * @param o           agent的this指针，本agent
     * @param method      被CGLib增强的方法
     * @param objects     参数数组
     * @param methodProxy 调用基类用
     * @return 代理签名方法的返回
     * @throws Throwable 基类不会被invoke
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 前置准备
        System.out.println("Find suitable customer");
        // 使用CGLib提供的拦截器调用委托类的具体方法
        methodProxy.invokeSuper(o, objects);
        // 后续处理
        System.out.println("Rend the car to customer");
        return null;
    }

    /**
     * 将委托类的实例返回给调用者
     *
     * @param clazz 调用者声明的所需委托类
     * @return 对应委托类的实例
     */
    public Object getInstance(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        // 将参数提供的class设置为生成新类的父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 输出：
     * Find suitable customer
     * Rent a truck
     * Rend the car to customer
     * Find suitable customer
     * Drive with cargo
     * Rend the car to customer
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
        // 此时的car已经是Truck类（委托类）
        Car car = (Car) new Agent().getInstance(Truck.class);
        car.rent();
        car.drive();

        Jeep jeep = (Jeep) new Agent().getInstance(Jeep.class);
        jeep.rent();
        jeep.drive();
    }
}
