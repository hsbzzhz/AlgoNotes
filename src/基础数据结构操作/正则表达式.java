package 基础数据结构操作;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 正则表达式 {

    public static void matchAll(){
        // 匹配所有的子串

        String raw = "ferg(3,10)as13fdsf3(3,4)f2r3rfasf(5,10)";
        // 1. 新建pattern
        Pattern r = Pattern.compile("\\((.*?)\\)");  // 加？ 就是简单匹配，不然就是贪婪匹配，从第一个括号匹配到最后一个
        // 2. 新建Matcher
        Matcher m = r.matcher(raw);
        while (m.find()){ // 用find
            System.out.println(m.group(1)); // group(0) 是把左右括号都带上的
        }
    }

    public static void simpleMatch(){
        // 返回一个boolean类型，这个是不是匹配
        String raw = "1234@234";
        boolean res = raw.matches("\\d+@.{3}");
        System.out.println(res);
    }

    public static void main(String[] args) {
        simpleMatch();
    }
}
