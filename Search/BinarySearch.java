package Search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = { 1, 3, 5 };

        var result = searchByRecursive(1, arr, 0, arr.length - 1);
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
}
