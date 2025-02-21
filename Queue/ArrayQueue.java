package Queue;

import java.util.Arrays;
import java.util.stream.Collectors;

// 環形陣列
// 此實現會空出一個空間無法使用，例如傳入 maxSize = 4，但實際可使用的空間為 3
public class ArrayQueue {
    private int maxSize;
    private int front; // 對列中的第一個元素位置，預設為 0
    private int rear; // 對列中的最後一個元素後一個位置(空出一個空間)，預設為 0
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front % maxSize;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("對列已滿，不能加入");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("對列為空，無法取出");
        }
        int target = arr[front];
        front = (front + 1) % maxSize;
        return target;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("對列為空");
            return;
        }

        int[] tmpArr = new int[size()];
        int tmpArrIndex = 0;
        for (int i = front; i < front + size(); i++) {
            tmpArr[tmpArrIndex] = arr[i % maxSize];
            tmpArrIndex++;
        }
        String arrStr = Arrays.stream(tmpArr).mapToObj(String::valueOf).collect(Collectors.joining(", "));
        System.out.println("[" + arrStr + "]");
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("對列為空");
        }

        return arr[front];
    }
}

class Demo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        queue.addQueue(10);
        queue.addQueue(20);
        queue.showQueue(); // [10, 20]
        System.out.println(queue.headQueue()); // 10
        queue.getQueue();
        System.out.println(queue.headQueue()); // 20
        queue.addQueue(30);
        queue.showQueue(); // [20, 30]
    }
}