import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 查找共用字符 {
    /*
     * 给定仅有小写字母组成的字符串数组words，请找出每个字符串中都出现都字符，若一字符在所有字符串出现多次， 则在结果中输出最小次数，请以a-z顺序输出，并以字符串形式返回
     * ref. 1002. https://leetcode.cn/problems/find-common-characters/
     */

    public static List<String> commonChars(String[] words) {
        // 建立一个数组，先把words[0]作为base
        int[] minfreq = new int[26];

        for (char c: words[0].toCharArray()){
            minfreq[c-'a']++;
        }
        // 这样的话，就从第一个元素开始比较好了
        for(int i =1; i<words.length; i++){
            int[] curFreq = new int[26];
            // 遍历每个字符串的时候，都建立一个临时数组
            for (char c: words[i].toCharArray()){
                curFreq[c-'a']++;
            }
            // 每次都用这个临时数组和base数组进行对比，取最小值。如果有0存在， 那么很快该值就会被0覆盖
            for(int j =0; j<minfreq.length;j++){
                minfreq[j] = Math.min(minfreq[j], curFreq[j]);
            }
        }

        // 把结果集转换为 list 输出
        List<String> result = new ArrayList<>();
        for(int k = 0; k < minfreq.length; k++){
            while (minfreq[k] != 0){
                result.add(String.valueOf((char)(k +'a')));
                minfreq[k]--;
            }
        }
        return result;
        
/*
        // 如果输出的是字符串，可以这样做
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < minfreq.length; k++) {
            while (minfreq[k] != 0) {
                sb.append((char)(k +'a'));
                minfreq[k]--;
            }
        }
        char[] res = sb.toString().toCharArray();
        Arrays.sort(res);
        return new String(res);
*/
    }
    public static void main(String[] args) {
        char ch = ';';
        System.out.println(ch - 'a');  // 这是26字母中的顺序
        System.out.println(ch - '0');  // 这是ACII中的顺序

        String[] str = {"soomth","common","mooorinings"};

        System.out.println(查找共用字符.commonChars(str));
    }
}
