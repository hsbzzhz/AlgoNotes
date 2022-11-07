import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        String s = "([]{()})";
        String s1 = "([)]";
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')','(');
        pairs.put(']','[');
        pairs.put('}','{');

        int depth = 0, temp = 0;
        Stack<Character> stack = new Stack<>(); // 栈中就只会放左括号
        for (char ch:s1.toCharArray()) {  // 把string 转换为 char
            if (pairs.containsKey(ch)) {   // 如果碰到右括号
                temp --;
            } else {
                stack.push(ch);
                temp++;
                depth = Math.max(temp, depth);
            }
        }
        if (stack.isEmpty()) {
            System.out.println(depth);
        } else {
            System.out.println("0");
        }
    }
}
