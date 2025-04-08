package Search;

import java.util.Arrays;

public class FibonacciSearch {
    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = { 1, 3, 4, 6, 12, 23 };
        int result = search(23, arr);
        System.out.println(result);
    }

    public static int search(int target, int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int k = 0; // 費氏數列分割數值下標
        int mid = 0;

        int[] fibonacciArr = getFibonacciArr();
        while (right > fibonacciArr[k] - 1) {
            k++;
        }

        // fibonacciArr[k] 可能大於 arr 的長度，所以創建新的 Array 重新擺放 arr
        int[] temp = Arrays.copyOf(arr, fibonacciArr[k]);
        // 超過的部分，用最大值(最右邊的值)填充
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        while (left <= right) {
            mid = left + fibonacciArr[k - 1] - 1;
            if (target < temp[mid]) {
                right = mid - 1;
                // 全部 = 左邊 + 右邊
                // fibonacciArr[k] = fibonacciArr[k - 1] + fibonacciArr[k - 2]
                // 要找左邊 fibonacciArr[k - 1]，所以 k 要減 1
                k--;
            } else if (target > temp[mid]) {
                left = mid + 1;
                // 全部 = 左邊 + 右邊
                // fibonacciArr[k] = fibonacciArr[k - 1] + fibonacciArr[k - 2]
                // 要找右邊 fibonacciArr[k - 2]，所以 k 要減 2
                k -= 2;
            } else {
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }

        return -1;
    }

    private static int[] getFibonacciArr() {
        int[] fibonacci = new int[maxSize];
        fibonacci[0] = 1;
        fibonacci[1] = 1;

        for (int i = 2; i < fibonacci.length; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        return fibonacci;
    }
}
