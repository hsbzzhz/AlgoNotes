public class 实现indexOf函数 {
    public int strStr(String haystack, String needle) {
        // 找到 needle 在 haystack中的第一个位置，并返回
        //  需要嵌套循环，否则用KMP
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {

            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {

                    break;
                }
                if (j==needle.length()-1){
                    return i;
                }
            }
        }
        return -1;
    }
}
