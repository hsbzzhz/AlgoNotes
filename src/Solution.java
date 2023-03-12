import 基础数据结构操作.ListNode;
import 基础数据结构操作.TreeNode;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Collectors;
public class Solution {

    int[] node = {1,3,5,6,7,8};
    int[][] graph = {{5,7,8},{},{6},{},{},{}};
    public List<Integer> getNodes(int depth, int[] roots) {
        Queue<Integer> queue = new LinkedList<>();
        for (int each : roots) {
            queue.add(each);
        }

        List<Integer> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                // System.out.println(cur);
                if (depth == 1) {
                    res.add(cur);
                }
                for (int each: graph[findIndex(cur)]) {
                    if (cur == 3) {
                        System.out.println("reach out");
                    }
                    queue.add(each);
                }
            }
            depth--;
        }
        System.out.println(res);
        return res;
    }

    private int findIndex(int target) {
        for (int i = 0; i < node.length; i++) {
            if (node[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
         new Solution().getNodes(3, new int[] {1,3});
    }
}
