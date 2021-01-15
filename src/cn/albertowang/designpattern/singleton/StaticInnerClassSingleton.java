package cn.albertowang.designpattern.singleton;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/14 下午3:57
 * @description 静态内部类的单例模式，只有在需要的时候才会创建实例，利用率较高
 **/

public class StaticInnerClassSingleton {

    // 构造函数私有化
    private StaticInnerClassSingleton() {
        System.out.println("This is SIC-constructor");
    }

    public static StaticInnerClassSingleton getInstance() {
        return InnerHolder.INSTANCE;
    }

    private static class InnerHolder {
        // 内部静态类holder需要声明为static final
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

}
