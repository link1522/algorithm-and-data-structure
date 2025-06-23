package DivideAndConquer;

public class TowerOfHanoi {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第 1 個盤從 " + a + " -> " + c);
        } else {
            // 代表將 a 移動到 b，中間會借助於 c
            hanoiTower(num - 1, a, c, b);
            // 代表將 b 移動到 c，中間會借助於 a
            hanoiTower(num - 1, b, a, c);
            System.out.println("第 " + num + " 個盤從 " + a + " -> " + c);
        }
    }
}
