package 常见排序算法;

import java.util.Arrays;

/**快速排序 https://blog.csdn.net/Alian_1223/article/details/123036590
 * ref. https://blog.csdn.net/weixin_43586713/article/details/119820797
 * 本方法用双指针交互方法
 * 排序过程：
 * 原始数组：3,4,7,8,1,2,5
 *         3,2,1,8,7,4,5
 *         3,1,2|4,5,8,7
 *
 */
public class QuickSort {
    public static void swap(int[] nums, int left, int right) {
        /**
         * 交互数组中，两个元素的值
         */
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void sort(int[] nums, int start, int end) {
        if (start > end) return; // 递归出口
        int i = start, j = end, pivot = nums[start]; // pivot定为最左边的元素

        while (i < j) {
            while (i < j && nums[j] >= pivot) j--; //右边指针值大于pivot不会被交换
            while (i < j && nums[i] <= pivot) i++; //左边指针值小于pivot不会被交换
            if(i < j) { //其余情况，都要进行交换
                swap(nums, i, j);
            }
        }
        swap(nums, start, i); // 最后一步，要把pivot 和 左指针进行交换

        // 重复左右两边，j就是pivot
        sort(nums, start,j-1);
        sort(nums, j+1, end);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,4,7,8,1,2,5};
        sort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
