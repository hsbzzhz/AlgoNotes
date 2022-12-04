# 深度优先
我们所熟悉的 DFS（深度优先搜索）问题通常是在树或者图结构上进行的

### 1. 回溯问题
本质是一个brute force算法，是一个决策树的遍历过程

```python
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:   ## step1.
        result.add(路径)
        return
    
    for 选择 in 选择列表:  ## step2.
        做选择
        backtrack(路径, 选择列表)  ## step3.
        撤销选择
```

**例如题目：[求子集](https://labuladong.gitee.io/algo/1/7/)**<br>
输入：nums = [1,2,3]<br>
输出 [ [],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3] ]

```java
public class 求子集 {
    List<List<Integer>> res = new ArrayList<>();
    // 记录回溯算法的递归路径
    List<Integer> track = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    // 回溯算法核心函数，遍历子集问题的回溯树
    void backtrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是一个子集
        res.add(new ArrayList<>(track));
        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            // 通过 start 参数控制树枝的遍历，避免产生重复的子集
            backtrack(nums, i + 1);
            // 撤销选择
            track.remove(track.size() - 1);
        }
    }
}
```

### 2. 岛屿相关
比如：
* [岛屿数量](https://leetcode.cn/problems/number-of-islands/) （Easy）
* [岛屿的周长](https://leetcode.cn/problems/island-perimeter/) （Easy）
* [岛屿的最大面积](https://leetcode.cn/problems/max-area-of-island/) （Medium）
* 最大人工岛 （Hard）

如图：每个格子中的数字可能是 0 或者 1。我们把数字为 0 的格子看成海洋格子，数字为 1 的格子看成陆地格子，这样相邻的陆地格子就连接成一个岛屿。

![img.png](img.png)


```java
class IslandTemplate {
    void dfs(int[][] grid, int row, int col) {
        // 判断 base case
        // 如果坐标 (r, c) 超出了网格范围，直接返回
        if (!inArea(grid, row, col)) {
            return;
        }

        // 如果格子不是岛屿，直接返回
        if (grid[row][col] != 1) {
            return;
        }

        // 标记岛屿，表明已遍历过
        grid[row][col] = 2;

        // 访问上、下、左、右四个相邻结点
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length // r在 [0. length) 范围内
                && 0 <= c && c < grid[0].length; // c在 [0. length) 范围内
    }
}
```
[ref1.](https://leetcode.cn/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/)
[ref2.](https://labuladong.gitee.io/algo/4/31/107/)
### 3. 树

前序遍历：
```java
class BFS {
    void traverse(TreeNode root) {
        // 判断 base case
        if (root == null) {
            return;
        }
        // 访问两个相邻结点：左子结点、右子结点
        traverse(root.left);
        traverse(root.right);
    }
}
```