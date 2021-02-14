package cn.albertowang.spring.aop.staticproxy;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:31
 * @description Car的实现2（委托类）
 **/

public class Truck implements Car {
    @Override
    public void rent() {
        System.out.println("Rent a truck");
    }

    @Override
    public void drive() {
        System.out.println("Drive with cargo");
    }
}
