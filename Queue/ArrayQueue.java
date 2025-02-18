package Queue;

public class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("對列已滿，不能加入");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("對列為空，無法取出");
        }
        front++;
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("對列為空");
            return;
        }

        System.out.print("[");
        for (int item : arr) {
            System.out.print(item + " ");
        }
        System.out.print("]");
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("對列為空");
        }

        return arr[front + 1];
    }
}
