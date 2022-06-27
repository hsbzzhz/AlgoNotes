public class 最长公共前缀 {
    public String longgestCommonPrefix(String[] strs){
        if(strs==null||strs.length==0){
            return "";
        }
        String res = strs[0];
        int i = 1; // 从第二个元素开始判断，因为第一个元素作为参照物
        while (i<strs.length){
            while (strs[i].indexOf(res) != 0 ){
                // 只要第一个字符串和 res 不匹配，就要剪枝
                res = res.substring(0,res.length()-1);
            }
            i++;
        }
        return res;
    }
}
