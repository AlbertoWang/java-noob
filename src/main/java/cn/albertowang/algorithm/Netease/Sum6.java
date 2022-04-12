package cn.albertowang.algorithm.Netease;

import java.util.*;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/3/27 15:26
 * @description TODO
 **/

public class Sum6 {

    public static int findMax(int[] nums) {
        int res = 0;
        HashMap<Integer, PriorityQueue<Integer>> hashMap = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            hashMap.put(i, new PriorityQueue<>((n1, n2) -> n2 - n1));
        }
        for (int i : nums) {
            int flag = i % 6;
            if (flag == 0)
                res += i;
            else {
                hashMap.get(flag).offer(i);
            }
        }
        boolean clear1 = false, clear2 = false, clear3 = false;
        while (true) {
            if (clear1 && clear2 && clear3)
                break;
            if (hashMap.get(1) != null && hashMap.get(1).size() != 0) {
                if (hashMap.get(5) != null && hashMap.get(5).size() != 0) {
                    res += hashMap.get(1).poll();
                    res += hashMap.get(5).poll();
                } else
                    clear1 = true;
            } else clear1 = true;
            if (hashMap.get(2) != null && hashMap.get(2).size() != 0) {
                if (hashMap.get(4) != null && hashMap.get(4).size() != 0) {
                    res += hashMap.get(2).poll();
                    res += hashMap.get(4).poll();
                } else clear2 = true;
            } else clear2 = true;
            if (hashMap.get(3) != null && hashMap.get(3).size() > 1) {
                res += hashMap.get(3).poll();
                res += hashMap.get(3).poll();
            } else
                clear3 = true;
        }
        return res == 0 ? -1 : res;
    }

    public static void main(String[] args) {
        int num;
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        int[] nums = new int[num];
        for (int i = 0; i < num; i++)
            nums[i] = sc.nextInt();
        System.out.print(Sum6.findMax(nums));
    }
}
