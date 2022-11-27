import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 字符串解码 {
    /**
     * https://leetcode.cn/problems/decode-string/solution/decode-string-fu-zhu-zhan-fa-di-gui-fa-by-jyd/
     *
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        Stack<Integer> stack_multi = new Stack<>(); // 用于处理 多个连续数字的情况比如 100
        List<String> stack_res = new ArrayList<>(); //

        for (Character c : s.toCharArray()) {
            if (c == '[') { // case1：分别将 res 和 multi 入栈，然后重置
                // 当碰到 [ 表示是一个新的元组，需把旧的暂存
                stack_multi.push(multi);
                stack_res.add(res.toString());

                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') { // case2：
                StringBuilder tmp = new StringBuilder(); // 临时变量
                int cur_multi = stack_multi.pop(); // 数字出栈，出栈后栈为空否？
                for (int i = 0; i < cur_multi; i++) {
                    tmp.append(res);
                }

                res = new StringBuilder(stack_res.remove(stack_res.size() - 1) + tmp); // 更新上 res
            } else if (Character.isDigit(c)) { // case3：当数字的情况
                // 这里是把所有位数一起打包成一个数字，最后放进栈中
                multi = multi * 10 + (c - '0');
            } else if (Character.isLetter(c)) { // case4：当字母的时候
                res.append(c);
            }
        }

        System.out.println(res);
        return res.toString();
    }

    public static void main(String[] args) {
        String source = "3[a2[c]]";
        decodeString(source);
    }
}
