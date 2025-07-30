package Greedy;

import java.util.Arrays;

public class Kruskal {
    // 邊的個數
    private int edgeNum;
    // 頂點
    private char[] vertexs;
    // 鄰接矩陣
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] matrix = {
                { 0, 12, INF, INF, INF, 16, 14 },
                { 12, 0, 10, INF, INF, 7, INF },
                { INF, 10, 0, 3, 5, 6, INF },
                { INF, INF, 3, 0, 4, INF, INF },
                { INF, INF, 5, 4, 0, 2, 8 },
                { 16, 7, 6, INF, 2, 0, 9 },
                { 14, INF, INF, INF, 8, 9, 0 },
        };

        var kruskal = new Kruskal(vertexs, matrix);
        kruskal.kruskal();
        // kruskal.print();

        // var test = kruskal.getEdges();
        // kruskal.sortEdges(test);
        // System.out.println(Arrays.toString(test));
    }

    public Kruskal(char[] vertexs, int[][] matrix) {
        int vlen = vertexs.length;
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (matrix[i][j] != INF && matrix[i][j] != 0) {
                    edgeNum++;
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private void sortEdges(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    var temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    private int getPosition(char vertex) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == vertex) {
                return i;
            }
        }
        return -1;
    }

    private Edge[] getEdges() {
        int index = 0;
        var edges = new Edge[edgeNum];

        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index] = new Edge(vertexs[i], vertexs[j], matrix[i][j]);
                    index++;
                }
            }
        }

        return edges;
    }

    /**
     * 獲取下標為 i 的頂點終點
     * 
     * @param ends 記錄各個頂點的終點
     * @param i    傳入頂點對應的下標
     * @return 返回終點對應的下標
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    private void kruskal() {
        int index = 0;
        int[] ends = new int[edgeNum];
        Edge[] results = new Edge[edgeNum];

        Edge[] edges = getEdges();
        sortEdges(edges);
        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            // p1 在已有生成數的最小終點
            int m = getEnd(ends, p1);
            // p2 在已有生成數的最小終點
            int n = getEnd(ends, p2);

            if (m != n) {
                ends[m] = n;
                results[index] = edges[i];
                index++;
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.println(results[i]);
        }
    }
}

// 邊
class Edge {
    // 起點
    char start;
    // 終點
    char end;
    // 權值
    int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge { <" + start + ", " + end + "> = " + weight + " }";
    }
}