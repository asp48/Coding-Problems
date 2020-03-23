/*
https://leetcode.com/problems/longest-common-prefix/

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:
Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Note:
All given inputs are in lowercase letters a-z.
*/

/*
There are multiple approaches to solve this problem.
1. Horizontal Scan
    LCP(S1.....Sn) = LCP(LCP(LCP(LCP(S1, S2), S3)... Sn-1), Sn)
    Taking two strings at a time, find the LCP and compare it with next string.
    Time: O(S), where S = m*n, m = average length of strings, n=number of strings
2. Vertical Scan
    Compare ith character from each string and keep moving until they differ.
    Time: O(minLen*n), where minLen = minimum string length, n = number of strings
    In the worst case, when all strings are same and of length m, it reaches m*n which is same as Horizontal Scan. Otherwise, in the average case, it converges faster than Horizontal Scan.
3. Divide and Conquer
    Same as horizontal scan, where you keep dividing given string lists into sublists untill sublist contains only two strings and then conquer them to find LCP.
4. Binary Search
    Apply binary search with search space(0, minLen) and find a point where the string to the left of it is the LCP.
    Time: (S*log(minLen)) where S = minLen*n, minLen = minimum string length, n= number of strings.
    log is multiplies by S because at each midpoint hit in BS, we have to compare (0....mid-1) characters from each string.
5. Trie
    We build a trie of n-1 strings and then find the LCP for the nth string.
    When finding LCP of nth string, we start from root and keep traversing until one of the following conditions are met,
    1. Current node has more than 1 children. (Strings are starting to differ)
    2. Current node is marked as end. (We have reached minLen)
    It takes O(S) to build trie for n-1 strings. Here S = maxLen*(n-1). Then finding LCP for nth string takes O(min(len, minLen)) where len is the length of nth string and minLen is the minimum length among n-1 strings.

Below code is the implementation of vertical scan.
0 ms, faster than 100.00% (Converges very fast)
*/
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0){
            return "";
        }
        int prefixLen = 0;
        for(int i=0; i<strs[0].length();i++){
            char ch = strs[0].charAt(i);
            boolean match = true;
            for(int j=1; j<strs.length; j++){
                if(i==strs[j].length()){
                    return strs[0].substring(0, prefixLen);
                }
                if(ch != strs[j].charAt(i)){
                    match = false;
                    break;
                }
            }
            if(match){
                prefixLen++;
            } else {
                break;
            }
        }
        return strs[0].substring(0, prefixLen);
    }
}