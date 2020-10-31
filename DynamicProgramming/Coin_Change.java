/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
*/

/*
Bottom-Up DP
dp[i] stores the minimum number of coins required to make up the amount i. If all the coin denominations are greater than the amount i, then a large value is stored at dp[i] to indicate that amount i cannot be achieved with the given denominations. Since lowest valid denomination can be 1, the number of required coins can never be greater than the amount. Hence amount+1 is stored as the large value.

time: O(S*n) S=amount, n=number of available denominations
space: O(n)
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount+1];
        for(int i=1;i<=amount;i++){
            int min = amount+1;
            for(int j=0;j<n;j++){
                if(coins[j]<=i){
                    min = Math.min(1 + dp[i-coins[j]], min);
                }
            }
            dp[i] = min;
        }
        return dp[amount]>amount?-1:dp[amount];
    }
    
    public int minCoins(int coins[], int M, int V) 
	{ 
	    //int ans = singleArray(coins, V, M);
	    //int ans = bottomUpDp(coins, V, coins.length-1, new Integer[M][V+1]);
	    //int ans = bottomUpDp2(coins, V, M, new Integer[V+1]);
	    int ans = singleArray2(coins, V, M);
	    return ans == Integer.MAX_VALUE?-1:ans;
	} 
	
	public static int singleArray(int[] coins, int tsum, int n){
	    int[] dp = new int[tsum+1];
	    for(int i=1;i<=tsum;i++){
	        dp[i] = Integer.MAX_VALUE;
	    }
	    for(int i=0;i<n;i++){
	        for(int j=1;j<=tsum;j++){
	           if(coins[i]<=j && dp[j-coins[i]]!=Integer.MAX_VALUE){
	               dp[j] = Math.min(dp[j], 1 + dp[j-coins[i]]);
	           }
	        }
	    }
	    return dp[tsum];
	}
	
	public static int bottomUpDp(int[] coins, int tsum, int i, Integer[][] dp){
	    if(i==-1){
	        return Integer.MAX_VALUE;
	    }
	    if(tsum==0){
	        return 0;
	    }
	    if(dp[i][tsum]!=null){
	        return dp[i][tsum];
	    }
	    int include = Integer.MAX_VALUE;
	    if(coins[i]<=tsum){
	        include = bottomUpDp(coins, tsum-coins[i], i, dp);
	        include = include == Integer.MAX_VALUE? include:1+include;
	    }
	    int exclude = bottomUpDp(coins, tsum, i-1, dp);
	    dp[i][tsum] = Math.min(include, exclude);
	    return dp[i][tsum];
	}
	
	public static int bottomUpDp2(int[] coins, int tsum, int n, Integer[] dp){
	    if(tsum==0){
	        return 0;
	    }
	    if(dp[tsum]!=null){
	        return dp[tsum];
	    }
	    int min = Integer.MAX_VALUE;
	    for(int i=0;i<n;i++){
	        if(coins[i]<=tsum){
	            int include = bottomUpDp2(coins, tsum-coins[i], n, dp);
	            include = include == Integer.MAX_VALUE? include: 1+include;
	            min = Math.min(min, include);
	        }
	    }
	    dp[tsum] = min;
	    return min;
	}
}
