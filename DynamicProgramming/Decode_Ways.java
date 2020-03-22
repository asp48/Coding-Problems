/*
https://leetcode.com/problems/decode-ways/

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

/*
Bottom-Up recursion with memorization

At each index i, the decision is to whether, 
1. consider only current character
2. consider current and next character

Time: O(2^N) without memo and O(N) with memo
Space: O(N) function stack
*/
class Solution {
    public int numDecodings(String s) {
        Integer[] memo = new Integer[s.length()];
        return helper(s.toCharArray(), 0, memo);
    }
    
    public int helper(char[] ca, int i, Integer[] memo) {
        // reached end, it means there is one way to do it
        if(i==ca.length){
            return 1;
        }
        // check if already computed
        if(memo[i]!=null){
            return memo[i];
        }
        int currChar = ca[i]-'0';
        // if current char is 0, there are 0 ways to decode. So return 0.
        if(currChar == 0){
            return 0;
        }
        // take only current char
        int takeOne = helper(ca, i+1, memo);
        int takeTwo = 0;
        // take current and next char
        if(i < ca.length-1){
            int nextChar = ca[i+1]-'0';
            if(currChar==1 || (currChar==2 && nextChar >= 0 && nextChar <= 6)){
                takeTwo = helper(ca, i+2, memo);
            }
        }
        // add different ways of decoding and store it in memo
        memo[i] = takeOne + takeTwo;
        // return the total number of ways to decode string s from index i
        return memo[i];
    }
}
/*
Top-Down DP
Time: O(N)
Space: O(N)
*/
class Solution2 {
    public int numDecodings(String s) {
        Integer[] dp = new Integer[s.length() + 1];
        char[] ca = s.toCharArray();
        dp[ca.length] = 1;
        for (int i = ca.length - 1; i >= 0; i--) {
            int currChar = ca[i] - '0';
            if (currChar == 0) {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];
                if (i < ca.length - 1) {
                    int nextChar = ca[i + 1] - '0';
                    if (currChar == 1 || (currChar == 2 && nextChar >= 0 && nextChar <= 6)) {
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }
        return dp[0];
    }
}