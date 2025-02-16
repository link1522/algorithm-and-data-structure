public class SparseArray {
    public static void main(String[] args) {
        // 定義一個二維數組
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.print(data + " ");
            }
            System.out.println();
        }

        System.out.println();

        // 二維數組轉稀疏數組
        int valueCount = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    valueCount++;
                }
            }
        }

        int[][] sparseArray = new int[valueCount + 1][3];
        sparseArray[0] = new int[] { chessArr1.length, chessArr1[0].length, valueCount };

        int pointer = 1;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sparseArray[pointer] = new int[] { i, j, chessArr1[i][j] };
                    pointer++;
                }
            }
        }

        for (int[] row : sparseArray) {
            for (int data : row) {
                System.out.print(data + " ");
            }
            System.out.println();
        }

        System.out.println();

        // 稀疏數組轉回二維數組
        int[][] chessArr2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            int row = sparseArray[i][0];
            int col = sparseArray[i][1];
            int value = sparseArray[i][2];
            chessArr2[row][col] = value;
        }

        for (int[] row2 : chessArr2) {
            for (int data2 : row2) {
                System.out.print(data2 + " ");
            }
            System.out.println();
        }
    }
}