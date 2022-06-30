import java.util.Stack;

public class 求二叉树根节点到指定节点的路径 {
    /**
     * 初始化一个二叉树，
     * 然后给定一个节点，输出从根节点到次节点的路径信息，
     * @param node
     * @param target
     * @param path
     * @return
     * ref. https://blog.csdn.net/qq_41512783/article/details/109516464
     */
    public static boolean getPathToTarget(TreeNode node, TreeNode target, Stack<TreeNode> path){
        if(node==null) return false;
        path.push(node);
        // 中序遍历
        if(node==target) return true;
        if(getPathToTarget(node.left, target, path)) return true;
        if(getPathToTarget(node.right,target,path)) return true;

        // 这个节点如果不存在 path 中，就把这个节点也出栈
        path.pop();
        // 如果所有都没有找到，就直接返回 false
        return false;
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

        Stack<TreeNode> path = new Stack<>();
        boolean hasPath = getPathToTarget(root,node4,path);
        if(hasPath){
            for(TreeNode node:path){
                System.out.print(node.val+" ");
            }
        }
        // 清除 path，如果需要下一轮对比
        path.clear();
    }
}
