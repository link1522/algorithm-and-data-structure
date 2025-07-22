package Greedy;

import java.util.Arrays;

public class Prim {
    public static void main(String[] args) {
        char[] data = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int verxs = data.length;
        // 10000 表示不連通 (非常大的權重，不會被選到)
        int[][] weight = {
                { 10000, 5, 7, 10000, 10000, 10000, 2 },
                { 5, 10000, 10000, 9, 10000, 10000, 3 },
                { 7, 10000, 10000, 10000, 8, 10000, 10000 },
                { 10000, 9, 10000, 10000, 10000, 4, 10000 },
                { 10000, 10000, 8, 10000, 10000, 5, 4 },
                { 10000, 10000, 10000, 4, 5, 10000, 6 },
                { 2, 3, 10000, 10000, 4, 6, 10000 }
        };

        var minTree = new MinTree(verxs, data, weight);
        minTree.showGraph();
        minTree.prim(0);
    }
}

// 最小生成樹
class MinTree {
    private MGraph graph;

    public MinTree(int verxs, char[] data, int[][] weight) {
        graph = new MGraph(verxs);

        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];

            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph() {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 獲取最小生成樹
     * 
     * @param v 圖從第幾個頂點開始生成
     */
    public void prim(int v) {
        int[] visited = new int[graph.verxs];

        visited[v] = 1; // 1 代表已訪問

        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs; k++) {
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            System.out.println("邊<" + graph.data[h1] + ", " + graph.data[h2] + "> 權值: " + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

class MGraph {
    // 圖的節點數量
    int verxs;
    // 節點數據
    char[] data;
    // 鄰接陣列
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
