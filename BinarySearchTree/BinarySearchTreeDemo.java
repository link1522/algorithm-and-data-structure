package BinarySearchTree;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2 };
        var binarySearchTree = new BinarySearchTree();
        for (int num : arr) {
            binarySearchTree.add(new Node(num));
        }
        // binarySearchTree.infixOrder();

        binarySearchTree.deleteNode(10);
        binarySearchTree.infixOrder();
    }
}

class BinarySearchTree {
    private Node root;

    public Node search(int value) {
        if (root == null)
            return null;
        return root.search(value);
    }

    public Node searchParent(int value) {
        if (root == null)
            return null;
        return root.searchParent(value);
    }

    public void deleteNode(int value) {
        if (root == null)
            return;

        var target = search(value);
        if (target == null)
            return;

        // 有找到 target 且樹中只有一個 root 節點 (target 即為 root)
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }

        var parent = searchParent(value);
        if (target.left == null && target.right == null) {
            // 如果 target 是葉子節點
            if (parent.left == target) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (target.left != null && target.right != null) {
            // 如果 target 有兩個子節點
            // 找右子樹中的最小值放到 target (也可以是左子樹找一個最大值)
            var minValue = deleteRightTreeMin(target.right);
            target.value = minValue;
        } else {
            // 如果 target 只有一個子節點
            Node targetChild;
            if (target.left != null) {
                targetChild = target.left;
            } else {
                targetChild = target.right;
            }

            if (parent.left == target) {
                parent.left = targetChild;
            } else {
                parent.right = targetChild;
            }
        }
    }

    /**
     * 
     * @param node
     * @return 以傳入的 node 節點為根找到的最小 value
     */
    public int deleteRightTreeMin(Node node) {
        var target = node;
        while (target.left != null) {
            target = target.left;
        }
        deleteNode(target.value);

        return target.value;
    }

    /**
     * 
     * @param node
     * @return 以傳入的 node 節點為根找到的最大 value
     */
    public int deleteLeftTreeMax(Node node) {
        var target = node;
        while (target.right != null) {
            target = target.right;
        }
        deleteNode(target.value);

        return target.value;
    }

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
