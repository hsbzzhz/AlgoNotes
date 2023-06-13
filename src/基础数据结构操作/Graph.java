package 基础数据结构操作;

/**
 * @author z30039665
 * @since 2023-01-29
 */
public class Graph {

    // 记录被遍历过的节点，判断是否有环
    // 如果题目告诉你图中不含环，可以把 visited 数组都省掉
    boolean[] visited;
    // 记录从起点到当前节点的路径
    boolean[] onPath; // 用于处理路径相关的问题

    /* 图遍历框架，遍历一个邻接表 */
    void traverse(Graph graph, int s) {
        // s为其实访问点
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

    int[] neighbors(int s) {
        // 辅助代码
        return new int[10];
    }
}
