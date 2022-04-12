package cn.albertowang.spring.aop.cglib;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:26
 * @description 目标类Target
 **/

public class Jeep extends Car {
    @Override
    public void rent() {
        System.out.println("Rent a jeep");
    }

    @Override
    public void drive() {
        System.out.println("Drive off-road");
    }
}
