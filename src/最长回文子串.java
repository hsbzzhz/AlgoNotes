public class 最长回文子串 {
    /*
     * topic: 字符串
     * 给定一个字符串 s，找到 s 中最长的回文子串
     * 《中心扩散法》
     */
    public String longestPalidrome(String s){
        // 返回值是子串
        if(s == null || s.length()<1){
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++){
            int odd_len = expandAroundCenter(s, i, i); // 一个字母作为中心，左右扩散
            int even_len = expandAroundCenter(s,i,i+1);  // 两个字母同时作为中心，左右扩散
            int len = Math.max(odd_len, even_len);
            if(len > end-start){
                start = i - (len-1)/2; // 从中心位置，减去一半就是start 位置
                end = i+len/2;  // 从中心位置，加上一半，就是 end 位置
            }
        }
        return s.substring(start, end+1);
    }

    public int expandAroundCenter(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            // 左右指针不能越界，还要左指针的值等于右指针的值
            left--;
            right++;
        }
        return right-left-1;
    }
}
