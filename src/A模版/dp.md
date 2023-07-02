### 背包问题
有一个可装重量为`W`的背包和`N`个物体，其中第`i`个物品的重量为`wt[i]`，价值为`val[i]`，现在用这个背包装物品，最多能装的价值是多少？

```java
int knapsack(int W, int N, int[] wt, int[] val) {
    assert N == wt.length;
    // base case 已初始化
    int[][] dp = new int[N + 1][W + 1];
    for (int i = 1; i <= N; i++) {
        for (int w = 1; w <= W; w++) {
            if (w - wt[i - 1] < 0) {
                // 这种情况下只能选择不装入背包
                dp[i][w] = dp[i - 1][w];
            } else {
                // 装入或者不装入背包，择优
                dp[i][w] = Math.max(
                    dp[i - 1][w - wt[i-1]] + val[i-1], 
                    dp[i - 1][w]
                );
            }
        }
    }
    
    return dp[N][W];
}
```
需要注意的点：
1. **物体个数**是第一个数组元素，
2. 外层循环也是物体个数
3. `dp[i][w]` 表示第i个物体，使用w容量的背包，可以装到的最大价值是

### 贪心算法
[435. 无重叠区间](https://leetcode.cn/problems/non-overlapping-intervals)
[452. 用最少数量的箭引爆气球](https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons)
[55. 跳跃游戏](https://leetcode.cn/problems/jump-game/)
### 动态规划
例题1： [198. 打家劫舍](https://leetcode.cn/problems/house-robber/)<br>
题目说明：每间房内都藏有一定的现金，不能偷连续两家，返回偷到到最多金额
![img.png](src/robber.png)
```java
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        // dp[0] 为偷完0个屋子得到到最大收益
        // dp[1]为偷了第一个屋子的最大收益
        dp[0] = 0; dp[1] = nums[0];
        for (int i = 2; i < dp.length; i++) {
            // 前一个屋子偷了，这个就不能偷了；要么前一个屋子没偷，偷这个屋子
            dp[i] = Math.max(dp[i - 2] + nums[i-1], dp[i-1]);
        }
        return dp[n];
    }
```