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

    // 通过CGLib的拦截器调用委托类的方法，与动态JDK类似，每次调用代理给出的委托类的方法，都会走一遍这个流程
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Find suitable customer"); // 前置准备
        // 使用CGLib提供的拦截器调用委托类的具体方法
        methodProxy.invokeSuper(o, objects);
        System.out.println("Rend the car to customer"); // 后续处理
        return null;
    }

    // 将委托类的实例返回给调用者
    public Object getInstance(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        // 将参数提供的class设置为生成新类的父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /*
    输出：
    Find suitable customer
    Rent a truck
    Rend the car to customer
    Find suitable customer
    Drive with cargo
    Rend the car to customer
    Find suitable customer
    Rent a jeep
    Rend the car to customer
    Find suitable customer
    Drive off-road
    Rend the car to customer
     */
    public static void main(String[] args) throws Exception {
        Car car = (Car) new Agent().getInstance(Truck.class); // 此时的car已经是Jeep类（委托类）
        car.rent();
        car.drive();

        Jeep jeep = (Jeep) new Agent().getInstance(Jeep.class);
        jeep.rent();
        jeep.drive();
    }
}
