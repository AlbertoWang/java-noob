package cn.albertowang.designpattern.singleton;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/14 下午3:52
 * @description 双重校验锁的单例模式（DCL），比较适合多线程
 **/

public class DoubleCheckLockSingleton {
    private static volatile DoubleCheckLockSingleton instance;

    // 构造函数私有化
    private DoubleCheckLockSingleton() {
        System.out.println("This is DCL-constructor");
    }

    public static DoubleCheckLockSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckLockSingleton();
                }
            }
        }
        return instance;
    }
}
