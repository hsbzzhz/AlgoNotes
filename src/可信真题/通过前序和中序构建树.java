package 可信真题;

import 基础数据结构操作.TreeNode;

import java.util.ArrayList;

public class 通过前序和中序构建树 {
    /*
     * ref. https://blog.csdn.net/yuhaomogui/article/details/125137379
     *
     */
    public static TreeNode getTree(ArrayList<Integer> pre, ArrayList<Integer> in){
        if(pre.isEmpty()) {
            // 如果子树没有节点，参照 root = pre[0] 就需要跳出递归
            return null;
        }
        // root
        int val = pre.get(0);
        TreeNode root = new TreeNode(val);

        // 寻找根节点在终须遍历中的位置
        int index = in.indexOf(val);
        // 左子树 (l_pre 和 l_in 中的内容是一样的)
        ArrayList<Integer> l_pre = new ArrayList<>(pre.subList(1, index +1));
        ArrayList<Integer> l_in = new ArrayList<>(in.subList(0,index));
        root.left = getTree(l_pre,l_in);
        // 右子树
        ArrayList<Integer> r_pre = new ArrayList<>(pre.subList(index +1, pre.size()));
        ArrayList<Integer> r_in = new ArrayList<>(in.subList(index+1, pre.size()));
        root.right = getTree(r_pre,r_in);
        return root;
    }


    public static void main(String[] args) {
        // List<Integer> arr = new ArrayList<>();
        // arr.add(4);
        ArrayList<Integer> pre = new ArrayList<Integer>(){
            {
                add(1);add(2);add(4);add(7);add(3);add(5);add(6);add(8);
            }
        };

        ArrayList<Integer> in = new ArrayList<Integer>(){
            {
                add(4);add(7);add(2);add(1);add(5);add(3);add(8);add(6);
            }
        };

        TreeNode root = 通过前序和中序构建树.getTree(pre,in);
        TreeNode.postOrder(root);
    }
}

