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
}