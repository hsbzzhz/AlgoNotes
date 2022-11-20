package A模版;

/**
 * ref. https://labuladong.gitee.io/algo/1/11/
 * 先写左边界 left = mid + 1
 * 再写右边界 right = mid -1
 * 最后如果找到 mid
 */
public class 二分法 {
    int binary_search(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 右边是个闭区间
        while(left <= right) {
            int mid = left + (right - left) / 2;  // 防止溢出
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] == target) {
                // 找到元素，直接返回
                return mid;
            }
        }
        // 直接返回
        return -1;
    }

    /*
     * 寻找左边界
     */
    int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1; // 把边界往左收缩
            }
        }
        /*
         * 循环结束，返回 left
         * 其实 循环结束条件就是 left == right
         */
        // 判断 target 是否存在于 nums 中
        // 此时 target 比所有数都大，返回 -1
        if (left == nums.length) return -1;
        // 判断一下 nums[left] 是不是 target
        return nums[left] == target ? left : -1;
    }

    /*
     * 寻找右边界：return left - 1
     */
    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1; // 把边界朝右收缩
            }
        }
        // 此时 left - 1 索引越界
        if (left - 1 < 0) return -1;
        // 判断一下 nums[left] 是不是 target
        return nums[left - 1] == target ? (left - 1) : -1;
    }
}
