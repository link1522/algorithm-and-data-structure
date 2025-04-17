package Tree;

public class BinaryTreeDemo {
    /*
     * 1
     * / \
     * 2 3
     * / \ / \
     * 4 5 6 7
     */
    public static void main(String[] args) {
        var binaryTree = new BinaryTree();
        var node1 = new Node(1, "AAAA");
        var node2 = new Node(2, "BBBB");
        var node3 = new Node(3, "CCCC");
        var node4 = new Node(4, "DDDD");
        var node5 = new Node(5, "EEEE");
        var node6 = new Node(6, "FFFF");
        var node7 = new Node(7, "GGGG");
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        binaryTree.setRoot(node1);
        binaryTree.preOrder();
        // binaryTree.infixOrder();
        // binaryTree.postOrder();
    }
}

class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preOrder() {
        if (root == null)
            return;
        root.preOrder();
    }

    public void infixOrder() {
        if (root == null)
            return;
        root.infixOrder();
    }

    public void postOrder() {
        if (root == null)
            return;
        root.postOrder();
    }
}

class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node {\n  id = " + id + ",\n  name = " + name + "\n}";
    }

    // 前序遍歷
    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍歷
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 後序遍歷
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);
    }
}
