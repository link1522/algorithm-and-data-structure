package Sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = { 3, 9, -1, 10, 20 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
     * 左右相比，大的放右邊
     * 時間複雜度 O(n^2)
     */
    public static void sort(int[] arr) {
        int temp;
        boolean hasSwap = false;

        for (int i = arr.length; i > 1; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    hasSwap = true;
                }
            }

            if (hasSwap) {
                // 如果有進行交換過，就重置 flag，用來記錄下次行為
                hasSwap = false;
            } else {
                // 如果在某次遍歷中，一次都沒有進行交換，代表該陣列已經排序完成，可以直接退出
                break;
            }
        }
    }
}
