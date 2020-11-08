/*
https://leetcode.com/problems/maximum-length-of-repeated-subarray/
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:

Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].

*/
class Solution {
    public int findLength(int[] A, int[] B) {
        /*Integer[][] dp = new Integer[A.length+1][B.length+1];
        int ans = bottomUp(A, B, A.length, B.length, 0, dp);
        for(int i=0;i<=A.length;i++){
            for(int j=0;j<=B.length;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }*/
        int ans = arrayDp(A, B);
        return ans;
    }
    
    /*
    TODO: NOT WORKING. FIX IT
    */
    public int bottomUp(int[] A, int[] B, int i, int j, int curLength, Integer[][] dp){
        if (i == 0 || j == 0) { 
            return curLength; 
        }
        if(dp[i][j]!=null){
            return dp[i][j];
        }
        if (A[i - 1] == B[j - 1]) { 
            curLength = bottomUp(A, B, i - 1, j - 1, 1+curLength, dp); 
        } 
        curLength = max(curLength, bottomUp(A, B, i, j - 1, 0, dp), 
                            bottomUp(A, B, i - 1, j, 0, dp)); 
        dp[i][j] = curLength;
        return curLength;
    }
    
    /*
    Time: O(M*N)
    Space: O(M*N)
    TODO: This solution is slow. There is one more faster solution.
    */
    public int arrayDp(int[] A, int[] B){
        int[][] dp = new int[A.length+1][B.length+1];
        int maxLen=0;
        for(int i=1;i<=A.length;i++){
            for(int j=1;j<=B.length;j++){
                if(A[i-1]==B[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1]; 
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j]=0;
                }
            }
        }
        return maxLen;
    }
    
    public int max(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }
}