package cn.albertowang.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/14 下午3:51
 * @description 测试主函数
 **/

public class Test {
    public static void main(String[] args) {
        // 无法使用
        // LazySingleton lazyInstance = new LazySingleton();
        // DoubleCheckLockSingleton doubleCheckLockInstance = new DoubleCheckLockSingleton();
        // StaticInnerClassSingleton staticInnerClassInstance = new StaticInnerClassSingleton();

        LazySingleton lazyInstance = LazySingleton.getInstance();
        DoubleCheckLockSingleton doubleCheckLockInstance = DoubleCheckLockSingleton.getInstance();
        StaticInnerClassSingleton staticInnerClassInstance = StaticInnerClassSingleton.getInstance();

    }
}
