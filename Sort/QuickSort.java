package Sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = { 3, 1, 3, 2, 3 };
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int initLeft, int initRight) {
        int left = initLeft;
        int right = initRight;
        int mid = (left + right) / 2;
        int temp;

        while (left < right) {
            while (arr[left] < arr[mid]) {
                left++;
            }
            while (arr[right] > arr[mid]) {
                right--;
            }

            if (left >= right) {
                break;
            }

            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            if (arr[left] == arr[mid]) {
                right--;
            }
            if (arr[right] == arr[mid]) {
                left++;
            }
        }

        if (left == right) {
            left++;
            right--;
        }

        if (initLeft < right) {
            sort(arr, initLeft, right);
        }

        if (left < initRight) {
            sort(arr, left, initRight);
        }
    }
}
