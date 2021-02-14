package cn.albertowang.spring.aop.staticproxy;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:27
 * @description 中介（代理类）
 **/

public class Agent {
    public void rentCar(Car car) {
        System.out.println("Find suitable customer"); // 前置准备
        car.rent(); // 超类方法，所有委托类都包含的方法
        System.out.println("Rend the car to customer"); // 后续处理
    }

    /*
    输出：
    Find suitable customer
    Rent a jeep
    Rend the car to customer
    Find suitable customer
    Rent a truck
    Rend the car to customer
     */
    public static void main(String[] args) {
        Agent agent = new Agent();
        agent.rentCar(new Jeep()); // 代理使用Jeep委托类
        agent.rentCar(new Truck()); // 代理使用Truck委托类
    }
}
