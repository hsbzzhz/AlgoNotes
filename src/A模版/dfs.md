# 深度优先
我们所熟悉的 DFS（深度优先搜索）问题通常是在树或者图结构上进行的

### 回溯问题

### 岛屿相关
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
[ref.](https://leetcode.cn/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/)
### 树

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