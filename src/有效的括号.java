import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class 有效的括号 {
    public static boolean isValid(String s){
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')','(');
        pairs.put(']','[');
        pairs.put('}','{');

        Stack<Character> stack = new Stack<>(); // 栈中就只会放左括号
        for (char ch:s.toCharArray()) {  // 把string 转换为 char
            if (pairs.containsKey(ch)){   // 如果碰到右括号, 就出栈，栈顶元素应该是hashmap的值
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)){
                    return false;
                }
                stack.pop(); // 推出栈顶元素
            }
            else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(有效的括号.isValid("{[()]}"));
    }
}
