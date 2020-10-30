/*
Given a set of N items, each with a weight and a value, and a weight limit W. Find the maximum value of a collection containing any of the N items any number of times so that the total weight is less than or equal to W.

 

Example 1:

Input: N = 2, W = 3
val[] = {1, 1}
wt[] = {2, 1}
Output: 3
Explaination: Pick the 2nd element thrice.
*/

// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            String line1[] = in.readLine().trim().split("\\s+");
            int N = Integer.parseInt(line1[0]);
            int W = Integer.parseInt(line1[1]);
            String line2[] = in.readLine().trim().split("\\s+");
            int val[] = new int[N];
            for(int i = 0;i < N;i++)
                val[i] = Integer.parseInt(line2[i]);
            String line3[] = in.readLine().trim().split("\\s+");
            int wt[] = new int[N];
            for(int i = 0;i < N;i++)
                wt[i] = Integer.parseInt(line3[i]);
                
            Solution ob = new Solution();
            System.out.println(ob.knapSack(N, W, val, wt));
        }
    }
}// } Driver Code Ends


//User function Template for Java

class Solution{
    static int knapSack(int N, int W, int val[], int wt[])
    {
        //return helper(N, W, val, wt, 0, 0, new Integer[N][W+1]);
        //return bottomUp(N, W, val, wt);
        return singleArrDp(N, W, val, wt);
    }
    
    static int helper(int N, int W, int val[], int wt[], int i, int cv, Integer[][] dp){
        if(i==N){
            return 0;
        }
        if(dp[i][W]!=null){
            return cv + dp[i][W];
        }
        int include=0, exclude=0;
        if(wt[i]<=W){
            include = helper(N, W-wt[i], val, wt, i, val[i], dp);
        }
        exclude = helper(N, W, val, wt, i+1, 0, dp);
        dp[i][W] = Math.max(include, exclude);
        return cv + dp[i][W];
    }
    
    static int bottomUp(int N, int W, int[] val, int[] wt){
        int[][] dp = new int[N][W+1];
        for(int i=0;i<N;i++){
            dp[i][0]=0;
        }
        for(int i=1;i<=W;i++){
            dp[0][i] = (wt[0]<=i)?(i/wt[0])*val[0]:0;   
        }
        for(int i=1;i<N;i++){
            for(int j=1;j<=W;j++){
                int exclude=0, include=0;
                if(wt[i]<=j){
                    include = val[i] + dp[i][j-wt[i]];
                }
                exclude = dp[i-1][j];
                dp[i][j] = Math.max(include, exclude);
            }
        }
        return dp[N-1][W];
    }
    
    static int singleArrDp(int N, int W, int[] val, int[] wt){
        int[] dp = new int[W+1];
        for(int i=1;i<=W;i++){
            dp[i] = (wt[0]<=i)?(i/wt[0])*val[0]:0;
        }
        for(int i=1;i<N;i++){
            for(int j=1;j<=W;j++){
                int include = (wt[i]<=j)? val[i] + dp[j-wt[i]]:0;
                dp[j] = Math.max(dp[j], include);
            }
        }
        return dp[W];
    }
}
