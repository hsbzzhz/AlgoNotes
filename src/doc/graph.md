## 并查集

```java
class UF {
    // 连通分量个数
    private int count;
    // 存储每个节点的父节点
    private int[] parent;

    // n 为图中节点的个数
    public UF(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    // 将节点 p 和节点 q 连通
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        
        if (rootP == rootQ)
            return;
        
        parent[rootQ] = rootP;
        // 两个连通分量合并成一个连通分量
        count--;
    }

    // 判断节点 p 和节点 q 是否连通
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
   
    // 返回某个节点 x 的根节点
    public int find(int x) {
        if (parent[x] != x) {
            // 递归寻找根节点，并赋值
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 返回图中的连通分量个数
    public int count() {
        return count;
    }
}
```

例题 [547. 省份数量](https://leetcode.cn/problems/number-of-provinces/)


```java

public class Solution {
    // 连通分量个数
    private int count;
    // 存储每个节点的父节点
    private int[] parent;

    public int findCircleNum(int[][] isConnected) {
        count = isConnected.length;
        // 存储每个节点的父节点
        int n = isConnected.length;
        parent = new int[count];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            // 因为是无向图，只用算半边
            for (int j = i+1; j < n ; j++) {
                if (isConnected[i][j] == 1) {
                    union(i ,j);
                }
            }
        }
        return count;
    }

    // 返回某个节点 x 的根节点
    public int find(int x) {
        if (parent[x] != x) {
            // 递归寻找根节点，并赋值
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }


    // 将节点 p 和节点 q 连通
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ)
            return;

        parent[rootQ] = rootP;
        // 两个连通分量合并成一个连通分量
        count--;
    }
}    
```
