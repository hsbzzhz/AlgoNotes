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
    
    public static void indexIdentify() {
        String s1 = "132abct";
        // 求子串在s1中的位置，如果匹配不上就返回-1
        int index = s1.indexOf("abc");
        System.out.println(index); // 3, 从0开始算
    }

    public static void transform(){
        // 字符串的大小写转换
        String s = "The Day Is Good";
        System.out.println(s.toLowerCase());   //输出"the day is good"
        System.out.println(s.toUpperCase());    //输出"THE DAY IS GOOD"

        // Char的大小写转换
        char a='A';
        char b='b';
        System.out.println(Character.toLowerCase(a));   //输出"a"
        System.out.println(Character.toUpperCase(b));   //输出"B"
    }
    
    public static void patternMatch(){
        boolean res = str.matches("^[a-zA-Z]\\d{1,8}$");
        // 把double换成String，.vauleOf就是转换成对应该类
        String raw = String.valueOf(3.49);

        // 在数字前面补零
        String formatCode = String.format("%08d",55666);//其中0表示补零，8表示总共要的位数，第二个参数要是整形，如果不是用Interger.valueOf来进行转换
        System.out.println(formatCode);

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
