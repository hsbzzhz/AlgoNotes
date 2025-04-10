package 基础数据结构操作;

import java.util.*;
import java.util.stream.Collectors;

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
        // 将map转换为 list
        List<Map.Entry<Integer, Integer>> arrayList = new ArrayList<>(map.entrySet());

        // 3.使用 stream api 遍历
        map.entrySet().stream().forEach((entry)->{
            System.out.println(entry.getKey()+" = "+entry.getValue());
        });
        // 通常更简化为foreach
        map.forEach((key, val) -> System.out.println(key + "=" + val));

        // 这里用stream处理 hashmap，输出array可以用toArray() 方法，如果不注明输出类型，就直接输出Object[]
        // 而且这里输出类型只能是包装类
        Integer[] res = map.keySet().stream().filter(key -> key != 2).sorted().toArray(Integer[]::new);
        for (int re : res) {
            System.out.println(re);
        }

    }

    public static void hashMapSort(){
        /**
         * 借助list进行排序
         */
        // 初始化一个hashmap
        Map<Integer, int[]> map = new HashMap<>();
        map.put(1,new int[]{1,2});
        map.put(2,new int[]{3,7});
        map.put(3,new int[]{5,1});
        // 1. 先把 map 放进 list 里
        List<Map.Entry<Integer,int[]>> list = new ArrayList<>(map.entrySet());
        // 2. 对list 进行排序
        list.sort(new Comparator<Map.Entry<Integer, int[]>>() {
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

    public static void linkedMapSort(){
        /*
        借助linkedmap 和stream进行排序
         */
        Map<String, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put("one", 3);
        unsortedMap.put("two", 1);
        unsortedMap.put("three", 2);
        LinkedHashMap<String, Integer> sortedMap = unsortedMap.entrySet()
                .stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public static void treeMapSort(){
        /*
        借助treemap进行排序
         */
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 3);
        map.put("two", 1);
        map.put("three", 2);
        TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
    }
    
    public static void createIfNot() {
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList("t1", "t2"));
        map.put("london", set);

        // 更新和计算
        // 如果指定key尚未关联value（或映射到null），将其赋值并返回null，否则直接返回当前值。
        map.putIfAbsent("Thai", new HashSet<>());
        // 如果指定key尚未关联value，将使用给定映射函数计算值
        map.computeIfAbsent("london", key -> new HashSet<>()).add("t3");

        System.out.println(map);
    }

    public static void main(String[] args) {
    }
}
