import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int[] nums = {-1,1,-3,5};
        int[] nums1 = {-22, 1, -3, -40, 47, 4};


        int[] nums2 = Arrays.stream(nums1).map(no -> no*2).toArray();

//        int[] res = new int[nums.length];
//        List<Integer> res = new ArrayList<>();
//        int odd =0, even = 0;
//        boolean flag = true;
//        while (odd< nums1.length && even< nums1.length) {
//            while (flag) {
//                if(nums1[even] > 0){
//                    res.add(nums1[even]);
//                    flag = false;
//                }
//                even++;
//            }
//            while (!flag) {
//                if (nums1[odd] < 0) {
//                    res.add(nums1[odd]);
//                    flag = true;
//                }
//                odd++;
//            }
//        }
    }
}
