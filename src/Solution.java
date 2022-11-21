import 基础数据结构操作.ListNode;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Collectors;

public class Solution {

    public static void find(String source) { // todo 顺着思路写下去
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == ']') { // 开始返回
                String temStr = "";
                boolean flag = true; // 表示，可以弹出一个 左括号
                while (!stack.isEmpty() && flag) {
                    char lastOne = stack.pop();
                    if (Character.isLetter(lastOne)) {
                        temStr+=String.valueOf(lastOne);
                    } else if (Character.isDigit(lastOne)) {
                        String temStr1 = temStr;
                        for (int j = 0; j < lastOne; j++) {
                            temStr+=temStr1;
                        }
                    } else flag = false; // 左括号的情况
                }
                sb.append(temStr);
            }
            stack.push(source.charAt(i));
        }
        System.out.println(sb);
    }
    public static void main(String[] args) {
        int[] nums = {3,1,3,4,2};
        String s = "3[a]2[bc]";
        find(s);
    }
}
