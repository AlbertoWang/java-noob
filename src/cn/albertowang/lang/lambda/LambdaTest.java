package cn.albertowang.lang.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/21 14:36
 * @description lambda表达式用法
 **/

public class LambdaTest {
    public static void main(String[] args) {
        int[] a = {1, 3, 5, 2, 4, 6};

        // 使用stream流化a，使用.box()自动装箱，使用.collect()转为集合
        List<Integer> aList = Arrays.stream(a).boxed().collect(Collectors.toList());
        // lambda表达式遍历
        aList.forEach(n -> System.out.print(n + " "));

        // 排序（使用Comparator<T>）
        aList.sort(new Comparator<Integer>() { // IDEA甚至会提示将此句改为lambda表达式
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println("\n" + aList.toString());

        // 排序（使用lambda表达式）
        aList = Arrays.stream(a).boxed().collect(Collectors.toList());
        aList.sort((o1, o2) -> o2 - o1);
        System.out.println(aList.toString());
    }
}
