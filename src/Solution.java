import 基础数据结构操作.ListNode;
import 基础数据结构操作.TreeNode;

import java.util.*;

public class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 只要当前根节点是p和q中的任意一个，就返回（因为不能比这个更深了，再深p和q中的一个就没了）
        if (root == p || root == q) {
            return root;
        }
        // 两个节点都在左子树上
        if (containNode(root.left, p) && containNode(root.left, q)) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // 两个节点都在右子树上
        if (containNode(root.right, p) && containNode(root.right, q)) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // 如果p,q分属两侧，则公共祖先为根节点
        return root;
    }

    public boolean containNode(TreeNode root, TreeNode node) {
        if (root == null) {
            return false;
        }
        if (root.val == node.val) {
            return true;
        }

        return containNode(root.left, node) || containNode(root.right, node);
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int ast : asteroids) {
            if (ast > 0) {
                stack.push(ast);
                continue;
            }
            while (!stack.isEmpty() && stack.peek() < Math.abs(ast) && stack.peek() > 0){
                stack.pop();
            }
            if (stack.isEmpty() || stack.peek() < 0) {
                stack.push(ast);
            }
            else if (stack.peek() == Math.abs(ast)) {
                stack.pop();
            }
        }
        int[] ans = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
    public static void main(String[] args) {
         new Solution().combinationSum2(new int[]{2,5,2,1,2}, 5);
    }
}
