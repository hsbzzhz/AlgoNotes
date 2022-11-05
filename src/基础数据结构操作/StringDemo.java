package 基础数据结构操作;

import java.util.Arrays;

public class StringDemo {

    private static final String str = "2022 cheer up!";
    public static void travelString(){
        // 三种方式遍历字符串

        // 方法一： 使用charAt
        for(int i=0; i<str.length(); i++){
            System.out.println(str.charAt(i));
        }

        // 方法二：使用 substring
        for(int i=0; i < str.length(); i++){
            System.out.println(str.substring(i,i+1));
        }

        // 方法三： 把 string 变成char数组
        char[] c = str.toCharArray();
        for (char value : c) {
            System.out.println(value);
        }
    }

    public static void patternMatch(){
        boolean res = str.matches("^[a-zA-Z]\\d{1,8}$");
        // 把double换成String，.vauleOf就是转换成对应该类
        String raw = String.valueOf(3.49);

        // 碰到数字，要在其中补0；如果不是数字怎么办

        // substring，记住是小写，然后是取1-4位的值
        System.out.println(str.substring(1,5)); //
    }

    public static void splitDemo(){
        // 分隔符为空格的时候可以" "，否则要加转义符 \\
        String temp = "dff.dff.efff.ggg";
        String[] spilt = temp.split("\\.");
        for (String s :spilt) {
            System.out.println(s);
        }
    }

    public static void array2String() {
        /*
         * String 和 char[] 互相转换
         */

        // 1. String 转换为 char
        String temp = "a:3";
        char ch1 = temp.charAt(0); // 转换单个String 为 char，即使只有一个 String 的时候也需要这样
        char[] charArray = temp.toCharArray();  // 将整个String 转换为 char数组，可以填参数offset？

        // 2. char数组，转 String
        char[] array = {'0', '1', '2'};
        // String str = array.toString();  // 这样是获取array的地址，不可取
        String str = Arrays.toString(array);  // 方法一： 用Arrays ：[0 ,1 ,2]
        String str2 = new String(array); // 方法二： new String ： 012
        String str3 = String.valueOf(array); //方法三： 012
    }

    public static void main(String[] args) {
    }
}
