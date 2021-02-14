package cn.albertowang.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/14 下午3:48
 * @description 懒汉写法的单例模式，不适用于多线程的情况
 **/

public class LazySingleton {
    private static LazySingleton instance;

    // 构造函数私有化
    private LazySingleton() {
        System.out.println("This is lazy-constructor");
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
