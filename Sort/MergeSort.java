package Sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = { 8, 2, 1, 9, 6, 4 };
        int[] temp = new int[arr.length]; // 需要額外空間處理排序
        sort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);
            sort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 左邊陣列的起始索引
        int j = mid + 1; // 右邊陣列的起始索引
        int t = 0; // temp 陣列的當前索引

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }

        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        // 將 temp 複製回 arr
        t = 0;
        int arrIndex = left;
        while (arrIndex <= right) {
            arr[arrIndex] = temp[t];
            arrIndex++;
            t++;
        }
    }
}
