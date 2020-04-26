/*
https://leetcode.com/problems/number-of-1-bits/

Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).

 

Example 1:

Input: 00000000000000000000000000001011
Output: 3
Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
Example 2:

Input: 00000000000000000000000010000000
Output: 1
Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
Example 3:

Input: 11111111111111111111111111111101
Output: 31
Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
*/

/*
Check each bit by using bit mask

Time: O(1)
Space: O(1)
*/
public class Solution {

    public int hammingWeight(int n) {
        int mask  = 1, count = 0;
        for(int i=0;i<32;i++){
            if((n&mask) != 0){
                count++;
            }
            mask <<= 1;
        }
        return count;
    }
}

/*
When we AND n with n-1, the least significant 1 bit becomes 0. Because lease significant 1 bit in n corresponds to 0 in n-1.
We keep doing AND operation until n becomes 0, which means there are no more 1 bits.
This converges faster than previous algorithm, because we are checking until last 1 bit instead of all 32 bits every time.

Time: O(1)
Space: O(1)
*/
public class Solution {
    public int hammingWeight(int n) {
        int mask  = 1, count = 0;
        while(n!=0){
            count++;
            n = n&n-1;
        }
        return count;
    }
}