
/*
https://leetcode.com/problems/power-of-three/

Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true
Example 2:

Input: 0
Output: false
Example 3:

Input: 9
Output: true
Example 4:

Input: 45
Output: false

Naive Solution:
Divide the given number repeatedly until remainder becomes 0.

Logarithmic Solution: Using common base
check if Math.log10(n)/Math.log10(3) is integer number.

Integer Limitations: Problem gives integer number as input
Check if the max possible integer multiple of 3 is divisible by the given number.
This principle can be used with other prime numbers.
Time: O(1)
Space: O(1)
*/
public class Power_of_Three {
    public boolean isPowerOfThree(int n) {
        //int max_power = (int)Math.floor(Math.log10(Integer.MAX_VALUE)/Math.log10(3));
        //System.out.println(max_power); 19
        //System.out.println(Math.pow(3, max_power)); 1162261467
        return n>0 && 1162261467%n==0;
    }
}