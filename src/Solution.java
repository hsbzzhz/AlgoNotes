import 基础数据结构操作.ListNode;
import 基础数据结构操作.TreeNode;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Collectors;

public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> ret = new ArrayList<>();
            for(TreeNode node : queue) {
                TreeNode cur = queue.poll();
                ret.add(cur.val);
                if (cur.left!=null){
                    //将左右子树加入优先队列
                    queue.add(cur.left);
                }
                if (cur.right!=null){
                    //将左右子树加入优先队列
                    queue.add(cur.right);
                }
            }
            res.add(ret);
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = {3,1,3,4,2};
    }
}
