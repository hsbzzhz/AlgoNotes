import java.util.ArrayList;
import java.util.List;

public class 复原IP地址 {
    /**
     *     作者：LeetCode-Solution
     *     链接：https://leetcode.cn/problems/restore-ip-addresses/solution/fu-yuan-ipdi-zhi-by-leetcode-solution/
     */
    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<String>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses(String s) {
        segments = new int[SEG_COUNT]; // 这里存的是path，四个ip地址
        dfs(s, 0, 0);
        System.out.println(ans);
        return ans;
    }

    public void dfs(String s, int segId, int segStart) {
        /*
         * 表示正在从 s[segStart] 的位置开始，搜索 IP 地址中的第 segId 段，其中 segId∈{0,1,2,3}
         *
         */
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案 --- step1.
        if (segId == SEG_COUNT) { // segments里有四个元素
            if (segStart == s.length()) { // s 也全部遍历完成
                StringBuilder ipAddr = new StringBuilder();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0'); // 单个ip
            if (addr > 0 && addr <= 255) {  // 0xFF 为16进制255
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String s = "25525511135";
        new 复原IP地址().restoreIpAddresses(s);
    }
}
