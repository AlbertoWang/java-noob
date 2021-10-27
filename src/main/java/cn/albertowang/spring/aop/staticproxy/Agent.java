package cn.albertowang.spring.aop.staticproxy;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/14 21:27
 * @description 中介（代理类）
 **/

public class Agent {
    public void rentCar(Car car) {
        // 前置准备
        System.out.println("Find suitable customer");
        car.rent(); // 超类方法，所有委托类都包含的方法
        // 后续处理
        System.out.println("Rend the car to customer");
    }

    /**
     * 输出：
     * Find suitable customer
     * Rent a jeep
     * Rend the car to customer
     * Find suitable customer
     * Rent a truck
     * Rend the car to customer
     *
     * @param args
     */
    public static void main(String[] args) {
        Agent agent = new Agent();
        // 代理使用Jeep委托类
        agent.rentCar(new Jeep());
        // 代理使用Truck委托类
        agent.rentCar(new Truck());
    }
}
