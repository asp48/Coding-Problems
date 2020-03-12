/*
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4

time: O(n)
The problem is very easy if we are allowed to use extra space.
The following solution uses constant extra space.
space; O(1)
Logic: Bit Manipulation
        AND     OR      XOR
0 0     0       0       0
1 1     1       1       0
0 1     0       1       1
1 0     1       1       1

If we xor all elments of the array, then the result will have non-repeated element. Because even number of duplicate elements get cancelled out.
*/
class Solution {
    public int singleNumber(int[] nums) {
        int result=0;
        for(int n:nums){
            result ^= n;
        }
        return result;
    }
}