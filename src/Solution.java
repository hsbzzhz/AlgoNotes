import 基础数据结构操作.ListNode;
import 基础数据结构操作.TreeNode;
import java.util.*;
public class Solution {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        StringBuilder path = new StringBuilder();
        dfs(n, n, path);
        return res;
    }

    private void dfs(int left, int right, StringBuilder path) {
        if (left < 0 || right < 0) {
            return;
        }
        // 只有左边括号先于右边括号用完才行，秒啊
        if (left > right) {
            return;
        }

        if (left == 0 && right == 0) {
            res.add(path.toString());
            System.out.println(path);
        }
        path.append('(');
        dfs(left-1, right, path);
        path.deleteCharAt(path.length()-1);

        path.append(')');
        dfs(left, right-1, path);
        path.deleteCharAt(path.length()-1);
    }

    public static void main(String[] args) {
        new Solution().generateParenthesis(3);
    }
}
