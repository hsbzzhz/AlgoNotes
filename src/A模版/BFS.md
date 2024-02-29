## bfs通用模板

计算从起点 start 到终点 target 到最近距离
```java
class Template {
    public void bfstemplate(Node start, Node target) {
        Queue<Node> queue = new LinkedList<>();  // 核心数据结构，bfs 中用queue
        Set<Node> visited = new HashSet<>();    // 访问过到节点，避免重复访问

        queue.offer(start); // 1. 将起点加入队列中
        visited.add(start); // 访问过的都要加入
        int step = 0;   // 记录扩散的步数

        while (!queue.isEmpty()) {   // queue is not empty
            int sz = queue.size(); // !这里一定要这么写
            /* 2. 按顺序访问队列中的节点 */
            for(int i =0; i< sz; i++) {
                Node cur = queue.poll(); // 访问的时候就出队，队头
                /* 3. 判断条件是否满足：是否到了终点*/
                if(cur == target) {
                    return step;
                }
                /* 4. 将当前节点的临节点也加入到队列中 */
                for (Node x: cur.adj()) {
                    if (!visited.contains(x)){  // 防止重复访问
                        queue.offer(x);
                        visited.add(x);
                    }
                }
            }
            step++;  // 5. 在此更新步数
        }
        return step;
    }
}
```

- 二叉树的层序遍历 详见：基础数据结构操作/TreeNode.java<br>
**例题**：[199. 二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/)<br>
![](src/right-view-tree.jpeg)<br>
**解题思路**： 就是层序遍历，但只取每层的最后一个元素，层序遍历需要增加一个循环来遍历每层的元素
```java
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> res = new LinkedList<>();
        // 树为空的情况
        if (root == null) {
            return res;
        }
        while (!queue.isEmpty()) {
            // 记录每层的元素格式，如果需要记录每层结构
            // List<Integer> level = new ArrayList<>();
            int levelSize = queue.size();
            // 逐个遍历，当遍历到最后一个元素时，则为最右元素
            for (int i = 0; i < levelSize; i++) {
                TreeNode cur = queue.poll();
                if (i == levelSize - 1) {
                    res.add(cur.val);
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            // 将每一层的结果添加到最终的结果中
            // result.add(level);
        }
        return res;
    }
```
