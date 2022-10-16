package 基础数据结构操作;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapDemo {
    public static void travelHashMap(){
        // 初始化一个hashmap
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,4);
        map.put(2,5);
        map.put(3,6);


        // 1. 使用for each遍历key
        for(Integer key: map.keySet()){
            Integer val = map.get(key);
            System.out.println(key + "=" + val);
        }
        // 2. 使用for each 遍历 map 的 entrySet() 集合
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            System.out.println(entry.getKey()+ " = "+ entry.getValue());
        }
        // 3.使用 stream api 遍历
        map.entrySet().stream().forEach((entry)->{
            System.out.println(entry.getKey()+" = "+entry.getValue());
        });

        // 这里用stream处理 hashmap，输出array可以用toArray() 方法，如果不注明输出类型，就直接输出Object[]
        // 而且这里输出类型只能是包装类
        Integer[] res = map.keySet().stream().filter(key -> key != 2).sorted().toArray(Integer[]::new);
        for (int re : res) {
            System.out.println(re);
        }

    }

    public static void main(String[] args) {
        travelHashMap();
    }
}
