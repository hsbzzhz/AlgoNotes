public class 除自身以外数组的乘积 {
    /**
     * 前缀和
     * 弄前后两个乘积列表，然后错位相乘即可
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums){
        int len = nums.length;
        // presum
        int[] preSum = new int[len];
        int[] postSum = new int[len];

        int[] ans = new int[len];

        // 从左到右，除了[i]    0 - i 的乘积，第一个元素为 1
        preSum[0] = 1;
        for(int i =1; i<len; i++){   // 这要从i=1开始
            preSum[i] = nums[i-1]*preSum[i-1];
        }

        // 从右到左， 除了[i]   i - len 的乘积，最后一个元素为 1
        postSum[len-1] = 1;
        for(int i=len-2; i>=0; i--){
            postSum[i] = nums[i+1]*postSum[i+1];
        }

        for (int i=0; i<len; i++){
            ans[i] = preSum[i] * postSum[i];  // 这个就是除了nums 自身以外数的乘积
        }
        return ans;
    }
}
