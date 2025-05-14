package Huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {
    public static void main(String[] args) {
        /*
         * 壓縮 & 解壓縮字串
         */
        // String content = "i like like like java do you like a java";
        // byte[] contentBytes = content.getBytes();
        // byte[] huffmanBytes = huffmanZip(contentBytes);
        // byte[] decodedBytes = decode(huffmanCodes, huffmanBytes);
        // System.out.println(new String(decodedBytes));

        /*
         * 壓縮 & 解壓縮文件
         */
        // zipFile("D:\\tmp\\600x400.jpg", "D:\\tmp\\600x400.cps");
        unzipFile("D:\\tmp\\600x400.cps", "D:\\tmp\\600x400_2.jpg");
    }

    public static void unzipFile(String zipFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 壓縮文件
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);
            byte[] huffmanBytes = huffmanZip(b);

            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os); // 使用對象流，以便後續讀出對象
            oos.writeObject(huffmanBytes); // 寫入壓縮後的資料
            oos.writeObject(huffmanCodes); // 寫入 huffman Codes，後續才可以解壓縮
        } catch (Exception exception) {

        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (Exception exception) {

            }
        }
    }

    /*
     * 解壓縮
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        var stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = !(i == huffmanBytes.length - 1);
            String str = byteToBitString(flag, huffmanBytes[i]);
            stringBuilder.append(str);
        }

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); i++) {
            int count = 1;

            while (i + count < stringBuilder.length()) {
                String str = stringBuilder.substring(i, i + count + 1);
                if (map.containsKey(str)) {
                    list.add(map.get(str));
                    i += count;
                    break;
                }
                count++;
            }
        }

        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }

        return bytes;
    }

    /**
     * 將 Byte 轉成二進制的字串(補碼)，ex: 8 => 1000
     * flag 代表是否補零
     * 在 huffman 最後一位可能沒有補滿 8 位，ex: 101，此時前面不用再補零
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b; // 將 byte 轉乘 int
        if (flag) {
            temp |= 256; // 補高位(補零)， 1 0000 0000 | 0000 0001 => 1 0000 0001 才可以做 substring
        }
        String str = Integer.toBinaryString(temp); // 返回補碼

        if (flag) {
            return str.substring(str.length() - 8); // 只取最後 8 位 (1 Byte)
        }
        return str;
    }

    /**
     * Huffman 編碼壓縮
     * 1. 透過資料的 byte[] 創建 nodes (node 中記錄 byte 與其出現次數)
     * 2. 用 nodes 創建 Huffman tree
     * 3. 從 Huffman tree 取 Huffman codes
     * 4. 透過 Huffman codes 轉換原資料 (壓縮)
     */
    private static byte[] huffmanZip(byte[] contentBytes) {
        List<Node> nodes = getNodes(contentBytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 取得每個字符 Node 組成的 List
     */
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

    /**
     * 創建 Huffman tree
     * 
     * @param nodes
     * @return
     */
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

    /**
     * 取得 Huffman codes (對照表)
     * 
     * @param node
     * @return
     */
    private static Map<Byte, String> getCodes(Node node) {
        if (node == null)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        getCodes(node.left, "0", stringBuilder);
        getCodes(node.right, "1", stringBuilder);

        return huffmanCodes;
    }

    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        int len;
        // len = (stringBuilder.length() + 7) / 8; // 也可以這樣取代以下的 if...else
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String stringByte;
            if (i + 8 > stringBuilder.length()) {
                stringByte = stringBuilder.substring(i);
            } else {
                stringByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(stringByte, 2);
            index++;
        }

        return huffmanCodeBytes;
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