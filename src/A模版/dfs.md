# 深度优先
我们所熟悉的 DFS（深度优先搜索）问题通常是在树或者图结构上进行的

## 1. 回溯问题
本质是一个brute force算法，是一个决策树的遍历过程

```python
result = []
def backtrack(start, 路径, 选择列表):
    if 满足结束条件:   ## step1.
        result.add(路径)
        return
    
    for i in 选择列表:  ## step2.
        做选择
        backtrack(i + 1, 路径, 选择列表)  ## step3.
        撤销选择
```

**例题1：[求子集](https://labuladong.gitee.io/algo/1/7/)**<br>
输入：nums = [1,2,3]<br>
输出 [ [],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3] ]

```java
public class 求子集 {
    List<List<Integer>> res = new ArrayList<>();
    // 记录回溯算法的递归路径

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> track = new ArrayList<>();
        backtrack(nums, track, 0);
        return res;
    }

    // 回溯算法核心函数，遍历子集问题的回溯树
    // start为第几层，每次回遡到上一层
    void backtrack(int[] nums, List<Integer> track, int start) {
        // 前序位置，每个节点的值都是一个子集
        res.add(new ArrayList<>(track));
        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            // 通过 i + 1 控制树枝的遍历，避免产生重复的子集
            // 如果传入start + 1 就是在原有路径上重复遍历
            backtrack(nums, track, i + 1);
            // 撤销选择
            track.remove(track.size() - 1);
        }
    }
}
```
**例题2**：[17. 电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/)<br>
示例 1：
输入：digits = "23" <br>
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

```java
public class Solution {
    private Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    // 路径
    private StringBuilder path = new StringBuilder();

    // 结果集
    private List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return res;
        }
        dfs(digits, 0);
        return res;
    }

    /**
     * "2","3"输出结果是 "ad","ae","af" --- "bd","be","bf" --- "cd","ce","cf"
     *
     * @param digits 键盘输入的数字
     * @param index 用index来遍历每个数字对应的字符串：abc、 def、 ghi
     */
    public void dfs(String digits, int index) {
        // 长度符合要求，加入结果集
        if (path.length() == digits.length()) {
            res.add(path.toString());
            return;
        }
        // 当前字母对应的字符串，需要逐个遍历加入path中 : abc
        String strs = phoneMap.get(digits.charAt(index));
        for (char ch : strs.toCharArray()) {
            path.append(ch);
            dfs(digits, index + 1); // 切换到下一个字母
            path.deleteCharAt(path.length() - 1);
        }
    }
}
```

**例题3**：[79. 单词搜索](https://leetcode.cn/problems/word-search/description/)<br>
**题目**：给定一个m * n二维字符网格board和一个字符串单词word，求word是否在网格中（同一个位置的字母，不可用被重复用）<br>
**示例**：

![img.png](src/wordsearch.png)

**思路**：
1. 回溯
2. 需要对每个格子作为起点进行计算
3. 递归结束条件是，word全遍历完
```java
public class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        // 不允许往回算，所以需要一个visited 数组
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, visited, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, boolean[][] visited, char[] word, int x, int y, int index) {
        /*
         * index: 为在word上的游标
         */
        if (x >= board.length || x < 0 || y >= board[0].length || y < 0) {
            return false;
        }

        if (visited[x][y]) {
            return false;
        }

        if (board[x][y] != word[index]) {
            return false;
        }

        if (index == word.length - 1) {
            return true;
        }

        int[][] directions = {{0,1}, {1,0},{-1,0},{0,-1}};
        visited[x][y] = true;
        for (int[] each : directions) {
            if (dfs(board, visited, word, each[0] + x, each[1] + y, index + 1)) {
                // 如果递归中有正确解，即可直接返回
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }
}
```

## 2. 岛屿相关（blood fill）
比如：
* [岛屿数量](https://leetcode.cn/problems/number-of-islands/) （Easy）
* [岛屿的周长](https://leetcode.cn/problems/island-perimeter/) （Easy）
* [岛屿的最大面积](https://leetcode.cn/problems/max-area-of-island/) （Medium）
* 最大人工岛 （Hard）

如图：每个格子中的数字可能是 0 或者 1。我们把数字为 0 的格子看成海洋格子，数字为 1 的格子看成陆地格子，这样相邻的陆地格子就连接成一个岛屿。

![island.png](src/island.png)


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
## 3. 树

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


**例题3** [236. 二叉树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/)

**思路**：
1.
```java
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
}    
```

**例题4**：[剑指 Offer 26. 树的子结构](https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/)<br>
**题目**：输入两棵二叉树A和B，判断树B是不是A的子结构，返回一个boolean结果<br>
**思路**：
1. 先序遍历树A中的每个节点An是否包含B树（isSubStructure），对每个A节点都去和B树做比较
2. 判断以An节点开始是否和B树重合（recur）：分别匹配两个树的左右节点
```java
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        if (recur(A, B)) {
            return true;
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean recur(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val != B.val) {
            return false;
        }

        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}
```

