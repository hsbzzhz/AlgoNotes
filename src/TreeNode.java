import java.time.temporal.Temporal;
import java.util.LinkedList;
import java.util.Stack;

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
        // bfs
        if (tree == null){
            return;
        }
        // 用链表来充当一个队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(tree); // 存入 list中
        while (!queue.isEmpty()){
            TreeNode node = queue.poll(); // 取出队头元素
            System.out.println(tree.val);
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }
        }
    }


    public static void treeDFS(TreeNode tree){
        // DFS
        Stack<TreeNode> stack = new Stack<>();
        stack.add(tree);
        while(!stack.empty()){
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.left !=null){
                stack.push(node.left);
            }
            if (node.right != null){
                stack.push(node.right);
            }
        }
    }

}
