package cn.albertowang.algorithm.Netease;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/3/27 15:46
 * @description TODO
 **/

public class Coupon {

    public static int coupon(int[] nums, int target) {
        int res = Integer.MAX_VALUE;
        Arrays.sort(nums);
        Arrays.stream(nums).forEach(System.out::println);
        int temp = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] <= target) {
                temp += nums[i];
            }
            for (int j = 0; nums[j] <= target - temp; j++) {
                temp += nums[j];
                if (temp >= target)
                    break;
            }
            res = Math.min(res, temp);
            temp = 0;
        }
        return res - target;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        num = sc.nextInt();
        int target;
        target = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        String[] ss = s.split(" ");
        int[] nums = new int[ss.length];
        for (int i = 0; i < ss.length; i++)
            nums[i] = Integer.parseInt(ss[i]);
//        Arrays.stream(nums).forEach(System.out::println);
        System.out.print(Coupon.coupon(nums, target));
    }
}
