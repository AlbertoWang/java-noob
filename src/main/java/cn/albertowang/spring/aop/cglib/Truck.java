package cn.albertowang.spring.aop.cglib;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:31
 * @description 目标类
 **/

public class Truck extends Car {
    @Override
    public void rent() {
        System.out.println("Rent a truck");
    }

    @Override
    public void drive() {
        System.out.println("Drive with cargo");
    }
}
