package Tree;

public class ThreadedNode {
    private int id;
    private String name;
    private ThreadedNode left;
    private ThreadedNode right;
    // 0 代表指向左子樹，1 代表指向前驅節點
    private int leftType = 0;
    // 0 代表指向右子樹，1 代表指向後繼節點
    private int rightType = 0;

    public ThreadedNode(int id, String name) {
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

    public ThreadedNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedNode left) {
        this.left = left;
    }

    public ThreadedNode getRight() {
        return right;
    }

    public void setRight(ThreadedNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "ThreadedNode {\n  id = " + id + ",\n  name = " + name + "\n}";
    }
}
