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
[435. 无重叠区间](https://leetcode.cn/problems/non-overlapping-intervals)<br>
**题目**：输入一个区间的集合，请你计算，要想使其中的区间都互不重叠，至少需要移除几个区间？<br>
比如说输入是 intervals = [[1,2],[2,3],[3,4],[1,3]]，算法返回 1，因为只要移除 [1,3] 后，剩下的区间就没有重叠了。<br>
**解析**：如果先计算出区间中有多少是不重合的区间，那减去剩下的即为结果

1. 按照结束时间排序
2. 边界为第一个区间的右边界，如果下一个区间的边界比它大，那么这两个区间一定不会重合
3. 更新右边界，如果遇到重合就更新答案
```java
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int res = 0;
        int end = intervals[0][1];
        for (int[] interval: intervals) {
            if (interval[0] >= end) {
                // 更新 end
                end = interval[1];
            } else {
                res++;
            }
        }
        
        return res -1;
    }
```

[55. 跳跃游戏](https://leetcode.cn/problems/jump-game/)<br>
**题目**：给定一个非负数组，初始位置是数组的第一个下标。数组中每个元素代表在该位置能跳跃的最大长度。判断是否能达到最后一个下标<br>
**解析**：就是往前跳
```java
    public boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        //前n-1个元素能够跳到的最远距离
        int k = 0;
        for (int i = 0; i <= k; i++) {
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            //更新最远距离
            k = Math.max(k, temp);
            //如果最远距离已经大于或等于最后一个元素的下标,则说明能跳过去,退出. 减少循环
            if (k >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变,且没有到末尾元素
        return false;
    }
```

[45. 跳跃游戏 II](https://leetcode.cn/problems/jump-game-ii/)<br>
**题目**：背景同上，求最少需要多少步跳到最后一个位置<br>
**解析**：目标是最后一个位置，最后一步是确定的，所以只需要看前面步数<br>

```java
    public int jump(int[] nums) {
        // 记录当前能跳跃到的位置的边界下标
        int border = 0;
        // 记录在边界范围内，能跳跃的最远位置的下标
        int maxPosition = 0;
        // 记录所用步数
        int steps = 0;
        for(int i=0;i<nums.length-1;i++){ // 不需要检查最后一个位置是因为，最后一个位置我们不用跳了已经
            // 继续往下遍历，统计边界范围内，哪一格能跳得更远，每走一步就更新一次能跳跃的最远位置下标
            // 其实就是在统计下一步的最优情况
            maxPosition = Math.max(maxPosition,nums[i]+i);
            // 如果到达了边界，那么一定要跳了，下一跳的边界下标就是之前统计的最优情况maxPosition
            // 并且步数加1
            if(i==border){
                border = maxPosition;
                steps++;
            }
        }
        return steps;
    }
```
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
