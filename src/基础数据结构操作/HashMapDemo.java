package 基础数据结构操作;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void travelHashMap(){
        // 初始化一个hashmap
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,4);
        map.put(2,5);
        map.put(3,6);
        // 使用for each遍历key
        for(Integer key: map.keySet()){
            Integer val = map.get(key);
//            System.out.println(key + "=" + val);
        }
        // 使用for each 遍历 map 的 entrySet() 集合
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
//            System.out.println(entry.getKey()+ " = "+ entry.getValue());
        }
        // 使用 stream api 遍历
        map.entrySet().stream().forEach((entry)->{
            System.out.println(entry.getKey()+" = "+entry.getValue());
        });

    }

    public static void main(String[] args) {
        travelHashMap();
    }
}
