package Tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        var arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.postOrder(0);
    }
}

class ArrayBinaryTree {
    int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int index) {
        if (index > arr.length - 1) {
            return;
        }
        if (arr == null || arr.length == 0) {
            return;
        }

        System.out.println(arr[index]);
        int left = leftNodeIndex(index);
        int right = rightNodeIndex(index);
        preOrder(left);
        preOrder(right);
    }

    public void infixOrder(int index) {
        if (index > arr.length - 1) {
            return;
        }
        if (arr == null || arr.length == 0) {
            return;
        }

        int left = leftNodeIndex(index);
        int right = rightNodeIndex(index);
        infixOrder(left);
        System.out.println(arr[index]);
        infixOrder(right);
    }

    public void postOrder(int index) {
        if (index > arr.length - 1) {
            return;
        }
        if (arr == null || arr.length == 0) {
            return;
        }

        int left = leftNodeIndex(index);
        int right = rightNodeIndex(index);
        postOrder(left);
        postOrder(right);
        System.out.println(arr[index]);
    }

    private int leftNodeIndex(int index) {
        return (index * 2) + 1;
    }

    private int rightNodeIndex(int index) {
        return (index * 2) + 2;
    }
}
