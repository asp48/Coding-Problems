/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.

If all non zero elements are moved to left, automatically all zeroes will settle at the right.
Same logic as sorting an array containing only 0,1,2.

time: O(n)
space: O(1)
*/
class Solution {
    public void moveZeroes(int[] nums) {
        int nonZeroEnd=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nonZeroEnd++;
                int t = nums[nonZeroEnd];
                nums[nonZeroEnd] = nums[i];
                nums[i] = t;
            }
        }
    }
}