package Sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = { 93, 43, 92, 123, 32 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        // 一維桶大小設定為 arr.length 避免溢出
        int[][] bucket = new int[10][arr.length];
        // 紀錄每個桶存放幾個有效數據
        int[] bucketElementCount = new int[10];

        // 找最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 位數長度
        int digitalLength = Integer.toString(max).length();

        int digital; // 位數 (從個、十、百 ... 位)
        int arrIndex; // 原陣列索引，用來將桶中的元素放回
        for (int i = 0, n = 1; i < digitalLength; i++, n *= 10) {
            // 依序放入桶中
            for (int j = 0; j < arr.length; j++) {
                digital = arr[j] / n % 10;
                bucket[digital][bucketElementCount[digital]] = arr[j];
                bucketElementCount[digital]++;
            }

            arrIndex = 0;
            // 放回原陣列
            for (int j = 0; j < bucketElementCount.length; j++) {
                if (bucketElementCount[j] == 0) {
                    continue;
                }

                for (int k = 0; k < bucketElementCount[j]; k++) {
                    arr[arrIndex] = bucket[j][k];
                    arrIndex++;
                }

                // bucket 計數器歸零
                bucketElementCount[j] = 0;
            }
        }
    }
}
