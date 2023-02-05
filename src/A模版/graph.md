
如果图包含环，遍历框架就要一个 `visited` 数组进行辅助
```java
// 记录被遍历过的节点
boolean[] visited;
// 记录从起点到当前节点的路径，用来判断环！
boolean[] onPath;

/* 图遍历框架 */
void traverse(Graph graph, int s) {
    if (visited[s]) return;
    // 经过节点 s，标记为已遍历
    visited[s] = true;
    // 做选择：标记节点 s 在路径上
    onPath[s] = true;
    for (int neighbor : graph.neighbors(s)) {
    traverse(graph, neighbor);
    }
    // 撤销选择：节点 s 离开路径
    onPath[s] = false;
}
```

[797. 所有可能的路径](https://leetcode.cn/problems/all-paths-from-source-to-target/)

题目输入一幅有向无环图，这个图包含 n 个节点，标号为 0, 1, 2,..., n - 1，请你计算所有从节点 0 到节点 n - 1 的路径。
输入的这个 graph 其实就是「邻接表」表示的一幅图，graph[i] 存储这节点 i 的所有邻居节点。

比如输入 graph = [[1,2],[3],[3],[]]，就代表下面这幅图：

![797.png](797.png)

comments: 这题明确是没有环的，就可以不用到visited数组