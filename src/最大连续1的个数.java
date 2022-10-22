public class 最大连续1的个数 {
    /*
     * 《滑动窗口》
     * ref. https://leetcode.cn/problems/max-consecutive-ones-iii/
     * 题目：给定{1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0} 这样的数组，
     * 求最长连续 1 的个数，可以有 k 次机会把 0 翻成 1
     */
    public int longestOnes(int[] nums, int k) {
        /* 逆向思维，要找到多少个连续的1，就要看窗口里最多有几个0
         *
         * 如果题目是：求连续target（1或0）的个数
         */
        int left =0, right =0, zeron = 0, res =0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeron++;
            }
            while (zeron > k) {
                if(nums[left] == 0) {
                    zeron--;
                }
                left++;
            }
            res = Math.max(res, right-left+1);
            right++;
        }
        return res;
    }
}
