package Huffman;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        List<Node> nodes = getNodes(contentBytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);

        System.out.println(huffmanCodes);
    }

    private static List<Node> getNodes(byte[] bytes) {
        HashMap<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            if (!map.containsKey(b)) {
                map.put(b, 1);
            } else {
                map.put(b, map.get(b) + 1);
            }
        }

        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            nodes.add(node);
        }

        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        if (nodes.size() == 0) {
            throw new InvalidParameterException("nodes 長度為空");
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);

            Node combined = new Node(null, left.weight + right.weight);
            combined.left = left;
            combined.right = right;
            nodes.add(combined);

            nodes.remove(left);
            nodes.remove(right);
        }

        return nodes.get(0);
    }

    static HashMap<Byte, String> huffmanCodes = new HashMap<>();

    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder currentStringBuilder = new StringBuilder(stringBuilder);
        currentStringBuilder.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", currentStringBuilder);
                getCodes(node.right, "1", currentStringBuilder);
            } else {
                huffmanCodes.put(node.data, currentStringBuilder.toString());
            }
        }
    }

    private static Map<Byte, String> getCodes(Node node) {
        if (node == null)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        getCodes(node.left, "0", stringBuilder);
        getCodes(node.right, "1", stringBuilder);

        return huffmanCodes;
    }
}

class Node implements Comparable<Node> {
    Byte data; // 字符的 ASCII 號碼
    int weight; // 字符出現次數
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "\nNode { data = " + data + ", weight = " + weight + " }";
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}