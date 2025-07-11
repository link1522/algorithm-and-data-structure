package KMP;

public class BruteForce {
    public static void main(String[] args) {
        // 需求
        // 找出字串中，第一個匹配子字串的索引
        var index = findSubStrIndex("ffeeappeffeapp", "app");
        System.out.println(index);
    }

    public static int findSubStrIndex(String str, String subStr) {
        char[] charArr = str.toCharArray();
        char[] subCharArr = subStr.toCharArray();

        // 主字串索引
        int i = 0;
        // 子字串索引
        int j = 0;

        while(i < charArr.length && j < subCharArr.length) {
            // 從第一個字元匹配到後，持續向後比對子字串中的字元
            if (charArr[i] == subCharArr[j]) {
                if (j == subCharArr.length - 1) {
                    return i - j;
                }
                i++;
                j++;
            } else {
                // 匹配失敗就重新回到原本位置 + 1，並重置子字串索引
                i = i - j + 1;
                j = 0;
            }
        }

        return -1;
    }
}
