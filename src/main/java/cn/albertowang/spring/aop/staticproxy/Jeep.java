package cn.albertowang.spring.aop.staticproxy;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:26
 * @description Car的实现1（委托类）
 **/

public class Jeep implements Car {
    @Override
    public void rent() {
        System.out.println("Rent a jeep");
    }

    @Override
    public void drive() {
        System.out.println("Drive off-road");
    }
}
