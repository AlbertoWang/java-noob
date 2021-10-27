package cn.albertowang.algorithm.sort.quicksort;

import java.util.Arrays;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/17 下午11:20
 * @description 快速排序（最右侧为pivot的做法）
 **/

public class QuickSort {

    public static void partition(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        // set last item as pivot
        int left = begin, right = end;

        while (left < right) {
            while (arr[left] <= arr[end] && left < right) {
                left++;
            }
            while (arr[right] >= arr[end] && left < right) {
                right--;
            }
            // swap
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

        // swap pivot and meeting point
        int temp = arr[left];
        arr[left] = arr[end];
        arr[end] = temp;

        // iteration
        partition(arr, begin, left - 1);
        partition(arr, left + 1, end);
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 1, 2, 9, 4, 7, 6};
        QuickSort.partition(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
