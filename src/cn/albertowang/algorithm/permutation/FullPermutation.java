package cn.albertowang.algorithm.permutation;

import java.util.ArrayList;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/23 16:11
 * @description 求全排列
 **/

public class FullPermutation {

    public static ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    // 迭代
    public static ArrayList<ArrayList<Integer>> fullPermutation(int[] a) {
        res.clear();
        res.add(new ArrayList<>()); // empty item
        for (int i : a) {
            int len = res.size();
            for (int j = 0; j < len; j++) {
                ArrayList<Integer> newSubSet = new ArrayList<>(res.get(j));
                newSubSet.add(i);
                res.add(newSubSet);
            }
        }
        return res;
    }

    // 递归
    public static ArrayList<ArrayList<Integer>> recurrenceFullPermutation(int[] a) {
        res.clear();
        recurrence(a, 0, new ArrayList<>());
        return res;
    }

    public static void recurrence(int[] a, int start, ArrayList<Integer> temp) {
        res.add(new ArrayList<>(temp));
        for (int i = start; i < a.length; i++) {
            temp.add(a[i]);
            recurrence(a, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] in = {1, 2, 3};
        System.out.println(FullPermutation.fullPermutation(in).toString());
        System.out.println(FullPermutation.recurrenceFullPermutation(in).toString());
    }
}
