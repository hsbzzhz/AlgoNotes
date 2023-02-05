import java.util.*;

public class 课程表 {
    /**
     * 题目：你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，例如 [0,1] ： 想学习到课程0，预修课程是1
     * 请判断是否可以修完所有到课程，返回 true 或 false
     *
     * 寻找图中到环。此题有dfs 和 bfs解法
     */

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // BFS
        // 建图，有向边代表「被依赖」关系
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 构建入度数组，？
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];  // from是预修课，to是想修的课
            // 节点 to 的入度加一
            indegree[to]++;
        }

        // 根据入度初始化队列中的节点
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                // 节点 i 没有入度，即没有依赖的节点
                // 可以作为拓扑排序的起点，加入队列
                q.offer(i);
            }
        }

        // 记录遍历的节点个数
        int count = 0;
        // 开始执行 BFS 循环
        while (!q.isEmpty()) {
            // 弹出节点 cur，并将它指向的节点的入度减一
            int cur = q.poll();
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    // 如果入度变为 0，说明 next 依赖的节点都已被遍历
                    q.offer(next);
                }
            }
        }

        // 如果所有节点都被遍历过，说明不成环
        return count == numCourses;
    }

    // 建图函数
    List<Integer>[] buildGraph(int numCourses, int[][] edges) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int from = edge[1], to = edge[0];
            // 添加一条从 from 指向 to 的有向边
            // 边的方向是「被依赖」关系，即修完课程 from 才能修课程 to
            graph[from].add(to);
        }
        return graph;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{1,0}};
        boolean res = new 课程表().canFinish(2, prerequisites);
        System.out.println(res);
    }
}
