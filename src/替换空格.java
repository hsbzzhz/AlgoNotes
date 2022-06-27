public class 替换空格 {
    public static String replaceSpace(String str){
        /*
         * 用了一个res来存储结果，因为String是不可变的
         */
        StringBuilder res = new StringBuilder();
        for(Character c: str.toCharArray()){
            if(c==' ') res.append("%20");
            else res.append(c);
        }
        return res.toString();
    }
}
