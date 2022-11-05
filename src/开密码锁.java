import java.util.*;
/*
 * ref. https://leetcode.cn/problems/zlDJc7/
 * bfs 解体
 * @todo 写更多
 */

public class 开密码锁 {
    public static void openLock(Set<String> deadends, String target) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                // 判断是否到达 ｜ 判断是否在 deadends 里
                if (deadends.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    System.out.println(step);
                    return;
                }
                /*  */
                for (int j =0; j < 4; j++) {
                    String up = forward(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }

                    String down = back(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            // 增加步数
            step++;
        }
        System.out.println(-1);  // 这里是没找到的情况
    }

    public static String forward(String str, int i) {
        /*
         * 把第i位向前拨轮
         */
        char[] array = str.toCharArray();
        if (array[i] == '9') {
            array[i] = '0';
        } else {
            array[i] += 1;
        }
        return String.valueOf(array);
    }

    public static String back(String str, int i) {
        /*
         * 把第i位向后拨轮
         */
        char[] array = str.toCharArray();
        if (array[i] == '0') {
            array[i] = '9';
        } else {
            array[i] -= 1;
        }
        return String.valueOf(array);
    }

    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";

        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        openLock(set, target);
    }
}
