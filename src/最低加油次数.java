import java.util.PriorityQueue;

public class 最低加油次数 {
    /**
     * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处
     * 初始燃料是 startFuel
     * stations[i][0] 表示位于出发点的距离，startions[i][1] 表示该距离处可加油 fuel 升
     *
     * https://leetcode.cn/problems/minimum-number-of-refueling-stops/solution/zui-di-jia-you-by-jiang-hui-4-nmmn/
     */

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        // 贪心解法： 优先队列 存路过油数，尽量不加油跑，如果不够油，就加一个最大的
        PriorityQueue<Integer> heap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        int ans = 0;
        int curGas = startFuel;
        int i = 0;
        while (curGas < target) { // 当curGas 等于 target，就说明可以跑到终点了
            if (i < stations.length && curGas >= stations[i][0]) {
                // 不加油
                heap.add(stations[i][1]);
                i++; // 下一站
            } else {
                if (!heap.isEmpty()) {
                    curGas += heap.poll();
                    ans++; //加油
                } else {
                    return -1; //无法到达
                }
            }
        }
        return ans;
    }
}
