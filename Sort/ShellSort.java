package Sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = { 93, 43, 92, 23, 32 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
     * 交換法
     */
    public static void sort(int[] arr) {
        int temp;

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i - gap;
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
