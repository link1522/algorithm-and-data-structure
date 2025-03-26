package Sort;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = { 9, 2, 8, 3, 4, 1 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 將陣列切成兩個子陣列，前面陣列為排序完的，後面陣列為尚未排序
     * 每次遍歷，將後面陣列中的第一個元素加入到前面陣列 (想成前面陣列往後面擴充一位)，並逐一比較大小，找到要插入的位置
     * ex:
     * [9] [2, 8, 3, 4, 1]
     * [2, 9] [8, 3, 4, 1]
     * [2, 8, 9] [3, 4, 1]
     * ...
     */
    public static void sort(int[] arr) {
        int cur;
        int insertValue;

        for (int i = 1; i < arr.length; i++) {
            cur = i; // 當前位置，同時也代表插入位置
            insertValue = arr[i];

            // 如果當前位置不是 0，且插入值小於前一位置的值
            while (cur > 0 && insertValue < arr[cur - 1]) {
                // 將前一位置的值賦予到當前位置
                arr[cur] = arr[cur - 1];
                // 位置往前移一位
                cur--;
            }

            // 找到插入位置，進行賦值
            arr[cur] = insertValue;
        }
    }
}