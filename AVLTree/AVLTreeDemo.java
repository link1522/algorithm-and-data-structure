package AVLTree;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 10, 12, 8, 9, 7, 6 };
        var avlTree = new AVLTree();
        for (int value : arr) {
            avlTree.add(new Node(value));
        }

        var height = avlTree.getRoot().height();
        System.out.println(height);
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

            // target 為 root 時 (parent 為 null)，直接將 root 指向 targetChild
            if (parent == null) {
                root = targetChild;
                return;
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

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 以當前節點為基準的最大高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public void leftRotate() {
        var newNode = new Node(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        right = right.right;
        left = newNode;
    }

    public void rightRotate() {
        var newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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

        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
            }
            rightRotate();
            return;
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
