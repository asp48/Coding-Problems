/*
https://leetcode.com/problems/interleaving-string/

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

Example 1:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true

Example 2:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
*/

/*
Recursion with Memorization
Time: O(m*n)
Space: O(m*n)
*/
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        //Boolean[][] dp = new Boolean[s1.length()][s2.length()];
        //return bottomUp(s1, s2, s3, s1.length()-1, s2.length()-1, s3.length()-1, dp);
        //return array2d(s1, s2, s3);
        return array1d(s1, s2, s3);
    }
    
    public boolean bottomUp(String s1, String s2, String s3, int i, int j, int k, Boolean[][] dp){
        // all strings are empty, return true
        if(i==-1 && j==-1 && k==-1){
            return true;
        }
        // s3 is empty, but either of s1 or s2 is not empty, return false.
        if(k==-1){
            return false;
        }
        // s3 is not empty, but both s1 and s2 are empty, return false.
        if(i==-1 && j==-1){
            return false;
        }
        // s1 is empty, compare remaining characters of s3 with s2
        if(i==-1){
            return s3.substring(0,k+1).equals(s2.substring(0,j+1));
        // s2 is empty, compare remaining characters of s3 with s1
        } else if(j==-1){
            return s3.substring(0,k+1).equals(s1.substring(0,i+1));
        // check memo table
        } else if(dp[i][j]!=null){ 
            return dp[i][j];
        } else {
            // check if s3(k) is equal to s1(i) or s2(j), recurse correspondingly. 
            dp[i][j] = ((s3.charAt(k)==s1.charAt(i)) && bottomUp(s1, s2, s3, i-1, j, k-1, dp)) || ((s3.charAt(k)==s2.charAt(j)) && bottomUp(s1, s2, s3, i, j-1, k-1, dp));
        }
        return dp[i][j];
    }

    /*
        Time: O(m*n)
        Space: O(m*n)
    */
    public boolean array2d(String s1, String s2, String s3){
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        // return false, if len(s3) != len(s1) + len(s2)
        if(s3.length() != (s1.length() + s2.length())){
            return false;
        }
        // dp[i][j] stores whether first i characters of s1 and first j characters of s2 can be 
        // interleaved to form first k characters of s3. Hence k = i+j
        for(int i=0;i<=s1.length();i++){
            for(int j=0;j<=s2.length();j++){
                int k = i+j;
                if(i==0 && j==0){
                    dp[i][j] = true;
                } else if(i==0){
                    dp[i][j] = dp[i][j-1] && (s2.charAt(j-1)==s3.charAt(k-1));
                } else if(j==0){
                    dp[i][j] = dp[i-1][j] && (s1.charAt(i-1)==s3.charAt(k-1));
                } else {
                    dp[i][j] = (dp[i-1][j] && (s3.charAt(k-1)==s1.charAt(i-1))) ||
                        (dp[i][j-1] && (s3.charAt(k-1)==s2.charAt(j-1)));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
    
    /*
        As the previous dp solution is just dependent on current and previous row, it can be 
        converted to 1D array as below.
        Time: O(m*n)
        Space: O(min(m,n))
    */
    public boolean array1d(String s1, String s2, String s3){
        boolean[] dp = new boolean[s2.length()+1];
        if(s3.length() != (s1.length() + s2.length())){
            return false;
        }
        for(int i=0;i<=s1.length();i++){
            for(int j=0;j<=s2.length();j++){
                int k = i+j;
                if(i==0 && j==0){
                    dp[j] = true;
                } else if(i==0){
                    dp[j] = dp[j-1] && (s2.charAt(j-1)==s3.charAt(k-1));
                } else if(j==0){
                    dp[j] = dp[j] && (s1.charAt(i-1)==s3.charAt(k-1));
                } else {
                    dp[j] = (dp[j] && (s3.charAt(k-1)==s1.charAt(i-1))) ||
                        (dp[j-1] && (s3.charAt(k-1)==s2.charAt(j-1)));
                }
            }
        }
        return dp[s2.length()];
    }
}