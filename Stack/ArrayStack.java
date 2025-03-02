package Stack;

public class ArrayStack {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(5);
        arrayStack.push(7);
        arrayStack.print();
        arrayStack.pop();
        arrayStack.print();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.print();
        arrayStack.pop();
    }

    private int top = -1;
    private int maxSize;
    private int[] stack;

    public ArrayStack(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("size 需要大於 0");
        }
        this.maxSize = size;
        stack = new int[size];
    }

    public int size() {
        return top + 1;
    }

    public Boolean isFull() {
        return size() == maxSize;
    }

    public Boolean isEmpty() {
        return top == -1;
    }

    public void push(int num) {
        if (isFull()) {
            System.out.println("Stack 已滿");
            return;
        }

        top++;
        stack[top] = num;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack 為空");
        }

        int value = stack[top];
        top--;

        return value;
    }

    public int peek() {
        return stack[top];
    }

    public void print() {
        for (int i = 0; i < maxSize - size(); i++) {
            System.out.println("| |");
        }

        for (int i = top; i >= 0; i--) {
            System.out.println("|" + stack[i] + "|");
        }
        System.out.println("---");
        System.out.println();
    }
}
