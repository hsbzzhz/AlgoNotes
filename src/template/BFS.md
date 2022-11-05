## bfs通用模板

e.g. 计算从起点 start 到终点 target 到最近距离
```java
class Template {
    public void bfstemplate(Node start, Node target) {
        Queue<Node> queue = new LinkedList<>();  // 核心数据结构，bfs 中用queue
        Set<Node> visited = new HashSet<>();    // 访问过到节点，避免重复访问

        queue.offer(start); // 1. 将起点加入队列中
        visited.add(start); // 访问过的都要加入
        int step = 0;   // 记录扩散的步数

        while (!queue.isEmpty()) {   // queue is not empty
            int sz = q.size(); // !这里一定要这么写
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