public class 判断是否为回文字符串 {
    /*
     * 给定一个字符串 s ，验证 s 是否是 回文串
     * 只考虑字母和数字字符，可以忽略字母的大小写
     */
    public static boolean isPalindrome(String s, int i, int j){
        // 用双指针来判断这个字符串是不是回文，还可以直接反转字符串然后进行判断
        int n = s.length();
        int left = 0, right = n-1;
        while(left < right){
            while (left<right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while (left<right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String target = "abccfba";

        System.out.println(isPalindrome(target,0,target.length()));
    }
}
