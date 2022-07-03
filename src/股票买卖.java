public class 股票买卖 {
    public static int maxProfit_greedy(int[] prices){
        int minPrice = prices[0], profit =0;
        for(int i=1; i<prices.length; i++){
            minPrice = Math.min(minPrice, prices[i]);
            profit = Math.max(prices[i]-minPrice, profit);
        }
        return profit;
    }

    public int maxProfit_k_int(int[] prices){
        int n = prices.length;
        int [][] dp = new int[n][2];
        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i=1;i<n;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[n-1][0];
    }
}
