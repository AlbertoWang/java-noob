package cn.albertowang.datastructure.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/21 16:40
 * @description 使用Heap
 **/

public class HeapTest {

    public static void main(String[] args) {
        Queue<Integer> heap = new PriorityQueue<>(); // 默认小顶堆
        heap.offer(1);
        heap.offer(2);
        heap.offer(3); // 1, 2, 3
        heap.forEach(n -> System.out.print(n + " "));

        System.out.println();
        heap = new PriorityQueue<>((t1, t2) -> t2 - t1); // 构造大顶堆
        heap.offer(1);
        heap.offer(2);
        heap.offer(3); /// 3, 1, 2
        heap.forEach(n -> System.out.print(n + " "));
    }
}
