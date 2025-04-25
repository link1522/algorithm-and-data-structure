package Tree;

public class Node {
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

    /*
     * 遍歷
     */

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

    /*
     * 查找
     */
    public Node preOrderSearch(int id) {
        if (this.id == id) {
            return this;
        }

        Node result = null;

        if (this.left != null) {
            result = this.left.preOrderSearch(id);
        }
        if (result != null) {
            return result;
        }

        if (this.right != null) {
            return this.right.preOrderSearch(id);
        }

        return result;
    }

    public Node infixOrderSearch(int id) {
        Node result = null;

        if (this.left != null) {
            result = this.left.infixOrderSearch(id);
        }
        if (result != null) {
            return result;
        }

        if (this.id == id) {
            return this;
        }

        if (this.right != null) {
            result = this.right.infixOrderSearch(id);
        }

        return result;
    }

    public Node postOrderSearch(int id) {
        Node result = null;

        if (this.left != null) {
            result = this.left.postOrderSearch(id);
        }
        if (result != null) {
            return result;
        }

        if (this.right != null) {
            result = this.right.postOrderSearch(id);
        }
        if (result != null) {
            return result;
        }

        if (this.id == id) {
            return this;
        }

        return null;
    }

    /*
     * 刪除
     */
    public void delete(int id) {
        if (this.left != null) {
            if (this.left.id == id) {
                this.left = null;
                return;
            }
            this.left.delete(id);
        }

        if (this.right != null) {
            if (this.right.id == id) {
                this.right = null;
                return;
            }
            this.right.delete(id);
        }
    }   
}
