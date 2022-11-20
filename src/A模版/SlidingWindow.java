package A模版;

public class SlidingWindow {
    /**
     *     滑动窗口模板
     *     public static int template(int[] nums){
     *         int left =0, right=0, window =0, res = 0;
     *         while (right<nums.length) {
     *             window += nums[right]; // 用于统计子区间的临时变量，当满足条件时...
     *             while ( case ) {  // 当不满足条件时，窗口要收缩，直至新当窗口满足需求
     *                 window -= nums[left]; // 把窗口最左当元素挪出窗口
     *                 left --; // 窗口进行收缩
     *             }
     *             window= Math.max(window, right - left + 1); // 更新窗口
     *             right ++; // 确保右指针是扫描整个数组
     *         }
     *         return res;
     *     }
     *
     */
}
