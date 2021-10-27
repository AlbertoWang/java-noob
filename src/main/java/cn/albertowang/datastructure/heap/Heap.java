package cn.albertowang.datastructure.heap;

import java.util.Arrays;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/18 上午11:41
 * @description 大顶堆示例
 **/

public class Heap {

    static int bottomToTop = 0;
    static int topToBottom = 0;

    public static void generateHeapFromLeaf(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapAdjustFromLeaf(arr, i);
        }
    }

    public static void generateHeapFromRoot(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length / 2; i++) {
            heapAdjustFromRoot(arr, 0, arr.length);
        }
    }

    // top k minimum
    public static void topK(int[] arr, int k) {
        if (arr == null) {
            return;
        }
        // init first heap
        heapAdjustFromRoot(arr, 0, k);
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < arr[0]) {
                // swap
                int temp = arr[i];
                arr[i] = arr[0];
                arr[0] = temp;
                // adjust
                heapAdjustFromRoot(arr, 0, k);
            }
        }
    }

    // top to bottom
    private static void heapAdjustFromRoot(int[] arr, int curr, int n) {
        int left = curr * 2 + 1;
        int right = curr * 2 + 2;
        while (left < n) {
            right = right == n ? left : right;
            int idx = arr[left] > arr[right] ? left : right;
            if (arr[curr] < arr[idx]) {
                topToBottom++;
                // swap
                int temp = arr[curr];
                arr[curr] = arr[idx];
                arr[idx] = temp;

                // update curr
                curr = idx;
            } else {
                // update curr
                curr = curr * 2 + 1;
            }
            // update left and right
            left = curr * 2 + 1;
            right = curr * 2 + 2;
        }
    }

    // bottom to top
    private static void heapAdjustFromLeaf(int[] arr, int curr) {
        // curr.p
        int parent = (curr - 1) >> 1;

        // iteration
        while (parent >= 0 && arr[curr] > arr[parent]) {
            bottomToTop++;
            // swap
            int temp = arr[curr];
            arr[curr] = arr[parent];
            arr[parent] = temp;

            // update curr and curr.p
            curr = parent;
            parent = (curr - 1) >> 1;
        }
    }

    public static void main(String[] args) {
        // bottom to top
        int[] arr = {3, 5, 8, 1, 2, 9, 4, 7, 6};
        Heap.generateHeapFromLeaf(arr);
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println("\n自叶向根交换次数: " + Heap.bottomToTop);

        // top to bottom
        arr = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};
        Heap.generateHeapFromRoot(arr);
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println("\n自根向叶交换次数: " + Heap.topToBottom);

        // top k
        arr = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};
        Heap.topK(arr, 5);
        Arrays.stream(arr).limit(5).forEach(n -> System.out.print(n + " "));
    }
}
