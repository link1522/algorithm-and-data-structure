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

        // 最大位數
        int maxDigitalCount = 1;
        int divider = 10;
        int count = 1;
        // 計算最大位數
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] / divider != 0) {
                count++;
                divider *= 10;
            }

            if (count > maxDigitalCount) {
                maxDigitalCount = count;
            }
        }

        int digital; // 位數 (從個、十、百 ... 位)
        int arrIndex; // 原陣列索引，用來將桶中的元素放回
        for (int i = 0; i < maxDigitalCount; i++) {
            divider = (int) Math.pow(10, i);

            // 依序放入桶中
            for (int j = 0; j < arr.length; j++) {
                digital = arr[j] / divider % 10;
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
