package KMP;

public class KMP {
    public static void main(String[] args) {
        var str = "BBC ABCDAB ABCDABCDABDE";
        var subStr = "ABCDABD";

        var index = kmpSearch(str, subStr, kmpNext(subStr));
        System.out.println(index);
    }

    /**
     * @param str 主字串
     * @param subStr 子字串
     * @param next 子字串的部分匹配表
     * @return -1 代表沒有匹配到，否則返回找到的第一個位置
     */
    public static int kmpSearch(String str, String subStr, int[] next) {
        for (int i = 0, j = 0; i < str.length(); i++) {

            while (j > 0 && str.charAt(i) != subStr.charAt(j)) {
                j = next[j - 1];
            }

            if (str.charAt(i) == subStr.charAt(j)) {
                j++;
            }

            if (j == subStr.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }

    // 產生部分匹配表
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < next.length; i++) {
            while(j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if(dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;
    }
}
