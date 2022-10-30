import java.util.Arrays;
/* ref. https://leetcode.cn/problems/koko-eating-bananas/
 * 求吃香蕉的最小速度，二分查找左边界
 */
public class 爱吃香蕉的珂珂 {
    /**
     *
     * @param piles ：n个树，每个树上会有多少香蕉
     * @param h：警卫会在h小时后回
     * @return 吃香蕉的最小速度
     */
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int low = 1, high = Arrays.stream(piles).max().getAsInt();  // 最慢一小时一个香蕉，最快吃数组里最多的一个，因为h > pile.length()

        while(low < high) {
            int mid = low + (high - low)/2;
            if(timeNeed(piles, mid) > h){ // 所需时间 大于 警卫返回时间，所以速度要增快 -> 往右区间挪：相当于target落在右区间 [mid] < target
                low = mid + 1;
            } else { // 包含两种情况： 1. [mid] == target  2. [mid] > target（以此速度吃完所需时间 小于 警卫返回时间，所以要减速），往左收缩区间
                high = mid;
            }
        }
        return low;
    }

    public static int timeNeed(int[] piles, int speed){
        // 以此速度吃完香蕉总共所需
        int time = 0;
        for(int pile: piles) {
            time += (pile + speed -1) / speed;  // 向上取整
        }
        return time;
    }

    public static void main(String[] args) {
        int[] source = {3,6,7,11};
        int res = new 爱吃香蕉的珂珂().minEatingSpeed(source,8);
        System.out.println(res);
    }
}
