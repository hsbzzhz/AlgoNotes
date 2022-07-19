public class String基础知识 {
    public static void travel(){
        // 三种方式遍历字符串
        String str = "2022 cheer up!";

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
}
