/*
https://leetcode.com/problems/longest-common-subsequence/submissions/

Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

 

If there is no common subsequence, return 0.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
*/

/*
Time: O(M*N)
Space: O(M*N)
*/
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        //Integer[][] dp = new Integer[text1.length()][text2.length()];
        //return lcs(text1.toCharArray(), text2.toCharArray(), text1.length()-1, text2.length()-1, dp);
        return singleArrayDp(text1.toCharArray(), text2.toCharArray());
    }
    
    public int singleArrayDp(char[] s1, char[] s2){
        int[][] dp = new int[s1.length+1][s2.length+1];
        for(int i=1;i<=s1.length;i++){
            for(int j=1;j<=s2.length;j++){
                if(s1[i-1]==s2[j-1]){
                    dp[i][j] = 1+dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[s1.length][s2.length];
    }
    
    public int lcs(char[] s1, char[] s2, int i, int j, Integer[][] dp){
        if(i==-1||j==-1){
            return 0;
        }
        if(dp[i][j]!=null){
            return dp[i][j];
        }
        if(s1[i]==s2[j]){
            dp[i][j] = 1 + lcs(s1, s2, i-1, j-1, dp);
            return dp[i][j];
        }
        dp[i][j] = Math.max(lcs(s1, s2, i-1, j, dp), lcs(s1, s2, i, j-1, dp));
        return dp[i][j];
    }
}