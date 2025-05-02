package Huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = { 13, 7, 8, 3, 29, 6, 1 };
        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.preOrder();
    }

    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);

            // 取出權值最小的節點
            Node leftNode = nodes.get(0);
            // 取出權值第二小的節點
            Node rightNode = nodes.get(1);

            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 從 nodes 中刪除處理過的節點
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 將 parent 加入到 nodes
            nodes.add(parent);
        }

        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }

    @Override
    public String toString() {
        // return "Node {\n value = " + value + ",\n left = " + left + ",\n right = " +
        // right + "\n}";
        return "Node { value = " + value + " }";
    }

    @Override
    public int compareTo(Node node) {
        return this.value - node.value;
    }
}
