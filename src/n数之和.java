import java.lang.reflect.Array;
import java.util.*;

public class n数之和 {
    public static int[] twoSum(int[] nums, int target) {
        /*
         *  两数之和
         *  给定一个数组，找出一个数组里相加等于target序号
         *  这里用的 hashmap实现的
         */
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    public int[] twoSum1(int[] nums, int target) {
        /*
         *  两数之和
         *  暴力法
         */
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }


    public List<List<Integer>> threeSum(int[] nums) {
        /*
         *  计算 三数之和 为0的组成方法
         *  双指针
         */
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len<3) return ans;  // 异常情况
        Arrays.sort(nums); // 先要排序
        for(int i =0; i<len; i++){
            if(nums[0]>0) break;  // 如果第一个数字都大于0，那肯定无解，跳出循环
            if(i>0 && nums[i]==nums[i-1]) continue; // 去重
            int left = i+1;
            int right = len -1;  //左右两只指针
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum==0){
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left<right && nums[left]==nums[left+1]) left++; // 去重
                    while (left<right && nums[right]==nums[right-1]) right--; //去重
                    left++;
                    right--;
                } else if (sum<0) {
                    left ++;
                } else if (sum>0) {
                    right--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        n数之和 test = new n数之和();
        int na[] = {-1,0,1,2,-1,-4};
        System.out.println(test.threeSum(na));
    }
}
