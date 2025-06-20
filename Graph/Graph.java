package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;
        String[] vertexs = { "A", "B", "C", "D", "E" };
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();
        System.out.println("DFS: ");
        graph.dfs();
        System.out.println();
        System.out.println("BFS: ");
        graph.bfs();
    }

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    /**
     * Breadth-first search
     */
    private void bfs(int i) {
        int u; // 對列取出的節點
        int w; // 鄰接節點索引
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getValueByIndex(i) + " ");
        isVisited[i] = true;
        queue.addLast(i);

        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " ");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];
        int n = getNumOfVertex();
        for (int i = 0; i < n; i++) {
            if (isVisited[i]) {
                continue;
            }
            bfs(i);
        }
    }

    /**
     * Depth-first search
     */
    private void dfs(int i) {
        System.out.print(getValueByIndex(i) + " ");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 重載 dfs
     */
    public void dfs() {
        isVisited = new boolean[getNumOfVertex()];
        int n = getNumOfVertex();
        for (int i = 0; i < n; i++) {
            if (isVisited[i]) {
                continue;
            }
            dfs(i);
        }
    }

    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
