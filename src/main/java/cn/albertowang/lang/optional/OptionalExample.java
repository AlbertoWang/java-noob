package cn.albertowang.lang.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/17 上午10:53
 * @description Optional包装器使用示例
 **/

public class OptionalExample {

    private static String getString() {
        return "Provided method";
    }

    public static void main(String[] args) {
        Optional<String> string = Optional.empty(); // 创建空包装器
        System.out.println("包装器内容是否已提供: " + string.isPresent());
        System.out.println("包装器内容获取(判空): " + string.orElse("Provided instance"));
        System.out.println("包装器内容获取(判空): " + string.orElseGet(OptionalExample::getString));
        // System.out.println(string.get()); // 由于包装器内容为空，报空指针Exception

        Optional<List<String>> stringList = Optional.of(Arrays.asList("Provided string list")); // 给包装器赋值
        System.out.println(string.map(s -> s.toUpperCase()).get()); // map与filter方法类似于Stream中的
    }
}
