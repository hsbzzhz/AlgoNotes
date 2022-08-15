package 常见排序算法;

import com.sun.corba.se.impl.encoding.CDROutputObject;

import java.util.Arrays;

public class MergeSort {
    /**
     * 摘自《算法4》
     */
    // 将子数组的从 lo 到 hi 进行排序
    // 辅助数组空间
    private static int[] temp;

    public static void main(String[] args) {
        int[] nums = new int[]{3,4,7,8,1,2,5};
        temp = new int[nums.length];
        sort(nums,0,nums.length-1);

        System.out.println(Arrays.toString(nums));
    }

    private static void sort(int[] nums, int lo, int hi){
        if(lo==hi){
            return;
        }
        int mid = (lo + hi)/2;
        sort(nums, lo , mid);
        sort(nums, mid+1, hi);
        merge(nums, lo, mid, hi);
    }

    // 将 nums[lo..mid] 和 nums[mid+1..hi] 这两个有序数组合并成一个有序数组
    private static void merge(int[] nums, int lo, int mid, int hi) {
        // 复制 nums 到 temp
        for(int i = lo; i<=hi; i++){
            temp[i] = nums[i];
        }

        // TODO: 2022/7/14  换用while
        // 合并两个有序数组
        int i = lo, j = mid+1;
        for (int p=lo; p<=hi;p++){
            if(i==mid+1){
                // 左半边数组移交全部被合并
                nums[p] = temp[j++];
            } else if (j==hi+1) {
                // 右半边数组已被合并
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }


}
