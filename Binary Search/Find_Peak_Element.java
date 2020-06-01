/*
https://leetcode.com/problems/find-peak-element/
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
*/

/*
Note that it always starts with an upward slope (nums[-1]=-inf) and ends with a downward slope(nums[n]=-inf), and nums[i]!=nums[i-1]
Therefore we need not compare both previous and next element, instead comparing with next element is enough.
Explaination: https://leetcode.com/problems/find-peak-element/solution/
Time: O(N)
Space: O(1)
*/
class Solution {
    public int findPeakElement(int[] nums) {
        int i = 0;
        for(;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                return i;
            }
        }
        return i;
    }
}

/*
Using Binary Search
inspiration from the problem, 'search in a rotated sorted array'
Time: O(logn)
Space: O(1)
*/
class Solution2 {
    public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length-1;
        while(low<high){
            int mid = low+(high-low)/2;
            // mid lies on the downward slope, so peak is on the left, therefore shift the search space to left
            if(nums[mid]>nums[mid+1]){
                high = mid;
            // mid lies on the upward slope, so peak is on the right, therefore shift the search space to right
            } else {
                low = mid+1;
            }
        }
        // when low==high, there is only one element and that is the peak
        return low;
    }
}

