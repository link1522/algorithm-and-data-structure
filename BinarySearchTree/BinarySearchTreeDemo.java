package BinarySearchTree;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 7, 3, 10, 12, 5, 1, 9 };
        var binarySearchTree = new BinarySearchTree();
        for (int num : arr) {
            binarySearchTree.add(new Node(num));
        }
        binarySearchTree.infixOrder();
    }
}

class BinarySearchTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            this.root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null)
            root.infixOrder();
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    // 遞迴加入，須滿足二叉排序樹
    public void add(Node node) {
        if (node == null)
            return;

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node {\n  value = " + value + "\n}";
    }
}
