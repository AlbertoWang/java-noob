package cn.albertowang.spring.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:46
 * @description 中介（代理类）
 **/

// 实现InvocationHandler（反射机制）来达到动态代理
public class Agent implements InvocationHandler {

    // 委托类的接口
    private Car car;

    // 通过反射调用委托类实现的方法，每次调用代理给出的委托类的方法，都会走一遍这个流程
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Find suitable customer"); // 前置准备
        // 使用反射调用委托类的具体方法
        method.invoke(this.car, args);
        System.out.println("Rend the car to customer"); // 后续处理
        return null;
    }

    // 将委托类的实例返回给调用者
    public Object getInstance(Car car) {
        // 将委托类替换为具体实现
        this.car = car;
        // 将委托类的类型确定为与参数相同的实现
        Class<?> clazz = car.getClass();
        // 返回委托类实例
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    /*
    输出：
    Find suitable customer
    Rent a jeep
    Rend the car to customer
    Find suitable customer
    Drive off-road
    Rend the car to customer
     */
    public static void main(String[] args) throws Exception {
        Car car = (Car) new Agent().getInstance(new Jeep()); // 此时的car已经是Jeep类（委托类）
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
