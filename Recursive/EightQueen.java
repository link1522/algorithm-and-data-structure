package Recursive;

/**
 * 八皇后問題
 * 在 8 x 8 的棋盤上，擺 8 個皇后棋，同一行、同一列、與斜線上，不可同時存在其他皇后棋
 */
public class EightQueen {
    // 有多少個皇后
    int max = 8;
    // 擺放位置，索引同時代表第 i + 1 個皇后棋與第 i 列 (row)
    int[] array = new int[max];
    // 紀錄有幾個解法
    static int count;

    public static void main(String[] args) {
        var eightQueen = new EightQueen();
        eightQueen.putQueen(0);
        System.out.println();
        System.out.println("一共有 " + count + " 種解法");
    }

    /**
     * 放置第 n 個皇后
     */
    private void putQueen(int n) {
        if (n == max) {
            count++;
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            // 先放置皇后棋 (從目前行由左至右擺放)
            array[n] = i;
            // 檢查是否衝突
            if (isValid(n)) {
                // 若不衝突就往下一行遞迴
                putQueen(n + 1);
            }
        }
    }

    /**
     * 檢查第 n 個皇后棋是否有違規擺放
     * 
     * @param n 第 n 個皇后
     * @return
     */
    private Boolean isValid(int n) {
        for (int i = 0; i < n; i++) {
            // array[i] == array[n] 檢查是否在同一 row
            // Math.abs(n - i) == Math.abs(array[n] - array[i]) 檢查斜線是否相同 (45度斜率)
            // 不需要判斷是否同一行，因為原本開的 array 舊式一行一行增加的
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }

        return true;
    }

    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
