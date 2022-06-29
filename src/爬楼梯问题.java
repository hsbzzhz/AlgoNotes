public class 爬楼梯问题 {
    /*
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * https://leetcode-cn.com/problems/climbing-stairs/
     * 这个就是 fibonacci
     */

    public static int ClimbStairs(int n){
        // 用递归实现
        if (n==0 ||n==1)return 1;
        return ClimbStairs(n-1) + ClimbStairs(n-2);
    }

    public static int ClimbStairs1(int n){
        int[] dp = new int[n+1];
        for(int i = 0; i<=n; i++){
            if(i==0||i==1) dp[i]=1;
            else {
                dp[i]=dp[i-1]+dp[i-2];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int steps = 5;
        int res1 = 爬楼梯问题.ClimbStairs(steps);
        int res2 = 爬楼梯问题.ClimbStairs1(steps);
        System.out.println(res1==res2);
    }
}
