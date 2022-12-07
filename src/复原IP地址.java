import java.util.ArrayList;
import java.util.List;

public class 复原IP地址 {
    /**
     * 题目：给了一段字符串 s，要用三个点把它分成4个ip地址，求能分出的所有答案
     * ref. https://leetcode.cn/problems/restore-ip-addresses/solution/shou-hua-tu-jie-huan-yuan-dfs-hui-su-de-xi-jie-by-/
     * 回溯
     */
    private List<String> ans = new ArrayList<String>();
    private List<String> temp = new ArrayList<String>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) { // 相当于剪枝(1)，不要也行。直接排除不符合长度的答案
            return ans;
        }
        dfs(s, 0);
        System.out.println(ans);
        return ans;
    }

    public void dfs(String s, int startIndex) {
        if (startIndex == s.length() && temp.size() == 4) {  //条件满足
            StringBuilder ipAddr = new StringBuilder(); // 这是单个ip 集合，包含四个子ip
            for (int i = 0; i < 4; i++) {
                ipAddr.append(temp.get(i));
                if (i != 3) {  // 除了最后一个元素，都要加上 .
                    ipAddr.append('.');
                }
            }
            ans.add(ipAddr.toString());
            return;
        }

         if (startIndex < s.length() && temp.size() == 4) { //  相当于剪枝(2)，不要也行。排除找到四个ip的答案，但是剩余还有字符串未被纳入
            return;
         }

        for (int len = 1; len <= 3; len++) { // 需要凑1-3位的ip
            if(startIndex + len -1 >= s.length()) return;  // 保证 sub 不会越界

            if (len != 1 && s.charAt(startIndex) == '0') { // 剔除不合法的前导0 ，如果是第一位是个0，那就给 0 作为一个ip
                return;
            }

            String st = s.substring(startIndex, startIndex + len); // 这是一段ip 例如255
            if (len == 3 && Integer.parseInt(st) > 255) {  // 剪枝(3):截取字符串长度大于255时，就不是合法ip，长度为3感觉可以省略掉
                return;
            }

            temp.add(st);  // 做选择
            dfs(s, startIndex + len);  // 进入下一层，跳过这个ip
            temp.remove(temp.size()-1); // 撤回，取消选择
        }
    }

    public static void main(String[] args) {
        String s = "25525511135";
        new 复原IP地址().restoreIpAddresses(s);
    }
}
