import java.util.Arrays;
import java.util.Collections;

public class BinarySearch {
    public int findTarget(int [] nums, int target){
        int low = 0, high = nums.length-1;

        while (low<=high){
            int mid = (low + high)/2;
            if (nums[mid]==target){
                return mid;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] arr = {3,9,0,7,1,4,6};
        // Arrays.sort(arr);  // 正序排列
        Arrays.sort(arr, Collections.reverseOrder());  // 倒序排列
        System.out.println(Arrays.toString(arr));
    }
}
