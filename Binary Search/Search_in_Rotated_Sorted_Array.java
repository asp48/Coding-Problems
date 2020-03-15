/*
https://leetcode.com/problems/search-in-rotated-sorted-array/

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/

/*
The rotated array consists of two increasing subarrays with a dip in between.
Ex: In the input [4,5,6,7,0,1,2], two increasing subarrays are [4,5,6,7] and [0,1,2], with a dip at [7,0]

In case of simple binary search, we switch blocks based on whether target is greater or smaller than the middle element.
To apply binary search for this problem, after finding middle index, we have to decide in which part falling dip is present. 
1. If the dip is on right side, it implies left side is a plain increasing subarray and if the target is in the range of that subarray, then we move to left, otherwise move to right.
2. if the dip is on left side, it implies right side is a plain increasing subarray and if the target is in the range of that subarray, then we move to right, otherwise move to left.
*/
class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length-1, mid;
        while(low <= high){
            mid = low + (high-low)/2;
            // we found the target, return the index
            if(nums[mid]==target){
                return mid;
            // dip is on the right side, including mid index
            } else if(nums[low]<=nums[mid]){
                // check if target is in the left increasing subarray(low, mid) 
                if(target>=nums[low] && target<nums[mid]){
                    // If yes, move left
                    high = mid -1;
                } else {
                    // If no, move right
                    low = mid + 1;
                }
            // dip is on the left side, excluding mid index
            } else {
                // check if target is in the right increasing subarray(mid, high)
                if(target>nums[mid] && target<=nums[high]){
                    // If yes, move right
                    low = mid+1;
                } else {
                    // If no, move left
                    high = mid-1;
                }
            }
        }
        return -1;
    }
}