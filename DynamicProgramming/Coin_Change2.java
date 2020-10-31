/*
Given a value N, find the number of ways to make change for N cents, if we have infinite supply of each of S = { S1, S2, .. , SM } valued coins. 

Example 1:

Input:
n = 4 , m = 3
S[] = {1,2,3}
Output: 4
Explanation: Four Possible ways are:
{1,1,1,1},{1,1,2},{2,2},{1,3}.
*/
// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
class GfG
{
    public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-->0)
                {
                    int n = sc.nextInt();
                    int m = sc.nextInt();
                    int Arr[] = new int[m];
                    for(int i = 0;i<m;i++)
                        Arr[i] = sc.nextInt();
                    Solution ob = new Solution();
                    System.out.println(ob.count(Arr,m,n));
                }
        }
}    // } Driver Code Ends


//User function Template for Java

class Solution
{
    public long count(int S[], int m, int n) 
    { 
        long[] dp = new long[n+1];
        dp[0]=1;
        for(int i=0;i<m;i++){
            for(int j=1;j<=n;j++){
                if(S[i]<=j){
                    dp[j] += dp[j-S[i]];
                }
            }
        }
        return dp[n];
        //return bottomUpDp(S, n, m-1, new Long[m][n+1]);
    } 
    
    public static long bottomUpDp(int[] S, int n, int i, Long[][] dp){
        if(i==-1){
            return 0;
        }
        if(n==0){
            return 1;
        }
        if(dp[i][n]!=null){
            return dp[i][n];
        }
        long include = 0;
        if(S[i]<=n){
            include = bottomUpDp(S, n-S[i], i, dp);
        }
        long exclude = bottomUpDp(S, n, i-1, dp);
        dp[i][n] = include+exclude;
        return dp[i][n];
    }
}
