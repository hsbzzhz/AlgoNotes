import 基础数据结构操作.ListNode;
import 基础数据结构操作.TreeNode;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Collectors;

public class Solution {

    public TreeNode createBinaryTree(int[][] descriptions) {
//        Map<Integer, Boolean> findRoot = new HashMap<>();
        Set<Integer> hasParent = new HashSet<>();
        Map<Integer, TreeNode> map = new HashMap<>();

        for (int[] each : descriptions) {
            hasParent.add(each[1]); //必定有父节点的，不是root
            if (!map.containsKey(each[0])) map.put(each[0], new TreeNode(each[0]));
            if (!map.containsKey(each[1])) map.put(each[1], new TreeNode(each[1]));

            //左右关系
            if (each[2] == 1) {
                map.get(0).left = map.get(1);
            } else {
                map.get(0).right = map.get(1);
            }
        }

        for (int key: map.keySet()) {
            if (!hasParent.contains(key)) return map.get(key);
        }
        return null;
    }
    public static void main(String[] args) {
        int[] nums = {3,1,3,4,2};
    }
}
