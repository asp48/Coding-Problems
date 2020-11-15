/*
https://leetcode.com/problems/shortest-common-supersequence/

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)

 

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
*/

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        //return bottomUp(str1, str2, str1.length()-1, str2.length()-1);
        return arrayDp(str1, str2);
    }
    
    /*
        Optimzing this solution with memorization will lead to Memory Overflow.
    */
    public String bottomUp(String str1, String str2, int i, int j){
        if(i==-1){
            return str2.substring(0, j+1);
        }
        if(j==-1){
            return str1.substring(0, i+1);
        }
        if(str1.charAt(i) == str2.charAt(j)){
            return bottomUp(str1, str2, i-1, j-1) + str1.charAt(i);
        }
        String seq1 = bottomUp(str1, str2, i-1, j) + str1.charAt(i);
        String seq2 = bottomUp(str1, str2, i, j-1) + str2.charAt(j);
        if (seq1.length()<seq2.length()){
            return seq1;
        } else {
            return seq2;
        }
    }
    
    /*
    The above recursive solution can be converted to iterative as below. 
    Here the dp[i][j] will store the length of shortest common supersequence that can contain str1(0,i)
    and str2(0, j) as subsequences.
    Find the actual string by traversing the dp table from bottom right to top left.
    */
    public String arrayDp(String str1, String str2){
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0){
                    dp[i][j] = j;
                } else if(j==0){
                    dp[i][j] = i;
                } else if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        // dp[m][n] is the length of Shortest Common Supersequence
        int i = m, j=n, lscs = dp[m][n], k = lscs;
        char[] ans = new char[lscs];
        while(i>0 && j>0){
            // if str1(i-1) equals str2(j-1), move up diagonally & add str1(i-1) to the result
            if(str1.charAt(i-1)==str2.charAt(j-1)){
                ans[--k] = str1.charAt(i-1);
                i--;
                j--;
            // move up & add str1(i-1) to the result
            } else if(dp[i-1][j]<dp[i][j-1]){
                ans[--k] = str1.charAt(i-1);
                i--;
            // move left & add str2(j-1) to the result
            } else {
                ans[--k] = str2.charAt(j-1);
                j--;
            }
        }
        // move up until i>0, adding characters from str1 to the result along the way
        while(i>0){
            ans[--k] = str1.charAt(i-1);
            i--;
        }
        // move left until j>0, adding characters from str2 to the result along the way
        while(j>0){
            ans[--k] = str2.charAt(j-1);
            j--;
        }
        return String.valueOf(ans);
    }
}