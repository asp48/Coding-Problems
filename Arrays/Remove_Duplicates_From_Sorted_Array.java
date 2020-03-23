/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array/

Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
Given nums = [1,1,2],
Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:
Given nums = [0,0,1,1,1,2,2,3,3,4],
Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
*/

/*
It is similar to sorting an array containing only 0,1 and 2.

Time: O(n)
Space: O(1)
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        // length of the required subarray, acts as a pointer to the end of the required subarray.
        int len = -1;
        // lastnum holds the number which was put at the right position during last iteration
        int lastnum = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++){
            if(nums[i]>lastnum){
                // update the lastnum
                lastnum = nums[i];
                // increment the pointer to add new element to the subarray
                len++;
                // swap nums[len] with nums[i]
                int t = nums[len];
                nums[len] = nums[i];
                nums[i] = t;
            }
            // skip if current number is smaller than or equal to lastnum
        }
        return len+1;
    }
}