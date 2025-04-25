package Tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        var threadedBinaryTree = new ThreadedBinaryTree();
        var node1 = new ThreadedNode(1, "AAAA");
        var node2 = new ThreadedNode(2, "BBBB");
        var node3 = new ThreadedNode(3, "CCCC");
        var node4 = new ThreadedNode(4, "DDDD");
        var node5 = new ThreadedNode(5, "EEEE");
        var node6 = new ThreadedNode(6, "FFFF");
        var node7 = new ThreadedNode(7, "GGGG");
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        threadedBinaryTree.setRoot(node1);
        threadedBinaryTree.threadedNodes();
        // System.out.println("node 6 左節點為: " + node6.getLeft().getId());
        // System.out.println("node 6 右節點為: " + node6.getRight().getId());
        threadedBinaryTree.threadedList();
    }
}

class ThreadedBinaryTree {
    private ThreadedNode root;
    // 為了線索化，需要一個變量紀錄前一個節點
    private ThreadedNode pre;

    public void setRoot(ThreadedNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        threadedNodes(root);
    }

    /**
     * 對二叉樹進行線索化 (此處使用中序線索化)
     */
    public void threadedNodes(ThreadedNode node) {
        if (node == null) 
            return;

        threadedNodes(node.getLeft());

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;

        threadedNodes(node.getRight());
    }

    public void threadedList() {
        if (root == null) return;
        ThreadedNode node = root;

        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }
    }
}