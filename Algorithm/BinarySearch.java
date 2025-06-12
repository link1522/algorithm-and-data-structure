package Algorithm;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = { 1, 3, 4, 6, 9 };
        int res = binarySearch(arr, 9);
        System.out.println(res);
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;
            if (target > arr[mid]) {
                left = mid + 1;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
