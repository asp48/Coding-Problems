/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

When an even number is multiplied by 2, bits shift towards left. However the number of bits remain the same.
So count[num] = count[num/2]
Bit in the unit position of an even number is always 0 and for odd number it is always 1.
So for odd number, 
count[num] = count[num-1] + 1
count[num] = count[(num-1)/2] + 1,  int division of (num-1/2) = (num/2) where num is odd.
count[num] = count[num/2] + 1
To sum up,
    count[num] = count[num/2] + (1 if num is odd, or 0 if num is even)
*/
class Solution {
    public int[] countBits(int num) {
        int[] count = new int[num+1];
        for(int i=1;i<=num;i++){
            count[i] = count[i/2] + (i&1);
        }
        return count;
    }
}