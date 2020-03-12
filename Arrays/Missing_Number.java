/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
*/
/*
Using Gauss' Formula to find sum of first n natural numbers. The difference between this calculated sum and the sum obtained by adding all the given numbers is the missing number.
time: O(n)
space: O(1)
*/
class Solution {
    public int missingNumber(int[] nums) {
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }
}
/*
Bit Manipulation. Using the idea that when multiple numbers in which all are pairs except one number, are xored, the result will be unique number among them.
If we xor n with each index and its value, result will be the missing number. (Because though the index of missing number will be present, the value will be missing and due to which it will not cancel out).
*/
class Solution2 {
    public int missingNumber(int[] nums) {
        int missing=nums.length;
        for(int i=0;i<nums.length;i++){
            missing^=i^nums[i];
        }
        return missing;
    }
}