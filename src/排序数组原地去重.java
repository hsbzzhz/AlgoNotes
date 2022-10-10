import java.util.Arrays;

public class 排序数组原地去重 {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                // 会把慢指针元素都刷一遍
                nums[slow] = nums[fast];
                slow++;
            }
            // 如果快指针遇到前后相同都就跳过
            fast++;
        }
        // int[] SlowArr = Arrays.copyOfRange(nums,0,slow);
        // java 中的切片 Arrays.toString() 是输出数组
        return slow;
    }

}
