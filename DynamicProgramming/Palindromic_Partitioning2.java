/*
https://leetcode.com/problems/palindrome-partitioning-ii/

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1
*/

/*
Time: O(n^2)
Space: O(n^2)
*/
class Solution {
    public int minCut(String str) {
        int n = str.length();
        // stores whether substring from i to j is palindrome or not
        boolean[][] palindrome = new boolean[n][n];
        // stores minimum number of cuts required for palindromic partitioning from 0 to i
        int[] cuts = new int[n];
        char[] s = str.toCharArray();
        // base conditions
        for(int i=0;i<n;i++){
            palindrome[i][i] = true;
            if(i!=n-1){
                palindrome[i][i+1] = (s[i]==s[i+1])?true:false;
            }
        }
        // generate all possible palindromes
        for(int k=3;k<=n;k++){
            for(int i=0;i<=(n-k);i++){
                int j = i+k-1;
                if(s[i]==s[j]){
                    palindrome[i][j] = palindrome[i+1][j-1];
                }
            }
        }
        // DP to calculate minimum cuts required
        for(int i=0;i<n;i++){
            if(palindrome[0][i]){
                // the partition from 0 to i is a palindrome, no cuts required
                cuts[i] = 0;
            } else {
                // setting min to max value: n+1, cuts cannot go more than string length
                int min = n+1;
                // calculate min cuts to partition from 0 to i
                for(int j=0;j<=i;j++){
                    if(palindrome[j][i]){
                        min = Math.min(min, 1+cuts[j-1]);
                    }
                }
                cuts[i] = min;
            }
        }
        return cuts[n-1];
    }
}

/*
Logic same as above
Simplified solution
*/
class Solution {
    public int minCut(String str) {
        int n = str.length();
        boolean[][] palindrome = new boolean[n][n];
        int[] cuts = new int[n];
        char[] s = str.toCharArray();
        // we are filling as a right-upper triangular matrix
        for(int i=0;i<n;i++){
            int min = n+1;
            for(int j=0;j<=i;j++){
                if(s[i]==s[j] && (i-j<2 || palindrome[j+1][i-1])){
                    palindrome[j][i] = true;
                    min = Math.min(min, j==0?0:1+cuts[j-1]);
                }
            }
            cuts[i] = min;
        }
        return cuts[n-1];
    }
}