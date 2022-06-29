public class 除自身以外数组的乘积 {
    public int[] productExceptSelf(int[] nums){
        /*
         * 前缀和
         * 弄前后两个乘积列表，然后错位相乘即可
         */
        int len = nums.length;
        // presum
        int[] preSum = new int[len];
        int[] postSum = new int[len];

        int[] ans = new int[len];

        // 先计算左边的
        preSum[0] = 1;
        for(int i =1; i<len; i++){   // 这要从i=1开始
            preSum[i] = nums[i-1]*preSum[i-1];
        }

        // 再算，从右到左的
        postSum[len-1] = 1;
        for(int i=len-2; i>=0; i--){
            postSum[i] = nums[i+1]*postSum[i+1];
        }

        for (int i=0; i<len; i++){
            ans[i] = preSum[i] + postSum[i];
        }
        return ans;
    }
}
