package 基础数据结构操作;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Dijkstra<V> {

    class Edge<V> {
        V from; // 起始顶点
        V to; // 结束顶点
        int weigth; // 边的权值

        public Edge(V from, V to, int weigth) {
            super();
            this.from = from;
            this.to = to;
            this.weigth = weigth;
        }

        @Override
        public String toString() {
            return "Edge [from=" + from + ", to=" + to + ", weigth=" + weigth + "]";
        }

    }

    public void dijkstra(V v0, V vi) {
        // 从顶点vO出发,查找到vi的最短路径

        // listU 记录还未处理的节点
        ArrayList<Integer> listU = new ArrayList<>();
        // dist[] 记录各个节点到起始节点的最短权值
        int[] dist = new int[this.vertices];
        // 记录各个节点的上一级节点(用来联系该节点到起始节点的路径)
        int[] path = new int[this.vertices];

        int start = this.vertexList.indexOf(v0); // 源点序号
        int end = this.vertexList.indexOf(vi); // 结束顶点序号

        // 初始化U集合
        for (int i = 0; i < this.vertices; i++) {
            if (i == start) { // S={start}
                continue;
            }
            listU.add(i); // u={vi}/{start}
        }
        // 初始化dist[],path[]
        for (int i = 0; i < this.vertices; i++) {
            // dist[]的当前节点权值就等于start->i节点的权值;初始化所有节点到源点的最短距离
            dist[i] = this.edgeMatrix[start][i];
            if (this.edgeMatrix[start][i] == Integer.MAX_VALUE) {
                path[i] = -1; // 节点i不可达
            } else {
                path[i] = start; // 若start能直达某点,则表示节点i可以直接访问到start;
            }
        }

        // 记录最小值的节点序号
        int minIndex;
        // int minIndexByI=0;
        do {
            System.out.println("集合U的状态: " + listU);
            // dist数组下标
            minIndex = listU.get(0);
            for (int i = 1; i < listU.size(); i++) {
                if (dist[listU.get(i)] < dist[minIndex]) {
                    minIndex = listU.get(i);
                    // minIndexByI = i;
                }
            }
            listU.remove((Integer) minIndex);
            // listU.remove(minIndexByI);

            // 更新dist和path数组,主要考察minIndex节点纳入S,即新加入节点最短路径变化.
            for (int i = 0; i < this.vertices; i++) {
                if (this.edgeMatrix[minIndex][i] != 0 && this.edgeMatrix[minIndex][i] < Integer.MAX_VALUE) {
                    // 找到minIndex的所有邻接点
                    if (this.edgeMatrix[minIndex][i] + dist[minIndex] < dist[i]) {
                        // 新加入节点更短
                        dist[i] = this.edgeMatrix[minIndex][i] + dist[minIndex];
                        path[i] = minIndex;
                    }
                }
            }
        } while (minIndex != end && !listU.isEmpty());

        System.out.println("节点" + v0 + "=>" + vi + "最短路径是: " + dist[end]);
        String str = "" + vi;
        int i = end;
        do {
            i = path[i];
            str = this.vertexList.get(i) + "=>" + str;
        } while (i != start);
        System.out.println(str);
    }
}

https://blog.csdn.net/m0_50913327/article/details/125099211?spm=1001.2101.3001.6650.6&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-6-125099211-blog-127861343.235%5Ev43%5Epc_blog_bottom_relevance_base3&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-6-125099211-blog-127861343.235%5Ev43%5Epc_blog_bottom_relevance_base3&utm_relevant_index=11