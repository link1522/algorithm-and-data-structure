package Recursive;

public class Maze {
    private static int[][] map = new int[8][7];

    public static void main(String[] args) {
        // 1 代表牆
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            int latestCol = map[0].length - 1;
            map[i][latestCol] = 1;
        }

        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            int latestRow = map.length - 1;
            map[latestRow][i] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;

        printMaze();
        System.out.println();
        findWay(map, 1, 1);
        printMaze();
    }

    /**
     * (i, j) 代表出發點, 並將 (6, 5) 設為出口。策略按照 下 -> 右 -> 上 -> 左 順序進行探測。不同策略會造成不同的走法
     * 
     * @param map 地圖，1 代表牆，0 代表可以走的路徑(沒有走過)，2 代表可以走通的路徑，3 代表已探測過但走不通
     * @param i   行
     * @param j   列
     * @return 是否找到出口
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (findWay(map, i + 1, j)) { // 向下走
                    return true;
                } else if (findWay(map, i, j + 1)) { // 向右走
                    return true;
                } else if (findWay(map, i - 1, j)) { // 向上走
                    return true;
                } else if (findWay(map, i, j - 1)) { // 向左走
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static void printMaze() {
        for (int[] row : map) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
