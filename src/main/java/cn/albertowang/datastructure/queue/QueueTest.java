package cn.albertowang.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/21 16:26
 * @description 使用Queue
 **/

public class QueueTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // 1
        queue.offer(2); // 1, 2

        Integer i = queue.peek(); // 1
        queue.offer(3); // 1, 2, 3
        queue.poll(); // 2, 3
    }
}
