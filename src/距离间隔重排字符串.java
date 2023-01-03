import java.util.*;

public class 距离间隔重排字符串 {
    /**
     * [会员]ref. https://leetcode.cn/problems/rearrange-string-k-distance-apart/
     * 题解： https://www.dianjilingqu.com/154432.html
     *
     * 题目，给s 和 k，要求将这个字符串s重排序，使得使得重排后的字符串中相同字母的位置间隔距离至少为 k
     * 例子： s = "aaadbbcc", k = 2
     *    输出: "abacabcd"
     *    解释: 相同的字母在新的字符串中间隔至少 2 个单位距离。
     * @param s 非空字符串s
     * @param k 整数距离k
     * @return
     */
    public static String rearrangeString(String s, int k) {
        if (k <= 1) {
            return s;
        }
        HashMap<Character, Integer> map = new HashMap<>(); // 每个字符串，和出现的频率
        // 大顶堆
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Character c : s.toCharArray()) {
            // 遍历字符，统计字符的出现次数
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        maxHeap.addAll(map.entrySet()); // 装入大顶堆，按照字符重复次数作为比较

        StringBuilder sb = new StringBuilder(s.length());
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();// queue里面也是放hashmap吗，这是干嘛的

        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();    // 从大顶堆取出重复次数最多的字符，直接把这个map弄掉了
            sb.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1); // 用掉一个字符，次数减一
            queue.offer(currentEntry);  // 放入到queue中，因为k距离后还要用。
            if (queue.size() == k) {
                // queue的大小到达了k，也就是说我们已经越过了k个单位，在结果中应该要出现相同的字母了
                Map.Entry<Character, Integer> entry = queue.poll();
                if (entry.getValue() > 0) {
                    // 该字符的重复次数大于 0，则添加入大顶堆中，要是0那还加它干嘛
                    maxHeap.add(entry);
                }
            }
        }
        // 退出 while 循环就是大顶堆已经没有元素了，如果此时 sb 的长度没有还原，说明还有元素挂在 queue 中
        // 即 queue.size() == k 这个条件没有完全满足，即存在一些字符无法间隔 k 个距离
        System.out.println(sb);
        return sb.length() == s.length() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        rearrangeString("aaadbbcc", 2);
    }
}
