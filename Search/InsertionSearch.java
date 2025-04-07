package Search;

public class InsertionSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        int result = search(10, arr, 0, arr.length - 1);
        System.out.println(result);
    }

    public static int search(int target, int[] arr, int left, int right) {
        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }

        // 與 Binary Search 相似，差在此公式
        int mid = left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]);

        if (target > arr[mid]) {
            return search(target, arr, mid + 1, right);
        } else if (target < arr[mid]) {
            return search(target, arr, left, mid - 1);
        } else {
            return mid;
        }
    }
}
