package 基础数据结构操作;

import java.util.*;

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
        // 2*.将map转换为 list
        List<Map.Entry<Integer, Integer>> arrayList = new ArrayList<>(map.entrySet());

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

    public static void hashMapSort(){
        /**
         * 给hashmap排序，先要给它转换为list
         */
        // 初始化一个hashmap
        Map<Integer, int[]> map = new HashMap<>();
        map.put(1,new int[]{1,2});
        map.put(2,new int[]{3,7});
        map.put(3,new int[]{5,1});
        // 1. 先把 map 放进 list 里
        List<Map.Entry<Integer,int[]>> list = new ArrayList<>(map.entrySet());
        // 2. 对list 进行排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, int[]>>() {
            @Override
            public int compare(Map.Entry<Integer, int[]> o1, Map.Entry<Integer, int[]> o2) {
                return o2.getValue()[1] - o1.getValue()[1]; // 按照 value 中数组的后一位 降序
            }
        });

        // 3. 输出  2, 1, 3
        for(Map.Entry<Integer, int[]> entry: list) {
            System.out.println(entry.getKey());
        }

    }

    public static void main(String[] args) {
//        travelHashMap();
        hashMapSort();
    }
}
