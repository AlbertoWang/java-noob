package cn.albertowang.algorithm.lru;

import java.util.LinkedList;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/26 11:59
 * @description LRU页面置换算法
 **/

public class LRU {

    public static void LRU(int[] a, int length) {
        LinkedList<Integer> queue = new LinkedList<>();
        for (Integer i : a) {
            if (queue.contains(i)) {
                queue.remove(i);
                queue.addFirst(i);
            } else {
                if (queue.size() < length) {
                    queue.addLast(i);
                } else {
                    queue.removeLast();
                    queue.addFirst(i);
                }
            }
        }
        queue.forEach(i -> System.out.println(i + " "));
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 1, 2, 3};
        LRU.LRU(a, 3);
    }
}
