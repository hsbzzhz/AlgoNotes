package 基础数据结构操作;

import java.util.LinkedList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x){
        val = x;
    }

    public TreeNode(){}

    public static void preOrder(TreeNode tree){
        // 前序遍历，中 - 左 - 右
        if (tree==null)
            return;
        System.out.println(tree.val);
        preOrder(tree.left);
        preOrder(tree.right);
    }

    public static void inOrder(TreeNode tree) {
        // 中序遍历，左 中 右
        if (tree == null){
            return;
        }
        inOrder(tree.left);
        System.out.println(tree.val);
        inOrder(tree.right);
    }

    public static void postOrder(TreeNode tree){
        // 后序遍历，左 右 中
        if (tree == null){
            return;
        }
        postOrder(tree.left);
        postOrder(tree.right);
        System.out.println(tree.val);
    }


        public static void levelOrder(TreeNode tree){
        /*
         * 使用 bfs 层序遍历一棵二叉树
         * 从上到下层序遍历，每层内从左至右
         */
        if (tree == null){
            return;
        }
        // 用链表来充当一个队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(tree); // 存入 list中
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll(); // 取出队头元素
            System.out.print(cur.val+" ");
            if (cur.left!=null){
                queue.add(cur.left);
            }
            if (cur.right != null){
                queue.add(cur.right);
            }
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.right=node3;
        node2.left = node4;

        /*
         * 三种递归遍历，都是dfs
         */
        TreeNode.levelOrder(root);
    }

}
