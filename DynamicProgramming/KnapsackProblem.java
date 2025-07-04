package DynamicProgramming;

public class KnapsackProblem {
    public static void main(String[] args) {
        // 每個物品的重量
        int[] w = { 1, 4, 3 };
        // 每個物品的價值
        int[] val = { 1500, 3000, 2000 };
        // 背包可容納的總重
        int m = 4;
        // 物品數量
        int n = val.length;

        // v[i][j] 表示在前 i 個物品中能夠裝入容量為 j 的背包中的最大值
        int[][] v = new int[n + 1][m + 1];

        // 紀錄放入物品的情況
        int[][] path = new int[n + 1][m + 1];

        // 初始化第一行第一列 (可以不用特別處理，因為預設就是 0)
        for (int i = 0; i < v.length; i++) {
            // 將第一列設為 0
            v[i][0] = 0;
        }
        for (int j = 0; j < v[0].length; j++) {
            // 將第一行設為 0
            v[0][j] = 0;
        }

        // 開始 DP
        for (int i = 1; i < v.length; i++) {
            // 特別分開表示 itemIndex 會比較好理解，其他寫 i - 1 代表前一項
            int itemIndex = i - 1;
            for (int j = 1; j < v[0].length; j++) {
                // j 同時代表背包可承受重量 (從 1 ~ 4)
                // 前一個總價值 (從表格上來看，就是垂直往上一格的值)
                int previousValue = v[i - 1][j];
                if (w[itemIndex] > j) {
                    // 如果物品重量超過背包能裝的總重，就直接等於前一個總價值
                    v[i][j] = previousValue;
                } else {
                    // 嘗試加入當前物品後的價值 =
                    // 當前物品價值 val[itemIndex] +
                    // 扣除當前物品重量後的最大價值(來自之前的紀錄) v[i - 1][j - w[itemIndex]]
                    int addCurItemValue = val[itemIndex] + v[i - 1][j - w[itemIndex]];
                    // 如果物品重量沒有超過背包能裝的總重，就拿
                    // - 前一個總價值 previousMax
                    // - 當前物品價值 + 扣除當前物品重量後的最大價值(來自之前的紀錄) v[i - 1][j - w[itemIndex]]
                    // 如果不用紀錄放入哪些物品，可以直接用 Math.max() 求最大值
                    // v[i][j] = Math.max(previousMax, addCurItemValue);
                    if (previousValue > addCurItemValue) {
                        v[i][j] = previousValue;
                    } else {
                        v[i][j] = addCurItemValue;
                        path[i][j] = 1; // 紀錄放入物品
                    }
                }
            }
        }

        // 輸出 v 形成的表格
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        // 印出那些商品放入了背包，path 從後往前找
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("放入第 %d 個物品\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
