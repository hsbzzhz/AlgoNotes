package 基础数据结构操作;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListDemo {

    public static void transfer(){
        // arraylist初始化，这里用一个String 数组来初始化
        List<String> list= new ArrayList<>(Arrays.asList(new String[]{"a0034","z8834","h440"}));
        List<String> list1= new ArrayList<>(Arrays.asList("a0034","z8834","h440"));

        // 将arraylist转为string数组，直接转可能会报错，这样比较保险
        String[] array = new String[list.size()];
        list.toArray(array);

        // arraylist 转 string，这里可以用StringBuilder
        String res = "";
        for(String str: list1) {
            res += str;
        }
        System.out.println(res);

    }

    public static void main(String[] args) {
        transfer();
    }
}
