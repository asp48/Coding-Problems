/*
https://leetcode.com/problems/factorial-trailing-zeroes/
Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Math theory
https://brilliant.org/wiki/trailing-number-of-zeros/

Time: O(logn)
Space: O(1)
*/
class Solution {
    public int trailingZeroes(int n) {
        int count = 0;
        for(long i=5;i<=n;i*=5){
            count += n/i;
        }
        return count;
    }
}

class Solution2 {
    public int trailingZeroes(int n) {
        int count = 0;
        while(n>=5){
            count += n/5;
            n/=5;
        }
        return count;
    }
}