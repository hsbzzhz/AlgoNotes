package 基础数据结构操作;

import java.util.StringJoiner;

public class StringDemo {
    public static void travelString(){
        // 三种方式遍历字符串
        java.lang.String str = "2022 cheer up!";

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
        for(int i=0; i< c.length; i++){
            System.out.println(c[i]);
        }
    }

    public static void main(java.lang.String[] args) {
        // 把String 数组转换为字符串
        java.lang.String[] names = {"Bob", "Alice", "Grace"};
        StringJoiner sj = new StringJoiner(", ");
        for (java.lang.String name : names) {
            sj.add(name);
        }
        System.out.println(sj.toString());
    }
}
