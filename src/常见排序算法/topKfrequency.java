package 常见排序算法;

import java.util.*;

/**
 * 最小堆就是堆顶元素为最小
 */
public class topKfrequency {
    public List<Integer> topKElement(int[] nums, int k){
        // 使用字典，统计每个元素出现的次数，(元素，次数)
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num)+1);
            } else {
                map.put(num, 1);
            }
        }
        // 遍历map，用最小堆保存前k个频率元素, 堆中存放堆是元素，而不是val
        PriorityQueue<Integer> pg = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2) ;
            }
        });
        for (Integer key: map.keySet()) {
            if(pg.size()<k){
                pg.add(key);
            } else if (map.get(key) > map.get(pg.peek())) {
                // 如果key的频率大于堆顶元素，就把堆顶元素给删类
                pg.remove();
                pg.add(key);
            }
        }
        // 取出最小堆中的元素
        List<Integer> res = new ArrayList<>();
        while(!pg.isEmpty()){
            res.add(pg.remove());
        }
        return res;
    }
}
