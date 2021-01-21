package cn.albertowang.lang.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/16 下午5:00
 * @description Java8流编程示例
 **/

/* 输出：
 * filter过滤的流:
 * sentence testing stream sentence testing distinct.
 * distinct处理的流:
 * sentence testing stream distinct.
 * 普通流(有序):
 * This is a sentence for testing stream in Java 8, and this is a sentence for testing distinct.
 * 并行流(无序):
 * this is and 8, This a in sentence Java sentence a stream is for testing distinct. for testing
 * 方法引用的map:
 * THIS IS A SENTENCE FOR TESTING STREAM IN JAVA 8, AND THIS IS A SENTENCE FOR TESTING DISTINCT.
 * lambda表达式的map:
 * T i a s f t s i J 8 a t i a s f t d
 * concat方法只允许合并未关闭的流:
 * 1 2 3 concat concat concat
 * 生成的有限流:
 * 5 6 7 8 9
 * 生成的无限流:
 * Generated
 * Generated
 * ...(无限个Generated)
 */

public class StreamExample {
    public static void main(String[] args) {
        String input = "This is a sentence for testing stream in Java 8, and this is a sentence for testing distinct.";
        String[] words = input.split(" ");
        List<String> wordList = Arrays.asList(words);

        // 流编程测试
        // Arrays.stream使用的是Collection接口中的方法
        System.out.println("filter过滤的流:");
        Arrays.stream(words).filter(w -> w.length() >= 5).forEach(w -> System.out.print(w + " "));  // filter进行过滤

        // Stream.of使用的是静态方法
        System.out.println("\ndistinct处理的流:");
        Stream.of(words).filter(w -> w.length() >= 5).distinct().forEach(w -> System.out.print(w + " "));  // distinct进行约束

        // 普通stream（保证顺序）
        System.out.println("\n普通流(有序):");
        wordList.stream().forEach(w -> System.out.print(w + " "));

        // 并行stream（不保证顺序）
        System.out.println("\n并行流(无序):");
        wordList.parallelStream().forEach(w -> System.out.print(w + " "));

        // map进行映射
        System.out.println("\n方法引用的map:");
        wordList.stream().map(String::toUpperCase).forEach(w -> System.out.print(w + " "));
        System.out.println("\nlambda表达式的map:");
        wordList.stream().map(w -> w.substring(0, 1)).forEach(w -> System.out.print(w + " "));

        // concat进行合并
        System.out.println("\nconcat方法只允许合并未关闭的流:");
        Stream.concat(Stream.iterate(1, n -> n + 1).limit(3),
                Stream.generate(() -> "concat").limit(3))
                .forEach(i -> System.out.print(i + " "));

        // 创建流
        // 有限流，创建从0逐次+1的n，流中元素个数限制为10个
        System.out.println("\n生成的有限流:");
        Stream<BigDecimal> decimalStream = Stream.iterate(BigDecimal.ZERO, n ->
                n.add(BigDecimal.ONE)).limit(10);  // limit进行限制
        decimalStream.skip(5).forEach(n -> System.out.print(n + " "));  // skip进行忽略

        // 不加限制就是无限流
        System.out.println("\n生成的无限流:");
        Stream<String> stringStream = Stream.generate(() -> "Generated");
        stringStream.forEach(n -> System.out.println(n + " "));
    }
}
