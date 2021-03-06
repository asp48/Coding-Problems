/*
https://leetcode.com/problems/palindromic-substrings/
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
*/

/*
Using similar approch as finding Longest Palindromic subsequence.
Time: O(2*n^2)
Space: O(2*n^2)
*/
class Solution {
    public int countSubstrings(String str) {
        int n = str.length();
        // stores count of palindromic substrings from i to j
        int[][] count = new int[n][n];
        char[] s = str.toCharArray();
        // stores if substring from i to j is palindrome or not
        boolean[][] palindrome = new boolean[n][n];
        // base conditions
        for(int i=0;i<n;i++){
            count[i][i] = 1;
            palindrome[i][i] = true;
            if(i!=n-1){
                count[i][i+1] = (s[i] == s[i+1])?3:2;
                palindrome[i][i+1] = (s[i] == s[i+1])?true:false;
            }
        }
        // populate palindrome table
        for(int k=3;k<=n;k++){
            for(int i=0;i<=(n-k);i++){
                int j=i+k-1;
                if(s[i]==s[j]){
                    palindrome[i][j] = palindrome[i+1][j-1];
                }
            }
        }
        // populate count table
        for(int k=3;k<=n;k++){
            for(int i=0;i<=(n-k);i++){
                int j=i+k-1;
                if(palindrome[i][j]){
                    count[i][j] = 1 + count[i][j-1] + count[i+1][j] - count[i+1][j-1];
                } else {
                    count[i][j] = count[i][j-1] + count[i+1][j] - count[i+1][j-1];
                }
            }
        }
        return count[0][n-1];
    }
}

/*
Using expand around center approach.
Time: O(n^2)
Space: O(1)
*/
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        char[] ca = s.toCharArray();
        int count = 0;
        for(int center=0; center<2*n-1; center++){
            int left = center/2;
            int right = left + center%2;
            while(left>=0 && right<n && ca[left]==ca[right]){
                left--;
                right++;
                count++;
            }
        }
        return count;
    }
}
