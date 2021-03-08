package cn.albertowang.algorithm.dp;

import java.util.Arrays;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/3/5 20:57
 * @description <a href="https://leetcode-cn.com/problems/longest-increasing-subsequence/">最长递增子序列</a>
 **/

public class LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++)
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            res = Math.max(dp[i], res);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(LongestIncreasingSubsequence.lengthOfLIS(nums));
    }
}
