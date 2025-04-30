package Tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = { 4, 6, 8, 5, 9 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int temp;

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            // 將最大值放到陣列最後
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            // 再重新調整 Heap，但這次長度只到還沒排好的範圍
            adjustHeap(arr, 0, i);
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 將 i 節點的子樹調整成大頂堆
     * 
     * @param arr    要調整的 arrary
     * @param i      非葉子節點所在的索引
     * @param length 表示對多少個元素進行調整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];

        // 一開始 k 代表左子節點 (2i + 1)
        // k + 1 代表右子節點 (2i + 2)
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // 如果左子節點小於右子節點，則將 k 移到右子節點
                k++;
            }
            if (arr[k] > temp) {
                // 子節點大於父節點，就讓子節點與父節點交換
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }

        // 將 temp 值放到調整後的位置
        arr[i] = temp;
    }
}
