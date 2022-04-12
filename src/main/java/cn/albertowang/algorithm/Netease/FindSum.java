package cn.albertowang.algorithm.Netease;

import java.util.Scanner;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/3/27 16:32
 * @description TODO
 **/

public class FindSum {
    static int nearest = 0;

    public static void solve(int[] input, int current, int sum, int target) {
        if (current == input.length) {
            return;
        }
        if (sum + input[current] > target) {
            nearest = Math.abs(nearest - target) > (sum + input[current] - target) ? (sum + input[current]) : nearest;
            return;
        }
        solve(input, current + 1, sum + input[current], target);
        solve(input, current + 1, sum, target);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int target;
        target = sc.nextInt();
        int coupon;
        coupon = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        String[] ss = s.split(" ");
        int[] nums = new int[ss.length];
        for (int i = 0; i < ss.length; i++)
            nums[i] = Integer.parseInt(ss[i]);
        FindSum.solve(nums, 0, 0, target);
        System.out.print(FindSum.nearest - coupon);
    }
}