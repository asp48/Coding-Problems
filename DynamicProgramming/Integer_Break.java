/*
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

Example 1:

Input: 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.
Example 2:

Input: 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
*/

/*
Similar to Rod_Cutting problem.
Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int integerBreak(int n) {
        //Integer[] dp = new Integer[n+1];
        //return bottomUp(n, dp);
        return singleArrayDp(n);
    }
    
    public int bottomUp(int n, Integer[] dp){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(dp[n]!=null){
            return dp[n];
        }
        int max=0;
        for(int i=1;i<=Math.ceil(n/2);i++){
            max = Math.max(max, i*bottomUp(n-i, dp));
            max = Math.max(max, i*(n-i));
        }
        dp[n] = max;
        return max;
    }
    
    public int singleArrayDp(int n){
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int j=2;j<=n;j++){
            int max=0;
            for(int i=1;i<=Math.ceil(j/2);i++){
                max = Math.max(max, i*dp[j-i]);
                max = Math.max(max, i*(j-i));
            }
            dp[j]=max;
        }
        return dp[n];
    }
}
