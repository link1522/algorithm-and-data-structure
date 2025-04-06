package Search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = { 1, 3, 3, 3, 5 };

        var result = searchMultipleByRecursive(3, arr, 0, arr.length - 1);
        System.out.println(result);
    }

    public static int searchByRecursive(int target, int[] arr, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (target > arr[mid]) {
            return searchByRecursive(target, arr, mid + 1, right);
        } else if (target < arr[mid]) {
            return searchByRecursive(target, arr, left, mid - 1);
        } else {
            return mid;
        }
    }

    public static List<Integer> searchMultipleByRecursive(int target, int[] arr, int left, int right) {

        var result = new ArrayList<Integer>();

        if (left > right) {
            return result;
        }

        int mid = (left + right) / 2;

        if (target > arr[mid]) {
            return searchMultipleByRecursive(target, arr, mid + 1, right);
        } else if (target < arr[mid]) {
            return searchMultipleByRecursive(target, arr, left, mid - 1);
        } else {
            int arrIndex = mid;
            while (arrIndex - 1 > 0 && arr[arrIndex - 1] == target) {
                arrIndex--;
            }

            while (arr[arrIndex] == target) {
                result.add(arrIndex);
                arrIndex++;
            }

            return result;
        }
    }
}
