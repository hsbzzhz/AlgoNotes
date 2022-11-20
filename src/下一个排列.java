import java.util.Arrays;

/**
 * 跟数学相关
 * ref. https://leetcode.cn/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
 * todo explain more
 */
public class 下一个排列 {
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = nums.length-1;
        while (i > 0) {
            if (nums[i] > nums[i-1]) {
                Arrays.sort(nums, i, len);
                for (int j = i; j < len; j++) {
                    if (nums[j] > nums[i-1]) {
                        int[] res = swap(nums, i-1, j);
                        System.out.println(Arrays.toString(res));
                        return;
                    }
                }
            }
            i--;
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static int[] swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }
    public static void main(String[] args) {
        int[] nums = {1,3,4,2};
        int[] nums1 = {4,3,2,1};
        nextPermutation(nums);
    }
}
