package LRU;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    /**
     * 使用 linkedHashmap实现（看这个）
     * 题目要求，实现LRUCache的get和put 还有初始化方法
     * 队尾是最新的
     * 队头是最老的元素，因为先加入到元素放在了队头，
     */
    int cap; // 表示这个cache 支持多大
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache(int capacity){
        this.cap = capacity;
    }

    // get 方法
    public int get(int key){
        if(!cache.containsKey(key)){
            return -1;
        }
        // 同时将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val){
        if(cache.containsKey(key)){
            // 修改 key 的值
            cache.put(key, val);
            // 同时将 key 变为最近使用
            makeRecently(key);
            return;
        }
        // 容量超过，顶替掉head，第二个元素变成head
        if (cache.size() >= this.cap){
            // 获取cache中的key，为一个iterator，获取iterator的第一个值
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 将新的 key 添加到链表尾部
        cache.put(key, val);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除 key， 重写插入到队尾
        cache.remove(key);
        cache.put(key, val);
    }

    public static void displayCache(LinkedHashMap<Integer,Integer> cache){
        // * 打印cache方便观察
        cache.entrySet().forEach(entry -> {
            System.out.println(entry.getKey()+" "+ entry.getValue());
        });
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        // 你可以把 cache 理解成一个队列
        // 假设左边是队头，右边是队尾
        // 最近使用的排在队尾，久未使用的排在队头（添加元素是从头到尾顺序添加）
        // 圆括号表示键值对 (key, val)
        cache.put(1,1);
        cache.put(2,2);
        displayCache(cache.cache);
        System.out.println("=========");
        cache.put(3,3);
        displayCache(cache.cache);
        int value = cache.get(3);
    }
}
